package co.edu.poli.actividad.servicios;

import java.util.ArrayList;
import java.util.List;

public class Region implements EspaciosGeografico {

    private String nombre;
    private List<EspaciosGeografico> hijos;

    public Region(String nombre) {
        this.nombre = nombre;
        this.hijos = new ArrayList<>();
    }

    public void agregar(EspaciosGeografico espacio) {
        hijos.add(espacio);
    }

    public void eliminar(EspaciosGeografico espacio) {
        hijos.remove(espacio);
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public List<EspaciosGeografico> getHijos() {
        return hijos;
    }
}
