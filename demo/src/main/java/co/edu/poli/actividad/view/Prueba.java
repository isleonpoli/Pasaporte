package co.edu.poli.actividad.view;

import co.edu.poli.actividad.model.Biometrico;
import co.edu.poli.actividad.model.Chip;
import co.edu.poli.actividad.model.ElementoSeguridad;
import co.edu.poli.actividad.model.Pais;
import co.edu.poli.actividad.model.Pasaporte;
import co.edu.poli.actividad.model.PasaporteDiplomatico;
import co.edu.poli.actividad.model.PasaporteOrdinario;
import co.edu.poli.actividad.model.Persona;
import co.edu.poli.actividad.servicios.PasaporteDiplomaticoBuilder;
import co.edu.poli.actividad.servicios.PasaporteOrdinarioBuilder;

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

        // ========================
        // BUILDER + BRIDGE (ElementoSeguridad como interfaz)
        // ========================
         */
        Pais pais = new Pais("COL", "Colombia", null);

        // USO DEL BRIDGE: usamos implementación concreta Chip
        ElementoSeguridad chip = new Chip("SEC001", "Chip");
        ElementoSeguridad biometrico = new Biometrico("SEC001", "Biometrico");

        PasaporteOrdinario p1 = new PasaporteOrdinarioBuilder()
                .id("P001")
                .fechaExpedicion("2025-09-21")
                .titular(persona1)
                .pais(pais)
                .motivo("Viaje de estudios")
                .elementoSeguridad(chip) // Aquí se aplica el patrón Bridge
                .build();

        PasaporteOrdinario p2 = new PasaporteOrdinarioBuilder()
                .id("P001")
                .fechaExpedicion("2025-09-21")
                .titular(persona1)
                .pais(pais)
                .motivo("Viaje de estudios")
                .elementoSeguridad(biometrico) // Aquí se aplica el patrón Bridge
                .build();

        PasaporteOrdinario p3 = new PasaporteOrdinarioBuilder()
                .id("P001")
                .fechaExpedicion("2025-09-21")
                .titular(persona1)
                .pais(pais)
                .motivo("Viaje de estudios")
                .build();

        PasaporteDiplomatico p4 = new PasaporteDiplomaticoBuilder()
                .id("P001")
                .fechaExpedicion("2025-09-21")
                .titular(persona1)
                .pais(pais)
                .motivo("Viaje de estudios")
                .elementoSeguridad(chip) // Aquí se aplica el patrón Bridge
                .build();

        PasaporteDiplomatico p5 = new PasaporteDiplomaticoBuilder()
                .id("P001")
                .fechaExpedicion("2025-09-21")
                .titular(persona1)
                .pais(pais)
                .motivo("Viaje de estudios")
                .elementoSeguridad(biometrico) // Aquí se aplica el patrón Bridge
                .build();

        PasaporteDiplomatico p6 = new PasaporteDiplomaticoBuilder()
                .id("P001")
                .fechaExpedicion("2025-09-21")
                .titular(persona1)
                .pais(pais)
                .motivo("Viaje de estudios")
                .build();

        Pasaporte[] pasaportes = {p1, p2, p3, p4, p5, p6};

        System.out.println("\n=== Lista de Pasaportes ===");
        for (Pasaporte p : pasaportes) {
            System.out.println(p);
        }

    }
}
