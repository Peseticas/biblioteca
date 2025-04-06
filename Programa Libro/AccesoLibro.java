package Biblioteca.dao;

import java.sql.*;
import java.util.Scanner;
import Biblioteca.config.ConfigSqlite;

import Biblioteca.excepciones.BDException;

public class AccesoLibro {

    private static final Scanner teclado = new Scanner(System.in);
    public static final String URL = "jdbc:sqlite:db/biblioteca.db";

    public static void insertarLibro(String isbn,String titulo,String escritor,int año, double puntuacion) throws BDException{
    	  

        String sql = "INSERT INTO Libro (isbn, titulo, escritor, año_publicacion, puntuacion) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, isbn);
            pstmt.setString(2, titulo);
            pstmt.setString(3, escritor);
            pstmt.setInt(4, año);
            pstmt.setDouble(5, puntuacion);
            pstmt.executeUpdate();
            System.out.println("Libro insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar libro: " + e.getMessage());
        }
    }

    public static void eliminarLibro(int codigo) throws BDException{
       
        teclado.nextLine();
        String sql = "DELETE FROM Libro WHERE codigo = ?";
        try (Connection conexion = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, codigo);
            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Libro eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún libro con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar libro: " + e.getMessage());
        }
    }

    public static void consultarLibros()throws BDException {
        String sql = "SELECT * FROM Libro";
        try (Connection conexion = DriverManager.getConnection(URL);
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            boolean hayLibros = false;
            while (rs.next()) {
                hayLibros = true;
                System.out.println("Libro [ID = " + rs.getInt("codigo") +
                        ", ISBN = " + rs.getString("isbn") +
                        ", Título = " + rs.getString("titulo") +
                        ", Escritor = " + rs.getString("escritor") +
                        ", Año Publicación = " + rs.getInt("año_publicacion") +
                        ", Puntuación = " + rs.getDouble("puntuacion") + "]");
            }
            if (!hayLibros) {
                System.out.println("No hay libros en la base de datos.");
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar libros: " + e.getMessage());
        }
    }

    public static void consultarPorEscritor()throws BDException {
        System.out.print("Ingrese el nombre del escritor: ");
        String escritor = teclado.nextLine();
        String sql = "SELECT * FROM Libro WHERE escritor = ? ORDER BY puntuacion DESC";
		try (Connection conexion = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, escritor);
            ResultSet rs = pstmt.executeQuery();
            boolean hayLibros = false;
            while (rs.next()) {
                hayLibros = true;
                System.out.println("Libro [ID = " + rs.getString("codigo") +
                        ", ISBN = " + rs.getString("isbn") +
                        ", Título = " + rs.getString("titulo") +
                        ", Escritor = " + rs.getString("escritor") +
                        ", Año Publicación = " + rs.getInt("año_publicacion") +
                        ", Puntuación = " + rs.getDouble("puntuacion") + "]");
            }
            if (!hayLibros) {
                System.out.println("No se encontraron libros del escritor '" + escritor + "'.");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar libros por escritor: " + e.getMessage());
        }
    }

    public static void consultarLibrosDevueltos(String fechaDevolucion)throws BDException {
        String sql = "SELECT * FROM prestamo WHERE fecha_devolucion = ?";
        try (Connection conexion = DriverManager.getConnection(URL);
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, fechaDevolucion);
            ResultSet rs = ps.executeQuery();

            boolean hayResultados = false;
            while (rs.next()) {
                hayResultados = true;
                System.out.println("Libro devuelto:");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Título: " + rs.getString("titulo"));
                System.out.println("Escritor: " + rs.getString("escritor"));
                System.out.println("Fecha de devolución: " + rs.getString("fecha_devolucion"));
                System.out.println("------------------------------------------------------");
            }
            if (!hayResultados) {
                System.out.println("No hay libros devueltos en esa fecha.");
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar libros devueltos: " + e.getMessage());
        }
    }

    public static void consultarLibrosNoPrestados()throws BDException {
        String sql = "SELECT * FROM prestamo WHERE fecha_devolucion IS NULL";
        try (Connection conexion = DriverManager.getConnection(URL);
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            boolean hayLibros = false;
            while (rs.next()) {
                hayLibros = true;
                System.out.println("Libro no prestado:");
                System.out.println("ID: " + rs.getInt("codigo"));
                System.out.println("Título: " + rs.getString("titulo"));
                System.out.println("Escritor: " + rs.getString("escritor"));
                System.out.println("------------------------------------------------------");
            }
            if (!hayLibros) {
                System.out.println("Todos los libros están prestados.");
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar libros no prestados: " + e.getMessage());
        }
    }
}

   