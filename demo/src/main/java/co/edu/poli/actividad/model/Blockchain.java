package co.edu.poli.actividad.model;

public class Blockchain implements ElementoSeguridad {
    private String id;
    private String descripcion;
    private String hash;

    public Blockchain(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    @Override
    public String getId() { return id; }

    @Override
    public String getDescripcion() { return descripcion; }

    @Override
    public String getTipo() { return "Blockchain"; }

    @Override
    public String getDetalles() {
        return "Elemento de Seguridad - Blockchain [ID: " + id + ", Descripción: " + descripcion + "]";
    }
}
