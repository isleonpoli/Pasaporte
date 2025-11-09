package co.edu.poli.actividad.servicios;

public abstract class Manejador {
    
    protected Manejador siguiente;

    public void setSiguiente(Manejador siguiente) {
        this.siguiente = siguiente;
    }

    public abstract boolean procesar(int id, int antecedentes, int generacion);
}
