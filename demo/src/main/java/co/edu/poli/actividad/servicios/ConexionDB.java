package co.edu.poli.actividad.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static ConexionDB instancia;   // Singleton
    private Connection connection;         // Conexión única

    // Cambia los datos según tu BD
    private final String url = "jdbc:postgresql://aws-1-us-east-2.pooler.supabase.com:5432/postgres";
    private final String user = "postgres.koxggaisjyjtrjyxynsj";
    private final String password = "PasaporteProject";

    // Constructor privado
    private ConexionDB() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida con la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    // Punto de acceso único
    public static synchronized ConexionDB getInstancia() {
        if (instancia == null) {
            instancia = new ConexionDB();
        }
        return instancia;
    }

    // Retorna la conexión única
    public Connection getConexion() {
        return connection;
    }

    // Cerrar la conexión manualmente al final del programa
    public void cerrarConexion() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
