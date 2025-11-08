package co.edu.poli.actividad.servicios;

public class Policia implements Suscriptor {

    @Override
    public String recibirNotificacion(String mensaje) {
        return "Policia Nacional" + mensaje;
    }
}
