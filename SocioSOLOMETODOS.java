import java.sql.*;
import java.util.Scanner;

public class Actividad_2x02 {
    private static final String DB_URL = "jdbc:sqlite:biblioteca.db";
    private static Scanner scanner = new Scanner(System.in);


    private static void crearTablaSocio() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            
            String sql = "CREATE TABLE IF NOT EXISTS socio (" +
                         "codigo INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         "dni TEXT NOT NULL UNIQUE, " +
                         "nombre TEXT NOT NULL, " +
                         "domicilio TEXT NOT NULL, " +
                         "telefono TEXT NOT NULL, " +
                         "correo TEXT NOT NULL)";
            stmt.execute(sql);
            
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla socio: " + e.getMessage());
        }
    }

    private static void insertarSocio() {
        System.out.print("Introduzca el DNI del socio: ");
        String dni = scanner.nextLine();
        
        System.out.print("Introduzca el nombre del socio: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Introduzca el domicilio del socio (incluyendo localidad): ");
        String domicilio = scanner.nextLine();
        
        System.out.print("Introduzca el teléfono del socio: ");
        String telefono = scanner.nextLine();
        
        System.out.print("Introduzca el correo del socio: ");
        String correo = scanner.nextLine();
        
        String sql = "INSERT INTO socio(dni, nombre, domicilio, telefono, correo) VALUES(?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, dni);
            pstmt.setString(2, nombre);
            pstmt.setString(3, domicilio);
            pstmt.setString(4, telefono);
            pstmt.setString(5, correo);
            pstmt.executeUpdate();
            
            System.out.println("Se ha insertado un socio en la base de datos.");
            
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                System.out.println("Error: Ya existe un socio con ese DNI en la base de datos.");
            } else {
                System.out.println("Error al insertar socio: " + e.getMessage());
            }
        }
    }

    private static void eliminarSocio() {
        System.out.print("Introduzca el código del socio a eliminar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        
        String sql = "DELETE FROM socio WHERE codigo = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, codigo);
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                System.out.println("Se ha eliminado un socio de la base de datos.");
            } else {
                System.out.println("No existe ningún socio con ese código en la base de datos.");
            }
            
        } catch (SQLException e) {
            if (e.getMessage().contains("FOREIGN KEY constraint failed")) {
                System.out.println("El socio está referenciado en un préstamo de la base de datos.");
            } else {
                System.out.println("Error al eliminar socio: " + e.getMessage());
            }
        }
    }

    private static void consultarTodosSocios() {
        String sql = "SELECT * FROM socio";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            int contador = 0;
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String domicilio = rs.getString("domicilio");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                
                System.out.printf("Socio [Código = %d, DNI = %s, Nombre = %s,%n    Domicilio = %s,%n    Teléfono = %s, Correo = %s]%n", 
                                 codigo, dni, nombre, domicilio, telefono, correo);
                contador++;
            }
            
            if (contador == 0) {
                System.out.println("No se ha encontrado ningún socio en la base de datos.");
            } else {
                System.out.printf("Se han consultado %d socios de la base de datos.%n", contador);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al consultar socios: " + e.getMessage());
        }
    }

    private static void consultarSociosPorLocalidad() {
        System.out.print("Introduzca la localidad a buscar: ");
        String localidad = scanner.nextLine();
        
        String sql = "SELECT * FROM socio WHERE domicilio LIKE ? ORDER BY nombre ASC";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + localidad + "%");
            ResultSet rs = pstmt.executeQuery();
            
            int contador = 0;
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String domicilio = rs.getString("domicilio");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                
                System.out.printf("Socio [Código = %d, DNI = %s, Nombre = %s,%n    Domicilio = %s,%n    Teléfono = %s, Correo = %s]%n", 
                                 codigo, dni, nombre, domicilio, telefono, correo);
                contador++;
            }
            
            if (contador == 0) {
                System.out.println("No existe ningún socio con esa localidad en la base de datos.");
            } else {
                System.out.printf("Se han consultado %d socios de la base de datos.%n", contador);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al consultar socios por localidad: " + e.getMessage());
        }
    }

    private static void consultarSociosSinPrestamos() {
        String sql = "SELECT s.* FROM socio s " +
                     "LEFT JOIN prestamo p ON s.codigo = p.codigo_socio " +
                     "WHERE p.codigo_socio IS NULL";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            int contador = 0;
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String domicilio = rs.getString("domicilio");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                
                System.out.printf("Socio [Código = %d, DNI = %s, Nombre = %s,%n    Domicilio = %s,%n    Teléfono = %s, Correo = %s]%n", 
                                 codigo, dni, nombre, domicilio, telefono, correo);
                contador++;
            }
            
            if (contador == 0) {
                System.out.println("No existe ningún socio sin préstamos en la base de datos.");
            } else {
                System.out.printf("Se han consultado %d socios de la base de datos.%n", contador);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al consultar socios sin préstamos: " + e.getMessage());
        }
    }

    private static void consultarSociosConPrestamosEnFecha() {
        System.out.print("Introduzca la fecha de inicio de los préstamos (formato DD/MM/AAAA): ");
        String fecha = scanner.nextLine();
        
        String sql = "SELECT DISTINCT s.* FROM socio s " +
                     "JOIN prestamo p ON s.codigo = p.codigo_socio " +
                     "WHERE p.fecha_inicio = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, fecha);
            ResultSet rs = pstmt.executeQuery();
            
            int contador = 0;
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String domicilio = rs.getString("domicilio");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                
                System.out.printf("Socio [Código = %d, DNI = %s, Nombre = %s,%n    Domicilio = %s,%n    Teléfono = %s, Correo = %s]%n", 
                                 codigo, dni, nombre, domicilio, telefono, correo);
                contador++;
            }
            
            if (contador == 0) {
                System.out.println("No existe ningún socio con préstamos en esa fecha en la base de datos.");
            } else {
                System.out.printf("Se han consultado %d socios de la base de datos.%n", contador);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al consultar socios con préstamos en fecha: " + e.getMessage());
        }
    }
}
