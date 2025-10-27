package co.edu.poli.actividad.servicios;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {
    private final Map<String, PasaporteTipo> cache = new HashMap<>();
    
    public PasaporteTipo getFlyweight(String paisEmisor) {
        // Si no existe en cache, crear uno nuevo con valores por defecto basados en el país
        if (!cache.containsKey(paisEmisor)) {
            String color = determinarColorPorPais(paisEmisor);
            String idioma = determinarIdiomaPorPais(paisEmisor);
            cache.put(paisEmisor, new PasaporteTipo(color, idioma, paisEmisor));
        }
        return cache.get(paisEmisor);
    }
    
    // Método sobrecargado para especificar color e idioma
    public PasaporteTipo getFlyweight(String paisEmisor, String colorCubierta, String idioma) {
        String clave = paisEmisor + "_" + colorCubierta + "_" + idioma;
        if (!cache.containsKey(clave)) {
            cache.put(clave, new PasaporteTipo(colorCubierta, idioma, paisEmisor));
        }
        return cache.get(clave);
    }
    
    private String determinarColorPorPais(String pais) {
        switch (pais.toLowerCase()) {
            case "colombia": return "Rojo";
            case "usa": return "Azul Marino";
            case "canadá": return "Rojo";
            case "francia": return "Borgoña";
            case "alemania": return "Negro";
            case "japon": return "Azul";
            default: return "Negro";
        }
    }
    
    private String determinarIdiomaPorPais(String pais) {
        switch (pais.toLowerCase()) {
            case "colombia": return "Español";
            case "usa": case "canadá": return "Inglés";
            case "francia": return "Francés";
            case "alemania": return "Alemán";
            case "japon": return "Japonés";
            default: return "Inglés";
        }
    }
    
    public int getCacheSize() {
        return cache.size();
    }
    
    public void mostrarCache() {
        System.out.println("=== Cache de Tipos de Pasaporte ===");
        for (PasaporteTipo tipo : cache.values()) {
            System.out.println("País: " + tipo.getPaisEmisor() + 
                             ", Color: " + tipo.getColorCubierta() + 
                             ", Idioma: " + tipo.getIdioma());
        }
        System.out.println("Total en cache: " + cache.size());
    }
}