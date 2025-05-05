package programa;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Formulario_Insertar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_nombre;
	private JTextField txt_edad;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre Completo");
		lblNewLabel.setBounds(28, 103, 89, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(28, 67, 48, 14);
		contentPane.add(lblDni);
		
		txt_nombre = new JTextField();
		txt_nombre.setBounds(145, 64, 181, 20);
		contentPane.add(txt_nombre);
		txt_nombre.setColumns(10);
		
		txt_edad = new JTextField();
		txt_edad.setBounds(145, 100, 181, 20);
		contentPane.add(txt_edad);
		txt_edad.setColumns(10);
		
		JButton btn_formulario = new JButton("Continuar");
		btn_formulario.setBounds(170, 210, 89, 23);
		contentPane.add(btn_formulario);
		
		JLabel lblNewLabel_1 = new JLabel("Poronga");
		lblNewLabel_1.setBounds(183, 11, 46, 14);
		contentPane.add(lblNewLabel_1);
	}
}
