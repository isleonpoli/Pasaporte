package co.edu.poli.actividad.view;

import java.util.List;
import java.util.Scanner;

import co.edu.poli.actividad.model.Pais;
import co.edu.poli.actividad.model.Pasaporte;
import co.edu.poli.actividad.model.PasaporteDiplomatico;
import co.edu.poli.actividad.model.PasaporteOrdinario;
import co.edu.poli.actividad.model.Persona;
import co.edu.poli.actividad.repositorio.ImplementacionPasaporte;
import co.edu.poli.actividad.servicios.ConexionDB;

public class Cliente {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            ImplementacionPasaporte repo = new ImplementacionPasaporte();

            int opcion;
            do {
                System.out.println("\n=== MENÚ PASAPORTE ===");
                System.out.println("1. Insertar pasaporte");
                System.out.println("2. Buscar pasaporte por ID exacto");
                System.out.println("3. Listar todos los pasaportes");
                System.out.println("4. Actualizar pasaporte");
                System.out.println("5. Eliminar pasaporte");
                System.out.println("6. Buscar pasaportes cuyo ID contenga un criterio");
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

                    System.out.println("Ingrese tipo de pasaporte (1: Ordinario, 2: Diplomático): ");
                    int tipo = sc.nextInt();
                    sc.nextLine(); // limpiar buffer

                    String motivo = "";
                    if (tipo == 1 || tipo == 2) {
                        System.out.println("Ingrese motivo: ");
                        motivo = sc.nextLine();
                    }

                    Pasaporte p1;
                    switch (tipo) {
                        case 1:
                            p1 = new PasaporteOrdinario(id, fecha, new Persona(idPersona, null, null), new Pais(codigoPais, null, null), motivo);
                            break;
                        case 2:
                            p1 = new PasaporteDiplomatico(id, fecha, new Persona(idPersona, null, null), new Pais(codigoPais, null, null), motivo);
                            break;
                        default:
                            System.out.println("Tipo no válido, se creará como Ordinario con motivo por defecto.");
                            p1 = new PasaporteOrdinario(id, fecha, new Persona(idPersona, null, null), new Pais(codigoPais, null, null), "Viaje");
                            break;
                    }

                    System.out.println(repo.insert(p1));
                    break;

                case 2:
                    System.out.println("Ingrese ID del pasaporte: ");
                    id = sc.nextLine();
                    Pasaporte p2 = repo.findById(id);
                    if (p2 != null) {
                        System.out.println("✅ Pasaporte encontrado: " + p2);
                    } else {
                        System.out.println("No se encontró pasaporte con ese ID");
                    }
                    break;

                case 3:
                    List<Pasaporte> lista = repo.findAll();
                    if (lista.isEmpty()) {
                        System.out.println(" No hay pasaportes registrados.");
                    } else {
                        lista.forEach(System.out::println);
                    }
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

                    System.out.println("Nuevo tipo de pasaporte (1: Ordinario, 2: Diplomático): ");
                    int tipoNuevo = sc.nextInt();
                    sc.nextLine(); // limpiar buffer

                    String motivoNuevo = "";
                    if (tipoNuevo == 1 || tipoNuevo == 2) {
                        System.out.println("Nuevo motivo: ");
                        motivoNuevo = sc.nextLine();
                    }

                    Pasaporte p3;
                    switch (tipoNuevo) {
                        case 1:
                            p3 = new PasaporteOrdinario(idUpdate, fechaNueva, new Persona(idPersonaNueva, null, null), new Pais(codigoPaisNuevo, null, null), motivoNuevo);
                            break;
                        case 2:
                            p3 = new PasaporteDiplomatico(idUpdate, fechaNueva, new Persona(idPersonaNueva, null, null), new Pais(codigoPaisNuevo, null, null), motivoNuevo);
                            break;
                        default:
                            System.out.println("Tipo no válido, se actualizará como Ordinario con motivo por defecto.");
                            p3 = new PasaporteOrdinario(idUpdate, fechaNueva, new Persona(idPersonaNueva, null, null), new Pais(codigoPaisNuevo, null, null), "Viaje");
                            break;
                    }

                    System.out.println(repo.update(p3));
                    break;

                case 5:
                    System.out.println("Ingrese ID del pasaporte a eliminar: ");
                    String idDelete = sc.nextLine();
                    if (repo.delete(idDelete)) {
                        System.out.println(" Pasaporte eliminado correctamente");
                    } else {
                        System.out.println(" No se encontró pasaporte con ese ID");
                    }
                    break;

                case 6:
                    System.out.println("Ingrese criterio de búsqueda (parte del ID): ");
                    String criterio = sc.nextLine();
                    List<Pasaporte> filtrados = repo.findByIdContains(criterio);
                    if (filtrados.isEmpty()) {
                        System.out.println(" No se encontraron pasaportes con el criterio \"" + criterio + "\"");
                    } else {
                        System.out.println(" Pasaportes encontrados:");
                        filtrados.forEach(System.out::println);
                    }
                    break;

                case 0:
                    System.out.println(" Saliendo...");
                    break;

                default:
                    System.out.println(" Opción no válida");
                    break;
            }
        } while (opcion != 0);

        ConexionDB.getInstancia().cerrarConexion();
    }
}
}