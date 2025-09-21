package co.edu.poli.actividad.servicios;

import co.edu.poli.actividad.model.Persona;

public class PersonaWrapper {

    private Persona persona;

    public PersonaWrapper(Persona persona) {
        this.persona = persona;
    }

    // Método clone externo → aquí aplicamos Prototype
    public Persona clonar() {
        return new Persona(
                persona.getId(),
                persona.getNombre(),
                persona.getFechaNacimiento()
        );
    }
}
