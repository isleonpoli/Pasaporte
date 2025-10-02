package co.edu.poli.actividad.view;

import java.io.InputStream;

public class Prueba {

    public static void main(String[] args) {
        // Verificación: ¿está el archivo en el classpath?
        InputStream test = Prueba.class.getClassLoader()
                .getResourceAsStream("config.properties");

        if (test != null) {
            System.out.println("Archivo config.properties encontrado en el classpath.");
        } else {
            System.out.println("Archivo config.properties NO encontrado.");
        }

    }
}
