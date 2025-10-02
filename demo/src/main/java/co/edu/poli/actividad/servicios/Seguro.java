package co.edu.poli.actividad.servicios;

/**
 * Decorador que añade información de Seguro.
 */
public class Seguro extends BaseDecorator {

    private final String tipoSeguro;
    private final String poliza; // opcional

    public Seguro(Component componente, String tipoSeguro) {
        this(componente, tipoSeguro, null);
    }

    public Seguro(Component componente, String tipoSeguro, String poliza) {
        super(componente);
        this.tipoSeguro = tipoSeguro;
        this.poliza = poliza;
    }

    @Override
    public String getDescripcion() {
        String base = super.getDescripcion();
        if (poliza != null && !poliza.isEmpty()) {
            return base + " | Seguro[tipo=" + tipoSeguro + ", poliza=" + poliza + "]";
        } else {
            return base + " | Seguro[tipo=" + tipoSeguro + "]";
        }
    }

    public String getTipoSeguro() { return tipoSeguro; }
    public String getPoliza() { return poliza; }
}
