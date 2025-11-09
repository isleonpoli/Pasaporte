package co.edu.poli.actividad.servicios;

public class CalculadoraTarifa {
    private CalculoTarifaStrategy estrategia;

    public void setEstrategia(CalculoTarifaStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public double ejecutarCalculo() {
        if (estrategia == null) {
            throw new IllegalStateException("No se ha establecido una estrategia de cálculo.");
        }
        return estrategia.calcularTarifa();
    }
}
