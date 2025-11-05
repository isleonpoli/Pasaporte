package co.edu.poli.actividad.servicios;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Policia implements Suscriptor {

    @Override
    public void recibirNotificacion(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notificación - Policía Nacional");
        alert.setHeaderText(null);
        alert.setContentText("Policía Nacional ha sido notificada: " + mensaje);
        alert.showAndWait();
    }
}
