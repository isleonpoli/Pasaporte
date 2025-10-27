package co.edu.poli.actividad.view;

import co.edu.poli.actividad.model.Biometrico;
import co.edu.poli.actividad.model.Chip;
import co.edu.poli.actividad.model.ElementoSeguridad;
import co.edu.poli.actividad.model.Pais;
import co.edu.poli.actividad.model.PasaporteDiplomatico;
import co.edu.poli.actividad.model.PasaporteEmergencia;
import co.edu.poli.actividad.model.PasaporteOrdinario;
import co.edu.poli.actividad.model.Persona;
import co.edu.poli.actividad.servicios.Fachada;
import co.edu.poli.actividad.servicios.FlyweightFactory;
import co.edu.poli.actividad.servicios.PasaporteAdapter;
import co.edu.poli.actividad.servicios.PasaporteDiplomaticoBuilder;
import co.edu.poli.actividad.servicios.PasaporteEmergenciaBuilder;
import co.edu.poli.actividad.servicios.PasaporteOrdinarioBuilder;
import co.edu.poli.actividad.servicios.PasaporteTipo;
import co.edu.poli.actividad.servicios.Proxy;

public class Prueba3 {

    public static void main(String[] args) {
        
        System.out.println("🚀 INICIANDO DEMOSTRACIÓN INTEGRADA DE PATRONES");
        System.out.println("=================================================\n");

        // ========================
        // CONFIGURACIÓN INICIAL
        // ========================
        
        // Creación de personas
        Persona persona1 = new Persona("1", "Carlos López", "1990-05-15");
        Persona persona2 = new Persona("2", "Ana Gómez", "1985-08-30");
        Persona persona3 = new Persona("3", "Miguel Torres", "1988-11-20");

        // Creación de países
        Pais colombia = new Pais("co", "Colombia", null);
        Pais usa = new Pais("us", "Estados Unidos", null);
        Pais japon = new Pais("jp", "Japón", null);
        Pais francia = new Pais("fr", "Francia", null);

        // Creación de elementos de seguridad
        ElementoSeguridad chip = new Chip("CHIP001", "Chip RFID Seguro");
        ElementoSeguridad biometrico = new Biometrico("BIO001", "Huella y Reconocimiento Facial");

        // ========================
        // FACHADA - Verificación inicial
        // ========================
        
        System.out.println("1. 🏛️  PATRÓN FACADE - VERIFICACIÓN DE REQUISITOS");
        System.out.println("--------------------------------------------------");
        
        Fachada fachada = new Fachada();
        
        // Verificar personas antes de crear pasaportes
        System.out.println("\n📋 Verificando requisitos para creación de pasaportes:");
        
        int[][] verificaciones = {
            {1, 1, 1},  // Carlos López - APROBADO
            {1, 2, 1},  // Ana Gómez - RECHAZADO (antecedentes)
            {1, 1, 2}   // Miguel Torres - RECHAZADO (generación)
        };
        
        boolean[] resultadosVerificacion = new boolean[3];
        for (int i = 0; i < verificaciones.length; i++) {
            System.out.println("\n--- Verificación persona " + (i + 1) + " ---");
            resultadosVerificacion[i] = fachada.procesarPasaporte(
                verificaciones[i][0], 
                verificaciones[i][1], 
                verificaciones[i][2]
            );
        }

        // ========================
        // CONSTRUCCIÓN DE PASAPORTES
        // ========================
        
        System.out.println("\n\n2. 🔨 CONSTRUCCIÓN DE PASAPORTES (BUILDER)");
        System.out.println("-------------------------------------------");
        
        // Solo crear pasaportes para personas aprobadas
        PasaporteOrdinario pasaporte1 = null;
        PasaporteDiplomatico pasaporte2 = null;
        PasaporteEmergencia pasaporte3 = null;
        
        if (resultadosVerificacion[0]) {
            pasaporte1 = new PasaporteOrdinarioBuilder()
                    .id("PO-001")
                    .fechaExpedicion("2025-10-01")
                    .titular(persona1)
                    .pais(colombia)
                    .motivo("Viaje de turismo")
                    .elementoSeguridad(chip)
                    .build();
            System.out.println("✅ Pasaporte Ordinario creado: " + pasaporte1.getId());
        }
        
        if (resultadosVerificacion[1]) {
            pasaporte2 = new PasaporteDiplomaticoBuilder()
                    .id("PD-001")
                    .fechaExpedicion("2025-11-15")
                    .titular(persona2)
                    .pais(usa)
                    .motivo("Misión diplomática")
                    .elementoSeguridad(biometrico)
                    .build();
            System.out.println("✅ Pasaporte Diplomático creado: " + pasaporte2.getId());
        } else {
            System.out.println("❌ Pasaporte Diplomático NO creado - Verificación falló");
        }
        
        if (resultadosVerificacion[2]) {
            pasaporte3 = new PasaporteEmergenciaBuilder()
                    .id("PE-001")
                    .fechaExpedicion("2025-12-20")
                    .titular(persona3)
                    .pais(japon)
                    .motivo("Emergencia médica")
                    .elementoSeguridad(chip)
                    .build();
            System.out.println("✅ Pasaporte Emergencia creado: " + pasaporte3.getId());
        } else {
            System.out.println("❌ Pasaporte Emergencia NO creado - Verificación falló");
        }

        // ========================
        // FLYWEIGHT - Gestión de tipos
        // ========================
        
        System.out.println("\n\n3.  PATRÓN FLYWEIGHT - GESTIÓN DE TIPOS");
        System.out.println("------------------------------------------");
        
        FlyweightFactory factoria = new FlyweightFactory();
        
        // Demostrar reutilización de objetos Flyweight
        System.out.println("\n Creando y reutilizando tipos de pasaporte:");
        
        PasaporteTipo[] tipos = new PasaporteTipo[6];
        tipos[0] = factoria.getFlyweight("Colombia");
        tipos[1] = factoria.getFlyweight("Estados Unidos");
        tipos[2] = factoria.getFlyweight("Japón");
        tipos[3] = factoria.getFlyweight("Francia"); // Nuevo
        tipos[4] = factoria.getFlyweight("Colombia"); // Reutilizado
        tipos[5] = factoria.getFlyweight("Estados Unidos"); // Reutilizado
        
        System.out.println("\n🔍 Verificando reutilización:");
        System.out.println("tipo Colombia 1 == tipo Colombia 2: " + (tipos[0] == tipos[4]));
        System.out.println("tipo USA 1 == tipo USA 2: " + (tipos[1] == tipos[5]));
        
        // Mostrar cache
        System.out.println("\n💾 Estado del cache Flyweight:");
        factoria.mostrarCache();

        // ========================
        // PROXY - Control de acceso
        // ========================
        
        System.out.println("\n\n4. 🛡️  PATRÓN PROXY - CONTROL DE ACCESO");
        System.out.println("---------------------------------------");
        
        // Crear adaptadores para los pasaportes
        PasaporteAdapter adapter1 = new PasaporteAdapter(pasaporte1);
        
        // Probar diferentes roles
        String[] roles = {"ADMIN", "TRABAJADOR", "INVITADO", "AUDITOR"};
        
        System.out.println("\n👥 Simulando diferentes roles de usuario:");
        
        for (String rol : roles) {
            System.out.println("\n--- ROL: " + rol + " ---");
            Proxy proxy = new Proxy(adapter1, rol);
            
            System.out.println("Información disponible:");
            System.out.println(proxy.mostrarInformacionSegunRol());
            
            // Intentar acceso completo
            System.out.println("Intento acceso completo:");
            System.out.println(proxy.mostrarInformacionCompleta());
        }

        // ========================
        // DEMOSTRACIÓN INTEGRADA
        // ========================
        
        System.out.println("\n\n5. 🔄 DEMOSTRACIÓN INTEGRADA COMPLETA");
        System.out.println("-------------------------------------");
        
        // Escenario: Un administrador quiere ver información de todos los pasaportes aprobados
        System.out.println("\n🎭 Escenario: Administrador revisa sistema");
        
        if (pasaporte1 != null) {
            System.out.println("\n📄 Pasaporte Ordinario (Colombia):");
            PasaporteAdapter adapter = new PasaporteAdapter(pasaporte1);
            Proxy adminProxy = new Proxy(adapter, "ADMIN");
            
            // Flyweight muestra detalles del tipo
            PasaporteTipo tipoColombia = factoria.getFlyweight("Colombia");
            tipoColombia.mostrarDetalles(pasaporte1);
            
            // Proxy muestra información completa (rol admin)
            System.out.println("\n🔓 Información completa (acceso ADMIN):");
            System.out.println(adminProxy.mostrarInformacionCompleta());
        }

        // ========================
        // RESUMEN Y ESTADÍSTICAS
        // ========================
        
        System.out.println("\n\n6. 📈 RESUMEN Y ESTADÍSTICAS");
        System.out.println("---------------------------");
        
        System.out.println("✅ Patrones implementados:");
        System.out.println("   • Facade: Simplificación proceso verificación");
        System.out.println("   • Flyweight: Reutilización tipos de pasaporte");
        System.out.println("   • Proxy: Control de acceso por roles");
        System.out.println("   • Builder: Construcción flexible de objetos");
        System.out.println("   • Adapter: Adaptación de interfaces");
        
        System.out.println("\n📊 Métricas:");
        System.out.println("   • Personas verificadas: " + verificaciones.length);
        System.out.println("   • Pasaportes creados: " + 
            (resultadosVerificacion[0] ? 1 : 0) + 
            (resultadosVerificacion[1] ? 1 : 0) + 
            (resultadosVerificacion[2] ? 1 : 0));
        System.out.println("   • Tipos Flyweight en cache: " + factoria.getCacheSize());
        System.out.println("   • Roles probados: " + roles.length);
        
        System.out.println("\n🎯 Beneficios de la integración:");
        System.out.println("   • Separación de responsabilidades");
        System.out.println("   • Reutilización de recursos (Flyweight)");
        System.out.println("   • Seguridad y control (Proxy)");
        System.out.println("   • Simplificación de procesos complejos (Facade)");
        System.out.println("   • Flexibilidad y mantenibilidad");

        System.out.println("\n=================================================");
        System.out.println("🎉 DEMOSTRACIÓN COMPLETADA EXITOSAMENTE");
        System.out.println("=================================================");
    }
}