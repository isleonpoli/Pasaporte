package co.edu.poli.actividad.servicios;

public class TarifaDiplomatico implements CalculoTarifaStrategy {
    @Override
    public double calcularTarifa() {
        // Los diplomáticos no pagan
        return 0.0;
    }
}
