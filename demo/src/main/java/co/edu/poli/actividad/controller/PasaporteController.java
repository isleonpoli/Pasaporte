package co.edu.poli.actividad.controller;

import co.edu.poli.actividad.model.Pasaporte;
import co.edu.poli.actividad.model.PasaporteOrdinario;
import co.edu.poli.actividad.model.PasaporteDiplomatico;
import co.edu.poli.actividad.model.Persona;
import co.edu.poli.actividad.model.Pais;
import co.edu.poli.actividad.repositorio.ImplementacionPasaporte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class PasaporteController {

    @FXML
    private ChoiceBox<String> ChoicekTipoPasaporte;

    @FXML
    private Button btnActualizar, btnBorrar, btnBuscar, btnCrear, btnFlitrar, btnListar;

    @FXML
    private TextField txtCodigoPais, txtMotivo, txtFecha, txtIdPasaporte, txtIdPersona;

    // Repositorio para manejar la BD
    private final ImplementacionPasaporte repo = new ImplementacionPasaporte();

    @FXML
    public void initialize() {
        // Inicializamos el ChoiceBox con tipos de pasaporte
        ChoicekTipoPasaporte.getItems().addAll("Ordinario", "Diplomatico");
    }

    // ======================
    // CREAR
    // ======================
    @FXML
    void ClickCrear(ActionEvent event) {
        try {
            Pasaporte p = construirPasaporte();
            if (p != null) {
                String msg = repo.insert(p);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Resultado", msg);
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Debes seleccionar un tipo de pasaporte");
            }
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al crear pasaporte: " + e.getMessage());
        }
    }

    // ======================
    // BUSCAR
    // ======================
    @FXML
    void ClickBuscar(ActionEvent event) {
        String id = txtIdPasaporte.getText();
        Pasaporte p = repo.findById(id);
        if (p != null) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Resultado", "Pasaporte encontrado:\n" + p);
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "No encontrado", "No existe un pasaporte con ese ID");
        }
    }

    // ======================
    // LISTAR
    // ======================
    @FXML
    void ClickListar(ActionEvent event) {
        List<Pasaporte> lista = repo.findAll();
        if (!lista.isEmpty()) {
            StringBuilder sb = new StringBuilder("Lista de pasaportes:\n");
            lista.forEach(p -> sb.append(p).append("\n"));
            mostrarAlerta(Alert.AlertType.INFORMATION, "Listado", sb.toString());
        } else {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Listado", "No hay pasaportes registrados");
        }
    }

    // ======================
    // ACTUALIZAR
    // ======================
    @FXML
    void ClickActualizar(ActionEvent event) {
        try {
            Pasaporte p = construirPasaporte();
            if (p != null) {
                String msg = repo.update(p);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Resultado", msg);
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Selecciona un tipo de pasaporte");
            }
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al actualizar pasaporte: " + e.getMessage());
        }
    }

    // ======================
    // BORRAR
    // ======================
    @FXML
    void ClickBorrar(ActionEvent event) {
        String id = txtIdPasaporte.getText();
        if (repo.delete(id)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Resultado", "✅ Pasaporte eliminado");
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "No encontrado", "❌ No se encontró el pasaporte con ese ID");
        }
    }

    // ======================
    // FILTRAR
    // ======================
    @FXML
    void ClickFiltrar(ActionEvent event) {
        String criterio = txtIdPasaporte.getText();
        List<Pasaporte> lista = repo.findByIdContains(criterio);
        if (!lista.isEmpty()) {
            StringBuilder sb = new StringBuilder("Resultados del filtro:\n");
            lista.forEach(p -> sb.append(p).append("\n"));
            mostrarAlerta(Alert.AlertType.INFORMATION, "Filtro", sb.toString());
        } else {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Filtro", "No se encontraron pasaportes con ese criterio");
        }
    }

    // ======================
    // MÉTODOS AUXILIARES
    // ======================
    private Pasaporte construirPasaporte() {
        String id = txtIdPasaporte.getText();
        String fecha = txtFecha.getText();
        String idPersona = txtIdPersona.getText();
        String codigoPais = txtCodigoPais.getText();
        String motivo = txtMotivo.getText();
        String tipo = ChoicekTipoPasaporte.getValue();

        Persona persona = new Persona(idPersona, null, null);
        Pais pais = new Pais(codigoPais, null, null);

        if ("Ordinario".equals(tipo)) {
            return new PasaporteOrdinario(id, fecha, persona, pais, motivo);
        } else if ("Diplomatico".equals(tipo)) {
            return new PasaporteDiplomatico(id, fecha, persona, pais, motivo);
        }
        return null;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
