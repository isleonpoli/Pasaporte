package co.edu.poli.actividad.controller;

import co.edu.poli.actividad.model.*;
import co.edu.poli.actividad.repositorio.ImplementacionPasaporte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class PasaporteController {

    @FXML private ChoiceBox<String> ChoicekTipoPasaporte;
    @FXML private TextField txtCodigoPais, txtMotivo, txtFecha, txtIdPasaporte, txtIdPersona;

    @FXML private TableView<Pasaporte> tablaPasaportes;
    @FXML private TableColumn<Pasaporte, String> colId, colFecha, colPersona, colPais, colTipo, colMotivo;

    private final ImplementacionPasaporte repo = new ImplementacionPasaporte();
    private final ObservableList<Pasaporte> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Inicializar ChoiceBox
        ChoicekTipoPasaporte.getItems().addAll("Ordinario", "Diplomatico");

        // Enlazar columnas de la tabla
        colId.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getId()));
        colFecha.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getFechaExpedicion()));
        colPersona.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getTitular() != null ? c.getValue().getTitular().getId() : ""));
        colPais.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getPais() != null ? c.getValue().getPais().getCodigoISO() : ""));
        colTipo.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue() instanceof PasaporteOrdinario ? "Ordinario" : "Diplomático"));
        colMotivo.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue() instanceof PasaporteOrdinario ? ((PasaporteOrdinario)c.getValue()).getMotivo()
                        : c.getValue() instanceof PasaporteDiplomatico ? ((PasaporteDiplomatico)c.getValue()).getMotivo() : ""));

        tablaPasaportes.setItems(data);
    }

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

    @FXML
    void ClickCrear(ActionEvent event) {
        Pasaporte p = construirPasaporte();
        if (p != null) {
            repo.insert(p);
            data.add(p);
        }
    }

    @FXML
    void ClickBuscar(ActionEvent event) {
        String id = txtIdPasaporte.getText();
        Pasaporte p = repo.findById(id);
        data.clear();
        if (p != null) data.add(p);
    }

    @FXML
    void ClickListar(ActionEvent event) {
        List<Pasaporte> lista = repo.findAll();
        data.setAll(lista);
    }

    @FXML
    void ClickActualizar(ActionEvent event) {
        Pasaporte p = construirPasaporte();
        if (p != null) {
            repo.update(p);
            ClickListar(null);
        }
    }

    @FXML
    void ClickBorrar(ActionEvent event) {
        String id = txtIdPasaporte.getText();
        if (repo.delete(id)) {
            data.removeIf(p -> p.getId().equals(id));
        }
    }

    @FXML
    void ClickFiltrar(ActionEvent event) {
        String criterio = txtIdPasaporte.getText();
        List<Pasaporte> lista = repo.findByIdContains(criterio);
        data.setAll(lista);
    }
}
