package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:D:\\Users\\Facundo\\Documentos\\Git-Repositorio\\SQLiteFINAL\\src\\TPI.sqlite";

    public static Connection connect() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL);
            System.out.println("Conexión establecida.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conexion;
    }
}
