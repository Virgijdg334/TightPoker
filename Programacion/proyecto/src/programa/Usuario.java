package programa;

// Clase que representa a un usuario con atributos personales y financieros
public class Usuario {

	// Atributos privados del usuario
	private String nombre_Usuario;
	private String nombre;
	private String apellidos;
	private int num_tlf;
	private double saldo;
	private long num_tarjeta;

	// Constructor inicializado para permitir añadir datos extras después
	public Usuario() {
		{
			// Inicialización de los atributos con valores por defecto
			this.nombre_Usuario = " ";
			this.nombre =  " ";
			this.apellidos = " ";
			this.num_tlf = 0;
			this.saldo = 0;
			this.num_tarjeta = 000000000000;
		}
	}

	// Constructor que inicializa todos los atributos del usuario
	public Usuario(String nombre_Usuario, String nombre, String apellidos, int num_tlf, double saldo, long num_tarjeta) {
		this.nombre_Usuario = nombre_Usuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.num_tlf = num_tlf;
		this.saldo = saldo;
		this.num_tarjeta = num_tarjeta;
	}

	// ==================== GETTERS ====================

	// Devuelve el nombre de usuario
	public String getUser() {
		return nombre_Usuario;
	}

	// Devuelve el nombre real del usuario
	public String getNombre() {
		return nombre;
	}

	// Devuelve los apellidos del usuario
	public String getApellidos() {
		return apellidos;
	}

	// Devuelve el número de teléfono del usuario
	public int getTelefono() {
		return num_tlf;
	}

	// Devuelve el saldo del usuario
	public double getSaldo() {
		return saldo;
	}

	// Devuelve el número de tarjeta del usuario
	public long getTarjeta() {
		return num_tarjeta;
	}

	// ==================== SETTERS ====================

	// Establece el nombre de usuario
	public void setNombre_Usuario(String nombre_Usuario) {
		this.nombre_Usuario = nombre_Usuario;
	}

	// Establece el nombre real
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Establece los apellidos
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	// Establece el número de teléfono
	public void setNum_tlf(int num_tlf) {
		this.num_tlf = num_tlf;
	}

	// Establece el saldo del usuario
	public void setSaldo(double nuevoSaldo) {
		this.saldo = nuevoSaldo;
	}

	// Establece el número de tarjeta
	public void setTarjeta(long nuevaTarjeta) {
		this.num_tarjeta = nuevaTarjeta;
	}
}
