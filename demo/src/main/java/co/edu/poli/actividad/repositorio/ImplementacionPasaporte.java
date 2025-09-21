package co.edu.poli.actividad.repositorio;

import java.sql.*;
import java.util.*;

import co.edu.poli.actividad.model.*;
import co.edu.poli.actividad.servicios.ConexionDB;
import co.edu.poli.actividad.servicios.PasaporteOrdinarioBuilder;
import co.edu.poli.actividad.servicios.PersonaWrapper;

public class ImplementacionPasaporte implements FiltrableRepository<Pasaporte> {

    private final Connection conn;

    public ImplementacionPasaporte() {
        this.conn = ConexionDB.getInstancia().getConexion();
    }

    // ======================
    // INSERT
    // ======================
    @Override
    public String insert(Pasaporte p) {
        String sqlBase = "INSERT INTO pasaporte (id, fecha_expedicion, persona_id, pais_codigoISO) VALUES (?, ?, ?, ?)";
        String sqlOrdinario = "INSERT INTO pasaporte_ordinario (id, motivo) VALUES (?, ?)";
        String sqlDiplomatico = "INSERT INTO pasaporte_diplomatico (id, motivo) VALUES (?, ?)";

        try {
            conn.setAutoCommit(false);

            // Inserta en tabla base
            try (PreparedStatement ps = conn.prepareStatement(sqlBase)) {
                ps.setString(1, p.getId());
                ps.setString(2, p.getFechaExpedicion());
                ps.setString(3, p.getTitular().getId());
                ps.setString(4, p.getPais().getCodigoISO());
                ps.executeUpdate();
            }

            // Inserta en tabla específica
            if (p instanceof PasaporteOrdinario) {
                PasaporteOrdinario po = (PasaporteOrdinario) p;
                try (PreparedStatement ps = conn.prepareStatement(sqlOrdinario)) {
                    ps.setString(1, po.getId());
                    ps.setString(2, po.getMotivo());
                    ps.executeUpdate();
                }
            } else if (p instanceof PasaporteDiplomatico) {
                PasaporteDiplomatico pd = (PasaporteDiplomatico) p;
                try (PreparedStatement ps = conn.prepareStatement(sqlDiplomatico)) {
                    ps.setString(1, pd.getId());
                    ps.setString(2, pd.getMotivo());
                    ps.executeUpdate();
                }
            }

            conn.commit();
            return "✅ Pasaporte insertado correctamente";

        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
            return "Error al insertar pasaporte: " + e.getMessage();
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    // ======================
    // FIND BY ID
    // ======================
    @Override
    public Pasaporte findById(String id) {
        String sql = "SELECT p.*, o.motivo AS motivo_ordinario, d.motivo AS motivo_diplomatico " +
                     "FROM pasaporte p " +
                     "LEFT JOIN pasaporte_ordinario o ON p.id = o.id " +
                     "LEFT JOIN pasaporte_diplomatico d ON p.id = d.id " +
                     "WHERE p.id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Persona titular = clonarPersona(rs.getString("persona_id"));
                Pais pais = findPaisById(rs.getString("pais_codigoISO"));

                if (rs.getString("motivo_ordinario") != null) {
                    // Usamos el Builder para crear el pasaporte ordinario
                    return new PasaporteOrdinarioBuilder()
                            .id(rs.getString("id"))
                            .fechaExpedicion(rs.getString("fecha_expedicion"))
                            .titular(titular)
                            .pais(pais)
                            .motivo(rs.getString("motivo_ordinario"))
                            .build();
                } else if (rs.getString("motivo_diplomatico") != null) {
                    return new PasaporteDiplomatico(
                            rs.getString("id"),
                            rs.getString("fecha_expedicion"),
                            titular,
                            pais,
                            rs.getString("motivo_diplomatico")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ======================
    // FIND ALL
    // ======================
    @Override
    public List<Pasaporte> findAll() {
        List<Pasaporte> pasaportes = new ArrayList<>();
        String sql = "SELECT p.*, o.motivo AS motivo_ordinario, d.motivo AS motivo_diplomatico " +
                     "FROM pasaporte p " +
                     "LEFT JOIN pasaporte_ordinario o ON p.id = o.id " +
                     "LEFT JOIN pasaporte_diplomatico d ON p.id = d.id";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Persona titular = clonarPersona(rs.getString("persona_id"));
                Pais pais = findPaisById(rs.getString("pais_codigoISO"));

                if (rs.getString("motivo_ordinario") != null) {
                    pasaportes.add(new PasaporteOrdinarioBuilder()
                            .id(rs.getString("id"))
                            .fechaExpedicion(rs.getString("fecha_expedicion"))
                            .titular(titular)
                            .pais(pais)
                            .motivo(rs.getString("motivo_ordinario"))
                            .build()
                    );
                } else if (rs.getString("motivo_diplomatico") != null) {
                    pasaportes.add(new PasaporteDiplomatico(
                            rs.getString("id"),
                            rs.getString("fecha_expedicion"),
                            titular,
                            pais,
                            rs.getString("motivo_diplomatico")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pasaportes;
    }

    // ======================
    // UPDATE
    // ======================
    @Override
    public String update(Pasaporte p) {
        String sqlBase = "UPDATE pasaporte SET fecha_expedicion = ?, persona_id = ?, pais_codigoISO = ? WHERE id = ?";
        String sqlOrdinario = "UPDATE pasaporte_ordinario SET motivo = ? WHERE id = ?";
        String sqlDiplomatico = "UPDATE pasaporte_diplomatico SET motivo = ? WHERE id = ?";

        try {
            conn.setAutoCommit(false);

            // Update base
            try (PreparedStatement ps = conn.prepareStatement(sqlBase)) {
                ps.setString(1, p.getFechaExpedicion());
                ps.setString(2, p.getTitular().getId());
                ps.setString(3, p.getPais().getCodigoISO());
                ps.setString(4, p.getId());
                ps.executeUpdate();
            }

            // Update subtabla
            if (p instanceof PasaporteOrdinario) {
                PasaporteOrdinario po = (PasaporteOrdinario) p;
                try (PreparedStatement ps = conn.prepareStatement(sqlOrdinario)) {
                    ps.setString(1, po.getMotivo());
                    ps.setString(2, po.getId());
                    ps.executeUpdate();
                }
            } else if (p instanceof PasaporteDiplomatico) {
                PasaporteDiplomatico pd = (PasaporteDiplomatico) p;
                try (PreparedStatement ps = conn.prepareStatement(sqlDiplomatico)) {
                    ps.setString(1, pd.getMotivo());
                    ps.setString(2, pd.getId());
                    ps.executeUpdate();
                }
            }

            conn.commit();
            return "✅ Pasaporte actualizado correctamente";

        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
            return "Error al actualizar pasaporte: " + e.getMessage();
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    // ======================
    // DELETE
    // ======================
    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM pasaporte WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ======================
    // FIND BY ID CONTAINS
    // ======================
    @Override
    public List<Pasaporte> findByIdContains(String criterio) {
        List<Pasaporte> pasaportes = new ArrayList<>();
        String sql = "SELECT p.*, o.motivo AS motivo_ordinario, d.motivo AS motivo_diplomatico " +
                     "FROM pasaporte p " +
                     "LEFT JOIN pasaporte_ordinario o ON p.id = o.id " +
                     "LEFT JOIN pasaporte_diplomatico d ON p.id = d.id " +
                     "WHERE p.id LIKE ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + criterio + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Persona titular = clonarPersona(rs.getString("persona_id"));
                Pais pais = findPaisById(rs.getString("pais_codigoISO"));

                if (rs.getString("motivo_ordinario") != null) {
                    pasaportes.add(new PasaporteOrdinarioBuilder()
                            .id(rs.getString("id"))
                            .fechaExpedicion(rs.getString("fecha_expedicion"))
                            .titular(titular)
                            .pais(pais)
                            .motivo(rs.getString("motivo_ordinario"))
                            .build()
                    );
                } else if (rs.getString("motivo_diplomatico") != null) {
                    pasaportes.add(new PasaporteDiplomatico(
                            rs.getString("id"),
                            rs.getString("fecha_expedicion"),
                            titular,
                            pais,
                            rs.getString("motivo_diplomatico")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pasaportes;
    }

    // ======================
    // Métodos auxiliares
    // ======================

    private Persona clonarPersona(String id) throws SQLException {
        String sql = "SELECT * FROM persona WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Persona p = new Persona(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("fecha_nacimiento")
                );
                // usamos el Prototype (Wrapper)
                return new PersonaWrapper(p).clonar();
            }
        }
        return null;
    }

    private Pais findPaisById(String codigoISO) throws SQLException {
        String sql = "SELECT * FROM pais WHERE codigoISO = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigoISO);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Pais(
                        rs.getString("codigoISO"),
                        rs.getString("nombre"),
                        new ArrayList<>()
                );
            }
        }
        return null;
    }
}
    