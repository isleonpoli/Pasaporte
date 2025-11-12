package co.edu.poli.actividad.servicios;

import java.util.Arrays;
import java.util.List;

public class FronteraCerrada implements Estado {
    @Override
    public String getNombre() {
        return "Frontera Cerrada";
    }

    @Override
    public List<String> obtenerTransicionesPosibles() {
        return Arrays.asList("Solicitud Visa", "Estado Normal");
    }
}
