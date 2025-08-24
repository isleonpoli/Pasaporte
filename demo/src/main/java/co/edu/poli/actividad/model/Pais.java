package co.edu.poli.actividad.model;

import java.util.*;

/**
 * 
 */
public class Pais implements Entidad {

    /**
     * Default constructor
     */
    public Pais() {
    }

    /**
     * 
     */
    private String codigoISO;

    /**
     * 
     */
    private String nombre;

    /**
     * 
     */
    private List<Ciudad> ciudad;

    public Pais(String codigoISO, String nombre, List<Ciudad> ciudad) {
        this.codigoISO = codigoISO;
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    public String getCodigoISO() {
        return codigoISO;
    }

    public void setCodigoISO(String codigoISO) {
        this.codigoISO = codigoISO;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Ciudad> getCiudad() {
        return ciudad;
    }

    public void setCiudad(List<Ciudad> ciudad) {
        this.ciudad = ciudad;
    }



    @Override
public String toString() {
    return "Pais{" +
            "nombre='" + nombre + '\'' +
            ", codigo='" + codigoISO + '\'' +
            ", ciudades=" + ciudad +
            '}';
}
}