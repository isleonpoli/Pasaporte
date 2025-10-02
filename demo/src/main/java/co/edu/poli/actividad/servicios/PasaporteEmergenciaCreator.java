package co.edu.poli.actividad.servicios;

import co.edu.poli.actividad.model.ElementoSeguridad;
import co.edu.poli.actividad.model.Pais;
import co.edu.poli.actividad.model.Pasaporte;
import co.edu.poli.actividad.model.PasaporteEmergencia;
import co.edu.poli.actividad.model.Persona;

public class PasaporteEmergenciaCreator implements PasaporteCreator {

    private String id;
    private String fechaExpedicion;
    private Persona titular;
    private Pais pais;
    private String motivo;
    private String fechaExpiracionEmergencia; // 🔹 nuevo
    private ElementoSeguridad elementoSeguridad;

    public PasaporteEmergenciaCreator id(String id) {
        this.id = id;
        return this;
    }

    public PasaporteEmergenciaCreator fechaExpedicion(String fecha) {
        this.fechaExpedicion = fecha;
        return this;
    }

    public PasaporteEmergenciaCreator titular(Persona titular) {
        this.titular = titular;
        return this;
    }

    public PasaporteEmergenciaCreator pais(Pais pais) {
        this.pais = pais;
        return this;
    }

    public PasaporteEmergenciaCreator motivo(String motivo) {
        this.motivo = motivo;
        return this;
    }

    public PasaporteEmergenciaCreator fechaExpiracionEmergencia(String fecha) {
        this.fechaExpiracionEmergencia = fecha;
        return this;
    }

    public PasaporteEmergenciaCreator elementoSeguridad(ElementoSeguridad elemento) {
        this.elementoSeguridad = elemento;
        return this;
    }

    @Override
    public Pasaporte createPasaporte() {
        return new PasaporteEmergenciaBuilder()
                .id(id)
                .fechaExpedicion(fechaExpedicion)
                .titular(titular)
                .pais(pais)
                .motivo(motivo)
                .fechaExpiracionEmergencia(fechaExpiracionEmergencia)
                .elementoSeguridad(elementoSeguridad)
                .build();
    }
}
