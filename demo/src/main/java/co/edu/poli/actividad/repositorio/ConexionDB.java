package co.edu.poli.actividad.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    //private static final String URL = "jdbc:postgresql://localhost:5432/tu_base";
    //private static final String USER = "tu_usuario";
    //private static final String PASSWORD = "tu_contraseña";"

    private static final String URL = "jdbc:postgresql://aws-1-us-east-2.pooler.supabase.com:5432/postgres";
    private static final String USER = "postgres.koxggaisjyjtrjyxynsj";
    private static final String PASSWORD = "PasaporteProject";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}