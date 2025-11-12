package co.edu.poli.actividad.servicios;

import java.util.Arrays;
import java.util.List;

public class EstadoNormal implements Estado {
    @Override
    public String getNombre() {
        return "Estado Normal";
    }

    @Override
    public List<String> obtenerTransicionesPosibles() {
        return Arrays.asList("Estado Revisión", "Solicitud Visa", "Frontera Cerrada");
    }
}
