package co.edu.poli.actividad.servicios;

import java.util.ArrayList;
import java.util.List;

/**
 * Decorador que añade un historial de viajes.
 */
public class HistorialViajes extends BaseDecorator {

    private final List<String> viajes;

    public HistorialViajes(Component componente) {
        super(componente);
        this.viajes = new ArrayList<>();
    }

    // Método para agregar un destino al historial
    public void agregarViaje(String destino) {
        viajes.add(destino);
    }

    // Retorna la lista de viajes
    public List<String> getViajes() {
        return viajes;
    }

    @Override
    public String getDescripcion() {
        String base = super.getDescripcion();
        if (viajes.isEmpty()) {
            return base + " | HistorialViajes[Sin registros]";
        } else {
            return base + " | HistorialViajes" + viajes;
        }
    }
}
