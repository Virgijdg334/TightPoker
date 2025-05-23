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
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import java.text.ParseException;
import javax.swing.JPasswordField;

public class LoginForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_nombre_usuario;
	private final JLabel lblfondo = new JLabel("");
	private JTextField textField_nacimiento;
	private JPasswordField passwordField;
	private static Usuario usuarioActual;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
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
	public LoginForm() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 484, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 JFrame frame = new JFrame("Login");
	     frame.setSize(300, 200);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.getContentPane().setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(109, 344, 238, 37);
		contentPane.add(passwordField);
		
		JCheckBox chckbxRecordarMiContresea = new JCheckBox("Recordar mi contreseña");
		chckbxRecordarMiContresea.setForeground(new Color(255, 255, 255));
		chckbxRecordarMiContresea.setBackground(new Color(3, 65, 37));
		chckbxRecordarMiContresea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxRecordarMiContresea.setBounds(109, 420, 209, 23);
		contentPane.add(chckbxRecordarMiContresea);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setForeground(new Color(235, 227, 194));
		lblNombreDeUsuario.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
		lblNombreDeUsuario.setBounds(109, 191, 252, 37);
		contentPane.add(lblNombreDeUsuario);
		
		JLabel lblNewLabel1 = new JLabel("Log In");
		lblNewLabel1.setForeground(new Color(235, 227, 194));
		lblNewLabel1.setBounds(175, 49, 181, 95);
		lblNewLabel1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 54));
		contentPane.add(lblNewLabel1);
		
		JLabel Label_contraseña = new JLabel("Contraseña");
		Label_contraseña.setForeground(new Color(235, 227, 194));
		Label_contraseña.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
		Label_contraseña.setBounds(109, 319, 238, 14);
		contentPane.add(Label_contraseña);
		
		textField_nombre_usuario = new JTextField();
		textField_nombre_usuario.setToolTipText("");
		textField_nombre_usuario.setBounds(109, 228, 238, 37);
		contentPane.add(textField_nombre_usuario);
		textField_nombre_usuario.setColumns(10);
		
		
		
		
		JButton btn_login = new JButton("Log In");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreUsuario = textField_nombre_usuario.getText().trim();
				String contraseña = new String(passwordField.getPassword());

	                boolean loginSuccess = login(nombreUsuario, contraseña);

	                if (loginSuccess) {
	                    JOptionPane.showMessageDialog(frame, "Login exitoso");
	                   
	                    try {
	                    	// Crear instancia de conexión
	                    	ConexionMySQL con = new ConexionMySQL("root", "", "sql7780337");
	                    	con.conectar(); 
	            	        
	                    	String query = "SELECT * FROM usuario WHERE nombreUsuario = ?";
	            	        PreparedStatement stmt = con.getConnection().prepareStatement(query);
	            	        stmt.setString(1, nombreUsuario);
	                    	
	            	        ResultSet rs = stmt.executeQuery();
	            	        
	            	        if (rs.next()) {
		                    	String nombre = rs.getString("nombre");
	
		                        String apellidos = rs.getString("apellidos");
	
		                        String DNI = rs.getString("dni");
		                        
		                        String username = rs.getString("nombreUsuario");
		                        
		                        long num_tarjeta = rs.getLong("n_tarjeta");
		                        
		                        int num_telefono = rs.getInt("telefono");
		                        
		                        double saldo = rs.getInt("saldo");
		                        
		                        Usuario user = new Usuario(username,nombre,apellidos,num_telefono,saldo,num_tarjeta);
			                    SesionUsuario.setUsuario(user);
	            	        }
	                    	
	                    	
		                    PantallaCarga P1 = new PantallaCarga();	            
		                    P1.setVisible(true);
		                    dispose();
	                    }catch (SQLException ex) {
	            	        ex.printStackTrace();
	            	    }
	                    
	                } else {
	                    JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos");
	                }
			}           
		});
		btn_login.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_login.setBackground(new Color(196, 49, 25));
		btn_login.setForeground(Color.WHITE);
		btn_login.setBounds(161, 535, 146, 50);
		contentPane.add(btn_login);

		btn_login.setFocusPainted(false);
		btn_login.setBorderPainted(false);
		btn_login.setContentAreaFilled(false);
		btn_login.setOpaque(false);
		
		// Dibujar fondo redondeado
		btn_login.setUI(new BasicButtonUI() {
		    @Override
		    public void paint(Graphics g, JComponent c) {
		        Graphics2D g2 = (Graphics2D) g.create();
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(btn_login.getBackground());
		        g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
		        g2.dispose();
		        super.paint(g, c);
		    }
		});
		
		lblfondo.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoPoker.png")));
		lblfondo.setBounds(-11, 0, 518, 757);
		contentPane.add(lblfondo);
		

		setUndecorated(true);

	}
	public static boolean login(String nombreUsuario, String contraseña) {
	    boolean isAuthenticated = false;

	    // Crea una instancia de tu clase de conexión
	    ConexionMySQL conn = new ConexionMySQL("root", "", "sql7780337");

	    try {
	        conn.conectar();

	        String query = "SELECT * FROM usuario WHERE nombreUsuario = ? AND contraseña = ?";
	        PreparedStatement stmt = conn.getConnection().prepareStatement(query);
	        stmt.setString(1, nombreUsuario);
	        stmt.setString(2, contraseña);

	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            isAuthenticated = true;
	        }

	        rs.close();
	        stmt.close();
	        conn.desconectar();

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }

	    return isAuthenticated;
	}
}

