package co.edu.poli.actividad.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import co.edu.poli.actividad.model.Pais;
import co.edu.poli.actividad.model.Pasaporte;
import co.edu.poli.actividad.model.Persona;

public class ImplementacionPasaporte implements Repository<Pasaporte, String>, FiltrableRepository<Pasaporte> {

    private final Connection conn;

    public ImplementacionPasaporte() {
        // Usamos siempre la misma conexión del Singleton
        this.conn = ConexionDB.getInstancia().getConexion();
    }

    @Override
    public String insert(Pasaporte p) {
        String sql = "INSERT INTO pasaporte (id, fecha_expedicion, persona_id, pais_codigoISO) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getId());
            ps.setString(2, p.getFechaExpedicion());
            ps.setString(3, p.getTitular().getId());   // FK persona
            ps.setString(4, p.getPais().getCodigoISO()); // FK pais

            ps.executeUpdate();
            return "✅ Pasaporte insertado correctamente";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al insertar pasaporte: " + e.getMessage();
        }
    }

    @Override
    public Pasaporte findById(String id) {
        String sql = "SELECT * FROM pasaporte WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return construirPasaporte(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Pasaporte> findAll() {
        List<Pasaporte> pasaportes = new ArrayList<>();
        String sql = "SELECT * FROM pasaporte";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                pasaportes.add(construirPasaporte(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pasaportes;
    }

    @Override
    public String update(Pasaporte p) {
        String sql = "UPDATE pasaporte SET fecha_expedicion = ?, persona_id = ?, pais_codigoISO = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getFechaExpedicion());
            ps.setString(2, p.getTitular().getId());
            ps.setString(3, p.getPais().getCodigoISO());
            ps.setString(4, p.getId());

            int filas = ps.executeUpdate();
            return (filas > 0)
                    ? "Pasaporte actualizado correctamente"
                    : "No se encontró pasaporte con id: " + p.getId();

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al actualizar pasaporte, revisar que existan los datos: " + e.getMessage();
        }
    }

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

    // 🔍 Nuevo método de búsqueda filtrada
    @Override
    public List<Pasaporte> findByIdContains(String criterio) {
        List<Pasaporte> pasaportes = new ArrayList<>();
        String sql = "SELECT * FROM pasaporte WHERE id LIKE ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + criterio + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                pasaportes.add(construirPasaporte(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pasaportes;
    }

    // ============================
    // Métodos auxiliares
    // ============================

    private Pasaporte construirPasaporte(ResultSet rs) throws SQLException {
        String pasaporteId = rs.getString("id");
        String fechaExpedicion = rs.getString("fecha_expedicion");

        // Persona
        String personaId = rs.getString("persona_id");
        Persona titular = findPersonaById(personaId);

        // País
        String paisCodigo = rs.getString("pais_codigoISO");
        Pais pais = findPaisById(paisCodigo);

        return new Pasaporte(pasaporteId, fechaExpedicion, titular, pais);
    }

    private Persona findPersonaById(String id) throws SQLException {
        String sql = "SELECT * FROM persona WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Persona(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("fecha_nacimiento")
                );
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
