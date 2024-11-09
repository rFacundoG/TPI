package Test3;

import java.util.Scanner;

public class Titular2 {
    Scanner scanner = new Scanner(System.in);

    public void validarTITULARnuevo() {
        String alias = "";
        String cbu = "";
        boolean propio = false;  // Cambiado a booleano

        double importe = 0;
        String concepto = "";
        String motivo = "";
        String referencia = "";
    
        while (true) {
            System.out.print("¿Es una cuenta propia? (si/no): ");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("salir")){break;}
            
            if (respuesta.equalsIgnoreCase("si")) {
                propio = true;  // Si es cuenta propia
                // Elegir entre Alias o CBU
                while (true) {
                    System.out.print("¿Qué deseas elegir? (Alias/CBU): ");
                    String eleccion = scanner.nextLine();
                    
                    if (eleccion.equalsIgnoreCase("alias")) {
                        alias = validarAlias("Ingrese ALIAS: ");
                        break; 
                    } else if (eleccion.equalsIgnoreCase("cbu")) {
                        cbu = validarCBU("Ingrese NUMERO CBU: ");
                        break;
                    } else {
                        System.out.println("Opción no válida. Por favor, elige Alias o CBU.");
                    }
                }
                break;  // Salir del bucle una vez que se haya elegido 'Alias' o 'CBU'
            } else if (respuesta.equalsIgnoreCase("no")) {
                propio = false;  // Si no es cuenta propia
                // Elegir entre Alias o CBU
                while (true) {
                    System.out.print("¿Qué deseas elegir? (Alias/CBU): ");
                    String eleccion = scanner.nextLine();
                    
                    if (eleccion.equalsIgnoreCase("alias")) {
                        alias = validarAlias("Ingrese ALIAS: ");
                        break; 
                    } else if (eleccion.equalsIgnoreCase("cbu")) {
                        cbu = validarCBU("Ingrese NUMERO CBU: ");
                        break;
                    } else {
                        System.out.println("Opción no válida. Por favor, elige Alias o CBU.");
                    }
                }
                break;  // Salir del bucle una vez que se haya elegido 'Alias' o 'CBU'
            } else {
                System.out.println("Respuesta no válida. Por favor, responde 'si' o 'no'.");
            }
        }
    
        
        String email = validarEMAIL("Ingrese el EMAIL: ");
        String titular = validarTITULAR("Ingrese el TITULAR: ");
    
        Datos2 cliente = new Datos2(alias, cbu, email, titular, propio, importe, concepto, motivo, referencia);
        GuardarBD2 BDguardar = new GuardarBD2();
        BDguardar.insertTITULAR(cliente);
    }

    private String validarAlias(String mensaje) {
        String alias = "";
        while (true) {
            System.out.print(mensaje);
            alias = scanner.nextLine();
            if (alias.equalsIgnoreCase("salir")) return alias;
    
            if (alias.length() < 22 && !alias.isEmpty()) {
                System.out.println("La longitud de la entrada es: " + alias.length());
                System.out.println("El ALIAS ingresado es: " + alias);
                return alias;
            } else if (alias.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else {
                System.out.println("Error: La longitud no puede ser más de 22 caracteres.");
            }
        }
    }
    
    private String validarCBU(String mensaje) {
        String cbu = "";
        while (true) {
            System.out.print(mensaje);
            cbu = scanner.nextLine();
            if (cbu.equalsIgnoreCase("salir")) return cbu;
    
            if (cbu.length() == 22) {
                if (cbu.matches("\\d+")) {
                    System.out.println("El número ingresado es: " + cbu);
                    return cbu; 
                } else {
                    System.out.println("Error: La entrada debe contener solo números.");
                }
            } else {
                System.out.println("Error: La longitud debe ser igual a 22 caracteres.");
            }
        }
    }

    private String validarEMAIL(String mensaje){
        String emailValido = "";

        while (true) {
            System.out.print(mensaje);
            emailValido = scanner.nextLine();

            if (emailValido.length() < 50 && emailValido.length() != 0) {
                System.out.println("La longitud de la entrada es: " + emailValido.length());
                System.out.println("El EMAIL ingresado es: " + emailValido);
                return emailValido;
            } else if (emailValido.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else {
                System.out.println("Error: En la longitud del EMAIL.");
            }
        }
    }

    private String validarTITULAR(String mensaje){
        String titularValido = "";

        while (true) {
            System.out.print(mensaje);
            titularValido = scanner.nextLine();

            if (titularValido.length() < 50 && titularValido.length() != 0) {
                System.out.println("La longitud de la entrada es: " + titularValido.length());
                System.out.println("El TITULAR ingresado es: " + titularValido);
                return titularValido;
            } else if (titularValido.isEmpty()) {
                System.out.println("Error: No se ingresó ningún valor. Intente de nuevo.");
            } else {
                System.out.println("Error: En la longitud de TITULARES.");
            }
        }
    }
}
