package Test3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Test2.GuardarBD;
import Test2.Transferencia;

public class Transferencia2 {
    public void cargarTrasferencia() {
    Scanner scanner = new Scanner(System.in);
    GuardarBD clienteDAO = new GuardarBD();

    // Paso 1: Listar los clientes con propio=true para seleccionar el DEBITO
    List<Datos2> clientesPropioTrue = clienteDAO.obtenerClientesPropioTrue();
    if (clientesPropioTrue.isEmpty()) {
        System.out.println("No hay clientes con cuenta propia (DEBITO).");
    } else {
        System.out.println("Clientes disponibles para DEBITO (propio=true):");
        for (int i = 0; i < clientesPropioTrue.size(); i++) {
            System.out.println((i + 1) + ". " + clientesPropioTrue.get(i));
        }

        System.out.print("Seleccione el número del cliente para DEBITO: ");
        int seleccionDebito = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        if (seleccionDebito >= 1 && seleccionDebito <= clientesPropioTrue.size()) {
            Datos2 clienteDebito = clientesPropioTrue.get(seleccionDebito - 1);

            // Paso 2: Seleccionar cliente CREDITO
            System.out.println("Seleccione la opción para elegir el CREDITO:");
            System.out.println("1. Listar clientes con cuenta no propia (propio=false)");
            System.out.println("2. Buscar por titular");
            int opcionCredito = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            List<Datos2> clientesCredito = new ArrayList<>();

            switch (opcionCredito) {
                case 1:
                    // Listar clientes con propio=false
                    clientesCredito = clienteDAO.obtenerClientesPropioFalse();
                    if (clientesCredito.isEmpty()) {
                        System.out.println("No hay clientes con cuenta no propia (CREDITO).");
                    } else {
                        System.out.println("Clientes disponibles para CREDITO (propio=false):");
                        for (int i = 0; i < clientesCredito.size(); i++) {
                            System.out.println((i + 1) + ". " + clientesCredito.get(i));
                        }
                    }
                    break;

                case 2:
                    // Buscar por titular
                    System.out.print("Ingrese el titular a buscar: ");
                    String titularBuscado = scanner.nextLine().toLowerCase();  // Convertir a minúsculas para búsqueda insensible a mayúsculas
                    clientesCredito = clienteDAO.buscarClientesPorTitular(titularBuscado);

                    if (clientesCredito.isEmpty()) {
                        System.out.println("No se encontraron clientes con ese titular.");
                    } else {
                        System.out.println("Clientes encontrados para CREDITO:");
                        for (int i = 0; i < clientesCredito.size(); i++) {
                            System.out.println((i + 1) + ". " + clientesCredito.get(i));
                        }
                    }
                    break;

                default:
                    System.out.println("Opción no válida.");
                    return;
            }

            // Paso 3: Seleccionar el cliente CREDITO
            if (!clientesCredito.isEmpty()) {
                System.out.print("Seleccione el número del cliente para CREDITO: ");
                int seleccionCredito = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                if (seleccionCredito >= 1 && seleccionCredito <= clientesCredito.size()) {
                    Datos2 clienteCredito = clientesCredito.get(seleccionCredito - 1);

                    // Paso 4: Pedir los datos de la transferencia (importe, concepto, motivo, referencia)
                    double importe = validarIMPORTE();
                    String concepto = validarCONCEPTO("Ingrese el CONCEPTO: ");
                    String motivo = validarMOTIVO();
                    String referencia = validarREFERENCIA("Ingrese la REFERENCIA: ");
                    
                    // Crear el objeto Transferencia con toda la información
                    Datos2 transferencia = new Datos2(
                        clienteDebito.getCbu(), clienteCredito.getCbu(),
                        clienteDebito.getEmail(), clienteCredito.getEmail(),
                        clienteDebito.getTitular(), clienteCredito.getTitular(),
                        importe, concepto, motivo, referencia
                    );
                    
                    // Paso 5: Insertar la transferencia en la base de datos
                    clienteDAO.insertarTransferencia(transferencia);
                    System.out.println("Transferencia registrada correctamente.");
                } else {
                    System.out.println("Selección inválida para CREDITO.");
                }
            }

        } else {
            System.out.println("Selección inválida para DEBITO.");
        }
    }
    scanner.close();
}
}
