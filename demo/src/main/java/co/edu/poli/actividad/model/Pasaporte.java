package co.edu.poli.actividad.model;

public abstract class Pasaporte {
    protected String id;
    protected String fechaExpedicion;
    protected Persona titular;
    protected Pais pais;

    public Pasaporte() {}

    public Pasaporte(String id, String fechaExpedicion, Persona titular, Pais pais) {
        this.id = id;
        this.fechaExpedicion = fechaExpedicion;
        this.titular = titular;
        this.pais = pais;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFechaExpedicion() { return fechaExpedicion; }
    public void setFechaExpedicion(String fechaExpedicion) { this.fechaExpedicion = fechaExpedicion; }

    public Persona getTitular() { return titular; }
    public void setTitular(Persona titular) { this.titular = titular; }

    public Pais getPais() { return pais; }
    public void setPais(Pais pais) { this.pais = pais; }

    @Override
    public String toString() {
        return "Pasaporte{" +
                "id='" + id + '\'' +
                ", fechaExpedicion='" + fechaExpedicion + '\'' +
                ", titular=" + (titular != null ? titular.getId() : "null") +
                ", pais=" + (pais != null ? pais.getCodigoISO() : "null") +
                '}';
    }
}