package co.edu.poli.actividad.servicios;

public class VerificacionAntecedentes {
    public boolean verificar(int numero) {
        if (numero == 1) {
            System.out.println("Verificación de antecedentes exitosa");
            return true;
        } else {
            System.out.println("Error: Antecedentes " + numero + " no válidos");
            return false;
        }
    }
}