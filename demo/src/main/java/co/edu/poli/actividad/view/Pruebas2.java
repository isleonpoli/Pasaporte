package co.edu.poli.actividad.view;

import co.edu.poli.actividad.servicios.Fachada;

public class Pruebas2 {

    public static void main(String[] args) {

        Fachada fachada = new Fachada();

        System.out.println("====== CASO 1: Todo correcto ======");
        fachada.verificacion(1, 1, 1);

        System.out.println("\n====== CASO 2: Error en ID ======");
        fachada.verificacion(0, 1, 1);

        System.out.println("\n====== CASO 3: Error en Antecedentes ======");
        fachada.verificacion(1, 0, 1);

        System.out.println("\n====== CASO 4: Error en Generación ======");
        fachada.verificacion(1, 1, 0);
    }
}
