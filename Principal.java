package ProyectoBiblioteca;

import entrada.Teclado;

public class Principal {

	public static void main (String[] args) {
	  int opcion;

        do {
            System.out.println("Menú Principal:");
            System.out.println("1. Libro");
            System.out.println("2. Socio");
            System.out.println("3. Préstamo");
            System.out.println("0. Salir");
            opcion = Teclado.leerEntero("Elige una opción: ");

            switch (opcion) {
                case 1:
                    menuLibro();
                    break;
                case 2:
                    menuSocio();
                    break;
                case 3:
                    menuPrestamo();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 0);
    }

    public static void menuLibro() {
        int opcion;
        do {
            System.out.println("\nMenú Libros:");
            System.out.println("0. Salir");
            System.out.println("1. Insertar un libro en la base de datos");
            System.out.println("2. Eliminar un libro por código");
            System.out.println("3. Consultar todos los libros");
            System.out.println("4. Consultar libros por autor, ordenados por título");
            System.out.println("5. Consultar libros sin préstamos");
            System.out.println("6. Consultar libros prestados en una fecha");
            opcion = Teclado.leerEntero("Elige una opción: ");
            
            switch (opcion) {
            	case 0:
            		System.out.println("Volviendo atrás...");
            		break;
            	case 1:
            		
            		break;
            	case 2:
            		
            		break;
            	case 3:
            		
            		break;
            	case 4:
            		
            		break;
            	case 5:
            		
            		break;
            	case 6:
            		
            		break;
            	default:
            }
            
        } while (opcion != 0);
    }

    public static void menuSocio() {
        int opcion;
        do {
            System.out.println("\nMenú Socios:");
            System.out.println("0. Salir");
            System.out.println("1. Insertar un socio en la base de datos");
            System.out.println("2. Eliminar un socio por código");
            System.out.println("3. Consultar todos los socios");
            System.out.println("4. Consultar socios por localidad, ordenados por nombre");
            System.out.println("5. Consultar socios sin préstamos");
            System.out.println("6. Consultar socios con préstamos en una fecha");
            opcion = Teclado.leerEntero("Elige una opción: ");
            
            switch (opcion) {
	        	case 0:
	        		System.out.println("Volviendo atrás...");
	        		break;
	        	case 1:
	        		
	        		break;
	        	case 2:
	        		
	        		break;
	        	case 3:
	        		
	        		break;
	        	case 4:
	        		
	        		break;
	        	case 5:
	        		
	        		break;
	        	case 6:
	        		
	        		break;
	        	default:
            }
            
        } while (opcion != 0);
    }

    public static void menuPrestamo() {
        int opcion;
        do {
            System.out.println("\nMenú Préstamos:");
            System.out.println("0. Salir");
            System.out.println("1. Insertar un préstamo en la base de datos");
            System.out.println("2. Eliminar un préstamo por código");
            System.out.println("3. Consultar todos los préstamos");
            System.out.println("4. Consultar préstamos por fecha");
            System.out.println("5. Consultar préstamos activos");
            System.out.println("6. Consultar préstamos vencidos");
            opcion = Teclado.leerEntero("Elige una opción: ");
            
            switch (opcion) {
	        	case 0:
	        		System.out.println("Volviendo atrás...");
	        		break;
	        	case 1:
	        		
	        		break;
	        	case 2:
	        		
	        		break;
	        	case 3:
	        		
	        		break;
	        	case 4:
	        		
	        		break;
	        	case 5:
	        		
	        		break;
	        	case 6:
	        		
	        		break;
	        	default:
	        }
            
        } while (opcion != 0);
    }
}

	
