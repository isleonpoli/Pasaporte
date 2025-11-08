package co.edu.poli.actividad.servicios;

public class MigracionColombia implements Suscriptor {

    @Override
    public String recibirNotificacion(String mensaje) {
        return "Migracion Colombia" + mensaje;
    }
}
