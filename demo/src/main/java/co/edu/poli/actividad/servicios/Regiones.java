package co.edu.poli.actividad.servicios;

import java.util.ArrayList;
import java.util.List;

public class Regiones implements EspaciosGeograficos {

    private String nombre;
    private List<EspaciosGeograficos> hijos;

    public Regiones(String nombre) {
        this.nombre = nombre;
        this.hijos = new ArrayList<>();
    }

    public void agregar(EspaciosGeograficos espacio) {
        hijos.add(espacio);
    }

    public void eliminar(EspaciosGeograficos espacio) {
        hijos.remove(espacio);
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public List<EspaciosGeograficos> getHijos() {
        return hijos;
    }
}
