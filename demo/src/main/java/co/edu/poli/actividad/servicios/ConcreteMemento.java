package co.edu.poli.actividad.servicios;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConcreteMemento implements Memento {

    private final String estado;
    private final String nombre;

    public ConcreteMemento(String estado) {
        this.estado = estado;
        this.nombre = "Cambio guardado - " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String getEstado() {
        return estado;
    }

    @Override
    public String getNombre() {
        return nombre;
    }
}
