package programa;

public class Usuario {
    private String nombre_Usuario;
    private String nombre;
    private String apellidos;
    private int num_tlf;
    private double saldo;
    private long num_tarjeta;

    // Constructor inicializado para permitir añadir datos extras despues
    
    public Usuario() {
    	{
    		this.nombre_Usuario = " ";
    		this.nombre =  " ";
    		this.apellidos = " ";
    		this.num_tlf = 0; 
    		this.saldo = 0;
    		this.num_tarjeta = 000000000000;
        }	
    }
    
    // Constructor
    
    public Usuario(String nombre_Usuario, String nombre, String apellidos, int num_tlf, double saldo, long num_tarjeta) {
        this.nombre_Usuario = nombre_Usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.num_tlf = num_tlf; 
        this.saldo = saldo;
        this.num_tarjeta = num_tarjeta;
    }
 
    
    // Getters
     
    public String getUser() {
    	return nombre_Usuario; 
    }
    public String getNombre() {
    	return nombre; 
    }
    public String getApellidos() {
    	return apellidos; 
    }
    public int getTelefono() {
    	return num_tlf; 
    }
    public double getSaldo() {
    	return saldo; 
    }
    public long getTarjeta() {
    	return num_tarjeta;
    }
 
    // Setters
    
	public void setNombre_Usuario(String nombre_Usuario) {
		this.nombre_Usuario = nombre_Usuario;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setNum_tlf(int num_tlf) {
		this.num_tlf = num_tlf;
	}

	public void setSaldo(double nuevoSaldo) {
		this.saldo = nuevoSaldo;
	}
    
	
}