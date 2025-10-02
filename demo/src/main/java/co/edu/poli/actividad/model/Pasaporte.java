package co.edu.poli.actividad.model;

public abstract class Pasaporte {
    protected String id;
    protected String fechaExpedicion;
    protected Persona titular;
    protected Pais pais;
    protected ElementoSeguridad elementoSeguridad;

    public Pasaporte() {}

    public Pasaporte(String id, String fechaExpedicion, Persona titular, Pais pais) {
    this(id, fechaExpedicion, titular, pais, null);
}

    public Pasaporte(String id, String fechaExpedicion, Persona titular, Pais pais, ElementoSeguridad elementoSeguridad) {
        this.id = id;
        this.fechaExpedicion = fechaExpedicion;
        this.titular = titular;
        this.pais = pais;
        this.elementoSeguridad = elementoSeguridad;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFechaExpedicion() { return fechaExpedicion; }
    public void setFechaExpedicion(String fechaExpedicion) { this.fechaExpedicion = fechaExpedicion; }

    public Persona getTitular() { return titular; }
    public void setTitular(Persona titular) { this.titular = titular; }

    public Pais getPais() { return pais; }
    public void setPais(Pais pais) { this.pais = pais; }

    public ElementoSeguridad getElementoSeguridad() {
        return elementoSeguridad;
    }

    public void setElementoSeguridad(ElementoSeguridad elementoSeguridad) {
        this.elementoSeguridad = elementoSeguridad;
    }

    @Override
    public String toString() {
        return "Pasaporte{" +
                "id='" + id + '\'' +
                ", fechaExpedicion='" + fechaExpedicion + '\'' +
                ", titular=" + (titular != null ? titular.getId() : "null") +
                ", pais=" + (pais != null ? pais.getCodigoISO() : "null") +
                ", elementoSeguridad=" + (elementoSeguridad != null ? elementoSeguridad.getDetalles() : "Sin elemento") +
                '}';
    }
}
