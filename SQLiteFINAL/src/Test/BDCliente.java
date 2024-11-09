package Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BDCliente {
    public void insertAliasCliente(AliasCliente cliente) {
        String consulta = "INSERT INTO Cliente (aliasDEBITO, aliasCREDITO, cbuDEBITO, cbuCREDITO, IMPORTE, CONCEPTO, MOTIVO, REFERENCIA, EMAIL, TITULAR) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conexion = DatabaseConnection.connect();
            PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
            pstmt.setString(1, cliente.getAliasDEBITO());
            pstmt.setString(2, cliente.getAliasCREDITO());
            pstmt.setString(3, cliente.getCbuDEBITO());
            pstmt.setString(4, cliente.getCbuCREDITO());
            pstmt.setDouble(5, cliente.getImporte());
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

    public void buscarNombre(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre a buscar: ");
        String nombreBuscado = scanner.nextLine();

        String consulta = "SELECT * FROM Cliente WHERE Nombre = ?";

        try (Connection conexion = DatabaseConnection.connect(); 
            PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
        
            pstmt.setString(1, nombreBuscado);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Nombre encontrado: " + rs.getString("Nombre")); 
            } else {
            System.out.println("Nombre no encontrado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    public void buscarApellido() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el apellido a buscar: ");
        String apellidoBuscado = scanner.nextLine();
    
        String sql = "SELECT * FROM Cliente WHERE Apellido = ?"; 
    
        try (Connection conexion = DatabaseConnection.connect(); 
            PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            
            pstmt.setString(1, apellidoBuscado);
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                System.out.println("Apellido encontrado: " + rs.getString("apellido"));
            } else {
                System.out.println("Apellido no encontrado.");
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.close();
    }

    // Mal revisar.
    public void buscarAlias() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("¿Que Alias buscara DEBITO o CREDITO? (DEB/CRE)");
        String tipoAlias = scanner.nextLine();
    
        String consulta = "";
        String aliasBuscado = "";
    
        if (tipoAlias.equalsIgnoreCase("DEB")) {
            consulta = "SELECT * FROM Cliente WHERE aliasDEBITO = ?";
            System.out.print("Ingrese el alias DEBITO a buscar: ");
            aliasBuscado = scanner.nextLine();
        } else if (tipoAlias.equalsIgnoreCase("CRE")) {
            consulta = "SELECT * FROM Cliente WHERE aliasCREDITO = ?";
            System.out.print("Ingrese el alias CREDITO a buscar: ");
            aliasBuscado = scanner.nextLine();
        } else {
            System.out.println("Tipo de alias no válido.");
            scanner.close();
            return; 
        }
    
        try (Connection conexion = DatabaseConnection.connect();
            PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
            
            pstmt.setString(1, aliasBuscado);
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                if (tipoAlias.equalsIgnoreCase("DEB")) {
                    System.out.println("Alias DEBITO encontrado: " + rs.getString("aliasDEBITO"));
                } else {
                    System.out.println("Alias CREDITO encontrado: " + rs.getString("aliasCREDITO"));
                }
            } else {
                System.out.println("Alias no encontrado.");
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        } 
        scanner.close();
    }
}
