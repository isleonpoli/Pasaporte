package co.edu.poli.actividad.servicios;

public abstract class ComponenteEntidad implements Suscriptor {
    protected Mediador mediador;
    private String nombre;

    public ComponenteEntidad(String nombre) {
        this.nombre = nombre;
    }

    public void setMediador(Mediador mediador) {
        this.mediador = mediador;
    }

    public String getNombre() {
        return nombre;
    }

    public void enviar(String mensaje) {
        if (mediador != null) {
            mediador.notificar(this, mensaje);
        }
    }

    // Método abstracto que cada componente implementa
    public abstract void recibirMensaje(String remitente, String mensaje);
}