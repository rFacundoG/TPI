package Test2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Test.DatabaseConnection;


public class GuardarBD {
    public void insertTITULAR(Datos cliente){
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

    public void insertCuenta(Datos cuenta){
        String cnosulta = "INSERT INTO Cliente (alias, cbu, PROPIO) VALUES (?, ?, ?)";

        try (Connection coexion = DatabaseConnection.connect();
            PreparedStatement pstmt = coexion.prepareStatement(cnosulta)){
            pstmt.setString(1, cuenta.getAlias());
            pstmt.setString(2, cuenta.getCbu());
            pstmt.executeUpdate();
            System.out.println("Informacion guardada en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al insertar en la base de datos: " + e.getMessage());
        }
    }

    public void intertRegistroTitularCBU(Datos cliente){
        String consulta = "INSERT INTO Registro (cbuCREDITO, EMAIL, TITULAR) VALUES (?, ?, ?)";
        try (Connection conexion = DatabaseConnection.connect();
            PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
            pstmt.setString(1, cliente.getCbu());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getTitular());
            pstmt.executeUpdate();
            System.out.println("Informacion guardada en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al insertar en la base de datos: " + e.getMessage());
        }
    }

    public void intertRegistroTitularALIAS(Datos cliente){
        String consulta = "INSERT INTO Registro (aliasCREDITO, EMAIL, TITULAR) VALUES (?, ?, ?)";
        try (Connection conexion = DatabaseConnection.connect();
            PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
            pstmt.setString(1, cliente.getAlias());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getTitular());
            pstmt.executeUpdate();
            System.out.println("Informacion guardada en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al insertar en la base de datos: " + e.getMessage());
        }
    }

    public void insertRegistroTrasferencia(DatosTrasferencia operar) {
        // Suponiendo que el campo "REFERENCIA" es único y lo usamos como criterio de búsqueda
        String consulta = "UPDATE Registro SET IMPORTE = ?, CONCEPTO = ?, MOTIVO = ? WHERE REFERENCIA = ?";
        try (Connection conexion = DatabaseConnection.connect();
             PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
            
            // Establecer los valores en el PreparedStatement
            pstmt.setDouble(1, operar.getImporte());
            pstmt.setString(2, operar.getConcepto());
            pstmt.setString(3, operar.getMotivo());
            pstmt.setString(4, operar.getReferencia());
    
            // Ejecutar la actualización
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                System.out.println("Información actualizada en la base de datos.");
            } else {
                System.out.println("No se encontró un registro con esa referencia.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar en la base de datos: " + e.getMessage());
        }
    }



    public void buscarPorAlias(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ALIAS a buscar: ");
        String aliasBuscado = scanner.nextLine();

        String consulta = "SELECT * FROM Cliente WHERE aliasCREDITO = ?";

        try (Connection conexion = DatabaseConnection.connect(); 
            PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
        
            pstmt.setString(1, aliasBuscado);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String titular = rs.getString("TITULAR");
                String email = rs.getString("EMAIL");
                String alias = rs.getString("aliasCREDITO");

                System.out.println("ALIAS encontrado: " + alias); 
                System.out.println("ALIAS: " + alias + ", Email: " + email + ", Titular: " + titular);
            } else {
                System.out.println("ALIAS no encontrado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    public void buscarPorCBU(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el CBU a buscar: ");
        String aliasBuscado = scanner.nextLine();

        String consulta = "SELECT * FROM Cliente WHERE cbuDEBITO = ?";

        try (Connection conexion = DatabaseConnection.connect(); 
            PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
        
            pstmt.setString(1, aliasBuscado);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("ALIAS encontrado: " + rs.getString("aliasDEBITO")); 
            } else {
            System.out.println("ALIAS no encontrado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    public List<Datos> buscarClientesPorTitular(String titularBuscado) {
        List<Datos> clientes = new ArrayList<>();
        String query = "SELECT cbu, alias, EMAIL, TITULAR, PROPIO FROM Cliente WHERE TITULAR LIKE ?";
    
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            // El signo '%' permite la búsqueda de coincidencias parciales
            stmt.setString(1, "%" + titularBuscado + "%");
    
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String cbu = rs.getString("cbu");
                    String alias = rs.getString("alias");
                    String email = rs.getString("EMAIL");
                    String titular = rs.getString("TITULAR");
                    boolean propio = rs.getBoolean("PROPIO");
                    Datos cliente = new Datos(cbu, alias, email, titular, propio);
                    clientes.add(cliente);
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return clientes;
    }

    public void actualizarEnRegistro(Datos cliente) {
        // Consulta SQL para actualizar los campos
        String query = "UPDATE Registro SET cbuCREDITO = ?, EMAIL = ?, TITULAR = ? WHERE cbuDEBITO = ?";
    
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            // Establecer los parámetros de la consulta
            stmt.setString(1, cliente.getAlias());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTitular());
            stmt.setString(4, cliente.getCbu());
    
            // Ejecutar la actualización
            int rowsAffected = stmt.executeUpdate();
            
            // Verificar si se actualizó algún registro
            if (rowsAffected > 0) {
                System.out.println("Cliente actualizado en Registro: " + cliente.getTitular());
            } else {
                System.out.println("No se encontró un registro con el cbuDEBITO: " + cliente.getCbu());
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public List<Datos> obtenerClientesPropioTrue() {
        List<Datos> clientes = new ArrayList<>();
        String query = "SELECT cbu, alias, EMAIL, TITULAR, propio FROM Cliente WHERE propio = 1";  // Propio = true
    
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
    
            while (rs.next()) {
                String cbu = rs.getString("cbu");
                String alias = rs.getString("alias");
                String email = rs.getString("EMAIL");
                String titular = rs.getString("TITULAR");
                boolean propio = rs.getBoolean("PROPIO");
    
                Datos cliente = new Datos(cbu, alias, email, titular, propio);
                clientes.add(cliente);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return clientes;
    }

    public List<Datos> obtenerClientesPropioFalse() {
        List<Datos> clientes = new ArrayList<>();
        String query = "SELECT cbu, alias, EMAIL, TITULAR, propio FROM Cliente WHERE propio = 0";  // Propio = false
    
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
    
            while (rs.next()) {
                String cbu = rs.getString("cbu");
                String alias = rs.getString("alias");
                String email = rs.getString("EMAIL");
                String titular = rs.getString("TITULAR");
                boolean propio = rs.getBoolean("PROPIO");
    
                Datos cliente = new Datos(cbu, alias, email, titular, propio);
                clientes.add(cliente);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return clientes;
    }

    public void insertarEnRegistro(Datos clienteDebito, Datos clienteCredito, DatosTrasferencia operar) {
        String query = "INSERT INTO Registro (aliasDEBITO, aliasCREDITO, cbuDEBITO, cbuCREDITO, EMAIL, TITULAR, IMPORTE, CONCEPTO, MOTIVO, REFERENCIA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Si clienteDebito tiene "propio" true (1), insertamos el alias en aliasDEBITO
            if (clienteDebito.isPropio()) {
                stmt.setString(1, clienteDebito.getCbu()); // aliasDEBITO
            } else {
                stmt.setString(1, null); // Si no es propio, no se inserta aliasDEBITO
            }

            // Si clienteCredito tiene "propio" false (0), insertamos el alias en aliasCREDITO
            if (clienteCredito.isPropio() == false) {
                stmt.setString(2, clienteCredito.getCbu()); // aliasCREDITO
                stmt.setString(5, clienteCredito.getEmail()); // email
                stmt.setString(6, clienteCredito.getTitular()); // titular
            } else {
                stmt.setString(2, null); // No se inserta aliasCREDITO si el cliente es propio
                stmt.setString(5, null); // No se inserta email si el cliente es propio
                stmt.setString(6, null); // No se inserta titular si el cliente es propio
            }

            // Si el clienteDebito tiene "propio" true, se asigna el cbuDEBITO
            if (clienteDebito.isPropio()) {
                stmt.setString(3, clienteDebito.getCbu()); // cbuDEBITO
            } else {
                stmt.setString(3, null); // No se inserta cbuDEBITO si no es propio
            }

            // Si el clienteCredito tiene "propio" false, se asigna el cbuCREDITO
            if (clienteCredito.isPropio() == false) {
                stmt.setString(4, clienteCredito.getCbu()); // cbuCREDITO
            } else {
                stmt.setString(4, null); // No se inserta cbuCREDITO si el cliente es propio
            }

            stmt.setDouble(7, operar.getImporte());
            stmt.setString(8, operar.getConcepto());
            stmt.setString(9, operar.getMotivo());
            stmt.setString(10, operar.getReferencia());

            // Ejecutar la inserción
            stmt.executeUpdate();
            System.out.println("Registro insertado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRegistroDesdeCliente(Datos datosCliente, DatosTrasferencia datosTrasferencia) {
        String sql = "INSERT INTO Registro (alias, cbu, email, titular, propio, importe, concepto, motivo, referencia) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.connect();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, datosCliente.getAlias());
            pstmt.setString(2, datosCliente.getCbu());
            pstmt.setString(3, datosCliente.getEmail());
            pstmt.setString(4, datosCliente.getTitular());
            pstmt.setBoolean(5, datosCliente.isPropio());
            pstmt.setDouble(6, datosTrasferencia.getImporte());
            pstmt.setString(7, datosTrasferencia.getConcepto());
            pstmt.setString(8, datosTrasferencia.getMotivo());
            pstmt.setString(9, datosTrasferencia.getReferencia());

            pstmt.executeUpdate();
            System.out.println("Registro insertado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al insertar en Registro.");
        }
    }

    public void buscarPorEmail(){

    }

    public void buscarPorTitular(){

    }
}
