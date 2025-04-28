package programa;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.SQLException;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.Principal;
import java.awt.event.ActionEvent;


public class PokerGUI extends JFrame {

	ConexionMySQL bd = new ConexionMySQL("sql7774423", "sxpS4Av81l" ,"sql7774423");
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JLabel lblfondo = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PokerGUI frame = new PokerGUI();
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
	public PokerGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 484, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JButton btn_login = new JButton("Log in/Registrarse");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Formulario_Insertar f1 = new Formulario_Insertar();
				f1.setVisible(true);
			}
		});
		btn_login.setBounds(168, 424, 143, 23);
		contentPane.add(btn_login);
		
		
				
		JLabel lblNewLabel = new JLabel("TightPoker");
		lblNewLabel.setBounds(151, 55, 165, 68);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 38));
		contentPane.add(lblNewLabel);
		
		
		
		
		JButton btn_conectarse = new JButton("BBDD");
		btn_conectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					bd.conectar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btn_conectarse.setBounds(114, 315, 89, 23);
		contentPane.add(btn_conectarse);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(246, 342, 89, 23);
		contentPane.add(btnNewButton);
		
		lblfondo.setIcon(new ImageIcon("C:\\Users\\Alumno1\\Downloads\\fondoPoker.png"));
		lblfondo.setBounds(-11, 0, 518, 757);
		contentPane.add(lblfondo);

		
		contentPane.setVisible(true);
		setUndecorated(true);
		
	}
}
