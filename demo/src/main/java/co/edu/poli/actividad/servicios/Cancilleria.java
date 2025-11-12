package co.edu.poli.actividad.servicios;

public class Cancilleria extends ComponenteEntidad {
    
    public Cancilleria() {
        super("Cancillería");
    }

    @Override
    public String recibirNotificacion(String mensaje) {
        return "Cancilleria" + mensaje;
    }

    @Override
    public void recibirMensaje(String remitente, String mensaje) {
        System.out.println(getNombre() + " recibió mensaje de " + remitente + ": " + mensaje);
    }
}