package co.edu.poli.actividad.model;

public class Persona implements Prototype {

    private String id;
    private String nombre;
    private String fechaNacimiento;

    public Persona(String id, String nombre, String fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    // Implementación del patrón Prototype
    @Override
    public Persona clone() {
        try {
            return (Persona) super.clone(); // clonación superficial
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar la persona", e);
        }
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                '}';
    }
}
