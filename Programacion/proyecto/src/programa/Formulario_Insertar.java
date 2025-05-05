package programa;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Formulario_Insertar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_nombre;
	private JTextField txt_edad;
	private final JLabel lblfondo = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Formulario_Insertar frame = new Formulario_Insertar();
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
	public Formulario_Insertar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 484, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("d");
		lblNewLabel_2.setForeground(new Color(235, 227, 194));
		lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 15));
		lblNewLabel_2.setBounds(83, 396, 124, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Apellidos");
		lblNewLabel_1.setForeground(new Color(235, 227, 194));
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 15));
		lblNewLabel_1.setBounds(83, 345, 124, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel1 = new JLabel("Registro");
		lblNewLabel1.setForeground(new Color(235, 227, 194));
		lblNewLabel1.setBounds(130, 95, 228, 95);
		lblNewLabel1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 54));
		contentPane.add(lblNewLabel1);
		
		JLabel lblNewLabel = new JLabel("Nombre Completo");
		lblNewLabel.setForeground(new Color(235, 227, 194));
		lblNewLabel.setBounds(83, 287, 124, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC,15));
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 14));
		lblDni.setForeground(new Color(225, 237, 194));
		lblNewLabel.setForeground(new Color(235, 227, 194));
		lblDni.setBounds(83, 229, 48, 14);
		contentPane.add(lblDni);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 15));
		
		JLabel lblNewLabel_2_1 = new JLabel("Nombre Completo");
		lblNewLabel_2_1.setForeground(new Color(235, 227, 194));
		lblNewLabel_2_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 15));
		lblNewLabel_2_1.setBounds(83, 446, 124, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Nombre Completo");
		lblNewLabel_2_2.setForeground(new Color(235, 227, 194));
		lblNewLabel_2_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 15));
		lblNewLabel_2_2.setBounds(83, 497, 124, 14);
		contentPane.add(lblNewLabel_2_2);
		
		txt_nombre = new JTextField();
		txt_nombre.setBounds(224, 227, 181, 20);
		contentPane.add(txt_nombre);
		txt_nombre.setColumns(10);
		
		txt_edad = new JTextField();
		txt_edad.setBounds(224, 285, 181, 20);
		contentPane.add(txt_edad);
		txt_edad.setColumns(10);
		
		JButton btn_formulario = new JButton("Continuar");
		btn_formulario.setBounds(198, 533, 89, 23);
		contentPane.add(btn_formulario);
		
		lblfondo.setIcon(new ImageIcon("C:\\Users\\Alumno1\\Documents\\TightPoker\\imagenes\\fondoPoker.png"));
		lblfondo.setBounds(-11, 0, 518, 757);
		contentPane.add(lblfondo);
		

		
		setUndecorated(true);

	}
}
