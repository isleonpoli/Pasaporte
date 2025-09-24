package co.edu.poli.actividad.servicios;

import java.util.Collections;
import java.util.List;

import co.edu.poli.actividad.model.Ciudad;

public class CiudadAdapter implements EspaciosGeograficos {

    private Ciudad ciudad;

    public CiudadAdapter(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String getNombre() {
        return ciudad.getNombre();
    }

    @Override
    public List<EspaciosGeograficos> getHijos() {
        return Collections.emptyList();
    }

    public Ciudad getCiudad() {
        return ciudad;
    }
}
