package co.edu.poli.actividad.servicios;

public class VerificacionAntecedentes extends Manejador {

    @Override
    public boolean procesar(int id, int antecedentes, int generacion) {
        System.out.println("Paso 2: Verificación de Antecedentes");
        if (antecedentes == 1) {
            System.out.println("Verificación de antecedentes exitosa");
            if (siguiente != null) return siguiente.procesar(id, antecedentes, generacion);
            return true;
        } else {
            System.out.println("Error: El id asociado al pasaporte tiene antecedentes");
            return false;
        }
    }
}
