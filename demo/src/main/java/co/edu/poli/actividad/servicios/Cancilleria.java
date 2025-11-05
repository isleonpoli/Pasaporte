package co.edu.poli.actividad.servicios;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Cancilleria implements Suscriptor {

    @Override
    public void recibirNotificacion(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notificación - Cancillería");
        alert.setHeaderText(null);
        alert.setContentText("Cancillería ha sido notificada: " + mensaje);
        alert.showAndWait();
    }
}
