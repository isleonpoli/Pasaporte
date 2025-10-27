package co.edu.poli.actividad.servicios;

public class Fachada {
    private VerificacionId verificacionId;
    private VerificacionAntecedentes verificacionAntecedentes;
    private GeneracionPasaporte generacionPasaporte;
    
    public Fachada() {
        this.verificacionId = new VerificacionId();
        this.verificacionAntecedentes = new VerificacionAntecedentes();
        this.generacionPasaporte = new GeneracionPasaporte();
    }
    
    public boolean procesarPasaporte(int numId, int numAntecedentes, int numGeneracion) {
        System.out.println("=== INICIANDO PROCESO DE PASAPORTE ===");
        System.out.println("Parámetros recibidos: ID=" + numId + ", Antecedentes=" + numAntecedentes + ", Generación=" + numGeneracion);
        
        // Paso 1: Verificar ID
        System.out.println("\n--- Paso 1: Verificación de ID ---");
        if (!verificacionId.verificar(numId)) {
            System.out.println("PROCESO DETENIDO: Error en verificación de ID");
            return false;
        }
        
        // Paso 2: Verificar antecedentes
        System.out.println("\n--- Paso 2: Verificación de Antecedentes ---");
        if (!verificacionAntecedentes.verificar(numAntecedentes)) {
            System.out.println("PROCESO DETENIDO: Error en verificación de antecedentes");
            return false;
        }
        
        // Paso 3: Generar pasaporte
        System.out.println("\n--- Paso 3: Generación de Pasaporte ---");
        if (!generacionPasaporte.generar(numGeneracion)) {
            System.out.println("PROCESO DETENIDO: Error en generación de pasaporte");
            return false;
        }
        
        System.out.println("\nPROCESO COMPLETADO EXITOSAMENTE");
        return true;
    }
}