//Universidad del Valle de Guatemala	                                                                                              
// Adriana Palacios 
//CC2008 - Introducción a la Programación Orientada a Objetos	carné 23044
//Semestre II, 2023
//Ejercicio1-ErasTour


import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;


//Metodo principal que inicia el programa 
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Comprador comprador = null;
        Localidad[] localidades = new Localidad[3];
        localidades[0] = new Localidad(1, "Localidad 1", 20, 100);
        localidades[1] = new Localidad(5, "Localidad 5", 20, 500);
        localidades[2] = new Localidad(10, "Localidad 10", 20, 1000);

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Nuevo comprador");
            System.out.println("2. Nueva solicitud de boletos");
            System.out.println("3. Consultar disponibilidad total");
            System.out.println("4. Consultar disponibilidad individual");
            System.out.println("5. Reporte de caja");
            System.out.println("6. Salir");

            int opcion = leerEnteroValido(scanner, "Opción inválida. Introduce un número del 1 al 6.");

            switch (opcion) {
                case 1:
                    comprador = nuevoComprador(scanner);
                    break;
                case 2:
                    if (comprador == null) {
                        System.out.println("Primero debes registrar un comprador.");
                    } else {
                        nuevaSolicitudBoletos(comprador, localidades);
                    }
                    break;
                case 3:
                    consultarDisponibilidadTotal(localidades);
                    break;
                case 4:
                    consultarDisponibilidadIndividual(localidades, scanner);
                    break;
                case 5:
                    reporteDeCaja(localidades);
                    break;
                case 6:
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción inválida. Introduce un número del 1 al 6.");
            }
        }
    }
//Scanner de utiliza para la entrada 
//Se muestra unmnesaje de error si se ingresa un valor invalido.


    private static int leerEnteroValido(Scanner scanner, String mensajeError) {
        int numero = 0;
        boolean numeroValido = false;

        while (!numeroValido) {
            try {
                numero = scanner.nextInt();
                numeroValido = true;
            } catch (InputMismatchException e) {
                System.out.println(mensajeError);
                scanner.nextLine(); // Limpiar el búfer
            }
        }

        return numero;
    }

//Se crea un nuevo comprador con todos los detalles ingresados por el usuario.


        // Regresa el nuevo comprador creado.

    private static Comprador nuevoComprador(Scanner scanner) {
        scanner.nextLine(); // Limpiar el búfer
        System.out.println("Ingrese el nombre del comprador:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el email del comprador:");
        String email = scanner.nextLine();
        System.out.println("Ingrese la cantidad de boletos a comprar:");
        int cantidadBoletos = leerEnteroValido(scanner, "Cantidad de boletos no válida. Ingrese un número entero.");
        System.out.println("Ingrese el presupuesto máximo:");
        int presupuestoMaximo = leerEnteroValido(scanner, "Presupuesto máximo no válido. Ingrese un número entero.");

        return new Comprador(nombre, email, cantidadBoletos, presupuestoMaximo);
    }



    private static void nuevaSolicitudBoletos(Comprador comprador, Localidad[] localidades) {
        //Esta funcion procesa una nueva solicictud de compra de boletos en una localidad al comprador
    Random random = new Random();
    int ticket = random.nextInt(15000) + 1;
    int a = random.nextInt(15000) + 1;
    int b = random.nextInt(15000) + 1;

    boolean ticketApto = ticket >= Math.min(a, b) && ticket <= Math.max(a, b);

    if (ticketApto) {
        int cantidadBoletos = Math.min(comprador.getCantidadBoletosDeseados(), (int) comprador.getPresupuestoMaximo());

        if (cantidadBoletos > 0) {
            int localidadIndex = random.nextInt(localidades.length);
            Localidad localidad = localidades[localidadIndex];

            if (localidad.hayEspacio() && localidad.hayDisponibilidad(cantidadBoletos)) {
                double costoTotal = cantidadBoletos * localidad.getPrecio();
                if (costoTotal <= comprador.getPresupuestoMaximo()) {
                    localidad.venderBoletos(cantidadBoletos);
                    comprador.restarPresupuesto(costoTotal);
                    comprador.restarBoletos(cantidadBoletos);
                    System.out.println("¡Compra exitosa! Se vendieron " + cantidadBoletos + " boletos en la " + localidad.getNombre());
                } else {
                    System.out.println("El precio de la localidad excede el presupuesto máximo del comprador.");
                }
            } else {
                System.out.println("No hay espacio o disponibilidad en la localidad seleccionada.");
            }
        } else {
            System.out.println("El comprador ya ha adquirido la cantidad máxima de boletos.");
        }
    } else {
        System.out.println("El ticket no es apto para comprar boletos.");
    }
}

//Consula la disponibilidad de boletos en una localidad especifica

private static void consultarDisponibilidadIndividual(Localidad[] localidades, Scanner scanner) {
    System.out.println("Ingrese el ID de la localidad (1, 5 o 10):");
    int localidadId = leerEnteroValido(scanner, "ID de localidad no válido. Ingrese un número entero.");
// lOcalidades disponibles 

    Localidad selectedLocalidad = null;
    for (Localidad localidad : localidades) {
        if (localidad.getId() == localidadId) {
            selectedLocalidad = localidad;
            break;
        }
    }

    if (selectedLocalidad != null) {
        System.out.println("Localidad " + selectedLocalidad.getNombre() + ": Boletos vendidos " + selectedLocalidad.getBoletosVendidos() + ", Boletos disponibles " + (selectedLocalidad.getCapacidadMaxima() - selectedLocalidad.getBoletosVendidos()));
    } else {
        System.out.println("Localidad no encontrada.");
    }
}

private static void reporteDeCaja(Localidad[] localidades) {
    // Realiza un reporte de el total recaudado de los boletos vendidos
    double totalCaja = 0;
    for (Localidad localidad : localidades) {
        totalCaja += localidad.getBoletosVendidos() * localidad.getPrecio();
    }
    System.out.println("Total recaudado: $" + totalCaja);
}

// Cosulta la disponibilidad que hay en total de boletos para todas las localidades
private static void consultarDisponibilidadTotal(Localidad[] localidades) {
    for (Localidad localidad : localidades) {
        System.out.println("Localidad " + localidad.getNombre() + ": Boletos vendidos " + localidad.getBoletosVendidos() + ", Boletos disponibles " + (localidad.getCapacidadMaxima() - localidad.getBoletosVendidos()));
    }
}

    
    }
