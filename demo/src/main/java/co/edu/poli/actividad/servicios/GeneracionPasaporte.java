package co.edu.poli.actividad.servicios;

public class GeneracionPasaporte extends Manejador {

    @Override
    public boolean procesar(int id, int antecedentes, int generacion) {
        System.out.println("Paso 3: Generación de Pasaporte");
        if (generacion == 1) {
            System.out.println("Generación de pasaporte exitosa");
            System.out.println("\nPROCESO COMPLETADO EXITOSAMENTE");
            return true;
        } else {
            System.out.println("Error: No se pudo generar pasaporte");
            return false;
        }
    }
}
