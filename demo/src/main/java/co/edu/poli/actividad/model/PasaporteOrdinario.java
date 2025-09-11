// PasaporteOrdinal.java
package co.edu.poli.actividad.model;


public class PasaporteOrdinario extends Pasaporte {
    private String motivo;

    public PasaporteOrdinario() {}

    public PasaporteOrdinario(String id, String fechaExpedicion, Persona titular, Pais pais, String motivo) {
        super(id, fechaExpedicion, titular, pais);
        this.motivo = motivo;
    }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    @Override
    public String toString() {
        return super.toString() + " | Ordinario{motivo='" + motivo + "'}";
    }
}
