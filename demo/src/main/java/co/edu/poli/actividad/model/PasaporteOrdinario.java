package co.edu.poli.actividad.model;

public class PasaporteOrdinario extends Pasaporte {
    private String motivo;

    // Constructor con ElementoSeguridad (Bridge)
    public PasaporteOrdinario(String id, String fechaExpedicion, Persona titular, Pais pais, String motivo, ElementoSeguridad elementoSeguridad) {
        super(id, fechaExpedicion, titular, pais, elementoSeguridad);
        this.motivo = motivo;
    }

    // Constructor sin ElementoSeguridad
    public PasaporteOrdinario(String id, String fechaExpedicion, Persona titular, Pais pais, String motivo) {
        super(id, fechaExpedicion, titular, pais); // usa el constructor de Pasaporte que no pide seguridad
        this.motivo = motivo;
    }

    public String getMotivo() { return motivo; }

    @Override
    public String toString() {
        return super.toString() + " | Ordinario{motivo='" + motivo + "'}";
    }
}

