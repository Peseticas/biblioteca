package Biblioteca.Libro;

import Biblioteca.excepciones.BDException;

import java.util.Scanner;

import Biblioteca.dao.AccesoLibro;

public class PrincipalLibro{
	
	 public static void main(String[] args) throws BDException {
	        int opcion;
	        Scanner teclado = new Scanner(System.in);

	        do {
	            System.out.println("\n MENÚ BIBLIOTECA");
	            System.out.println("0. Salir");
	            System.out.println("1. Insertar un libro");
	            System.out.println("2. Eliminar libro");
	            System.out.println("3. Consultar todos los libros");
	            System.out.println("4. Consultar libros por escritor");
	            System.out.println("5. Consultar libros no prestados");
	            System.out.println("6. Consultar libros devueltos");
	            System.out.print("Elija una opción: ");

	            opcion = teclado.nextInt();
	            teclado.nextLine(); 

	            switch (opcion) {
	                case 0:
	                    System.out.println("Saliendo del programa...");
	                    break;
	                case 1:
	                	System.out.print("Ingrese ISBN: ");
	                    String isbn = teclado.nextLine();
	                    System.out.print("Ingrese título: ");
	                    String titulo = teclado.nextLine();
	                    System.out.print("Ingrese escritor: ");
	                    String escritor = teclado.nextLine();
	                    System.out.print("Ingrese año de publicación: ");
	                    int año = teclado.nextInt();
	                    System.out.print("Ingrese puntuación: ");
	                    double puntuacion = teclado.nextDouble();
	                    teclado.nextLine();
	                    
	                    try {
	                    	
		                    AccesoLibro.insertarLibro(isbn,titulo,escritor,año,puntuacion);

	                    } catch(BDException e) {
	                    	System.out.println(e.getMessage());
	                    }
	                    break;
	                case 2:
	                	 System.out.print("Ingrese el código del libro a eliminar: ");
	                     int codigo = teclado.nextInt();
	                	
                         try {
	                    	
		                    AccesoLibro.eliminarLibro(codigo);

	                    } catch(BDException e) {
	                    	System.out.println(e.getMessage());
	                    }
	                    break;
	                case 3:
	                    
	                		try {
	                    	
		                    AccesoLibro.consultarLibros();

	                    } catch(BDException e) {
	                    	System.out.println(e.getMessage());
	                    }
	                    break;
	                case 4:
	                	try {
	                    	
		                    AccesoLibro.consultarPorEscritor();

	                    } catch(BDException e) {
	                    	System.out.println(e.getMessage());
	                    }
	                    break;
	                case 5:
	                	try {
	                    	
		                    AccesoLibro.consultarLibrosNoPrestados();

	                    } catch(BDException e) {
	                    	System.out.println(e.getMessage());
	                    }
	                	
	                    break;
	                case 6:
	                    System.out.print("Ingrese la fecha de devolución (DD-MM-YYYY): ");
	                    String fecha = teclado.nextLine();
	                    try {
	                    	
	                    	AccesoLibro.consultarLibrosDevueltos(fecha);

	                    } catch(BDException e) {
	                    	System.out.println(e.getMessage());
	                    }
	                    break;
	                default:
	                    System.out.println("Opción no válida.");
	            }
	        } while (opcion != 0);
	    }
	}

