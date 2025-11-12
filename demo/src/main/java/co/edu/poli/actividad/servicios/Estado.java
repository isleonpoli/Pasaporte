package co.edu.poli.actividad.servicios;

import java.util.List;

public interface Estado {
    String getNombre();
    List<String> obtenerTransicionesPosibles();
}
