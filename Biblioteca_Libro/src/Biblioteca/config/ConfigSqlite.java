package Biblioteca.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import Biblioteca.excepciones.BDException;

public class ConfigSqlite {

    private static final String DRIVER = "org.sqlite.JDBC";
    public static final String URL = "jdbc:sqlite:db/biblioteca.db";
	

    /**
     * Abre conexión con la base de datos SQLite
     * @return Connection
     * @throws BDException
     */
    public static Connection abrirConexion() throws BDException {
        Connection conexion = null;

        try {
            // Carga el driver
            Class.forName(DRIVER);
            ConfigSqlite config = new ConfigSqlite();  
            // Abre conexión con propiedades
            conexion = DriverManager.getConnection(URL, config.toProperties());
       
        } catch (ClassNotFoundException e) {
            throw new BDException(BDException.ERROR_CARGAR_DRIVER + e.getMessage());
        } catch (SQLException e) {
            throw new BDException(BDException.ERROR_ABRIR_CONEXION + e.getMessage());
        }

        return conexion;
    }

    /**
     * Cierra conexión con SQLite
     * @param conexion Connection
     * @throws BDException
     */
    public static void cerrarConexion(Connection conexion) throws BDException {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                throw new BDException(BDException.ERROR_CERRAR_CONEXION + e.getMessage());
            }
        }
    }


    /**
     * Configura propiedades para la conexión, incluyendo claves foráneas
     * @return Properties
     */
    public Properties toProperties() {
        Properties properties = new Properties();
        properties.setProperty("foreign_keys", "true");
        return properties;
    }
}

