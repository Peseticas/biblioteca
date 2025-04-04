package Biblioteca.excepciones;

public class BDException extends Exception {
    public static final String ERROR_CARGAR_DRIVER = "Error al cargar el driver: ";
    public static final String ERROR_ABRIR_CONEXION = "Error al abrir la conexión: ";
    public static final String ERROR_CERRAR_CONEXION = "Error al cerrar la conexión: ";

    public BDException(String mensaje) {
        super(mensaje);
    }
}

