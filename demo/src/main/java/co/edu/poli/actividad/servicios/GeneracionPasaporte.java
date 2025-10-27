package co.edu.poli.actividad.servicios;

public class GeneracionPasaporte {
    public boolean generar(int numero) {
        if (numero == 1) {
            System.out.println("Generación de pasaporte exitosa");
            return true;
        } else {
            System.out.println("Error: No se pudo generar pasaporte con código " + numero);
            return false;
        }
    }
}