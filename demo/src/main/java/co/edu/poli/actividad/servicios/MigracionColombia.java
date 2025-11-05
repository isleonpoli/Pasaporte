package co.edu.poli.actividad.servicios;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MigracionColombia implements Suscriptor {

    @Override
    public void recibirNotificacion(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notificación - Migración Colombia");
        alert.setHeaderText(null);
        alert.setContentText("Migración Colombia ha sido notificada: " + mensaje);
        alert.showAndWait();
    }
}
