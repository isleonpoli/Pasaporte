package co.edu.poli.actividad.servicios;

/**
 * Decorador base que delega en un Component.
 */
public abstract class BaseDecorator implements Component {

    protected final Component componente;

    public BaseDecorator(Component componente) {
        this.componente = componente;
    }

    @Override
    public String getDescripcion() {
        return componente.getDescripcion();
    }
}
