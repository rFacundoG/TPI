package Test;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FileManager {
    public void writeToFile(String filename) {
        String query = "SELECT * FROM Cliente";
        try (Connection conexion = DatabaseConnection.connect();
            PreparedStatement pstmt = conexion.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            FileWriter writer = new FileWriter(filename)) {
            while (rs.next()) {
                String cbuCREDITO = rs.getString("cbuCREDITO");
                String EMAIL = rs.getString("EMAIL");
                String TITULAR = rs.getString("TITULAR");
                String line = String.format("%-20s %-20s %-12s %-20s %-20s %-20s %-30s %-20s",cbuCREDITO,EMAIL,TITULAR);
                writer.write(line + "\n");
            }
            System.out.println("Datos guardados en el archivo.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String espacios(int cantidad) {
        return " ".repeat(cantidad); // Desde Java 11
    }
}
