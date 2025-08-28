package co.edu.poli.actividad.model;



/**
 * 
 */
public class Ciudad {

    /**
     * Default constructor
     */
    public Ciudad() {
    }

    /**
     * 
     */
    private String codigo;

    /**
     * 
     */
    private String nombre;

    public Ciudad(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre + " (codigo: " + codigo + ")";
    }
}
