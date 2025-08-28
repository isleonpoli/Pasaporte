package co.edu.poli.actividad.view;

import java.util.Scanner;
import java.util.List;

import co.edu.poli.actividad.model.Pasaporte;
import co.edu.poli.actividad.model.Persona;
import co.edu.poli.actividad.model.Pais;
import co.edu.poli.actividad.repositorio.ImplementacionPasaporte;
import co.edu.poli.actividad.repositorio.Repository;
import co.edu.poli.actividad.repositorio.ConexionDB; // importar la conexión

public class Clientes {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Repository<Pasaporte, String> repo = new ImplementacionPasaporte();

        int opcion;
        do {
            System.out.println("\n=== MENÚ PASAPORTE ===");
            System.out.println("1. Insertar pasaporte");
            System.out.println("2. Buscar pasaporte por ID");
            System.out.println("3. Listar todos los pasaportes");
            System.out.println("4. Actualizar pasaporte");
            System.out.println("5. Eliminar pasaporte");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese ID pasaporte: ");
                    String id = sc.nextLine();

                    System.out.println("Ingrese fecha expedición (YYYY-MM-DD): ");
                    String fecha = sc.nextLine();

                    System.out.println("Ingrese ID de persona: ");
                    String idPersona = sc.nextLine();

                    System.out.println("Ingrese código ISO del país: ");
                    String codigoPais = sc.nextLine();

                    Pasaporte p1 = new Pasaporte(
                        id, fecha, 
                        new Persona(idPersona, null, null), 
                        new Pais(codigoPais, null, null)
                    );
                    System.out.println(repo.insert(p1));
                    break;

                case 2:
                    System.out.println("Ingrese ID del pasaporte: ");
                    id = sc.nextLine();
                    Pasaporte p2 = repo.findById(id);
                    if (p2 != null) {
                        System.out.println("Pasaporte encontrado: " + p2);
                    } else {
                        System.out.println("No se encontró pasaporte con ese ID");
                    }
                    break;

                case 3:
                    List<Pasaporte> lista = repo.findAll();
                    lista.forEach(System.out::println);
                    break;

                case 4:
                    System.out.println("Ingrese ID del pasaporte a actualizar: ");
                    String idUpdate = sc.nextLine();

                    System.out.println("Nueva fecha expedición (YYYY-MM-DD): ");
                    String fechaNueva = sc.nextLine();

                    System.out.println("Nuevo ID de persona: ");
                    String idPersonaNueva = sc.nextLine();

                    System.out.println("Nuevo código ISO del país: ");
                    String codigoPaisNuevo = sc.nextLine();

                    Pasaporte p3 = new Pasaporte(
                        idUpdate, fechaNueva, 
                        new Persona(idPersonaNueva, null, null), 
                        new Pais(codigoPaisNuevo, null, null)
                    );
                    System.out.println(repo.update(p3));
                    break;

                case 5:
                    System.out.println("Ingrese ID del pasaporte a eliminar: ");
                    String idDelete = sc.nextLine();
                    if (repo.delete(idDelete)) {
                        System.out.println("Pasaporte eliminado correctamente");
                    } else {
                        System.out.println("No se encontró pasaporte con ese ID");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (opcion != 0);

        // 🔹 Cerrar recursos
        sc.close();
        ConexionDB.getInstancia().cerrarConexion();//  cerrar conexión Singleton
    }
}
