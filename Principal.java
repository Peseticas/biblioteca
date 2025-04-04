package ProyectoBiblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import org.sqlite.SQLiteConfig;

import ejemploBD.excepciones.BDException;
import entrada.Teclado;

public class Principal {

	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String URLBD = "jdbc:sqlite:DB/Biblioteca.db";
	
	/**
	 * Abre conexi�n con la base de datos sqllite
	 * @param <SQLiteConfig>
	 * @return
	 * @throws BDException
	 */
	public static Connection abrirConexion() throws BDException {
		Connection conexion = null;

		try {
			// Carga el driver
			Class.forName(DRIVER);
			SQLiteConfig config = new SQLiteConfig();  
	        config.enforceForeignKeys(true);
			// Abre conexi�n
			conexion = DriverManager.getConnection(URLBD,config.toProperties());			 
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new BDException(BDException.ERROR_CARGAR_DRIVER + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BDException(BDException.ERROR_ABRIR_CONEXION + e.getMessage());
		}

		return conexion;

	}
	
	
	/**
	 * Cierra conexi�n con SQLLite
	 * @param conexion
	 * @throws BDException 
	 */
	public static void cerrarConexion(Connection conexion) throws BDException {
		try {
			conexion.close();
		} catch (SQLException e) {
			throw new BDException(BDException.ERROR_CERRAR_CONEXION + e.getMessage() );
		}
	}
	
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
            try {
                Connection conexion = abrirConexion();
                
                switch (opcion) {
                    case 0:
                        System.out.println("Volviendo atrás...");
                        break;
                        
                    case 1: // Insertar socio
    
                        String dni = Teclado.leerCadena("Introduzca el DNI del socio: ");
                        
                        String nombre = Teclado.leerCadena("Introduzca el nombre del socio: ");
 
                        String domicilio = Teclado.leerCadena("Introduzca el domicilio del socio (incluyendo localidad): ");

                        String telefono = Teclado.leerCadena("Introduzca el teléfono del socio: ");
  
                        String correo = Teclado.leerCadena("Introduzca el correo del socio: ");
                        
                        String insertar = "INSERT INTO socio(dni, nombre, domicilio, telefono, correo) VALUES(?, ?, ?, ?, ?)";
                        PreparedStatement insertarStatement = conexion.prepareStatement(insertar);
                        insertarStatement.setString(1, dni);
                        insertarStatement.setString(2, nombre);
                        insertarStatement.setString(3, domicilio);
                        insertarStatement.setString(4, telefono);
                        insertarStatement.setString(5, correo);
                        
                        try {
                            insertarStatement.executeUpdate();
                            System.out.println("Se ha insertado un socio en la base de datos.");
                        } catch (SQLException e) {
                            if (e.getMessage().contains("UNIQUE constraint failed")) {
                                System.out.println("Error: Ya existe un socio con ese DNI en la base de datos.");
                            } else {
                                System.out.println("Error al insertar socio: " + e.getMessage());
                            }
                        }
                        break;
                        
                    case 2: // Eliminar socio
                        int codigo = Teclado.leerEntero("Introduzca el código del socio a eliminar: ");
                        
                        String eliminar = "DELETE FROM socio WHERE codigo = ?";
                        PreparedStatement eliminarStatement = conexion.prepareStatement(eliminar);
                        eliminarStatement.setInt(1, codigo);
                        
                        int eliminados = eliminarStatement.executeUpdate();
                        
                        if (eliminados > 0) {
                            System.out.println("Se ha eliminado un socio de la base de datos.");
                        } else {
                            System.out.println("No existe ningún socio con ese código en la base de datos.");
                        }
                        break;
                        
                    case 3: // Consultar todos los socios
                        String consultarTodos = "SELECT * FROM socio";
                        Statement consultarTodosStatement = conexion.createStatement();
                        ResultSet resultadoTodos = consultarTodosStatement.executeQuery(consultarTodos);
                        
                        int contadorTodos = 0;
                        while (resultadoTodos.next()) {
                            int cod = resultadoTodos.getInt("codigo");
                            String dn = resultadoTodos.getString("dni");
                            String nom = resultadoTodos.getString("nombre");
                            String dom = resultadoTodos.getString("domicilio");
                            String tel = resultadoTodos.getString("telefono");
                            String cor = resultadoTodos.getString("correo");
                            
                            System.out.printf("Socio [Código = %d, DNI = %s, Nombre = %s,%n    Domicilio = %s,%n    Teléfono = %s, Correo = %s]%n", 
                                             cod, dn, nom, dom, tel, cor);
                            contadorTodos++;
                        }
                        
                        if (contadorTodos == 0) {
                            System.out.println("No se ha encontrado ningún socio en la base de datos.");
                        } else {
                            System.out.printf("Se han consultado %d socios de la base de datos.%n", contadorTodos);
                        }
                        break;
                        
                    case 4: // Consultar por localidad
                        String localidad = Teclado.leerCadena("Introduzca la localidad a buscar: ");
                        
                        String consultarLocalidad = "SELECT * FROM socio WHERE domicilio LIKE ? ORDER BY nombre ASC";
                        PreparedStatement localidadStatement = conexion.prepareStatement(consultarLocalidad);
                        localidadStatement.setString(1, "%" + localidad + "%");
                        ResultSet resultadoLocalidad = localidadStatement.executeQuery();
                        
                        int contadorLocalidad = 0;
                        while (resultadoLocalidad.next()) {
                            int cod = resultadoLocalidad.getInt("codigo");
                            String dn = resultadoLocalidad.getString("dni");
                            String nom = resultadoLocalidad.getString("nombre");
                            String dom = resultadoLocalidad.getString("domicilio");
                            String tel = resultadoLocalidad.getString("telefono");
                            String cor = resultadoLocalidad.getString("correo");
                            
                            System.out.printf("Socio [Código = %d, DNI = %s, Nombre = %s,%n    Domicilio = %s,%n    Teléfono = %s, Correo = %s]%n", 
                                             cod, dn, nom, dom, tel, cor);
                            contadorLocalidad++;
                        }
                        
                        if (contadorLocalidad == 0) {
                            System.out.println("No existe ningún socio con esa localidad en la base de datos.");
                        } else {
                            System.out.printf("Se han consultado %d socios de la base de datos.%n", contadorLocalidad);
                        }
                        break;
                        
                    case 5: // Consultar socios sin préstamos
                        String sinPrestamos = "SELECT s.* FROM socio s " +
                                             "LEFT JOIN prestamo p ON s.codigo = p.codigo_socio " +
                                             "WHERE p.codigo_socio IS NULL";
                        Statement sinPrestamosStatement = conexion.createStatement();
                        ResultSet resultadoSinPrestamos = sinPrestamosStatement.executeQuery(sinPrestamos);
                        
                        int contadorSinPrestamos = 0;
                        while (resultadoSinPrestamos.next()) {
                            int cod = resultadoSinPrestamos.getInt("codigo");
                            String dn = resultadoSinPrestamos.getString("dni");
                            String nom = resultadoSinPrestamos.getString("nombre");
                            String dom = resultadoSinPrestamos.getString("domicilio");
                            String tel = resultadoSinPrestamos.getString("telefono");
                            String cor = resultadoSinPrestamos.getString("correo");
                            
                            System.out.printf("Socio [Código = %d, DNI = %s, Nombre = %s,%n    Domicilio = %s,%n    Teléfono = %s, Correo = %s]%n", 
                                             cod, dn, nom, dom, tel, cor);
                            contadorSinPrestamos++;
                        }
                        
                        if (contadorSinPrestamos == 0) {
                            System.out.println("No existe ningún socio sin préstamos en la base de datos.");
                        } else {
                            System.out.printf("Se han consultado %d socios de la base de datos.%n", contadorSinPrestamos);
                        }
                        break;
                        
                    case 6: // Consultar socios con préstamos en fecha
                        String fecha = Teclado.leerCadena("Introduzca la fecha de inicio de los préstamos (formato DD/MM/AAAA): ");
                        
                        String conPrestamos = "SELECT DISTINCT s.* FROM socio s " +
                                            "JOIN prestamo p ON s.codigo = p.codigo_socio " +
                                            "WHERE p.fecha_inicio = ?";
                        PreparedStatement conPrestamosStatement = conexion.prepareStatement(conPrestamos);
                        conPrestamosStatement.setString(1, fecha);
                        ResultSet resultadoConPrestamos = conPrestamosStatement.executeQuery();
                        
                        int contadorConPrestamos = 0;
                        while (resultadoConPrestamos.next()) {
                            int cod = resultadoConPrestamos.getInt("codigo");
                            String dn = resultadoConPrestamos.getString("dni");
                            String nom = resultadoConPrestamos.getString("nombre");
                            String dom = resultadoConPrestamos.getString("domicilio");
                            String tel = resultadoConPrestamos.getString("telefono");
                            String cor = resultadoConPrestamos.getString("correo");
                            
                            System.out.printf("Socio [Código = %d, DNI = %s, Nombre = %s,%n    Domicilio = %s,%n    Teléfono = %s, Correo = %s]%n", 
                                             cod, dn, nom, dom, tel, cor);
                            contadorConPrestamos++;
                        }
                        
                        if (contadorConPrestamos == 0) {
                            System.out.println("No existe ningún socio con préstamos en esa fecha en la base de datos.");
                        } else {
                            System.out.printf("Se han consultado %d socios de la base de datos.%n", contadorConPrestamos);
                        }
                        break;
                        
                    default:
                        System.out.println("La opción de menú debe estar comprendida entre 0 y 6.");
                }
                
                cerrarConexion(conexion);
                
            } catch (BDException e) {
                System.out.println("Error con la base de datos: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Error en la operación con la base de datos: " + e.getMessage());
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
                System.out.println("Saliendo del programa...");
                break;
                
            case 1: // Insertar préstamo
                int codigoLibro = Teclado.leerEntero("Introduzca el código del libro: ");
                
                int codigoSocio = Teclado.leerEntero("Introduzca el código del socio: ");

                String fechaFin = Teclado.leerCadena("Introduzca la fecha de fin del préstamo (Formato: YYYY-MM-DD): ");

                try {
                    AccesoPrestamo.insertarPrestamo(codigoLibro, codigoSocio, fechaFin, null);
                    System.out.println("Se ha insertado un préstamo en la base de datos.");
                } catch (BDException e) {
                    System.out.println(e.getMessage());
                }
                break;
                
            case 2: // Actualizar préstamo
                int codigoLibroAct = Teclado.leerEntero("Introduzca el código del libro: ");

                int codigoSocioAct = Teclado.leerEntero("Introduzca el código del socio: ");

                String fechaInicioAct = Teclado.leerCadena("Introduzca la fecha de inicio del préstamo (Formato: YYYY-MM-DD): ");

                String fechaDevolucionAct = Teclado.leerCadena("Introduzca la nueva fecha de devolución (Formato: YYYY-MM-DD): ");

                try {
                    AccesoPrestamo.actualizarPrestamo(codigoLibroAct, codigoSocioAct, fechaInicioAct, fechaInicioAct, fechaDevolucionAct);
                    System.out.println("Se ha actualizado un préstamo de la base de datos.");
                } catch (BDException e) {
                    System.out.println("No existe ningún préstamo con esos datos identificativos en la base de datos.");
                }
                
                break;
                
            case 3: // Eliminar préstamo
                int codigoLibroElim = Teclado.leerEntero("Introduzca el código del libro: ");

                int codigoSocioElim = Teclado.leerEntero("Introduzca el código del socio: ");

                String fechaInicioElim = Teclado.leerCadena("Introduzca la fecha de inicio del préstamo (Formato: YYYY-MM-DD): ");

                try {
                    AccesoPrestamo.eliminarPrestamo(codigoLibroElim, codigoSocioElim, fechaInicioElim);
                    System.out.println("Se ha eliminado un préstamo de la base de datos.");
                } catch (BDException e) {
                    System.out.println("No existe ningún préstamo con esos datos identificativos en la base de datos.");
                }
                break;
                
            case 4: // Consultar todos los préstamos
                try {
                    List<Prestamo> prestamos = AccesoPrestamo.consultarTodosLosPrestamos();
                    if (prestamos.isEmpty()) {
                        System.out.println("No se ha encontrado ningún préstamo en la base de datos.");
                    } else {
                        for (Prestamo prestamo : prestamos) {
                            System.out.printf("Préstamo [CódigoLibro = %d, CódigoSocio = %d, FechaInicio = %s,%n    FechaFin = %s, FechaDevolucion = %s]%n",
                                            prestamo.getCodigoLibro(), prestamo.getCodigoSocio(), 
                                            prestamo.getFechaInicio(), prestamo.getFechaFin(), 
                                            prestamo.getFechaDevolucion());
                        }
                        System.out.printf("Se han consultado %d préstamos de la base de datos.%n", prestamos.size());
                    }
                } catch (BDException e) {
                    System.out.println("Error al consultar préstamos: " + e.getMessage());
                }
                break;
                
            case 5: // Consultar préstamos no devueltos
                try {
                    List<Prestamo> prestamosNoDevueltos = AccesoPrestamo.consultarPrestamosNoDevueltos();
                    if (prestamosNoDevueltos.isEmpty()) {
                        System.out.println("No existe ningún préstamo no devuelto en la base de datos.");
                    } else {
                        for (Prestamo prestamo : prestamosNoDevueltos) {
                            System.out.printf("Préstamo [CódigoLibro = %d, CódigoSocio = %d, FechaInicio = %s,%n    FechaFin = %s, FechaDevolucion = %s]%n",
                                            prestamo.getCodigoLibro(), prestamo.getCodigoSocio(), 
                                            prestamo.getFechaInicio(), prestamo.getFechaFin(), 
                                            prestamo.getFechaDevolucion());
                        }
                        System.out.printf("Se han consultado %d préstamos de la base de datos.%n", prestamosNoDevueltos.size());
                    }
                } catch (BDException e) {
                    System.out.println("Error al consultar préstamos no devueltos: " + e.getMessage());
                }
                break;
                
            case 6: // Consultar préstamos por fecha con detalles
                String fechaConsulta = Teclado.leerCadena("Introduzca la fecha de inicio de los préstamos (Formato: YYYY-MM-DD): ");

                try {
                    List<Prestamo> prestamosFecha = AccesoPrestamo.consultarPrestamosPorFecha(fechaConsulta);
                    if (prestamosFecha.isEmpty()) {
                        System.out.println("No existe ningún préstamo realizado en esa fecha en la base de datos.");
                    } else {
                        // Necesitaríamos un método adicional en AccesoPrestamo que devuelva más información
                        // Por ahora mostramos la información básica
                        for (Prestamo prestamo : prestamosFecha) {
                            System.out.printf("Préstamo [CódigoLibro = %d, CódigoSocio = %d, FechaInicio = %s,%n    FechaFin = %s, FechaDevolucion = %s]%n",
                                            prestamo.getCodigoLibro(), prestamo.getCodigoSocio(), 
                                            prestamo.getFechaInicio(), prestamo.getFechaFin(), 
                                            prestamo.getFechaDevolucion());
                        }
                        System.out.printf("Se han consultado %d préstamos de la base de datos.%n", prestamosFecha.size());
                    }
                } catch (BDException e) {
                    System.out.println("Error al consultar préstamos por fecha: " + e.getMessage());
                }
                break;
                
            default:
                System.out.println("La opción de menú debe estar comprendida entre 0 y 6.");
        }
        
        } while (opcion != 0);
    }
}

	
