package co.edu.poli.actividad.model;

public class PasaporteDiplomatico extends Pasaporte {
    private String motivo;

    // Constructor con ElementoSeguridad
    public PasaporteDiplomatico(String id, String fechaExpedicion, Persona titular, Pais pais, String motivo, ElementoSeguridad elementoSeguridad) {
        super(id, fechaExpedicion, titular, pais, elementoSeguridad);
        this.motivo = motivo;
    }

    // Constructor sin ElementoSeguridad
    public PasaporteDiplomatico(String id, String fechaExpedicion, Persona titular, Pais pais, String motivo) {
        super(id, fechaExpedicion, titular, pais); // no pasa elemento de seguridad
        this.motivo = motivo;
    }

    public String getMotivo() { return motivo; }

    @Override
    public String toString() {
        return super.toString() + " | Diplomático{motivo='" + motivo + "'}";
    }
}
