package co.edu.poli.actividad.servicios;

public class ContextoEstado {
    private Estado estadoActual;

    public ContextoEstado() {
        this.estadoActual = new EstadoNormal(); // estado inicial
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void cambiarEstado(Estado nuevo) {
        this.estadoActual = nuevo;
    }
}
