package co.edu.poli.actividad.servicios;

import java.util.List;

public interface EspaciosGeografico {
    String getNombre();
    List<EspaciosGeografico> getHijos();
}
