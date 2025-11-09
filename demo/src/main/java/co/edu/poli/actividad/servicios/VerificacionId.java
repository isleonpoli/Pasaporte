package co.edu.poli.actividad.servicios;

public class VerificacionId extends Manejador {

    @Override
    public boolean procesar(int id, int antecedentes, int generacion) {
        System.out.println("Paso 1: Verificación de ID");
        if (id == 1) {
            System.out.println("Verificación de ID exitosa");
            if (siguiente != null) return siguiente.procesar(id, antecedentes, generacion);
            return true;
        } else {
            System.out.println("Error: ID  no válido");
            return false;
        }
    }
}
