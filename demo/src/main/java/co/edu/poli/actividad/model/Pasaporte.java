package co.edu.poli.actividad.model;

import java.util.*;

/**
 * 
 */
public class Pasaporte {

    /**
     * Default constructor
     */
    public Pasaporte() {
    }

    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private String fechaExpedicion;

    /**
     * 
     */
    private Persona titular;

    /**
     * 
     */
    private Pais pais;

    public Pasaporte(String id, String fechaExpedicion, Persona titular, Pais pais) {
        this.id = id;
        this.fechaExpedicion = fechaExpedicion;
        this.titular = titular;
        this.pais = pais;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public Persona getTitular() {
        return titular;
    }

    public void setTitular(Persona titular) {
        this.titular = titular;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public void mostrarInfo () {
        System.out.println("Pasaporte n° = " + id);
        System.out.println("Titular = " + titular);
        System.out.println("Pais = " + pais);
        System.out.println("Fecha expedición = " + fechaExpedicion);
    }
}