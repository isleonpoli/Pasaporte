package co.edu.poli.actividad.servicios;

import co.edu.poli.actividad.model.PasaporteDiplomatico;
import co.edu.poli.actividad.model.PasaporteOrdinario;

public class PasaporteTipo {
    private String colorCubierta;
    private String idioma;
    private String paisEmisor;
    
    public PasaporteTipo(String colorCubierta, String idioma, String paisEmisor) {
        this.colorCubierta = colorCubierta;
        this.idioma = idioma;
        this.paisEmisor = paisEmisor;
    }
    
    public void mostrarDetalles(Object pasaporte) {
        String numero = "";
        String titular = "";
        String tipo = "";
        String seguridad = "";
        
        if (pasaporte instanceof PasaporteOrdinario) {
            PasaporteOrdinario p = (PasaporteOrdinario) pasaporte;
            numero = p.getId();
            titular = p.getTitular().getNombre();
            tipo = "Pasaporte Ordinario";
            seguridad = p.getElementoSeguridad().getClass().getSimpleName();
        } else if (pasaporte instanceof PasaporteDiplomatico) {
            PasaporteDiplomatico p = (PasaporteDiplomatico) pasaporte;
            numero = p.getId();
            titular = p.getTitular().getNombre();
            tipo = "Pasaporte Diplomático";
            seguridad = p.getElementoSeguridad().getClass().getSimpleName();
        }
        
        System.out.println("=== Detalles del Pasaporte ===");
        System.out.println("Tipo: " + tipo);
        System.out.println("Número: " + numero);
        System.out.println("Titular: " + titular);
        System.out.println("Seguridad: " + seguridad);
        System.out.println("--- Detalles del Tipo ---");
        System.out.println("País Emisor: " + paisEmisor);
        System.out.println("Color Cubierta: " + colorCubierta);
        System.out.println("Idioma: " + idioma);
        System.out.println("===========================");
    }

    // Getters
    public String getColorCubierta() { return colorCubierta; }
    public String getIdioma() { return idioma; }
    public String getPaisEmisor() { return paisEmisor; }
}