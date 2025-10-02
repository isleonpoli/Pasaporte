package co.edu.poli.actividad.servicios;

import co.edu.poli.actividad.model.ElementoSeguridad;
import co.edu.poli.actividad.model.Pais;
import co.edu.poli.actividad.model.Pasaporte;
import co.edu.poli.actividad.model.Persona;

public class PasaporteDiplomaticoCreator implements PasaporteCreator {

    private String id;
    private String fechaExpedicion;
    private Persona titular;
    private Pais pais;
    private String motivo;
    private ElementoSeguridad elementoSeguridad;

    // setters para pasar datos antes de la creación
    public PasaporteDiplomaticoCreator id(String id) {
        this.id = id;
        return this;
    }

    public PasaporteDiplomaticoCreator fechaExpedicion(String fecha) {
        this.fechaExpedicion = fecha;
        return this;
    }

    public PasaporteDiplomaticoCreator titular(Persona titular) {
        this.titular = titular;
        return this;
    }

    public PasaporteDiplomaticoCreator pais(Pais pais) {
        this.pais = pais;
        return this;
    }

    public PasaporteDiplomaticoCreator motivo(String motivo) {
        this.motivo = motivo;
        return this;
    }

    public PasaporteDiplomaticoCreator elementoSeguridad(ElementoSeguridad elemento) {
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
