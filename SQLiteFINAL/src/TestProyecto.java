import java.sql.Connection;

public class TestProyecto {
    public static void main(String[] args) {
        ValidarDatos validarDatos = new ValidarDatos();
        //FileManager exportarArchivo = new FileManager();

        // Llamar al método estático connect
        Connection conexion = DatabaseConnection.connect();
        
        if (conexion != null) {
            validarDatos.Atributos();
            // exportarArchivo.writeToFile("Factura4.txt");
        } else {
            System.out.println("Error en la conexión a la base de datos.");
        }
    }
}
