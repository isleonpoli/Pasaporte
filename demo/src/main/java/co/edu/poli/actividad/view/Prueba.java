package co.edu.poli.actividad.view;

import co.edu.poli.actividad.model.Blockchain;
import co.edu.poli.actividad.model.ElementoSeguridad;
import co.edu.poli.actividad.model.Pais;
import co.edu.poli.actividad.model.Pasaporte;
import co.edu.poli.actividad.model.PasaporteEmergencia;
import co.edu.poli.actividad.model.Persona;
import co.edu.poli.actividad.servicios.Base; 
import co.edu.poli.actividad.servicios.Component;
import co.edu.poli.actividad.servicios.HistorialViajes;
import co.edu.poli.actividad.servicios.PasaporteEmergenciaBuilder;
import co.edu.poli.actividad.servicios.Seguro;
import co.edu.poli.actividad.servicios.Visa;

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

        ElementoSeguridad blockchain = new Blockchain("SEC001", "hash");

        PasaporteEmergencia p1 = new PasaporteEmergenciaBuilder()
                .id("P001")
                .fechaExpedicion("2025-09-21")
                .fechaExpiracionEmergencia("2025-12-21")
                .titular(persona1)
                .pais(pais)
                .motivo("Operacion urgente")
                .elementoSeguridad(blockchain)
                .build();

        Pasaporte[] pasaportes = {p1};

        System.out.println("\n=== Lista de Pasaportes ===");
        for (Pasaporte p : pasaportes) {
            System.out.println(p);
        }

        // ========================
        // DECORATOR (Persona con Visa, Seguro, Historial)
        // ========================
        System.out.println("\n=== Decorator aplicado a Persona ===");
        
        Component base = new Base(persona1);
        System.out.println("Persona base: " + base.getDescripcion());

        // Visa
        Component personaConVisa = new Visa(base, "Turismo", "V-2025-001");
        System.out.println("Persona con Visa: " + personaConVisa.getDescripcion());

        // Seguro
        Component personaConVisaSeguro = new Seguro(personaConVisa, "Médico", "POL-12345");
        System.out.println("Persona con Visa + Seguro: " + personaConVisaSeguro.getDescripcion());

        // Historial de viajes
        HistorialViajes personaConHistorial = new HistorialViajes(personaConVisaSeguro);
        personaConHistorial.agregarViaje("España");
        personaConHistorial.agregarViaje("Argentina");
        personaConHistorial.agregarViaje("México");

        System.out.println("Persona con Visa + Seguro + Historial: " + personaConHistorial.getDescripcion());

        // ========================
        // MOSTRAR FUNCIONALIDADES EXTRA
        // ========================

        // Mostrar detalle de historial de viajes
        System.out.println("\n=== Detalle de Historial de Viajes ===");
        for (String viaje : personaConHistorial.getViajes()) {
            System.out.println("- " + viaje);
        }

        // Mostrar fecha de expiración del pasaporte de emergencia
        System.out.println("\n=== Expiración del Pasaporte de Emergencia ===");
        if (p1 instanceof PasaporteEmergencia) {
            PasaporteEmergencia pe = (PasaporteEmergencia) p1;
            System.out.println("El pasaporte de emergencia expira en: " + pe.getFechaExpiracionEmergencia());
        }
    }
}
