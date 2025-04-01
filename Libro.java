package Biblioteca.Libro;

public class Libro {
	
	//Atributos
	
	private int codigo;
	private String ISBN;
	private String titulo;
	private String escritor;
	private int ano_publicacion;
	private float puntuacion;
	
	//constructor
	
	public Libro(int codigo, String iSBN, String titulo, String escritor, int ano_publicacion, float puntuacion) {
		super();
		this.codigo = codigo;
		ISBN = iSBN;
		this.titulo = titulo;
		this.escritor = escritor;
		this.ano_publicacion = ano_publicacion;
		this.puntuacion = puntuacion;
	}
	
}


	