package co.edu.poli.actividad.model;

public class ElementoSeguridad {

    private String id;
    private String descripcion;
    private String tipo;

    // Constructor vacío
    public ElementoSeguridad() {
    }

    // Constructor completo
    public ElementoSeguridad(String id, String descripcion, String tipo) {
        this.id = id;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "ElementoSeguridad{" +
                "id='" + id + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
