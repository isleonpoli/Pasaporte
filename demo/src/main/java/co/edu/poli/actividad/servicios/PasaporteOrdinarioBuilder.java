package co.edu.poli.actividad.servicios;

import co.edu.poli.actividad.model.ElementoSeguridad;
import co.edu.poli.actividad.model.PasaporteOrdinario;
import co.edu.poli.actividad.model.Pais;
import co.edu.poli.actividad.model.Persona;

public class PasaporteOrdinarioBuilder {

    private String id;
    private String fechaExpedicion;
    private Persona titular;
    private Pais pais;
    private String motivo;
    private ElementoSeguridad elementoSeguridad;

    public PasaporteOrdinarioBuilder id(String id) {
        this.id = id;
        return this;
    }

    public PasaporteOrdinarioBuilder fechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
        return this;
    }

    public PasaporteOrdinarioBuilder titular(Persona titular) {
        this.titular = titular;
        return this;
    }

    public PasaporteOrdinarioBuilder pais(Pais pais) {
        this.pais = pais;
        return this;
    }

    public PasaporteOrdinarioBuilder motivo(String motivo) {
        this.motivo = motivo;
        return this;
    }

    public PasaporteOrdinarioBuilder elementoSeguridad(ElementoSeguridad elementoSeguridad) {
        this.elementoSeguridad = elementoSeguridad;
        return this;
    }

    public PasaporteOrdinario build() {
        if (this.elementoSeguridad != null) {
            return new PasaporteOrdinario(id, fechaExpedicion, titular, pais, motivo, elementoSeguridad);
        } else {
            return new PasaporteOrdinario(id, fechaExpedicion, titular, pais, motivo);
        }
    }
}
