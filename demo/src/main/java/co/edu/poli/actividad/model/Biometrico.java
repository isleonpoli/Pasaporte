package co.edu.poli.actividad.model;

public class Biometrico implements ElementoSeguridad {
    private String id;
    private String descripcion;

    public Biometrico(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    @Override
    public String getId() { return id; }

    @Override
    public String getDescripcion() { return descripcion; }

    @Override
    public String getTipo() { return "Biométrico"; }

    @Override
    public String getDetalles() {
        return "Elemento de Seguridad - Biométrico [ID: " + id + ", Descripción: " + descripcion + "]";
    }
}
