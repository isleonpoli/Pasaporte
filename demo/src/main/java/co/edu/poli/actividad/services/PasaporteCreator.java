// interface creadora
package co.edu.poli.actividad.services;
import co.edu.poli.actividad.model.Pasaporte;

public interface PasaporteCreator {
    // devuelve instancia vacía que el controlador rellenará
    Pasaporte createPasaporte();
}