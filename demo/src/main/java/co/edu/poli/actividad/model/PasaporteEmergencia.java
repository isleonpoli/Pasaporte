package co.edu.poli.actividad.model;

public class PasaporteEmergencia extends Pasaporte {
    private String motivo;
    private String fechaExpiracionEmergencia; // nuevo atributo

    // Constructor con ElementoSeguridad
    public PasaporteEmergencia(
            String id, 
            String fechaExpedicion, 
            Persona titular, 
            Pais pais, 
            String motivo, 
            String fechaExpiracionEmergencia,
            ElementoSeguridad elementoSeguridad) {
        super(id, fechaExpedicion, titular, pais, elementoSeguridad);
        this.motivo = motivo;
        this.fechaExpiracionEmergencia = fechaExpiracionEmergencia;
    }

    // Constructor sin ElementoSeguridad
    public PasaporteEmergencia(
            String id, 
            String fechaExpedicion, 
            Persona titular, 
            Pais pais, 
            String motivo, 
            String fechaExpiracionEmergencia) {
        super(id, fechaExpedicion, titular, pais); 
        this.motivo = motivo;
        this.fechaExpiracionEmergencia = fechaExpiracionEmergencia;
    }

    // Getters y Setters
    public String getMotivo() { 
        return motivo; 
    }

    public void setMotivo(String motivo) { 
        this.motivo = motivo; 
    }

    public String getFechaExpiracionEmergencia() { 
        return fechaExpiracionEmergencia; 
    }

    public void setFechaExpiracionEmergencia(String fechaExpiracionEmergencia) { 
        this.fechaExpiracionEmergencia = fechaExpiracionEmergencia; 
    }

    @Override
    public String toString() {
        return super.toString() 
                + " | Emergencia{motivo='" + motivo 
                + "', fechaExpiracionEmergencia='" + fechaExpiracionEmergencia + "'}";
    }
}
