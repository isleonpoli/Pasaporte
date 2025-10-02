package co.edu.poli.actividad.model;

public class Chip implements ElementoSeguridad {
    private String id;
    private String descripcion;

    public Chip(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    @Override
    public String getId() { return id; }

    @Override
    public String getDescripcion() { return descripcion; }

    @Override
    public String getTipo() { return "Chip"; }

    @Override
    public String getDetalles() {
        return "Elemento de Seguridad - Chip [ID: " + id + ", Descripción: " + descripcion + "]";
    }
}
