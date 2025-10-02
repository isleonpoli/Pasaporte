package co.edu.poli.actividad.servicios;

/**
 * Decorador que añade información de Visa.
 */
public class Visa extends BaseDecorator {

    private final String tipoVisa;
    private final String numeroVisa; // opcional

    public Visa(Component componente, String tipoVisa) {
        this(componente, tipoVisa, null);
    }

    public Visa(Component componente, String tipoVisa, String numeroVisa) {
        super(componente);
        this.tipoVisa = tipoVisa;
        this.numeroVisa = numeroVisa;
    }

    @Override
    public String getDescripcion() {
        String base = super.getDescripcion();
        if (numeroVisa != null && !numeroVisa.isEmpty()) {
            return base + " | Visa[tipo=" + tipoVisa + ", numero=" + numeroVisa + "]";
        } else {
            return base + " | Visa[tipo=" + tipoVisa + "]";
        }
    }

    public String getTipoVisa() { return tipoVisa; }
    public String getNumeroVisa() { return numeroVisa; }
}
