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
    StringBuilder sb = new StringBuilder();
    sb.append("ID: ").append(pasaporte.getId()).append("\n");
    sb.append("Fecha de expedición: ").append(pasaporte.getFechaExpedicion()).append("\n");
    sb.append("Titular: ").append(pasaporte.getTitular() != null ? pasaporte.getTitular().getId() : "No asignado").append("\n");
    sb.append("País: ").append(pasaporte.getPais() != null ? pasaporte.getPais().getCodigoISO() : "No asignado").append("\n");

    if (pasaporte instanceof PasaporteOrdinario) {
        PasaporteOrdinario o = (PasaporteOrdinario) pasaporte;
        sb.append("Motivo: ").append(o.getMotivo() != null ? o.getMotivo() : "No asignado").append("\n");
        sb.append("Elemento de seguridad: ").append(o.getElementoSeguridad() != null ? o.getElementoSeguridad() : "No asignado").append("\n");
    } else if (pasaporte instanceof PasaporteDiplomatico) {
        PasaporteDiplomatico d = (PasaporteDiplomatico) pasaporte;
        sb.append("Motivo: ").append(d.getMotivo() != null ? d.getMotivo() : "No asignado").append("\n");
    }

    return sb.toString();
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