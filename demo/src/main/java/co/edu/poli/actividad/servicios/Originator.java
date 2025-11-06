package co.edu.poli.actividad.servicios;

public class Originator {
    private PasaporteAdapter estadoActual;

    public void setEstado(PasaporteAdapter estado) {
        this.estadoActual = estado;
    }

    public Memento guardar() {
        return new ConcreteMemento(estadoActual.mostrarInformacionCompleta());
    }

    public void restaurar(Memento memento) {
        System.out.println("Restaurando estado guardado:");
        System.out.println(memento.getEstado());
    }
}
