package Test2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Test.DatabaseConnection;

public class ExportarArchivo {
    public void writeToFile(String filename) {
        String query = "SELECT * FROM Registro";  // Asegúrate de que la consulta sea la correcta
        StringBuilder datos = new StringBuilder();  // Acumula las líneas de datos a escribir
        double importe = 0.0;  // Total acumulado
        int fila = 1;  // Contador de filas

        try (Connection conexion = DatabaseConnection.connect();
             PreparedStatement pstmt = conexion.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Recupera los datos de la base de datos
                String cbuDEBITO = rs.getString("cbuDEBITO");
                String cbuCREDITO = rs.getString("cbuCREDITO");
                double importeActual = rs.getDouble("IMPORTE"); 
                String concepto = rs.getString("CONCEPTO");
                String motivo = rs.getString("MOTIVO");
                String referencia = rs.getString("REFERENCIA");
                String email = rs.getString("EMAIL");
                String titular = rs.getString("TITULAR");

                // Generar la fila con el formato específico
                String filaData = String.format("%22s%22s"+espacios(44)+"%012d%-50s%-3s%-12s%-50s%-1s\n",
                        cbuDEBITO, cbuCREDITO, (int) (importeActual * 100), concepto, motivo, referencia, email, titular);

                // Acumula la fila
                datos.append(filaData);

                // Acumula el importe total
                importe += importeActual;
                fila++;  // Incrementa el contador de filas
            }

            // Guardar los datos en el archivo
            try (FileOutputStream fos = new FileOutputStream(new File(filename))) {
                fos.write(datos.toString().getBytes());  // Escribe las líneas de datos
                // Al final, escribe el total acumulado
                fos.write(String.format("%05d%017d%194s\n", fila, (int) (importe * 100), "").getBytes());
                System.out.println("Archivo generado exitosamente: " + filename);
            } catch (IOException e) {
                System.out.println("Error al guardar el archivo: " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para agregar espacios
    public static String espacios(int cantidad) {
        return " ".repeat(cantidad); // Desde Java 11
    }
}
