package Test3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Test.DatabaseConnection;


public class GuardarBD2 {
    public void insertTITULAR(Datos2 cliente){
        String consulta = "INSERT INTO Cliente (alias, cbu, EMAIL, TITULAR, PROPIO) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion = DatabaseConnection.connect();
            PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
            pstmt.setString(1, cliente.getAlias());
            pstmt.setString(2, cliente.getCbu());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setString(4, cliente.getTitular());
            // Convertir el booleano 'propio' a 1 o 0
            pstmt.setInt(5, cliente.isPropio() ? 1 : 0);  // Si 'propio' es true, ponemos 1; si es false, ponemos 0
            pstmt.executeUpdate();
            System.out.println("Informacion guardada en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al insertar en la base de datos: " + e.getMessage());
        }
    }
}
