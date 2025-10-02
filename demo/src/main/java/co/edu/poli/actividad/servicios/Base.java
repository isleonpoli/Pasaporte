package co.edu.poli.actividad.servicios;

import co.edu.poli.actividad.model.Persona;

/**
 * Base adapta una Persona del paquete model al interface Component.
 */
public class Base implements Component {

    private final Persona persona;

    public Base(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String getDescripcion() {
        // Construcción segura de la descripción a partir de los getters disponibles en Persona
        String id = persona.getId() != null ? persona.getId() : "";
        String nombre = persona.getNombre() != null ? persona.getNombre() : "";
        String fecha = persona.getFechaNacimiento() != null ? persona.getFechaNacimiento() : "";

        return String.format("Persona[id=%s, nombre=%s, fechaNacimiento=%s]", id, nombre, fecha);
    }

    public Persona getPersona() {
        return persona;
    }
}
