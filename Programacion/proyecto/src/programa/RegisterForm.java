package programa;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import java.text.ParseException;
import javax.swing.JPasswordField;

public class RegisterForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_nombre;
	private JTextField textField_nombre_usuario;
	private final JLabel lblfondo = new JLabel("");
	private JTextField textField_apellidos;
	private JTextField textField_codigo_postal;
	private JTextField textField_edad;
	private JTextField textField_telefono;
	private JTextField textField_dni;
	private JPasswordField passwordField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterForm frame = new RegisterForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterForm() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 484, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_edad = new JTextField();
		textField_edad.setColumns(10);
		textField_edad.setBounds(218, 348, 181, 20);
		contentPane.add(textField_edad);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(218, 308, 181, 20);
		contentPane.add(passwordField);
		
		JCheckBox CheckBox_Terminos = new JCheckBox("Aceptar Terminos y Condiciones");
		CheckBox_Terminos.setForeground(new Color(255, 255, 255));
		CheckBox_Terminos.setBackground(new Color(3, 65, 37));
		CheckBox_Terminos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		CheckBox_Terminos.setBounds(137, 531, 209, 23);
		contentPane.add(CheckBox_Terminos);
		
		textField_apellidos = new JTextField();
		textField_apellidos.setColumns(10);
		textField_apellidos.setBounds(218, 227, 181, 20);
		contentPane.add(textField_apellidos);

		textField_codigo_postal = new JTextField();
		textField_codigo_postal.setColumns(10);
		textField_codigo_postal.setBounds(218, 467, 181, 20);
		contentPane.add(textField_codigo_postal);

		try {
		    MaskFormatter mascaraFecha = new MaskFormatter("##/##/####");
		    mascaraFecha.setPlaceholderCharacter('_');
		} catch (ParseException e) {
		    e.printStackTrace();
		}


		textField_telefono = new JTextField();
		textField_telefono.setColumns(10);
		textField_telefono.setBounds(218, 387, 181, 20);
		contentPane.add(textField_telefono);

		textField_dni = new JTextField();
		textField_dni.setColumns(10);
		textField_dni.setBounds(218, 427, 181, 20);
		contentPane.add(textField_dni);

		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setForeground(new Color(235, 227, 194));
		lblEdad.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 15));
		lblEdad.setBounds(76, 350, 124, 14);
		contentPane.add(lblEdad);
		
		JLabel Label_codigo_postal = new JLabel("Codigo Postal");
		Label_codigo_postal.setForeground(new Color(235, 227, 194));
		Label_codigo_postal.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
		Label_codigo_postal.setBounds(76, 470, 124, 14);
		contentPane.add(Label_codigo_postal);
		
		JLabel Label_nombre_usuario = new JLabel("Nombre de Usuario");
		Label_nombre_usuario.setForeground(new Color(235, 227, 194));
		Label_nombre_usuario.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
		Label_nombre_usuario.setBounds(76, 270, 124, 14);
		contentPane.add(Label_nombre_usuario);
		
		JLabel Label_apellidos = new JLabel("Apellidos");
		Label_apellidos.setForeground(new Color(235, 227, 194));
		Label_apellidos.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
		Label_apellidos.setBounds(76, 230, 89, 14);
		contentPane.add(Label_apellidos);
		
		JLabel lblNewLabel1 = new JLabel("Registro");
		lblNewLabel1.setForeground(new Color(235, 227, 194));
		lblNewLabel1.setBounds(151, 52, 181, 95);
		lblNewLabel1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 54));
		contentPane.add(lblNewLabel1);
		
		JLabel Label_nombre = new JLabel("Nombre");
		Label_nombre.setForeground(new Color(235, 227, 194));
		Label_nombre.setBounds(76, 190, 124, 14);
		contentPane.add(Label_nombre);
		Label_nombre.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
		
		JLabel Label_dni = new JLabel("DNI");
		Label_dni.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
		Label_dni.setForeground(new Color(225, 237, 194));
		Label_nombre.setForeground(new Color(235, 227, 194));
		Label_dni.setBounds(77, 430, 48, 14);
		contentPane.add(Label_dni);
		Label_nombre.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
		
		JLabel Label_contraseña = new JLabel("Contraseña");
		Label_contraseña.setForeground(new Color(235, 227, 194));
		Label_contraseña.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
		Label_contraseña.setBounds(76, 310, 124, 14);
		contentPane.add(Label_contraseña);
		
		JLabel Label_telefono = new JLabel("Numero de Teléfono");
		Label_telefono.setForeground(new Color(235, 227, 194));
		Label_telefono.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
		Label_telefono.setBounds(76, 390, 124, 14);
		contentPane.add(Label_telefono);
		
		textField_nombre = new JTextField();
		textField_nombre.setBounds(218, 187, 181, 20);
		contentPane.add(textField_nombre);
		textField_nombre.setColumns(10);
		
		textField_nombre_usuario = new JTextField();
		textField_nombre_usuario.setBounds(218, 267, 181, 20);
		contentPane.add(textField_nombre_usuario);
		textField_nombre_usuario.setColumns(10);
		
		JButton btn_formulario = new JButton("Registrarse");
		btn_formulario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener datos del formulario
		        String nombre = textField_nombre.getText().trim();
		        String apellidos = textField_apellidos.getText().trim();
		        String nombreUsuario = textField_nombre_usuario.getText().trim();
		        String contraseña = new String(passwordField.getPassword());
		        String edad = textField_edad.getText().trim();
		        String telefono = textField_telefono.getText().trim();
		        String dni = textField_dni.getText().trim();
		        String codigoPostal = textField_codigo_postal.getText().trim();
		        // Puedes declararlo como atributo si necesitas el valor.

		        // Validación básica
		        if (nombre.isEmpty() || apellidos.isEmpty() || nombreUsuario.isEmpty() || contraseña.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos obligatorios.");
		            return;
		        }

		        if (!CheckBox_Terminos.isSelected()) {
		            JOptionPane.showMessageDialog(null, "Debes aceptar los Términos y Condiciones.");
		            return;
		        }

		        if (codigoPostal.length() > 5) {
		        	JOptionPane.showMessageDialog(null, "Introduce un codigo postal valido");
		        	return;
		        }
		        
		        try {
		            // Establecer conexión a la base de datos
		        	ConexionMySQL conn = new ConexionMySQL("sql7778758", "kqnAkZuehU", "sql7778758");
                    conn.conectar();

                    String sql = "INSERT INTO usuario (nombre, apellidos, nombreUsuario, contraseña, edad, telefono, dni, codigoPostal) VALUES ('" + nombre + "', '" + apellidos + "', '" + nombreUsuario + "', '" + contraseña + "', '" + edad + "', '" + telefono + "', '" + dni + "', '" + codigoPostal + "')";
                    conn.ejecutarInsertDeleteUpdate(sql);

		            // Simulación de registro exitoso
		            JOptionPane.showMessageDialog(null, "¡Registro exitoso!");
		            // Limpiar campos después del registro
		            textField_nombre.setText("");
		            textField_apellidos.setText("");
		            textField_nombre_usuario.setText("");
		            passwordField.setText("");
		            textField_telefono.setText("");
		            textField_dni.setText("");
		            textField_edad.setText("");
		            textField_codigo_postal.setText("");
		            CheckBox_Terminos.setSelected(false);
		            
		            PantallaCarga P1 = new PantallaCarga();	            
                    P1.setVisible(true);
                    dispose();

		        } catch (Exception ex) {
		            // Manejo de errores
		            JOptionPane.showMessageDialog(null, "Error durante el registro: " + ex.getMessage());
		        }
		    }
		});
			
		btn_formulario.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_formulario.setBackground(new Color(196, 49, 25));
		btn_formulario.setForeground(Color.WHITE);
		btn_formulario.setBounds(167, 582, 141, 37);
		contentPane.add(btn_formulario);

		btn_formulario.setFocusPainted(false);
		btn_formulario.setBorderPainted(false);
		btn_formulario.setContentAreaFilled(false);
		btn_formulario.setOpaque(false);
		
		// Dibujar fondo redondeado
		btn_formulario.setUI(new BasicButtonUI() {
		    @Override
		    public void paint(Graphics g, JComponent c) {
		        Graphics2D g2 = (Graphics2D) g.create();
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(btn_formulario.getBackground());
		        g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
		        g2.dispose();
		        super.paint(g, c);
		    }
		});
		
		lblfondo.setIcon(new ImageIcon("C:\\Users\\Alumno1\\Documents\\TightPoker\\imagenes\\fondoPoker2.png"));
		lblfondo.setBounds(-11, 0, 518, 757);
		contentPane.add(lblfondo);
		

		
		setUndecorated(true);

	}
}