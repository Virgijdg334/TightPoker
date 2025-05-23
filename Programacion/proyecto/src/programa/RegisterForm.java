package programa;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.MaskFormatter;

/**
 * La clase RegisterForm representa la ventana de registro de nuevos usuarios.
 * Permite a los usuarios introducir sus datos personales para crear una cuenta.
 */
public class RegisterForm extends JFrame {

	private static final long serialVersionUID = 1L; // Identificador para la serialización
	private JPanel contentPane; // Panel principal de la ventana
	private JTextField textField_nombre;
	private JTextField textField_apellidos;
	private JTextField textField_nombre_usuario;
	private JPasswordField passwordField; // Campo específico para contraseñas
	private JTextField textField_edad;
	private JTextField textField_telefono;
	private JTextField textField_dni;
	private JTextField textField_codigo_postal;
	private final JLabel lblfondo = new JLabel(""); // Etiqueta para la imagen de fondo

	/**
	 * Método principal que lanza la aplicación.
	 * Asegura que la interfaz de usuario se ejecute en el Event Dispatch Thread (EDT).
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterForm frame = new RegisterForm();
					frame.setVisible(true); // Hace visible la ventana de registro
				} catch (Exception e) {
					e.printStackTrace(); // Imprime la traza de la excepción si ocurre un error
				}
			}
		});
	}

	/**
	 * Constructor de la clase RegisterForm.
	 * Inicializa y configura todos los componentes de la interfaz de usuario.
	 */
	public RegisterForm() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana al hacer clic en 'X'
		setBounds(100, 100, 484, 743); // Establece la posición y el tamaño de la ventana
		setUndecorated(true); // Elimina los bordes y la barra de título para un diseño personalizado

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Borde vacío para el padding interno
		contentPane.setLayout(null); // Usa un layout nulo para posicionamiento absoluto de los componentes
		setContentPane(contentPane); // Establece este panel como el contenido principal de la ventana

		// Título principal de la ventana de registro
		JLabel lblNewLabel1 = new JLabel("Registro");
		lblNewLabel1.setForeground(new Color(235, 227, 194)); // Color del texto
		lblNewLabel1.setBounds(151, 52, 181, 95); // Posición y tamaño
		lblNewLabel1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 54)); // Fuente y tamaño
		contentPane.add(lblNewLabel1); // Añade el título al panel de contenido

		// --- Campos de texto y sus etiquetas ---

		// Nombre
		JLabel Label_nombre = new JLabel("Nombre");
		Label_nombre.setForeground(new Color(235, 227, 194));
		Label_nombre.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
		Label_nombre.setBounds(76, 190, 124, 14);
		contentPane.add(Label_nombre);

		textField_nombre = new JTextField();
		textField_nombre.setBounds(218, 187, 181, 20);
		textField_nombre.setColumns(10); // Ancho de columna sugerido
		contentPane.add(textField_nombre);

		// Apellidos
		JLabel Label_apellidos = new JLabel("Apellidos");
		Label_apellidos.setForeground(new Color(235, 227, 194));
		Label_apellidos.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
		Label_apellidos.setBounds(76, 230, 89, 14);
		contentPane.add(Label_apellidos);

		textField_apellidos = new JTextField();
		textField_apellidos.setColumns(10);
		textField_apellidos.setBounds(218, 227, 181, 20);
		contentPane.add(textField_apellidos);

		// Nombre de Usuario
		JLabel Label_nombre_usuario = new JLabel("Nombre de Usuario");
		Label_nombre_usuario.setForeground(new Color(235, 227, 194));
		Label_nombre_usuario.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
		Label_nombre_usuario.setBounds(76, 270, 124, 14);
		contentPane.add(Label_nombre_usuario);

		textField_nombre_usuario = new JTextField();
		textField_nombre_usuario.setBounds(218, 267, 181, 20);
		textField_nombre_usuario.setColumns(10);
		contentPane.add(textField_nombre_usuario);

		// Contraseña
		JLabel Label_contraseña = new JLabel("Contraseña");
		Label_contraseña.setForeground(new Color(235, 227, 194));
		Label_contraseña.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
		Label_contraseña.setBounds(76, 310, 124, 14);
		contentPane.add(Label_contraseña);

		passwordField = new JPasswordField();
		passwordField.setBounds(218, 308, 181, 20);
		contentPane.add(passwordField);

		// Edad
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setForeground(new Color(235, 227, 194));
		lblEdad.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 15));
		lblEdad.setBounds(76, 350, 124, 14);
		contentPane.add(lblEdad);

		textField_edad = new JTextField();
		textField_edad.setColumns(10);
		textField_edad.setBounds(218, 348, 181, 20);
		contentPane.add(textField_edad);

		// Número de Teléfono
		JLabel Label_telefono = new JLabel("Número de Teléfono");
		Label_telefono.setForeground(new Color(235, 227, 194));
		Label_telefono.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
		Label_telefono.setBounds(76, 390, 124, 14);
		contentPane.add(Label_telefono);

		textField_telefono = new JTextField();
		textField_telefono.setColumns(10);
		textField_telefono.setBounds(218, 387, 181, 20);
		contentPane.add(textField_telefono);

		// DNI
		JLabel Label_dni = new JLabel("DNI");
		Label_dni.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
		Label_dni.setForeground(new Color(235, 227, 194)); // Corregido el color para consistencia
		Label_dni.setBounds(77, 430, 48, 14);
		contentPane.add(Label_dni);

		textField_dni = new JTextField();
		textField_dni.setColumns(10);
		textField_dni.setBounds(218, 427, 181, 20);
		contentPane.add(textField_dni);

		// Código Postal
		JLabel Label_codigo_postal = new JLabel("Código Postal");
		Label_codigo_postal.setForeground(new Color(235, 227, 194));
		Label_codigo_postal.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
		Label_codigo_postal.setBounds(76, 470, 124, 14);
		contentPane.add(Label_codigo_postal);

		textField_codigo_postal = new JTextField();
		textField_codigo_postal.setColumns(10);
		textField_codigo_postal.setBounds(218, 467, 181, 20);
		contentPane.add(textField_codigo_postal);

		// Este bloque de código para MaskFormatter no se estaba utilizando para ningún JFormattedTextField.
		// Si se necesita un campo de fecha, se debe usar JFormattedTextField.
		try {
			MaskFormatter mascaraFecha = new MaskFormatter("##/##/####");
			mascaraFecha.setPlaceholderCharacter('_');
			// Si quisieras aplicarlo a un campo, sería algo como:
			// JFormattedTextField formattedTextField_fecha = new JFormattedTextField(mascaraFecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// --- CheckBox de Términos y Condiciones ---
		JCheckBox CheckBox_Terminos = new JCheckBox("Aceptar Términos y Condiciones");
		CheckBox_Terminos.setForeground(new Color(255, 255, 255));
		CheckBox_Terminos.setBackground(new Color(3, 65, 37));
		CheckBox_Terminos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		CheckBox_Terminos.setBounds(137, 531, 209, 23);
		contentPane.add(CheckBox_Terminos);

		// --- Botón "Registrarse" del formulario ---
		JButton btn_formulario = new JButton("Registrarse");
		btn_formulario.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_formulario.setBackground(new Color(196, 49, 25)); // Color de fondo
		btn_formulario.setForeground(Color.WHITE); // Color del texto
		btn_formulario.setBounds(167, 582, 141, 37); // Posición y tamaño
		contentPane.add(btn_formulario);

		// Aplica estilo redondeado al botón de formulario
		btn_formulario.setFocusPainted(false);
		btn_formulario.setBorderPainted(false);
		btn_formulario.setContentAreaFilled(false);
		btn_formulario.setOpaque(false);

		btn_formulario.setUI(new BasicButtonUI() {
			@Override
			public void paint(Graphics g, JComponent c) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(btn_formulario.getBackground());
				g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30); // Rellena con esquinas redondeadas
				g2.dispose();
				super.paint(g, c); // Pinta el texto y el icono del botón
			}
		});

		// --- ActionListener para el botón "Registrarse" ---
		btn_formulario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener datos de los campos del formulario, eliminando espacios en blanco al inicio/final
				String nombre = textField_nombre.getText().trim();
				String apellidos = textField_apellidos.getText().trim();
				String nombreUsuario = textField_nombre_usuario.getText().trim();
				String contraseña = new String(passwordField.getPassword()); // Obtiene la contraseña de forma segura
				String edad = textField_edad.getText().trim();
				String telefono = textField_telefono.getText().trim();
				String dni = textField_dni.getText().trim();
				String codigoPostal = textField_codigo_postal.getText().trim();

				// --- Validaciones básicas del formulario ---
				if (nombre.isEmpty() || apellidos.isEmpty() || nombreUsuario.isEmpty() || contraseña.isEmpty() ||
						edad.isEmpty() || telefono.isEmpty() || dni.isEmpty() || codigoPostal.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos obligatorios.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
					return; // Detiene la ejecución si hay campos vacíos
				}

				if (!CheckBox_Terminos.isSelected()) {
					JOptionPane.showMessageDialog(null, "Debes aceptar los Términos y Condiciones para registrarte.", "Términos No Aceptados", JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Validación de longitud del código postal (asumiendo 5 dígitos para España)
				if (codigoPostal.length() != 5) {
					JOptionPane.showMessageDialog(null, "Introduce un código postal válido de 5 dígitos.", "Código Postal Inválido", JOptionPane.WARNING_MESSAGE);
					return;
				}
				// Puedes añadir más validaciones aquí (ej. formato de teléfono, DNI, edad numérica)

				try {
					// Establecer conexión a la base de datos
					ConexionMySQL conn = new ConexionMySQL("root", "password", "sql7780337");
					conn.conectar();

					// Prepara la sentencia SQL para insertar un nuevo usuario.
					// Se recomienda usar PreparedStatement para prevenir inyección SQL en un entorno real.
					String sql = "INSERT INTO usuario (nombre, apellidos, nombreUsuario, contraseña, edad, telefono, dni, codigoPostal) VALUES ('" +
							nombre + "', '" + apellidos + "', '" + nombreUsuario + "', '" + contraseña + "', '" +
							edad + "', '" + telefono + "', '" + dni + "', '" + codigoPostal + "')";
					conn.ejecutarInsertDeleteUpdate(sql); // Ejecuta la inserción

					// Mensaje de registro exitoso
					JOptionPane.showMessageDialog(null, "¡Registro exitoso! Ahora dirígete a la página de Log In e ingresa las credenciales de tu cuenta.", "Registro Completado", JOptionPane.INFORMATION_MESSAGE);

					// Limpiar campos después del registro exitoso
					textField_nombre.setText("");
					textField_apellidos.setText("");
					textField_nombre_usuario.setText("");
					passwordField.setText("");
					textField_telefono.setText("");
					textField_dni.setText("");
					textField_edad.setText("");
					textField_codigo_postal.setText("");
					CheckBox_Terminos.setSelected(false); // Desmarca el checkbox

					// Abre la pantalla de carga y cierra la ventana actual
					PantallaCarga2 P1 = new PantallaCarga2();
					P1.setVisible(true);
					dispose();

				} catch (Exception ex) {
					// Manejo de errores durante la conexión o inserción en la base de datos
					JOptionPane.showMessageDialog(null, "Error durante el registro: " + ex.getMessage(), "Error de Registro", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace(); // Imprime la traza completa del error para depuración
				}
			}
		});

		// --- Botón de Volver a la Página Principal (Casa) ---
		URL imageUrl = getClass().getResource("/imagenes/casa.png"); // Ruta de la imagen del icono
		ImageIcon icon = null;

		if (imageUrl != null) {
			icon = new ImageIcon(imageUrl);
			// Escala la imagen para que encaje en el botón
			Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
		} else {
			System.out.println("Imagen 'casa.png' no encontrada.");
			icon = new ImageIcon(); // Icono vacío si no se encuentra la imagen
		}

		// Crea el botón redondo personalizado con el icono de casa
		JButton botonRedondo3 = new JButton(icon) {
			@Override
			protected void paintComponent(Graphics g) {
				g.fillOval(0, 0, getWidth(), getHeight()); // Rellena el óvalo como fondo del botón
				super.paintComponent(g); // Pinta el icono y el texto
			}

			@Override
			protected void paintBorder(Graphics g) {
				g.drawOval(0, 0, getWidth() - 1, getHeight() - 1); // Dibuja el borde del óvalo
			}

			@Override
			public boolean contains(int x, int y) {
				// Define el área de clic del botón como un círculo
				int radius = getWidth() / 2;
				return (Math.pow(x - radius, 2) + Math.pow(y - radius, 2)) <= Math.pow(radius, 2);
			}
		};
		botonRedondo3.setBounds(213, 635, 60, 60); // Posición y tamaño del botón
		botonRedondo3.setContentAreaFilled(false); // No rellena el área rectangular del botón
		botonRedondo3.setFocusPainted(false); // No pinta el recuadro de foco
		botonRedondo3.setBorderPainted(false); // No pinta el borde por defecto
		botonRedondo3.setForeground(new Color(6, 66, 47)); // Color para el borde del botón
		botonRedondo3.setFont(new Font("Arial", Font.BOLD, 16));

		// ActionListener para el botón de volver a la página principal
		botonRedondo3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PokerGUI p1 = new PokerGUI(); // Crea una nueva instancia de PokerGUI
				dispose(); // Cierra la ventana actual (RegisterForm)
				p1.setVisible(true); // Hace visible la ventana principal
			}
		});
		getContentPane().add(botonRedondo3); // Añade el botón al panel de contenido

		// --- Imagen de fondo ---
		lblfondo.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoPoker2.png"))); // Carga la imagen de fondo
		lblfondo.setBounds(-11, 0, 518, 757); // Posición y tamaño de la imagen
		contentPane.add(lblfondo); // Añade la imagen de fondo al panel de contenido
	}
}