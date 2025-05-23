package programa;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.text.MaskFormatter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import java.text.ParseException;
import javax.swing.JPasswordField;

/** * La clase LoginForm representa la ventana de inicio de sesión de la aplicación.
 * Permite a los usuarios introducir sus credenciales (nombre de usuario y contraseña)
 * para acceder al sistema.
 */
public class LoginForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_nombre_usuario;
	private final JLabel lblfondo = new JLabel("");
	private JTextField textField_nacimiento;
	private JPasswordField passwordField;
	private static Usuario usuarioActual;


	/** * Método principal que lanza la aplicación.
	 * Asegura que la creación y visualización de la interfaz de usuario
	 * se realice en el Event Dispatch Thread (EDT) para garantizar la seguridad de los hilos.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Crea una nueva instancia de LoginForm
					LoginForm frame = new LoginForm();
					// Hace visible la ventana de login
					frame.setVisible(true);
				} catch (Exception e) {
					// Imprime la traza de la excepción si ocurre un error durante la inicialización
					e.printStackTrace();
				}
			}
		});
	}

	/** * Constructor de la clase LoginForm.
	 * Inicializa y configura todos los componentes de la interfaz de usuario.
	 */
	public LoginForm() {
		// Configura la operación por defecto al cerrar la ventana (cerrar solo esta ventana)
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Establece la posición y el tamaño de la ventana
		setBounds(100, 100, 484, 743);
		// Inicializa el panel de contenido principal de la ventana
		contentPane = new JPanel();
		// Establece un borde vacío para el padding interno
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Establece este panel como el contenido principal de la ventana
		setContentPane(contentPane);
		// Usa un layout nulo para posicionar los componentes de forma absoluta
		contentPane.setLayout(null);

		// Creación de un nuevo JFrame separado llamado "Login" (Nota: Este JFrame parece ser redundante y no se utiliza para la GUI principal)
		JFrame frame = new JFrame("Login");
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Campo de texto para la contraseña
		passwordField = new JPasswordField();
		passwordField.setBounds(109, 344, 238, 37);
		contentPane.add(passwordField);

		// Casilla de verificación para recordar la contraseña
		JCheckBox chckbxRecordarMiContresea = new JCheckBox("Recordar mi contreseña");
		chckbxRecordarMiContresea.setForeground(new Color(255, 255, 255));
		chckbxRecordarMiContresea.setBackground(new Color(3, 65, 37));
		chckbxRecordarMiContresea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxRecordarMiContresea.setBounds(109, 420, 209, 23);
		contentPane.add(chckbxRecordarMiContresea);

		// Etiqueta para el campo de nombre de usuario
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setForeground(new Color(235, 227, 194));
		lblNombreDeUsuario.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
		lblNombreDeUsuario.setBounds(109, 191, 252, 37);
		contentPane.add(lblNombreDeUsuario);

		// Etiqueta del título principal "Log In"
		JLabel lblNewLabel1 = new JLabel("Log In");
		lblNewLabel1.setForeground(new Color(235, 227, 194));
		lblNewLabel1.setBounds(175, 49, 181, 95);
		lblNewLabel1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 54));
		contentPane.add(lblNewLabel1);

		// Etiqueta para el campo de contraseña
		JLabel Label_contraseña = new JLabel("Contraseña");
		Label_contraseña.setForeground(new Color(235, 227, 194));
		Label_contraseña.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
		Label_contraseña.setBounds(109, 319, 238, 14);
		contentPane.add(Label_contraseña);

		// Campo de texto para el nombre de usuario
		textField_nombre_usuario = new JTextField();
		textField_nombre_usuario.setToolTipText("");
		textField_nombre_usuario.setBounds(109, 228, 238, 37);
		contentPane.add(textField_nombre_usuario);
		textField_nombre_usuario.setColumns(10);

		// Botón de "Log In"
		JButton btn_login = new JButton("Log In");
		// Añade un ActionListener para manejar el evento de clic del botón
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtiene el texto del campo de nombre de usuario y elimina espacios en blanco
				String nombreUsuario = textField_nombre_usuario.getText().trim();
				// Obtiene la contraseña del campo de contraseña
				String contraseña = new String(passwordField.getPassword());

				// Llama al método login para autenticar al usuario
				boolean loginSuccess = login(nombreUsuario, contraseña);

				// Comprueba si el inicio de sesión fue exitoso
				if (loginSuccess) {
					// Muestra un mensaje de éxito
					JOptionPane.showMessageDialog(frame, "Login exitoso");

					try {
						// Crea una instancia de la clase de conexión a la base de datos
						ConexionMySQL con = new ConexionMySQL("root", "password", "sql7780337");
						// Establece la conexión a la base de datos
						con.conectar();

						// Define la consulta SQL para seleccionar todos los datos del usuario
						String query = "SELECT * FROM usuario WHERE nombreUsuario = ?";
						// Prepara la sentencia SQL para prevenir inyección SQL
						PreparedStatement stmt = con.getConnection().prepareStatement(query);
						// Establece el valor del parámetro en la consulta
						stmt.setString(1, nombreUsuario);

						// Ejecuta la consulta y obtiene el conjunto de resultados
						ResultSet rs = stmt.executeQuery();

						// Si se encuentra un registro de usuario
						if (rs.next()) {
							// Recupera los datos del usuario de la base de datos
							String nombre = rs.getString("nombre");
							String apellidos = rs.getString("apellidos");
							String DNI = rs.getString("dni"); // (Nota: DNI está declarado pero no se usa en el constructor de Usuario)
							String username = rs.getString("nombreUsuario");
							long num_tarjeta = rs.getLong("n_tarjeta");
							int num_telefono = rs.getInt("telefono");
							double saldo = rs.getInt("saldo");

							// Crea un nuevo objeto Usuario con los datos recuperados
							Usuario user = new Usuario(username,nombre,apellidos,num_telefono,saldo,num_tarjeta);
							// Establece el usuario actual en la sesión de usuario
							SesionUsuario.setUsuario(user);
						}

						// Abre la pantalla de carga
						PantallaCarga P1 = new PantallaCarga();
						P1.setVisible(true);
						// Cierra la ventana actual (LoginForm)
						dispose();
					}catch (SQLException ex) {
						// Imprime la traza de la excepción si hay un error de SQL
						ex.printStackTrace();
					}

				} else {
					// Muestra un mensaje de error si el login falla
					JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos");
				}
			}
		});
		// Configura la fuente del botón de login
		btn_login.setFont(new Font("Tahoma", Font.BOLD, 17));
		// Configura el color de fondo del botón
		btn_login.setBackground(new Color(196, 49, 25));
		// Configura el color del texto del botón
		btn_login.setForeground(Color.WHITE);
		// Establece la posición y el tamaño del botón
		btn_login.setBounds(161, 535, 146, 50);
		// Añade el botón al panel de contenido
		contentPane.add(btn_login);

		// Configuración para hacer el botón redondeado y sin bordes por defecto
		btn_login.setFocusPainted(false);
		btn_login.setBorderPainted(false);
		btn_login.setContentAreaFilled(false);
		btn_login.setOpaque(false);

		// Personaliza el UI del botón para dibujar un fondo redondeado
		btn_login.setUI(new BasicButtonUI() {
			@Override
			public void paint(Graphics g, JComponent c) {
				// Crea una copia del objeto Graphics para no afectar el contexto de pintado original
				Graphics2D g2 = (Graphics2D) g.create();
				// Habilita el antialiasing para bordes más suaves
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				// Establece el color de fondo del botón para el relleno
				g2.setColor(btn_login.getBackground());
				// Rellena un rectángulo con esquinas redondeadas
				g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
				// Libera los recursos gráficos
				g2.dispose();
				// Llama al método paint de la superclase para dibujar el texto y el icono del botón
				super.paint(g, c);
			}
		});

		// Configura la imagen de fondo
		lblfondo.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoPoker.png")));
		lblfondo.setBounds(-11, 0, 518, 757);
		contentPane.add(lblfondo);

		// Elimina la barra de título y los bordes de la ventana para un diseño personalizado
		setUndecorated(true);

	}

	/**
	 * Método estático para autenticar un usuario contra la base de datos.
	 * @return true si las credenciales son correctas, false en caso contrario.
	 */
	public static boolean login(String nombreUsuario, String contraseña) {
		boolean isAuthenticated = false;

		// Crea una instancia de la clase de conexión a la base de datos
		ConexionMySQL conn = new ConexionMySQL("root", "password", "sql7780337");

		try {
			// Establece la conexión a la base de datos
			conn.conectar();

			// Define la consulta SQL para verificar el nombre de usuario y la contraseña
			String query = "SELECT * FROM usuario WHERE nombreUsuario = ? AND contraseña = ?";
			// Prepara la sentencia SQL para prevenir inyección SQL
			PreparedStatement stmt = conn.getConnection().prepareStatement(query);
			// Establece los valores de los parámetros en la consulta
			stmt.setString(1, nombreUsuario);
			stmt.setString(2, contraseña);

			// Ejecuta la consulta y obtiene el conjunto de resultados
			ResultSet rs = stmt.executeQuery();

			// Si se encuentra un registro, el usuario está autenticado
			if (rs.next()) {
				isAuthenticated = true;
			}

			// Cierra el ResultSet, PreparedStatement y la conexión para liberar recursos
			rs.close();
			stmt.close();
			conn.desconectar();

		} catch (SQLException ex) {
			// Imprime la traza de la excepción si hay un error de SQL
			ex.printStackTrace();
		}

		// Devuelve el resultado de la autenticación
		return isAuthenticated;
	}
}