package Test2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Transferencia {
    Scanner scanner = new Scanner(System.in);

    public void cargarTrasferencia(){
        Scanner scanner = new Scanner(System.in);
        GuardarBD clienteDAO = new GuardarBD();

        // Paso 1: Listar los clientes con propio=true para seleccionar el DEBITO
        List<Datos> clientesPropioTrue = clienteDAO.obtenerClientesPropioTrue();
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
                Datos clienteDebito = clientesPropioTrue.get(seleccionDebito - 1);

                // Paso 2: Seleccionar cliente CREDITO
                System.out.println("Seleccione la opción para elegir el CREDITO:");
                System.out.println("1. Listar clientes con cuenta no propia (propio=false)");
                System.out.println("2. Buscar por titular");
                int opcionCredito = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                List<Datos> clientesCredito = new ArrayList<>();

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
                        Datos clienteCredito = clientesCredito.get(seleccionCredito - 1);
                        Transferencia operacion = new Transferencia();
                        DatosTrasferencia operar = operacion.crearDatosTrasferencia();

                        clienteDAO.insertarEnRegistro(clienteDebito, clienteCredito, operar);
                        

                        // Paso 4: Insertar en la tabla Registro
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

    public DatosTrasferencia crearDatosTrasferencia() {
        double importe = validarIMPORTE("Ingrese el IMPORTE: ");
        String concepto = validarCONCEPTO("Ingrese el CONCEPTO: ");
        String motivo = validarMOTIVO("Ingrese el MOTIVO: ");
        String referencia = validarREFERENCIA("Ingrese la REFERENCIA: ");
        return new DatosTrasferencia(importe, concepto, motivo, referencia);
    }

    private double validarIMPORTE(String mensaje){
        double importeActual = 0;

        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
                continue; // Volver al inicio del bucle
            }

            try {
                importeActual = Double.parseDouble(input);
                
                if (importeActual <= 0) {
                    System.out.println("Error: el IMPORTE no puede ser igual o menor a 0.");
                } else {
                    System.out.println("El IMPORTE ingresado es: " + importeActual);
                    return importeActual; 
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada no válida. Debe ser un número.");
            }
        }
    }

    private String validarCONCEPTO(String mensaje){
        String conceptoValido = "";

        while (true) {
            System.out.print("Ingrese el CONCEPTO: ");
            conceptoValido = scanner.nextLine();

            if (conceptoValido.length() < 50 && conceptoValido.length() != 0) {
                System.out.println("La longitud de la entrada es: " + conceptoValido.length());
                System.out.println("El dato ingresado es: "+conceptoValido);
                return conceptoValido;
            } else if (conceptoValido.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else {
                System.out.println("Error: En la longitud del CONCEPTO.");
            }
        }
    }

    public String validarMOTIVO(String mensaje) {
        final String[] MOTIVOS = {
            "ALQ (Alquileres)", 
            "CUO (Cuotas)", 
            "EXP (Expensas)", 
            "FAC (Facturas)", 
            "PRE (Préstamos)", 
            "SEG (Seguros)", 
            "HON (Honorarios)", 
            "VAR (Varios)"
        };
        
        String motivoSeleccionado = null;
        
        while (true) {
            System.out.println("\nSeleccione el número del MOTIVO (o 'salir' para terminar): ");
            for (int i = 0; i < MOTIVOS.length; i++) {
                System.out.println((i + 1) + ") " + MOTIVOS[i]);
            }
            
            String input = scanner.nextLine();
    
            if (input.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa...");
                return null; 
            }
    
            try {
                int opcion = Integer.parseInt(input);
                if (opcion >= 1 && opcion <= MOTIVOS.length) {
                    motivoSeleccionado = MOTIVOS[opcion - 1].substring(0, 3); // Toma las primeras 3 letras
                    System.out.println("Has seleccionado " + MOTIVOS[opcion - 1]);
                    return motivoSeleccionado;
                } else {
                    System.out.println("Número no válido, inténtelo de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada no válida. Debe ser un número.");
            }
        }
    }
    

    private String validarREFERENCIA(String mensaje){
        String referenciaValido = "";

        while (true) {
            System.out.print("Ingrese la REFERENCIA: ");
            referenciaValido = scanner.nextLine();

            if (referenciaValido.length() < 30 && referenciaValido.length() != 0) {
                System.out.println("La longitud de la entrada es: " + referenciaValido.length());
                return referenciaValido;
            } else if (referenciaValido.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else {
                System.out.println("Error: En la longitud de la REFERENCIA.");
            }
        }
    }
}
