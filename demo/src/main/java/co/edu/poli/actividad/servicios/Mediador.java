package co.edu.poli.actividad.servicios;

public interface Mediador {
    void registrarComponente(ComponenteEntidad componente);
    void notificar(ComponenteEntidad remitente, String mensaje);
}