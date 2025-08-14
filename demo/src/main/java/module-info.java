module co.edu.poli.actividad.pasaporte {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.poli.actividad.pasaporte to javafx.fxml;
    exports co.edu.poli.actividad.pasaporte;
}
