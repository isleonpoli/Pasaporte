// creador concreto para ordinal
package co.edu.poli.actividad.services;
import co.edu.poli.actividad.model.PasaporteOrdinario;
import co.edu.poli.actividad.model.Pasaporte;

public class PasaporteOrdinarioCreator implements PasaporteCreator {
    @Override
    public Pasaporte createPasaporte() {
        return new PasaporteOrdinario();
    }
}