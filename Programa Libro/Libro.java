package Biblioteca.Libro;

public class Libro {
	
	//Atributos
	
	private int codigo;
	private String ISBN;
	private String titulo;
	private String escritor;
	private int año_publicacion;
	private float puntuacion;
	
	//constructor
	
	public Libro(int codigo, String ISBN, String titulo, String escritor, int año_publicacion, double puntuacion) {
		super();
		this.codigo = codigo;
		this.ISBN = ISBN;
		this.titulo = titulo;
		this.escritor = escritor;
		this.puntuacion = (float) puntuacion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEscritor() {
		return escritor;
	}

	public void setEscritor(String escritor) {
		this.escritor = escritor;
	}

	public int getAño_publicacion() {
		return getAño_publicacion();
	}

	public void setAño_publicacion(int año_publicacion) {
	}

	public float getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(float puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	
}


	