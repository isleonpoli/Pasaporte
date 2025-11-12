package co.edu.poli.actividad.servicios;

import java.util.Collections;
import java.util.List;

public class EstadoRevision implements Estado {
    @Override
    public String getNombre() {
        return "Estado Revisión";
    }

    @Override
    public List<String> obtenerTransicionesPosibles() {
        return Collections.singletonList("Solicitud Visa");
    }
}
