package co.edu.poli.actividad.servicios;

public class VerificacionId {
    public boolean verificar(int numero) {
        if (numero == 1) {
            System.out.println("Verificación de ID exitosa");
            return true;
        } else {
            System.out.println("Error: ID " + numero + " no válido");
            return false;
        }
    }
}