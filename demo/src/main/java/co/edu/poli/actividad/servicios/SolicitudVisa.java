package co.edu.poli.actividad.servicios;

import java.util.Arrays;
import java.util.List;

public class SolicitudVisa implements Estado {
    @Override
    public String getNombre() {
        return "Solicitud Visa";
    }

    @Override
    public List<String> obtenerTransicionesPosibles() {
        return Arrays.asList("Estado Normal", "Frontera Cerrada");
    }
}
