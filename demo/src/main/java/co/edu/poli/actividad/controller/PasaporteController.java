package co.edu.poli.actividad.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.poli.actividad.model.Pais;
import co.edu.poli.actividad.model.Pasaporte;
import co.edu.poli.actividad.model.PasaporteDiplomatico;
import co.edu.poli.actividad.model.PasaporteOrdinario;
import co.edu.poli.actividad.model.Persona;
import co.edu.poli.actividad.repositorio.ImplementacionPasaporte;
import co.edu.poli.actividad.servicios.CalculadoraTarifa;
import co.edu.poli.actividad.servicios.TarifaDiplomatico;
import co.edu.poli.actividad.servicios.TarifaOrdinario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class PasaporteController {

    @FXML
    private ChoiceBox<String> ChoicekTipoPasaporte;
    @FXML
    private TextField txtCodigoPais, txtMotivo, txtFecha, txtIdPasaporte, txtIdPersona;

    @FXML
    private TableView<Pasaporte> tablaPasaportes;
    @FXML
    private TableColumn<Pasaporte, String> colId, colFecha, colPersona, colPais, colTipo, colMotivo;

    @FXML
    private Button btnModificar;
    @FXML
    private ComboBox<String> choiceHistorial;

    //Strategy
    @FXML
    private Label lblTarifa;

    private final co.edu.poli.actividad.servicios.Caretaker caretaker = new co.edu.poli.actividad.servicios.Caretaker();
    private final co.edu.poli.actividad.servicios.Originator originator = new co.edu.poli.actividad.servicios.Originator();
    private final Map<String, co.edu.poli.actividad.servicios.Memento> mapaMementos = new HashMap<>();

    private final ImplementacionPasaporte repo = new ImplementacionPasaporte();
    private final ObservableList<Pasaporte> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        ChoicekTipoPasaporte.getItems().addAll("Ordinario", "Diplomatico");

        colId.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getId()));
        colFecha.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getFechaExpedicion()));
        colPersona.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getTitular() != null ? c.getValue().getTitular().getId() : ""));
        colPais.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getPais() != null ? c.getValue().getPais().getCodigoISO() : ""));
        colTipo.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue() instanceof PasaporteOrdinario ? "Ordinario" : "Diplomático"));
        colMotivo.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue() instanceof PasaporteOrdinario ? ((PasaporteOrdinario) c.getValue()).getMotivo()
                : c.getValue() instanceof PasaporteDiplomatico ? ((PasaporteDiplomatico) c.getValue()).getMotivo() : ""));

        tablaPasaportes.setItems(data);

        // Debug: imprimir tamaño inicial de la lista
        System.out.println("DEBUG initialize -> data size = " + data.size());

        // Listener para seleccionar fila
        tablaPasaportes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            System.out.println("DEBUG table selection changed. newSelection = " + newSelection);
            if (newSelection != null) {
                // Imprime los valores que intentamos mostrar
                System.out.println("DEBUG pasaporte.id = " + newSelection.getId());
                System.out.println("DEBUG pasaporte.fechaExpedicion = " + newSelection.getFechaExpedicion());
                if (newSelection instanceof PasaporteOrdinario) {
                    System.out.println("DEBUG pasaporte instanceof PasaporteOrdinario");
                    System.out.println("DEBUG motivo = " + ((PasaporteOrdinario) newSelection).getMotivo());
                } else if (newSelection instanceof PasaporteDiplomatico) {
                    System.out.println("DEBUG pasaporte instanceof PasaporteDiplomatico");
                    System.out.println("DEBUG motivo = " + ((PasaporteDiplomatico) newSelection).getMotivo());
                } else {
                    System.out.println("DEBUG pasaporte tipo desconocido");
                }

                // Rellenar campos (protecciones contra null)
                txtIdPasaporte.setText(safe(newSelection.getId()));
                txtFecha.setText(safe(newSelection.getFechaExpedicion()));

                String idTitular = newSelection.getTitular() != null ? safe(newSelection.getTitular().getId()) : "";
                txtIdPersona.setText(idTitular);

                String codigoPais = newSelection.getPais() != null ? safe(newSelection.getPais().getCodigoISO()) : "";
                txtCodigoPais.setText(codigoPais);

                if (newSelection instanceof PasaporteOrdinario) {
                    txtMotivo.setText(safe(((PasaporteOrdinario) newSelection).getMotivo()));
                    ChoicekTipoPasaporte.setValue("Ordinario");
                } else if (newSelection instanceof PasaporteDiplomatico) {
                    txtMotivo.setText(safe(((PasaporteDiplomatico) newSelection).getMotivo()));
                    ChoicekTipoPasaporte.setValue("Diplomatico");
                } else {
                    txtMotivo.clear();
                    ChoicekTipoPasaporte.setValue(null);
                }
            } else {
                // Si no hay selección, opcionalmente limpiar
                System.out.println("DEBUG selection is null -> clearing fields.");
                // comentar si no quieres limpiar automáticamente
                // clearFields();
            }
        });

        // Listener del historial (choice/combobox)
        choiceHistorial.setOnAction(e -> {
            String seleccionado = choiceHistorial.getValue();
            System.out.println("DEBUG choiceHistorial selected = " + seleccionado);
            if (seleccionado != null && mapaMementos.containsKey(seleccionado)) {
                co.edu.poli.actividad.servicios.Memento m = mapaMementos.get(seleccionado);
                mostrarEstadoEnCampos(m.getEstado());
            }
        });
    }

// Helper seguro para evitar NPE y convertir null->""
    private String safe(String s) {
        return s == null ? "" : s;
    }

    private void clearFields() {
        txtIdPasaporte.clear();
        txtFecha.clear();
        txtIdPersona.clear();
        txtCodigoPais.clear();
        txtMotivo.clear();
        //ChoicekTipoPasaporte.setValue(null);
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
        if (p != null) {
            data.add(p);
        }
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

            co.edu.poli.actividad.servicios.Publisher publisher = new co.edu.poli.actividad.servicios.Publisher();
            publisher.agregarSuscriptor(new co.edu.poli.actividad.servicios.Cancilleria());
            publisher.agregarSuscriptor(new co.edu.poli.actividad.servicios.Policia());
            publisher.agregarSuscriptor(new co.edu.poli.actividad.servicios.MigracionColombia());

            String baseMensaje = "Se ha notificado a las siguientes entidades que el pasaporte con ID "
                    + p.getId() + " se ha modificado:\n\n";

            String notificaciones = publisher.notificar("");

            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            alert.setTitle("Notificación de Actualización");
            alert.setHeaderText(null);
            alert.setContentText(baseMensaje + notificaciones);

            alert.setResizable(true);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

            alert.showAndWait();
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

    @FXML
    private void VerEspaciosGeograficos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/actividad/view/EspaciosGeograficos.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Espacios Geográficos");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ClickModificar(ActionEvent event) {
        Pasaporte p = construirPasaporte();
        if (p == null) {
            return;
        }

        co.edu.poli.actividad.servicios.PasaporteAdapter adapter
                = new co.edu.poli.actividad.servicios.PasaporteAdapter(p);

        originator.setEstado(adapter);

        co.edu.poli.actividad.servicios.Memento m = originator.guardar();
        caretaker.addMemento(p.getId(), m);
        mapaMementos.put(m.getNombre(), m);

        choiceHistorial.getItems().clear();
        caretaker.getHistorial(p.getId()).forEach(mem -> choiceHistorial.getItems().add(mem.getNombre()));

        try {
            repo.update(p);
            ClickListar(null);
        } catch (Exception e) {
            javafx.scene.control.Alert errorAlert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            errorAlert.setTitle("Error al actualizar");
            errorAlert.setHeaderText("No se pudo actualizar el pasaporte");
            errorAlert.setContentText("Ocurrió un error al intentar actualizar el pasaporte con ID: " + p.getId());
            errorAlert.showAndWait();
            return;
        }

        co.edu.poli.actividad.servicios.Publisher publisher = new co.edu.poli.actividad.servicios.Publisher();
        publisher.agregarSuscriptor(new co.edu.poli.actividad.servicios.Cancilleria());
        publisher.agregarSuscriptor(new co.edu.poli.actividad.servicios.Policia());
        publisher.agregarSuscriptor(new co.edu.poli.actividad.servicios.MigracionColombia());

        String baseMensaje = "Se ha notificado a las siguientes entidades que el pasaporte con ID "
                + p.getId() + " se ha modificado:\n\n";

        String notificaciones = publisher.notificar("");

        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Notificación de Actualización");
        alert.setHeaderText(null);
        alert.setContentText(baseMensaje + notificaciones);

        alert.setResizable(true);
        alert.getDialogPane().setMinHeight(javafx.scene.layout.Region.USE_PREF_SIZE);

        alert.showAndWait();
    }


    /*   private void mostrarEstadoEnCampos(String estado) {
        // Buscar campos en el texto del memento (ya que guardamos como string)
        String[] lineas = estado.split("\n");
        for (String l : lineas) {
            if (l.startsWith("ID:")) txtIdPasaporte.setText(l.replace("ID:", "").trim());
            if (l.startsWith("Titular:")) txtIdPersona.setText(l.replace("Titular:", "").trim());
            if (l.startsWith("País:")) txtCodigoPais.setText(l.replace("País:", "").trim());
            if (l.startsWith("Fecha Expedición:")) txtFecha.setText(l.replace("Fecha Expedición:", "").trim());
            if (l.startsWith("Motivo:")) txtMotivo.setText(l.replace("Motivo:", "").trim());
        }
    }
     */
    private void mostrarAlerta(String mensaje) {
        javafx.scene.control.Alert alerta = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
     
    private void mostrarEstadoEnCampos(String estado) {
        if (estado == null) {
            return;
        }
        System.out.println("DEBUG mostrarEstadoEnCampos -> estado:\n" + estado);

        // Reiniciar
        clearFields();

        String[] lineas = estado.split("\\r?\\n");
        for (String l : lineas) {
            l = l.trim();
            if (l.startsWith("ID:")) {
                txtIdPasaporte.setText(l.substring(3).trim());
            } else if (l.startsWith("Fecha Expedición:") || l.startsWith("Fecha de expedición:") || l.startsWith("Fecha:")) {
                // adaptadores de posibles etiquetas
                String val = l.substring(l.indexOf(":") + 1).trim();
                txtFecha.setText(val);
            } else if (l.startsWith("Titular:")) {
                String val = l.substring(l.indexOf(":") + 1).trim();
                // aquí asumimos que en el memento guardaste el id del titular
                txtIdPersona.setText(val);
            } else if (l.startsWith("País:") || l.startsWith("Pais:")) {
                String val = l.substring(l.indexOf(":") + 1).trim();
                txtCodigoPais.setText(val);
            } else if (l.startsWith("Motivo:")) {
                String val = l.substring(l.indexOf(":") + 1).trim();
                txtMotivo.setText(val);
            }
        }
    }

//Strategy
    @FXML
    private void ClickCalcularTarifa() {
        String tipo = ChoicekTipoPasaporte.getValue();
        if (tipo == null) {
            mostrarAlerta("Por favor seleccione un tipo de pasaporte.");
            return;
        }

        CalculadoraTarifa calculadora = new CalculadoraTarifa();

        // Seleccionar estrategia según el tipo
        switch (tipo) {
            case "Ordinario":
                calculadora.setEstrategia(new TarifaOrdinario());
                break;
            case "Diplomatico":
                calculadora.setEstrategia(new TarifaDiplomatico());
                break;
            default:
                mostrarAlerta("Tipo de pasaporte no reconocido.");
                return;
        }

        // Ejecutar y mostrar el resultado
        double resultado = calculadora.ejecutarCalculo();
        lblTarifa.setText("Costo de emisión: $" + resultado);
    }    

}
