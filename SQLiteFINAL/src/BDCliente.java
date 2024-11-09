import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BDCliente {
    public void insertAliasCliente(AliasCliente cliente) {
        String sql = "INSERT INTO Cliente (aliasDEBITO, aliasCREDITO, cbuDEBITO, cbuCREDITO, IMPORTE, CONCEPTO, MOTIVO, REFERENCIA, EMAIL, TITULAR) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getAliasDEBITO());
            pstmt.setString(2, cliente.getAliasCREDITO());
            pstmt.setString(3, cliente.getCbuDEBITO());
            pstmt.setString(4, cliente.getCbuCREDITO());
            pstmt.setDouble(5, cliente.getImporteActual());
            pstmt.setString(6, cliente.getConcepto());
            pstmt.setString(7, cliente.getMotivo());
            pstmt.setString(8, cliente.getReferencia());
            pstmt.setString(9, cliente.getEmail());
            pstmt.setString(10, cliente.getTitular());
            pstmt.executeUpdate();
            System.out.println("Alias guardado en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al insertar en la base de datos: " + e.getMessage());
        }
    }

    public void tablaCliente() {
        String query = "SELECT * FROM Cliente";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Código: " + rs.getString("ID_Cliente") + ", Alias: " + rs.getString("aliasDEBITO"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
