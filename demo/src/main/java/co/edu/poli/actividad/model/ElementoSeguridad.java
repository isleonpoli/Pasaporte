package co.edu.poli.actividad.model;

public final class ElementoSeguridad {
    private final String id;
    private final String tipo;
    private final String descripcion;

    private ElementoSeguridad(Builder builder) {
        this.id = builder.id;
        this.tipo = builder.tipo;
        this.descripcion = builder.descripcion;
    }

    public String getId() { 
        return id; 
    }

    public String getTipo() { 
        return tipo; 
    }

    public String getDescripcion() { 
        return descripcion; 
    }

    @Override
    public String toString() {
        return "ElementoSeguridad{" +
                "id='" + id + '\'' +
                ", tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    // Builder de ElementoSeguridad
    public static class Builder {
        private String id;
        private String tipo;
        private String descripcion;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder tipo(String tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder descripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public ElementoSeguridad build() {
            if (id == null) {
                throw new IllegalStateException("El elemento de seguridad debe tener un ID.");
            }
            if (tipo == null) {
                throw new IllegalStateException("El elemento de seguridad debe tener un tipo.");
            }
            return new ElementoSeguridad(this);
        }
    }
}