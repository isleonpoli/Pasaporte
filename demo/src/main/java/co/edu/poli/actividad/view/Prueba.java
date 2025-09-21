package co.edu.poli.actividad.view;

import co.edu.poli.actividad.model.*;
import co.edu.poli.actividad.servicios.PasaporteOrdinarioBuilder;
import co.edu.poli.actividad.servicios.PersonaWrapper;

public class Prueba {

    public static void main(String[] args) {

        // ========================
        // PROTOTYPE (Persona)
        // ========================
        Persona persona1 = new Persona("123", "Carlos López", "1990-05-15");
        System.out.println("Persona original: " + persona1);

        // Usamos el wrapper para clonar
        PersonaWrapper wrapper = new PersonaWrapper(persona1);
        Persona personaClonada = wrapper.clonar();

        System.out.println("Persona clonada:  " + personaClonada);

        // Modificamos el clon para comprobar independencia
        personaClonada.setNombre("Pedro Ramírez");
        System.out.println("Persona original después del cambio: " + persona1);
        System.out.println("Persona clonada después del cambio: " + personaClonada);

        // ========================
        // BUILDER (PasaporteOrdinario)
        // ========================
        Pais pais = new Pais("COL", "Colombia", null);
        ElementoSeguridad chip = new ElementoSeguridad("SEC001", "Chip biométrico", "Electrónico");

        PasaporteOrdinario pasaporte = new PasaporteOrdinarioBuilder()
                .id("P001")
                .fechaExpedicion("2025-09-21")
                .titular(persona1)
                .pais(pais)
                .motivo("Viaje de estudios")
                .elementoSeguridad(chip)
                .build();

        System.out.println("\nPasaporte construido con Builder:");
        System.out.println("ID: " + pasaporte.getId());
        System.out.println("Fecha: " + pasaporte.getFechaExpedicion());
        System.out.println("Titular: " + pasaporte.getTitular().getNombre());
        System.out.println("País: " + pasaporte.getPais().getNombre());
        System.out.println("Motivo: " + pasaporte.getMotivo());
        System.out.println("Elemento de seguridad: " + pasaporte.getElementoSeguridad().getDescripcion());
    }
}
