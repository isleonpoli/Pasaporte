package co.edu.poli.actividad.servicios;

import co.edu.poli.actividad.model.ElementoSeguridad;
import co.edu.poli.actividad.model.Pais;
import co.edu.poli.actividad.model.PasaporteEmergencia;
import co.edu.poli.actividad.model.Persona;

public class PasaporteEmergenciaBuilder {

    private String id;
    private String fechaExpedicion;
    private Persona titular;
    private Pais pais;
    private String motivo;
    private String fechaExpiracionEmergencia; // 🔹 nuevo
    private ElementoSeguridad elementoSeguridad;

    public PasaporteEmergenciaBuilder id(String id) {
        this.id = id;
        return this;
    }

    public PasaporteEmergenciaBuilder fechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
        return this;
    }

    public PasaporteEmergenciaBuilder titular(Persona titular) {
        this.titular = titular;
        return this;
    }

    public PasaporteEmergenciaBuilder pais(Pais pais) {
        this.pais = pais;
        return this;
    }

    public PasaporteEmergenciaBuilder motivo(String motivo) {
        this.motivo = motivo;
        return this;
    }

    public PasaporteEmergenciaBuilder fechaExpiracionEmergencia(String fecha) {
        this.fechaExpiracionEmergencia = fecha;
        return this;
    }

    public PasaporteEmergenciaBuilder elementoSeguridad(ElementoSeguridad elementoSeguridad) {
        this.elementoSeguridad = elementoSeguridad;
        return this;
    }

    public PasaporteEmergencia build() {
        if (this.elementoSeguridad != null) {
            return new PasaporteEmergencia(id, fechaExpedicion, titular, pais, motivo, fechaExpiracionEmergencia, elementoSeguridad);
        } else {
            return new PasaporteEmergencia(id, fechaExpedicion, titular, pais, motivo, fechaExpiracionEmergencia);
        }
    }
}
