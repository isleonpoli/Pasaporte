package co.edu.poli.actividad.servicios;

public class Cancilleria implements Suscriptor {

    @Override
    public String recibirNotificacion(String mensaje) {
        return "Cancilleria" + mensaje;
    }
}
