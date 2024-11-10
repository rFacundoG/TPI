package Test2;


import java.sql.Connection;
import java.util.Scanner;

import Test.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //System.out.println("-----------------");
        //System.out.println("¿Que desea hacer?");
        //System.out.println("-----------------");
        //System.out.println("1) Ingresar un Nuevo Titular");
        //System.out.println("2) Hacer una Trasnferencia");
        ExportarArchivo archivo = new ExportarArchivo();
        Titular titularNew = new Titular();
        Transferencia transferencia = new Transferencia();
        Connection conexion = DatabaseConnection.connect();
        
        if (conexion != null) {
            System.out.println("Que desea hacer");
            //titularNew.validarTITULARnuevo();
            //transferencia.cargarTrasferencia();
            archivo.writeToFile("Terminamos2.txt");
        } else {
            System.out.println("Error en la conexión a la base de datos.");
        }

        /*System.out.println("Ingres el importe: ");
        long importeActual = scanner.nextLong();

        if (importeActual <= 0) {
            System.out.println("Error: el IMPORTE no puede ser igual o menor a 0.");
        } else {
            System.out.println("El IMPORTE ingresado es: " + importeActual);
        }*/

    /*while (true) {
        System.out.print("Ingrese IMPORTE: ");
        
        if (!scanner.hasNextLong()) {
            System.out.println("Error: Entrada no válida. Debe ser un número entero.");
            scanner.next(); // Limpiar el buffer
            continue; // Volver al inicio del bucle
        }

        importeActual2 = scanner.nextLong();
        
        if (importeActual2 <= 0) {
            System.out.println("Error: el IMPORTE no puede ser igual o menor a 0.");
        } else {
            System.out.println("El IMPORTE ingresado es: " + importeActual2);
            return; 
        }
    }*/
    scanner.close();
    }
}
