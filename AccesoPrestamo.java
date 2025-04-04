package ejemploBD.dao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ejemploBD.config.config.BDException;
import ejemploBD.config.config.ConfigSqlLite;
import ejemploBD.modelo.modelo.Prestamo;

public class AccesoPrestamo {
		public static void insertarPrestamo(int codigoLibro, int codigoSocio, String fechaInicio, String fechaFin, String fechaDevolucion) throws BDException {
	    PreparedStatement ps = null;
	    Connection conexion = null;
	    ResultSet rs = null;

	    try {
	        conexion = ConfigSqlLite.abrirConexion();

	        // 1. Verificar que el libro existe
	        String queryLibro = "SELECT COUNT(*) FROM Libro WHERE Codigo = ?";
	        ps = conexion.prepareStatement(queryLibro);
	        ps.setInt(1, codigoLibro);
	        rs = ps.executeQuery();
	        if (rs.next() && rs.getInt(1) == 0) {
	            throw new BDException(" El libro con código " + codigoLibro + " no existe.");
	        }

	        // 2. Verificar que el socio existe
	        String querySocio = "SELECT COUNT(*) FROM Socio WHERE Codigo = ?";
	        ps = conexion.prepareStatement(querySocio);
	        ps.setInt(1, codigoSocio);
	        rs = ps.executeQuery();
	        if (rs.next() && rs.getInt(1) == 0) {
	            throw new BDException(" El socio con código " + codigoSocio + " no existe.");
	        }

	        // 3. Verificar si ya existe algún préstamo con ese libro (sin importar estado)
	        String queryPrestamoExistente = "SELECT COUNT(*) FROM Prestamo WHERE codigo_libro = ?";
	        ps = conexion.prepareStatement(queryPrestamoExistente);
	        ps.setInt(1, codigoLibro);
	        rs = ps.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            throw new BDException(" Ya existe un préstamo registrado con el libro código " + codigoLibro + ".");
	        }

	        // 4. Insertar el préstamo si pasa todas las validaciones
	        String queryInsert = "INSERT INTO prestamo (codigo_libro, codigo_socio, fecha_inicio, fecha_fin, fecha_devolucion) VALUES (?, ?, ?, ?, ?)";
	        ps = conexion.prepareStatement(queryInsert);
	        ps.setInt(1, codigoLibro);
	        ps.setInt(2, codigoSocio);
	        ps.setString(3, fechaInicio);
	        ps.setString(4, fechaFin);
	        ps.setString(5, fechaDevolucion);

	        ps.executeUpdate();
	        System.out.println(" Préstamo insertado correctamente.");

	    } catch (SQLException e) {
	        throw new BDException(" Error al insertar el préstamo: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conexion != null) ConfigSqlLite.cerrarConexion(conexion);
	        } catch (SQLException e) {
	            System.err.println("⚠️ Error al cerrar recursos: " + e.getMessage());
	        }
	    }
	}



    
		public static void actualizarPrestamo(int codigoLibro, int codigoSocio, String fechaInicio, String fechaFin, String fechaDevolucion) throws BDException {
		    PreparedStatement ps = null;
		    Connection conexion = null;
	
		    try {
		        // Conexión a la base de datos
		        conexion = ConfigSqlLite.abrirConexion();
	
		        // Consulta SQL para actualizar un préstamo
		        String query = "UPDATE prestamo SET fecha_inicio = ?, fecha_fin = ?, fecha_devolucion = ? WHERE codigo_libro = ? AND codigo_socio = ? AND fecha_inicio = ?";
	
		        // Preparar el statement
		        ps = conexion.prepareStatement(query);
	
		        // Asignar valores a los parámetros de la consulta
		        ps.setString(1, fechaInicio);
		        ps.setString(2, fechaFin);
		        ps.setString(3, fechaDevolucion);
		        ps.setInt(4, codigoLibro);
		        ps.setInt(5, codigoSocio);
		        ps.setString(6, fechaInicio);
	
		        // Ejecutar la consulta de actualización
		        int filasAfectadas = ps.executeUpdate();
	
		        if (filasAfectadas > 0) {
		            System.out.println("El préstamo con código libro " + codigoLibro + " y código socio " + codigoSocio + " ha sido actualizado correctamente.");
		        } else {
		            System.out.println("No se encontró el préstamo con los parámetros especificados.");
		        }
		    } catch (SQLException e) {
		        throw new BDException("Error al actualizar el préstamo: " + e.getMessage());
		    } finally {
		        if (conexion != null) {
		            ConfigSqlLite.cerrarConexion(conexion);
		        }
		    }
		}

            
         public static void eliminarPrestamo(int codigoLibro, int codigoSocio, String fechaInicio) throws BDException {
        	    PreparedStatement ps = null;
        	    Connection conexion = null;

        	    try {
        	        // Conexión a la base de datos
        	        conexion = ConfigSqlLite.abrirConexion();

        	        // Consulta SQL para eliminar un préstamo
        	        String query = "DELETE FROM prestamo WHERE codigo_libro = ? AND codigo_socio = ? AND fecha_inicio = ?";

        	        // Preparar el statement
        	        ps = conexion.prepareStatement(query);

        	        // Asignar valores a los parámetros de la consulta
        	        ps.setInt(1, codigoLibro);
        	        ps.setInt(2, codigoSocio);
        	        ps.setString(3, fechaInicio);

        	        // Ejecutar la consulta de eliminación
        	        int filasAfectadas = ps.executeUpdate();

        	        if (filasAfectadas > 0) {
        	            System.out.println("El préstamo con código libro " + codigoLibro + " y código socio " + codigoSocio + " ha sido eliminado correctamente.");
        	        } else {
        	            System.out.println("No se encontró el préstamo con los parámetros especificados.");
        	        }
        	    } catch (SQLException e) {
        	        throw new BDException("Error al eliminar el préstamo: " + e.getMessage());
        	    } finally {
        	        if (conexion != null) {
        	            ConfigSqlLite.cerrarConexion(conexion);
        	        }
        	    }
        	}
         public static List<Prestamo> consultarPrestamosNoDevueltos() throws BDException {
        	    List<Prestamo> prestamosNoDevueltos = new ArrayList<>();
        	    PreparedStatement ps = null;
        	    Connection conexion = null;

        	    try {
        	        conexion = ConfigSqlLite.abrirConexion();
        	        String query = "SELECT * FROM prestamo WHERE fecha_devolucion IS NULL";
        	        ps = conexion.prepareStatement(query);
        	        ResultSet resultados = ps.executeQuery();

        	        while (resultados.next()) {
        	            Prestamo prestamo = new Prestamo(
        	                resultados.getInt("codigo_libro"),
        	                resultados.getInt("codigo_socio"),
        	                resultados.getString("fecha_inicio"),
        	                resultados.getString("fecha_fin"),
        	                resultados.getString("fecha_devolucion")
        	            );
        	            prestamosNoDevueltos.add(prestamo);
        	        }

        	    } catch (SQLException e) {
        	        throw new BDException("Error al consultar los préstamos no devueltos: " + e.getMessage());
        	    } finally {
        	        if (conexion != null) {
        	            ConfigSqlLite.cerrarConexion(conexion);
        	        }
        	    }

        	    return prestamosNoDevueltos;
        	}

        	public static List<Prestamo> consultarPrestamosPorFecha(String fecha) throws BDException {
        	    List<Prestamo> prestamosPorFecha = new ArrayList<>();
        	    PreparedStatement ps = null;
        	    Connection conexion = null;

        	    try {
        	        conexion = ConfigSqlLite.abrirConexion();
        	        String query = "SELECT codigo_libro, codigo_socio, fecha_inicio, fecha_fin, fecha_devolucion "
        	                     + "FROM prestamo "
        	                     + "WHERE fecha_inicio = ?";
        	        ps = conexion.prepareStatement(query);
        	        ps.setString(1, fecha);
        	        ResultSet resultados = ps.executeQuery();

        	        while (resultados.next()) {
        	            Prestamo prestamo = new Prestamo(
        	                resultados.getInt("codigo_libro"),
        	                resultados.getInt("codigo_socio"),
        	                resultados.getString("fecha_inicio"),
        	                resultados.getString("fecha_fin"),
        	                resultados.getString("fecha_devolucion")
        	            );
        	            prestamosPorFecha.add(prestamo);
        	        }

        	    } catch (SQLException e) {
        	        throw new BDException("Error al consultar los préstamos por fecha: " + e.getMessage());
        	    } finally {
        	        if (conexion != null) {
        	            ConfigSqlLite.cerrarConexion(conexion);
        	        }
        	    }

        	    return prestamosPorFecha;
        	}

        	// (Opcional) Si quieres mostrar todos los préstamos - útil para case 5
        	public static List<Prestamo> consultarTodosLosPrestamos() throws BDException {
        	    List<Prestamo> prestamos = new ArrayList<>();
        	    PreparedStatement ps = null;
        	    Connection conexion = null;

        	    try {
        	        conexion = ConfigSqlLite.abrirConexion();
        	        String query = "SELECT * FROM prestamo";
        	        ps = conexion.prepareStatement(query);
        	        ResultSet resultados = ps.executeQuery();

        	        while (resultados.next()) {
        	            Prestamo prestamo = new Prestamo(
        	                resultados.getInt("codigo_libro"),
        	                resultados.getInt("codigo_socio"),
        	                resultados.getString("fecha_inicio"),
        	                resultados.getString("fecha_fin"),
        	                resultados.getString("fecha_devolucion")
        	            );
        	            prestamos.add(prestamo);
        	        }

        	    } catch (SQLException e) {
        	        throw new BDException("Error al consultar todos los préstamos: " + e.getMessage());
        	    } finally {
        	        if (conexion != null) {
        	            ConfigSqlLite.cerrarConexion(conexion);
        	        }
        	    }

        	    return prestamos;
        	}

        }
