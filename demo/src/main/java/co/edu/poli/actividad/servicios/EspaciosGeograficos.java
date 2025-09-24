package co.edu.poli.actividad.servicios;

import java.util.List;

public interface EspaciosGeograficos {
    String getNombre();
    List<EspaciosGeograficos> getHijos();
}
