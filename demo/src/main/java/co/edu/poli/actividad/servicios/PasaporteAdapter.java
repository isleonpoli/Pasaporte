package co.edu.poli.actividad.servicios;

import co.edu.poli.actividad.model.Pasaporte;
import co.edu.poli.actividad.model.PasaporteDiplomatico;
import co.edu.poli.actividad.model.PasaporteOrdinario;

public class PasaporteAdapter implements PasaporteInterface {
    final private Pasaporte pasaporte; // Puede ser PasaporteOrdinario o PasaporteDiplomatico
    
    public PasaporteAdapter(Pasaporte pasaporte) {
        this.pasaporte = pasaporte;
    }
    
    @Override
    public String mostrarInformacionCompleta() {
        if (pasaporte instanceof PasaporteOrdinario) {
            PasaporteOrdinario p = (PasaporteOrdinario) pasaporte;
            return "=== INFORMACIÓN COMPLETA ===\n" +
                   "Tipo: Pasaporte Ordinario\n" +
                   "ID: " + p.getId() + "\n" +
                   "Titular: " + p.getTitular().getNombre() + "\n" +
                   "País: " + p.getPais().getNombre() + "\n" +
                   "Fecha Expedición: " + p.getFechaExpedicion() + "\n" +
                   "Motivo: " + p.getMotivo() + "\n" +
                   "Seguridad: " + p.getElementoSeguridad().getClass().getSimpleName();
        } else if (pasaporte instanceof PasaporteDiplomatico) {
            PasaporteDiplomatico p = (PasaporteDiplomatico) pasaporte;
            return "=== INFORMACIÓN COMPLETA ===\n" +
                   "Tipo: Pasaporte Diplomático\n" +
                   "ID: " + p.getId() + "\n" +
                   "Titular: " + p.getTitular().getNombre() + "\n" +
                   "País: " + p.getPais().getNombre() + "\n" +
                   "Fecha Expedición: " + p.getFechaExpedicion() + "\n" +
                   "Motivo: " + p.getMotivo() + "\n" +
                   "Seguridad: " + p.getElementoSeguridad().getClass().getSimpleName();
        }
        return "Tipo de pasaporte no reconocido";
    }
    
    @Override
    public String mostrarInformacionBasica() {
        if (pasaporte instanceof PasaporteOrdinario) {
            PasaporteOrdinario p = (PasaporteOrdinario) pasaporte;
            return "ID: " + p.getId() + "\n" + 
                   "Titular: " + p.getTitular().getNombre() + "\n" + 
                   "Pais: " + p.getPais().getNombre();
        } else if (pasaporte instanceof PasaporteDiplomatico) {
            PasaporteDiplomatico p = (PasaporteDiplomatico) pasaporte;
            return "ID: " + p.getId() + "\n" + 
                   "Titular: " + p.getTitular().getNombre() + "\n" + 
                   "Pais: " + p.getPais().getNombre();
        }
        return "Información no disponible";
    }
    
    
}