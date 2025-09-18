
// creador concreto para diplomático
package co.edu.poli.actividad.services;
import co.edu.poli.actividad.model.PasaporteDiplomatico;
import co.edu.poli.actividad.model.Pasaporte;

public class PasaporteDiplomaticoCreator implements PasaporteCreator {
    @Override
    public Pasaporte createPasaporte() {
        return new PasaporteDiplomatico();
    }
}