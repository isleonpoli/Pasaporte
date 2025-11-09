package co.edu.poli.actividad.servicios;

public class TarifaOrdinario implements CalculoTarifaStrategy {
    @Override
    public double calcularTarifa() {
        // Tarifa fija o podrías hacerla depender de más datos
        return 150.0;
    }
}
