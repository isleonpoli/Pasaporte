package co.edu.poli.actividad.servicios;

public class Policia extends ComponenteEntidad {
    
    public Policia() {
        super("Policía Nacional");
    }

    @Override
    public String recibirNotificacion(String mensaje) {
        return "Policia Nacional" + mensaje;
    }

    @Override
    public void recibirMensaje(String remitente, String mensaje) {
        System.out.println(getNombre() + " recibió mensaje de " + remitente + ": " + mensaje);
    }
}