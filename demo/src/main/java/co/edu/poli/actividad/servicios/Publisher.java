package co.edu.poli.actividad.servicios;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private List<Suscriptor> suscriptores = new ArrayList<>();

    public void agregarSuscriptor(Suscriptor suscriptor) {
        suscriptores.add(suscriptor);
    }

    public void eliminarSuscriptor(Suscriptor suscriptor) {
        suscriptores.remove(suscriptor);
    }

    public String notificar(String mensaje) {
        StringBuilder resultado = new StringBuilder();
        for (Suscriptor s : suscriptores) {
            resultado.append(s.recibirNotificacion(mensaje)).append("\n");
        }
        return resultado.toString();
    }
}
