package ejemploBD.modelo.modelo;

public class Libro {
	
	//Atributos
	
	private int codigo;
	private String ISBN;
	private String titulo;
	private String escritor;
	private int anoPublicacion;
	private float puntuacion;
	
	//constructor
	
	public Libro(int codigo, String iSBN, String titulo, String escritor, int anoPublicacion, float puntuacion) {
		super();
		this.codigo = codigo;
		ISBN = iSBN;
		this.titulo = titulo;
		this.escritor = escritor;
		this.anoPublicacion = anoPublicacion;
		this.puntuacion = puntuacion;
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

	public int getAno_publicacion() {
		return anoPublicacion;
	}

	public void setAno_publicacion(int ano_publicacion) {
		this.anoPublicacion = ano_publicacion;
	}

	public float getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(float puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	
	
}


	