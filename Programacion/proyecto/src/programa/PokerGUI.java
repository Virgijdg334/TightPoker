package programa;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

import java.sql.SQLException;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.security.Principal;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JSeparator;

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
		
		JButton btn_login = new JButton("Log in");
		btn_login.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn_login.setBackground(new Color(196, 49, 25));
		btn_login.setForeground(Color.WHITE);
		btn_login.setBounds(133, 274, 228, 78);
		
				// Redondear el botón y eliminar bordes
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
						
								// Acción del botón
								btn_login.addActionListener(new ActionListener() {
								    public void actionPerformed(ActionEvent e) {
								        Formulario_Insertar f1 = new Formulario_Insertar();
								        f1.setVisible(true);
								    }
								});
										

										
										JPanel panelRombo_1 = new JPanel() {
											protected void paintComponent(Graphics g) {
												super.paintComponent(g);
												Graphics2D g2 = (Graphics2D) g;

												int width = getWidth();
												int height = getHeight();

												int[] xPoints = { width / 2, width, width / 2, 0 };
												int[] yPoints = { 0, height / 2, height, height / 2 };

												g2.setColor(new Color(235, 227, 194)); // color similar al título
												g2.fillPolygon(xPoints, yPoints, 4);
											}
										};
										panelRombo_1.setOpaque(false);
										panelRombo_1.setBounds(405, 370, 20, 20);
										contentPane.add(panelRombo_1);
										
										// Panel con rombo personalizado
										JPanel panelRombo = new JPanel() {
											@Override
											protected void paintComponent(Graphics g) {
												super.paintComponent(g);
												Graphics2D g2 = (Graphics2D) g;

												int width = getWidth();
												int height = getHeight();

												int[] xPoints = { width / 2, width, width / 2, 0 };
												int[] yPoints = { 0, height / 2, height, height / 2 };

												g2.setColor(new Color(235, 227, 194)); // color similar al título
												g2.fillPolygon(xPoints, yPoints, 4);
											}
										};
										panelRombo.setBackground(new Color(128, 64, 0));
										panelRombo.setOpaque(false);
										panelRombo.setBounds(61, 370, 20, 20); // Posición del rombo
										contentPane.add(panelRombo);
										
										JSeparator separator = new JSeparator();
										separator.setForeground(new Color(225, 227, 194));
										separator.setBackground(new Color(225, 227, 194));
										separator.setBounds(101, 380, 282, 3);
										contentPane.add(separator);
										
										JButton btn_register = new JButton("Register");
										btn_register.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
											}
										});
										
										btn_register.setFont(new Font("Tahoma", Font.BOLD, 24));
										btn_register.setForeground(Color.WHITE);
										btn_register.setBackground(new Color(196, 49, 25));
										btn_register.setBounds(133, 412, 228, 78);
										
												// Hacer el botón redondeado y sin bordes visibles
												btn_register.setFocusPainted(false);
												btn_register.setBorderPainted(false);
												btn_register.setContentAreaFilled(false);
												btn_register.setOpaque(false);
												
														// Dibujar el fondo redondeado
														btn_register.setUI(new BasicButtonUI() {
														    @Override
														    public void paint(Graphics g, JComponent c) {
														        Graphics2D g2 = (Graphics2D) g.create();
														        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
														        g2.setColor(btn_register.getBackground());
														        g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
														        g2.dispose();
														        super.paint(g, c);
														    }
														});
														
																contentPane.add(btn_register);
								
										contentPane.add(btn_login);
		
		
				
		JLabel lblNewLabel = new JLabel("TightPoker");
		lblNewLabel.setForeground(new Color(235, 227, 194));
		lblNewLabel.setBounds(133, 66, 228, 95);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 54));
		contentPane.add(lblNewLabel);
		
		lblfondo.setIcon(new ImageIcon("C:\\Users\\Alumno1\\Documents\\TightPoker\\imagenes\\fondoPoker.png"));
		lblfondo.setBounds(-11, 0, 518, 757);
		contentPane.add(lblfondo);

		
		contentPane.setVisible(true);
		setUndecorated(true);
		
	}
}
