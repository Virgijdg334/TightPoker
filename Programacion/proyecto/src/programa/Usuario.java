package programa;

public class Usuario {
    private String nombre_Usuario;

    public Usuario(String nombre_Usuario) {
        this.nombre_Usuario = nombre_Usuario;
    }

    // Getters
    public String getNombre() {
    	return nombre_Usuario; 
    }
 
    // Setter
    
    public void setNombre(String NombreU) {
    	nombre_Usuario = NombreU;
	}
    
}