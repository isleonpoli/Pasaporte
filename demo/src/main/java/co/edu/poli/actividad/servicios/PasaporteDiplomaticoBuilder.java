package co.edu.poli.actividad.servicios;

import co.edu.poli.actividad.model.ElementoSeguridad;
import co.edu.poli.actividad.model.Pais;
import co.edu.poli.actividad.model.PasaporteDiplomatico;
import co.edu.poli.actividad.model.Persona;

public class PasaporteDiplomaticoBuilder {

    private String id;
    private String fechaExpedicion;
    private Persona titular;
    private Pais pais;
    private String motivo;
    private ElementoSeguridad elementoSeguridad;

    public PasaporteDiplomaticoBuilder id(String id) {
        this.id = id;
        return this;
    }

    public PasaporteDiplomaticoBuilder fechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
        return this;
    }

    public PasaporteDiplomaticoBuilder titular(Persona titular) {
        this.titular = titular;
        return this;
    }

    public PasaporteDiplomaticoBuilder pais(Pais pais) {
        this.pais = pais;
        return this;
    }

    public PasaporteDiplomaticoBuilder motivo(String motivo) {
        this.motivo = motivo;
        return this;
    }

    public PasaporteDiplomaticoBuilder elementoSeguridad(ElementoSeguridad elementoSeguridad) {
        this.elementoSeguridad = elementoSeguridad;
        return this;
    }

    public PasaporteDiplomatico build() {
        if (this.elementoSeguridad != null) {
            return new PasaporteDiplomatico(id, fechaExpedicion, titular, pais, motivo, elementoSeguridad);
        } else {
            return new PasaporteDiplomatico(id, fechaExpedicion, titular, pais, motivo);
        }
    }
}
