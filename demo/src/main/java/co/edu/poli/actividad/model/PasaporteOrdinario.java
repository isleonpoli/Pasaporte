package co.edu.poli.actividad.model;

public class PasaporteOrdinario extends Pasaporte {
    private String motivo;
    private ElementoSeguridad elementoSeguridad; // Agregación débil

    // Constructor por defecto para Factory
    public PasaporteOrdinario() {
    }

    // Constructor privado para Builder
    private PasaporteOrdinario(Builder builder) {
        super(builder.id, builder.fechaExpedicion, builder.titular, builder.pais);
        this.motivo = builder.motivo;
        this.elementoSeguridad = builder.elementoSeguridad;
    }

    public String getMotivo() { 
        return motivo; 
    }
    public void setMotivo(String motivo) { 
        this.motivo = motivo; 
    }

    public ElementoSeguridad getElementoSeguridad() { return elementoSeguridad; }
    public void setElementoSeguridad(ElementoSeguridad elementoSeguridad) { this.elementoSeguridad = elementoSeguridad; }

    @Override
    public String toString() {
        String base = super.toString() + " | Ordinario{motivo='" + motivo + "'}";
        if (elementoSeguridad != null) {
            base += " | " + elementoSeguridad.toString();
        }
        return base;
    }

    // Builder para PasaporteOrdinario
    public static class Builder {
        // Parámetros obligatorios
        private String id;
        private String fechaExpedicion;
        private Persona titular;
        private Pais pais;
        private String motivo;

        // Parámetros opcionales
        private ElementoSeguridad elementoSeguridad;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder fechaExpedicion(String fechaExpedicion) {
            this.fechaExpedicion = fechaExpedicion;
            return this;
        }

        public Builder titular(Persona titular) {
            this.titular = titular;
            return this;
        }

        public Builder pais(Pais pais) {
            this.pais = pais;
            return this;
        }

        public Builder motivo(String motivo) {
            this.motivo = motivo;
            return this;
        }

        public Builder elementoSeguridad(ElementoSeguridad elementoSeguridad) {
            this.elementoSeguridad = elementoSeguridad;
            return this;
        }

        public PasaporteOrdinario build() {
            // Validaciones
            if (id == null || fechaExpedicion == null || titular == null || pais == null || motivo == null) {
                throw new IllegalStateException("Todos los campos obligatorios deben ser proporcionados.");
            }
            
            return new PasaporteOrdinario(this);
        }
    }
}