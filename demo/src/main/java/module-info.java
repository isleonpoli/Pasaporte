module co.edu.poli.actividad.pasaporte {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires transitive javafx.graphics;
    
    opens co.edu.poli.actividad.view to javafx.fxml;
    opens co.edu.poli.actividad.controller to javafx.fxml;
    exports co.edu.poli.actividad.view;
    exports co.edu.poli.actividad.controller;
}