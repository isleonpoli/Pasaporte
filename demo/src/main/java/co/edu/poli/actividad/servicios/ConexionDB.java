package co.edu.poli.actividad.servicios;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {

    private static ConexionDB instancia;
    private Connection connection;

    private String url;
    private String user;
    private String password;

    private ConexionDB() {
        try {
            Properties props = new Properties();

            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            if (input == null) {
                throw new RuntimeException("No se encontró el archivo config.properties en el classpath.");
            }
            props.load(input);

            url = props.getProperty("db.url");

            user = System.getenv("DB_USER");
            password = System.getenv("DB_PASSWORD");

            if (user == null || password == null) {
                throw new RuntimeException("Variables de entorno DB_USER o DB_PASSWORD no están definidas.");
            }

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida con la base de datos.");

        } catch (IOException e) {
            System.err.println("Error al cargar archivo de propiedades: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Error en configuración: " + e.getMessage());
        }
    }

    public static synchronized ConexionDB getInstancia() {
        if (instancia == null) {
            instancia = new ConexionDB();
        }
        return instancia;
    }

    public Connection getConexion() {
        return connection;
    }

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
