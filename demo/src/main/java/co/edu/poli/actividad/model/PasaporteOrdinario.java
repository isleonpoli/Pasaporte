package co.edu.poli.actividad.model;

public class PasaporteOrdinario extends Pasaporte {
    private String motivo;
    private ElementoSeguridad elementoSeguridad;

    public PasaporteOrdinario(String id, String fechaExpedicion, Persona titular, Pais pais, String motivo, ElementoSeguridad elementoSeguridad) {
        super(id, fechaExpedicion, titular, pais);
        this.motivo = motivo;
        this.elementoSeguridad = elementoSeguridad;
    }

    public PasaporteOrdinario(String id, String fechaExpedicion, Persona titular, Pais pais, String motivo) {
    super(id, fechaExpedicion, titular, pais);
    this.motivo = motivo;
    this.elementoSeguridad = null; // valor por defecto
}


    // getters
    public String getMotivo() { return motivo; }
    public ElementoSeguridad getElementoSeguridad() { return elementoSeguridad; }
}
