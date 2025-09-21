package co.edu.poli.actividad.servicios;

import co.edu.poli.actividad.model.*;

public class PasaporteOrdinarioCreator implements PasaporteCreator {

    private String id;
    private String fechaExpedicion;
    private Persona titular;
    private Pais pais;
    private String motivo;
    private ElementoSeguridad elementoSeguridad;

    // setters para pasar datos antes de la creación
    public PasaporteOrdinarioCreator id(String id) {
        this.id = id;
        return this;
    }

    public PasaporteOrdinarioCreator fechaExpedicion(String fecha) {
        this.fechaExpedicion = fecha;
        return this;
    }

    public PasaporteOrdinarioCreator titular(Persona titular) {
        this.titular = titular;
        return this;
    }

    public PasaporteOrdinarioCreator pais(Pais pais) {
        this.pais = pais;
        return this;
    }

    public PasaporteOrdinarioCreator motivo(String motivo) {
        this.motivo = motivo;
        return this;
    }

    public PasaporteOrdinarioCreator elementoSeguridad(ElementoSeguridad elemento) {
        this.elementoSeguridad = elemento;
        return this;
    }

    @Override
    public Pasaporte createPasaporte() {
        return new PasaporteOrdinarioBuilder()
                .id(id)
                .fechaExpedicion(fechaExpedicion)
                .titular(titular)
                .pais(pais)
                .motivo(motivo)
                .elementoSeguridad(elementoSeguridad)
                .build();
    }
}
