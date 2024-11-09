package Test;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        //ValidarDatos2 validarDatos2 = new ValidarDatos2();
        FileManager exportarTXT = new FileManager();
        // Llamar al método estático connect
        Connection conexion = DatabaseConnection.connect();
        
        if (conexion != null) {

            System.out.println("Que desea hacer");
            System.out.println("");
            //validarDatos2.validarGeneral();
            exportarTXT.writeToFile("FacturaFinal2.txt");
        } else {
            System.out.println("Error en la conexión a la base de datos.");
        }
    }
}
