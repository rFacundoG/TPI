import java.util.Scanner;

public class ValidarDatos {
    Scanner scanner = new Scanner(System.in);

    public void Atributos(){
        String aliasDEBITO = ValidarAliasDEBITO("ALIAS DEBITO");
        String aliasCREDITO = ValidarAliasCREDITO("ALIAS CREDITO");
        String cbuDEBITO = ValidarCBUdebito("CBU DEBITO");
        String cbuCREDITO = ValidarCBUcredito("CBU CREDITO");
        double importeActual = ValidarIMPORTE(0);
        String concepto = ValidarCONCEPTO("CONCEPTO");
        String motivo = ValidarMOTIVO();  // Llama a ValidarMOTIVO y obtiene el motivo seleccionado
        if (motivo == null) {
            return; // Termina si el usuario elige salir
        }
        String referencia = ValidarREFERENCIA("REFERENCIA");
        String email = ValidarEMAIL("EMAIL");
        String titular = ValidarTITULAR("TITULAR"); 
        
        AliasCliente cliente = new AliasCliente(aliasDEBITO, aliasCREDITO, cbuDEBITO, cbuCREDITO, importeActual, concepto, referencia, email, titular, motivo);
        BDCliente bdCliente = new BDCliente();
        bdCliente.insertAliasCliente(cliente);
    }

    private String ValidarAliasDEBITO(String tipo){
        String aliasDE = "";
        
        while (true) {
            System.out.print("Ingrese ALIAS CBU DEBITO: ");
            aliasDE = scanner.nextLine();
            if (aliasDE.equalsIgnoreCase("salir")) break;
            
            if (aliasDE.length() < 22 && aliasDE.length() != 0) {
                System.out.println("La longitud de la entrada es: " + aliasDE.length());
                System.out.println("Dato ingresado: "+aliasDE);
                break;
            } else if (aliasDE.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else{
                System.out.println("Error: La longitud no puede ser mas de 22 caracteres.");
            }
        }
        return null;
    }

    private String ValidarAliasCREDITO(String tipo){
        String aliasCRE = "";

        while (true) {
            System.out.print("Ingrese ALIAS CBU CREDITO: ");
            aliasCRE = scanner.nextLine();
            if (aliasCRE.equalsIgnoreCase("salir")) break;

            if (aliasCRE.length() < 22 && aliasCRE.length() != 0) {
                System.out.println("La longitud de la entrada es: " + aliasCRE.length());
                System.out.println("Dato ingresado: "+aliasCRE);
                break;
            } else if (aliasCRE.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else {
                System.out.println("Error: La longitud no puede ser mas de 22 caracteres.");
            }
        }
        return null;
    }


    private String ValidarCBUdebito(String tipo){
        String cbuDE = "";

        while (true) {
            System.out.print("Ingrese NUMERO CBU DEBITO: ");
            cbuDE = scanner.nextLine();
            if (cbuDE.equalsIgnoreCase("salir")) break;

            // Verificar si la longitud de la entrada es igual a 22
            if (cbuDE.length() == 22) {
                // Verificar si la entrada es un número
                if (cbuDE.matches("\\d+")) {
                    System.out.println("El número ingresado es: " + cbuDE);
                    break; 
                } else {
                    System.out.println("Error: La entrada debe contener solo números.");
                }
            } else {
                System.out.println("Error: La longitud debe ser igual a 22 caracteres.");
            }
        }
        return null;
    }

    private String ValidarCBUcredito(String tipo){
        String cbuCRE = "";

        while (true) {
            System.out.print("Ingrese NUMERO CBU CREDITO: ");
            cbuCRE = scanner.nextLine();
            if (cbuCRE.equalsIgnoreCase("salir")) break;

            // Verificar si la longitud de la entrada es igual a 22
            if (cbuCRE.length() == 22) {
                // Verificar si la entrada es un número
                if (cbuCRE.matches("\\d+")) {
                    System.out.println("El número ingresado es: " + cbuCRE);
                    break; 
                } else {
                    System.out.println("Error: La entrada debe contener solo números.");
                }
            } else {
                System.out.println("Error: La longitud debe ser igual a 22 caracteres.");
            }
        }
        return null;
    }

    private double ValidarIMPORTE(double tipo){
        double importeActual = 0;

        while (true) {
            System.out.print("Ingrese IMPORTE: ");
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
                    break; 
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada no válida. Debe ser un número.");
            }
        }
        return 0;
    }

    private String ValidarCONCEPTO(String tipo){
        String concepto = "";

        while (true) {
            System.out.print("Ingrese el CONCEPTO: ");
            concepto = scanner.nextLine();

            if (concepto.length() < 50 && concepto.length() != 0) {
                System.out.println("La longitud de la entrada es: " + concepto.length());
                break;
            } else if (concepto.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else {
                System.out.println("Error: En la longitud del CONCEPTO.");
            }
        }
        return concepto;
    }

    public String ValidarMOTIVO(){
        boolean opcionValida = false;
        
        String motivo = "";
        String motivoSeleccionado = "";

        while (!opcionValida) {
            System.out.println("\n1) ALQ (Alquileres)\n2) CUO (Cuotas)\n3) EXP (Expensas)\n4) FAC (Facturas) 5)PRE (Préstamos)\n6) SEG (Seguros)\n7) HON (Honorarios)\n8) VAR (Varios)");
            System.out.print("Seleccione el numero del MOTIVO (o 'salir' para terminar): ");
            
            motivo = scanner.nextLine();

            if (motivo.equals("salir")) {
                System.out.println("Saliendo del programa...");
                return null; 
            }

            switch (motivo) {
                case "1":
                    System.out.println("Haz seleccionado Alquileres.");
                    motivoSeleccionado = "ALQ";
                    opcionValida = true;
                    break;
                case "2":
                    System.out.println("Haz selecionado Cuotas.");
                    motivoSeleccionado = "CUO";
                    opcionValida = true;
                    break;
                case "3":
                    System.out.println("Haz selecionado Expensas.");
                    motivoSeleccionado = "EXP";
                    opcionValida = true;
                    break;
                case "4":
                    System.out.println("Haz selecionado Facturas.");
                    motivoSeleccionado = "FAC";
                    opcionValida = true;
                    break;
                case "5":
                    System.out.println("Haz selecionado Préstamos.");
                    motivoSeleccionado = "PRE";
                    opcionValida = true;
                    break;
                case "6":
                    System.out.println("Haz selecionado Seguros.");
                    motivoSeleccionado = "SEG";
                    opcionValida = true;
                    break;
                case "7":
                    System.out.println("Haz Selecionado Honorarios.");
                    motivoSeleccionado = "HON";
                    opcionValida = true;
                    break;
                case "8":
                    System.out.println("Haz selecionado Varios.");
                    motivoSeleccionado = "VAR";
                    opcionValida = true;
                    break;
                default:
                    System.out.println("Numero no valido, intentelo de nuevo.");
            }
        }
        return motivoSeleccionado;
    }

    private String ValidarREFERENCIA(String tipo){
        String referencia = "";

        while (true) {
            System.out.print("Ingrese la REFERENCIA: ");
            referencia = scanner.nextLine();

            if (referencia.length() < 30 && referencia.length() != 0) {
                System.out.println("La longitud de la entrada es: " + referencia.length());
                break;
            } else if (referencia.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else {
                System.out.println("Error: En la longitud de la REFERENCIA.");
            }
        }
        return null;
    }

    private String ValidarEMAIL(String tipo){
        String email = "";

        while (true) {
            System.out.print("Ingrese el EMAIL: ");
            email = scanner.nextLine();

            if (email.length() < 50 && email.length() != 0) {
                System.out.println("La longitud de la entrada es: " + email.length());
                break;
            } else if (email.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else {
                System.out.println("Error: En la longitud del EMAIL.");
            }
        }
        return null;
    }

    private String ValidarTITULAR(String tipo){
        String titular = "";

        while (true) {
            System.out.print("Ingrese el TITULAR: ");
            titular = scanner.nextLine();

            if (titular.length() < 50 && titular.length() != 0) {
                System.out.println("La longitud de la entrada es: " + titular.length());
                break;
            } else if (titular.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else {
                System.out.println("Error: En la longitud de TITULARES.");
            }
        }
        return null;
    }
}
