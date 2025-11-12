package co.edu.poli.actividad.servicios;

public class MigracionColombia extends ComponenteEntidad {
    
    public MigracionColombia() {
        super("Migración Colombia");
    }

    @Override
    public String recibirNotificacion(String mensaje) {
        return "Migracion Colombia" + mensaje;
    }

    @Override
    public void recibirMensaje(String remitente, String mensaje) {
        System.out.println(getNombre() + " recibió mensaje de " + remitente + ": " + mensaje);
    }
}