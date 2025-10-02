package co.edu.poli.actividad.view;

import co.edu.poli.actividad.model.Biometrico;
import co.edu.poli.actividad.model.Chip;
import co.edu.poli.actividad.model.ElementoSeguridad;
import co.edu.poli.actividad.model.Pais;
import co.edu.poli.actividad.model.Pasaporte;
import co.edu.poli.actividad.model.PasaporteDiplomatico;
import co.edu.poli.actividad.model.PasaporteOrdinario;
import co.edu.poli.actividad.model.Persona;
import co.edu.poli.actividad.servicios.*; // Para los builders y decorators

public class Prueba {

    public static void main(String[] args) {

        // ========================
        // PROTOTYPE (Persona)
        // ========================
        Persona persona1 = new Persona("123", "Carlos López", "1990-05-15");
        /*
        PersonaWrapper wrapper = new PersonaWrapper(persona1);
        Persona personaClonada = wrapper.clonar();

        System.out.println("Persona clonada:  " + personaClonada);

        personaClonada.setNombre("Pedro Ramírez");
        System.out.println("Persona original después del cambio: " + persona1);
        System.out.println("Persona clonada después del cambio: " + personaClonada);
        */

        // ========================
        // BUILDER + BRIDGE (ElementoSeguridad como interfaz)
        // ========================
        Pais pais = new Pais("COL", "Colombia", null);

        ElementoSeguridad chip = new Chip("SEC001", "Chip");
        ElementoSeguridad biometrico = new Biometrico("SEC002", "Biometrico");

        PasaporteOrdinario p1 = new PasaporteOrdinarioBuilder()
                .id("P001")
                .fechaExpedicion("2025-09-21")
                .titular(persona1)
                .pais(pais)
                .motivo("Viaje de estudios")
                .elementoSeguridad(chip)
                .build();

        PasaporteOrdinario p2 = new PasaporteOrdinarioBuilder()
                .id("P002")
                .fechaExpedicion("2025-09-21")
                .titular(persona1)
                .pais(pais)
                .motivo("Viaje de estudios")
                .elementoSeguridad(biometrico)
                .build();

        PasaporteDiplomatico p3 = new PasaporteDiplomaticoBuilder()
                .id("P003")
                .fechaExpedicion("2025-09-21")
                .titular(persona1)
                .pais(pais)
                .motivo("Viaje oficial")
                .elementoSeguridad(chip)
                .build();

        Pasaporte[] pasaportes = {p1, p2, p3};

        System.out.println("\n=== Lista de Pasaportes ===");
        for (Pasaporte p : pasaportes) {
            System.out.println(p);
        }

        // ========================
        // DECORATOR (Persona con Visa y Seguro)
        // ========================
        System.out.println("\n=== Decorator aplicado a Persona ===");

        Component base = new Base(persona1);
        System.out.println("Persona base: " + base.getDescripcion());

        Component personaConVisa = new Visa(base, "Turismo", "V-2025-001");
        System.out.println("Persona con Visa: " + personaConVisa.getDescripcion());

        Component personaConVisaSeguro = new Seguro(personaConVisa, "Médico", "POL-12345");
        System.out.println("Persona con Visa + Seguro: " + personaConVisaSeguro.getDescripcion());
    }
}
