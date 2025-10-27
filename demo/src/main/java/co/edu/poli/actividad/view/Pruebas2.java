package co.edu.poli.actividad.view;

import co.edu.poli.actividad.model.Biometrico;
import co.edu.poli.actividad.model.Chip;
import co.edu.poli.actividad.model.ElementoSeguridad;
import co.edu.poli.actividad.model.Pais;
import co.edu.poli.actividad.model.PasaporteDiplomatico;
import co.edu.poli.actividad.model.PasaporteOrdinario;
import co.edu.poli.actividad.model.Persona;
import co.edu.poli.actividad.servicios.Fachada;
import co.edu.poli.actividad.servicios.FlyweightFactory;
import co.edu.poli.actividad.servicios.PasaporteAdapter;
import co.edu.poli.actividad.servicios.PasaporteDiplomaticoBuilder;
import co.edu.poli.actividad.servicios.PasaporteOrdinarioBuilder;
import co.edu.poli.actividad.servicios.PasaporteTipo;
import co.edu.poli.actividad.servicios.Proxy;

public class Pruebas2 {

public static void main(String[] args) {

        Persona persona1 = new Persona("123", "Carlos López", "1990-05-15");
        Persona persona2 = new Persona("456", "Ana Gómez", "1985-08-30");

        Pais pais1 = new Pais("colombia", "Colombia", null);
        Pais pais2 = new Pais("usa", "usa", null);

        ElementoSeguridad chip = new Chip("CHIP001", "Chip RFID");
        ElementoSeguridad biometrico = new Biometrico("BIO001", "Datos biométricos");

        PasaporteOrdinario p1 = new PasaporteOrdinarioBuilder()
                .id("P001")
                .fechaExpedicion("2025-10-01")
                .titular(persona1)
                .pais(pais1)
                .motivo("Viaje de turismo")
                .elementoSeguridad(chip)
                .build();      
                
        PasaporteDiplomatico p2 = new PasaporteDiplomaticoBuilder()
                .id("P002")
                .fechaExpedicion("2025-11-15")
                .titular(persona2)
                .pais(pais2)
                .motivo("Misión diplomática")
                .elementoSeguridad(biometrico)
                .build();
        
        PasaporteDiplomatico p3 = new PasaporteDiplomaticoBuilder()
                .id("P003")
                .fechaExpedicion("2025-12-20")
                .titular(persona1)
                .pais(pais2)
                .motivo("Misión diplomática")
                .elementoSeguridad(chip)
                .build();
        
        FlyweightFactory factoria = new FlyweightFactory();

        // Obtener los tipos de pasaporte (Flyweight) para cada país
        PasaporteTipo tipo1 = factoria.getFlyweight(pais1.getNombre());
        PasaporteTipo tipo2 = factoria.getFlyweight(pais2.getNombre());
        PasaporteTipo tipo3 = factoria.getFlyweight(pais2.getNombre());

        // Mostrar detalles de los pasaportes usando el Flyweight
        /*System.out.println("=== PATRÓN FLYWEIGHT ===");
        tipo1.mostrarDetalles(p1);
        tipo2.mostrarDetalles(p2);
        tipo3.mostrarDetalles(p3);

        System.out.println("=== ESTADÍSTICAS FLYWEIGHT ===");
        factoria.mostrarCache();*/
        

        PasaporteAdapter p1Adapter = new PasaporteAdapter(p1);
        PasaporteAdapter p2Adapter = new PasaporteAdapter(p2);

        String rol = "ADMIN";
        String rol2 = "TRABAJADOR";
        String rol3 = "INVITADO";

        Proxy proxy1 = new Proxy(p1Adapter, rol);
        System.out.println("\n--- ROL: " + rol + " ---");
        System.out.println("Pasaporte 1:");
        System.out.println(proxy1.mostrarInformacionSegunRol());

        Proxy proxy2 = new Proxy(p2Adapter, rol2);
        System.out.println("\n--- ROL: " + rol2 + " ---");
        System.out.println("Pasaporte 2:");
        System.out.println(proxy2.mostrarInformacionSegunRol());

        Proxy proxy3 = new Proxy(p1Adapter, rol3);
        System.out.println("\n--- ROL: " + rol3 + " ---");
        System.out.println("Pasaporte 3:");
        System.out.println(proxy3.mostrarInformacionSegunRol());

         Fachada fachada = new Fachada();

        int[] casoPrueba = {2, 1, 1};

        boolean resultado = fachada.procesarPasaporte(casoPrueba[0], casoPrueba[1], casoPrueba[2]);
        System.out.println("RESULTADO: " + (resultado ? "APROBADO" : "RECHAZADO"));
    }
}