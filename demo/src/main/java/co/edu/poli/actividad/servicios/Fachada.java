package co.edu.poli.actividad.servicios;

public class Fachada {

    private Manejador cadena;

    public Fachada() {
        // Crear manejadores
        Manejador verificacionId = new VerificacionId();
        Manejador verificacionAntecedentes = new VerificacionAntecedentes();
        Manejador generacionPasaporte = new GeneracionPasaporte();

        // Encadenar
        verificacionId.setSiguiente(verificacionAntecedentes);
        verificacionAntecedentes.setSiguiente(generacionPasaporte);

        // Guardar referencia al primero
        this.cadena = verificacionId;
    }

    public boolean verificacion(int numId, int numAntecedentes, int numGeneracion) {
        System.out.println("=== INICIANDO PROCESO DE PASAPORTE ===");
        return cadena.procesar(numId, numAntecedentes, numGeneracion);
    }
}
