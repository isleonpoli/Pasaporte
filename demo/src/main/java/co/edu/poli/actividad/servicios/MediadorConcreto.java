package co.edu.poli.actividad.servicios;

import java.util.ArrayList;
import java.util.List;

public class MediadorConcreto implements Mediador {
    private List<ComponenteEntidad> componentes = new ArrayList<>();

    @Override
    public void registrarComponente(ComponenteEntidad componente) {
        componentes.add(componente);
        componente.setMediador(this);
    }

    @Override
    public void notificar(ComponenteEntidad remitente, String mensaje) {
        for (ComponenteEntidad componente : componentes) {
            // No enviar el mensaje al remitente
            if (componente != remitente) {
                // Aplicar reglas de notificación según el remitente
                if (debeRecibirMensaje(remitente, componente)) {
                    componente.recibirMensaje(remitente.getNombre(), mensaje);
                }
            }
        }
    }

    private boolean debeRecibirMensaje(ComponenteEntidad remitente, ComponenteEntidad receptor) {
        String nombreRemitente = remitente.getNombre();
        String nombreReceptor = receptor.getNombre();

        // Policía notifica a Cancillería y Migración Colombia
        if (nombreRemitente.contains("Policía")) {
            return nombreReceptor.contains("Cancillería") || nombreReceptor.contains("Migración");
        }
        // Cancillería notifica solo a Policía
        else if (nombreRemitente.contains("Cancillería")) {
            return nombreReceptor.contains("Policía");
        }
        // Migración Colombia notifica solo a Cancillería
        else if (nombreRemitente.contains("Migración")) {
            return nombreReceptor.contains("Cancillería");
        }

        return false;
    }

    public String obtenerNotificaciones(ComponenteEntidad remitente, String mensaje) {
        StringBuilder resultado = new StringBuilder();
        
        for (ComponenteEntidad componente : componentes) {
            if (componente != remitente && debeRecibirMensaje(remitente, componente)) {
                resultado.append(componente.getNombre()).append("\n");
            }
        }
        
        return resultado.toString();
    }
}