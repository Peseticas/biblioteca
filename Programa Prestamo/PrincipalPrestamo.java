package ejemploBD.dao.dao;
import java.util.List;
import java.util.Scanner;
import ejemploBD.config.config.BDException;
import ejemploBD.modelo.modelo.Prestamo;


public class PrincipalPrestamo {

    public static void main(String[] args) {
        int opcion;
        Scanner teclado = new Scanner(System.in);

        do {
            System.out.println("0) Salir del programa.");
            System.out.println("1) Insertar un préstamo en la base de datos.");
            System.out.println("2) Actualizar un préstamo, por datos identificativos, de la base de datos.");
            System.out.println("3) Eliminar un préstamo, por datos identificativos, de la base de datos.");
            System.out.println("4) Consultar todos los préstamos de la base de datos.");
            System.out.println("5) Consultar los préstamos no devueltos de la base de datos.");
            System.out.println("6) Consultar DNI y nombre de socio, ISBN y título de libro y fecha de devolución de los préstamos realizados en una fecha de la base de datos.");
            opcion = teclado.nextInt();
            teclado.nextLine(); // Limpiar buffer

            switch (opcion) {
            
                case 0:
                	
                    System.out.println("Saliendo...");
                    break;
                case 1:
                	
                    System.out.println("¿Código del libro?");
                    int codigoLibro = teclado.nextInt();
                    teclado.nextLine();

                    System.out.println("¿Código del socio?");
                    int codigoSocio = teclado.nextInt();
                    teclado.nextLine();

                    
                    System.out.println("¿Fecha de fin? (Formato: YYYY-MM-DD)");
                    String fechaFin = teclado.nextLine();

                    System.out.println("¿Fecha de devolución? (Formato: YYYY-MM-DD o vacío si aún no se ha devuelto)");
                    String fechaDevolucion = teclado.nextLine();

                    try {
                    	AccesoPrestamo.insertarPrestamo(codigoLibro, codigoSocio, fechaFin, fechaDevolucion);

                    } catch (BDException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                	
                    System.out.println("¿Código del libro?");
                    int codigoLibroActualizar = teclado.nextInt();
                    teclado.nextLine();

                    System.out.println("¿Código del socio?");
                    int codigoSocioActualizar = teclado.nextInt();
                    teclado.nextLine();

                    System.out.println("¿Fecha de inicio?");
                    String fechaInicioActualizar = teclado.nextLine();

                    System.out.println("¿Nueva fecha de fin? (Formato: YYYY-MM-DD)");
                    String fechaFinActualizar = teclado.nextLine();

                    System.out.println("¿Nueva fecha de devolución? (Formato: YYYY-MM-DD o vacío si aún no se ha devuelto)");
                    String fechaDevolucionActualizar = teclado.nextLine();

                    try {
                        AccesoPrestamo.actualizarPrestamo(codigoLibroActualizar, codigoSocioActualizar, fechaInicioActualizar, fechaFinActualizar, fechaDevolucionActualizar);
                    } catch (BDException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                	
                    System.out.println("¿Código del libro?");
                    int codigoLibroEliminar = teclado.nextInt();
                    teclado.nextLine();

                    System.out.println("¿Código del socio?");
                    int codigoSocioEliminar = teclado.nextInt();
                    teclado.nextLine();

                    System.out.println("¿Fecha de inicio?");
                    String fechaInicioEliminar = teclado.nextLine();

                    try {
                        AccesoPrestamo.eliminarPrestamo(codigoLibroEliminar, codigoSocioEliminar, fechaInicioEliminar);
                    } catch (BDException e) {
                        System.out.println(e.getMessage());
                    }
                    
                    break;

                case 4:
                    // Consultar préstamos no devueltos
                    try {
                        List<Prestamo> prestamos = AccesoPrestamo.consultarPrestamosNoDevueltos();
                        if (prestamos.isEmpty()) {
                            System.out.println("No hay préstamos pendientes de devolución.");
                        } else {
                            for (Prestamo prestamo : prestamos) {
                                System.out.println(prestamo);
                            }
                        }
                    } catch (BDException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    // Consultar todos los préstamos (si decides usar este)
                    try {
                        List<Prestamo> prestamos = AccesoPrestamo.consultarTodosLosPrestamos();
                        if (prestamos.isEmpty()) {
                            System.out.println("No hay préstamos registrados.");
                        } else {
                            for (Prestamo prestamo : prestamos) {
                                System.out.println(prestamo);
                            }
                        }
                    } catch (BDException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    // Consultar préstamos por fecha
                    System.out.println("¿Fecha del préstamo? (Formato: YYYY-MM-DD)");
                    String fechaConsulta = teclado.nextLine();           

                    try {
                        List<Prestamo> prestamosPorFecha = AccesoPrestamo.consultarPrestamosPorFecha(fechaConsulta);
                        if (prestamosPorFecha.isEmpty()) {
                            System.out.println("No hay préstamos en la fecha indicada.");
                        } else {
                            for (Prestamo prestamo : prestamosPorFecha) {
                                System.out.println(prestamo);
                            }
                        }
                    } catch (BDException e) {
                        System.out.println(e.getMessage());
                  }
                    break;

                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
            
        } while (opcion != 0);

    teclado.close();
    }
    
}
