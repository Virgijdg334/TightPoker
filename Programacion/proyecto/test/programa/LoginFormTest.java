package programa;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import programa.LoginForm;

public class LoginFormTest {
    @BeforeAll
    static void setup() {
        // Aquí podrías inicializar una base de datos de prueba o mocks si es necesario
        // Por ejemplo: insertar un usuario de prueba
        // Esto depende de cómo esté implementada la clase ConexionMySQL
    }

    @Test
    void testLoginConUsuarioIncorrecto() {
        String usuario = "usuarioInvalido";
        String contrasena = "contrasenaValida";
        assertFalse(LoginForm.login(usuario, contrasena));
    }

    @Test
    void testLoginConContrasenaIncorrecta() {
        String usuario = "usuarioValido";
        String contrasena = "contrasenaInvalida";
        assertFalse(LoginForm.login(usuario, contrasena));
    }

    @Test
    void testLoginConCamposVacios() {
        assertFalse(LoginForm.login("", ""));
    }

    @Test
    void testLoginConNulos() {
        assertFalse(LoginForm.login(null, null));
    }
}
