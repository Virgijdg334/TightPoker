package programa;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.net.URL;
import java.security.Principal;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JSeparator;

public class PaginaPrincipal extends JFrame {

	ConexionMySQL conn = new ConexionMySQL("root", "password", "sql7780337");
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JLabel lblfondo = new JLabel("");
	Usuario actual = SesionUsuario.getUsuario();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaginaPrincipal frame = new PaginaPrincipal();
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
	public PaginaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 484, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JButton btn_login1 = new JButton("Log in");
		btn_login1.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn_login1.setBackground(new Color(196, 49, 25));
		btn_login1.setForeground(Color.WHITE);
		btn_login1.setBounds(133, 274, 228, 78);
		
				// Redondear el botón y eliminar bordes
				btn_login1.setFocusPainted(false);
				btn_login1.setBorderPainted(false);
				btn_login1.setContentAreaFilled(false);
				btn_login1.setOpaque(false);
				
						// Dibujar fondo redondeado
						btn_login1.setUI(new BasicButtonUI() {
						    @Override
						    public void paint(Graphics g, JComponent c) {
						        Graphics2D g2 = (Graphics2D) g.create();
						        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						        g2.setColor(btn_login1.getBackground());
						        g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
						        g2.dispose();
						        super.paint(g, c);
						    }
						});


		
		JButton btn_Partidas_Cercanas = new JButton("Partidas Cercanas");
		btn_Partidas_Cercanas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PartidasCercanas P1 = new PartidasCercanas();
				P1.setVisible(true);
				
			}
		});
		
		URL imageUrl1 = getClass().getResource("/imagenes/fotoperfil3.png");
		ImageIcon icon1 = null;

		if (imageUrl1 != null) {
		    icon1 = new ImageIcon(imageUrl1);
		    Image image = icon1.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		    icon1 = new ImageIcon(image);
		} else {
		    System.out.println("Imagen no encontrada");
		    // Puedes usar un icono por defecto si falla
		    icon1 = new ImageIcon(); // o poner null si lo prefieres
		}

		// Crear el botón redondo con el ícono cargado
		JButton botonRedondo = new JButton(icon1) {
		    @Override
		    protected void paintComponent(Graphics g) {
		        g.fillOval(0, 0, getWidth(), getHeight());
		        super.paintComponent(g);
		    }

		    @Override
		    protected void paintBorder(Graphics g) {
		        g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
		    }

		    @Override
		    public boolean contains(int x, int y) {
		        int radius = getWidth() / 2;
		        return (Math.pow(x - radius, 2) + Math.pow(y - radius, 2)) <= Math.pow(radius, 2);
		    }
		};

		botonRedondo.setBounds(200, 181, 60, 60);
		botonRedondo.setContentAreaFilled(false);
		botonRedondo.setFocusPainted(false);
		botonRedondo.setBorderPainted(false);
		botonRedondo.setForeground(new Color(235, 227, 194));
		botonRedondo.setFont(new Font("Arial", Font.BOLD, 16));

		getContentPane().add(botonRedondo);
		
		btn_Partidas_Cercanas.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_Partidas_Cercanas.setForeground(Color.WHITE);
		btn_Partidas_Cercanas.setBackground(new Color(196, 49, 25));	
		btn_Partidas_Cercanas.setFocusPainted(false);
		btn_Partidas_Cercanas.setBorderPainted(false);
		btn_Partidas_Cercanas.setContentAreaFilled(false);
		btn_Partidas_Cercanas.setOpaque(false);
		btn_Partidas_Cercanas.setBounds(133, 415, 228, 78);

		URL imageUrl = getClass().getResource("/imagenes/casa.png");
		ImageIcon icon = null;

		if (imageUrl != null) {
			icon = new ImageIcon(imageUrl);
			Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
		} else {
			System.out.println("Imagen no encontrada");
			// Puedes usar un icono por defecto si falla
			icon = new ImageIcon(); // o poner null si lo prefieres
		}

		// Crear el botón redondo con el ícono cargado
		JButton botonRedondo3 = new JButton(icon) {
			@Override
			protected void paintComponent(Graphics g) {
				g.fillOval(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}

			@Override
			protected void paintBorder(Graphics g) {
				g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
			}

			@Override
			public boolean contains(int x, int y) {
				int radius = getWidth() / 2;
				return (Math.pow(x - radius, 2) + Math.pow(y - radius, 2)) <= Math.pow(radius, 2);
			}
		};
		botonRedondo.setBounds( 213, 412, 60, 60);
		botonRedondo.setContentAreaFilled(false);
		botonRedondo.setFocusPainted(false);
		botonRedondo.setBorderPainted(false);
		botonRedondo.setForeground(new Color(5, 66, 47));
		botonRedondo.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(botonRedondo);

		// Crear el botón redondo con el ícono cargado
		JButton botonRedondo2 = new JButton(icon) {
			@Override
			protected void paintComponent(Graphics g) {
				g.fillOval(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}

			@Override
			protected void paintBorder(Graphics g) {
				g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
			}

			@Override
			public boolean contains(int x, int y) {
				int radius = getWidth() / 2;
				return (Math.pow(x - radius, 2) + Math.pow(y - radius, 2)) <= Math.pow(radius, 2);
			}
		};
		botonRedondo.setBounds(518, 181, 60, 60);
		botonRedondo.setContentAreaFilled(false);
		botonRedondo.setFocusPainted(false);
		botonRedondo.setBorderPainted(false);
		botonRedondo.setForeground(new Color(5, 66, 47));
		botonRedondo.setFont(new Font("Arial", Font.BOLD, 16));

		// Dibujar el fondo redondeado
		btn_Partidas_Cercanas.setUI(new BasicButtonUI() {
		    @Override
		    public void paint(Graphics g, JComponent c) {
		        Graphics2D g2 = (Graphics2D) g.create();
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(btn_Partidas_Cercanas.getBackground());
		        g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
		        g2.dispose();
		        super.paint(g, c);
		    }
		});
		
		contentPane.add(btn_Partidas_Cercanas);

		JButton btn_Torneos = new JButton("Torneos");
		btn_Torneos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Torneos T1 = new Torneos();
				dispose();
				T1.setVisible(true);
			}
		});
		btn_Torneos.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_Torneos.setForeground(Color.WHITE);
		btn_Torneos.setBackground(new Color(196, 49, 25));	
		btn_Torneos.setFocusPainted(false);
		btn_Torneos.setBorderPainted(false);
		btn_Torneos.setContentAreaFilled(false);
		btn_Torneos.setOpaque(false);
		btn_Torneos.setBounds(133, 275, 228, 78);
		contentPane.add(btn_Torneos);
		
		btn_Torneos.setUI(new BasicButtonUI() {
		    @Override
		    public void paint(Graphics g, JComponent c) {
		        Graphics2D g2 = (Graphics2D) g.create();
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(btn_Torneos.getBackground());
		        g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
		        g2.dispose();
		        super.paint(g, c);
		    }
		});
		
		contentPane.add(btn_Partidas_Cercanas);
		
		JButton btn_cajero = new JButton("Cajero");
		btn_cajero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cajero C1 = new Cajero();
				dispose();
				C1.setVisible(true);
				
			}
		});
		btn_cajero.setOpaque(false);
		btn_cajero.setForeground(Color.WHITE);
		btn_cajero.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_cajero.setFocusPainted(false);
		btn_cajero.setContentAreaFilled(false);
		btn_cajero.setBorderPainted(false);
		btn_cajero.setBackground(new Color(196, 49, 25));
		btn_cajero.setBounds(167, 559, 151, 43);
		contentPane.add(btn_cajero);
		
		btn_cajero.setUI(new BasicButtonUI() {
		    @Override
		    public void paint(Graphics g, JComponent c) {
		        Graphics2D g2 = (Graphics2D) g.create();
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(btn_cajero.getBackground());
		        g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
		        g2.dispose();
		        super.paint(g, c);
		    }
		});
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(235, 227, 194));
		separator_1.setBackground(new Color(235, 227, 194));
		separator_1.setBounds(108, 246, 282, 2);
		contentPane.add(separator_1);
	
						
		
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
		panelRombo.setOpaque(false);
		panelRombo.setBounds(78, 518, 20, 20); // Posición del rombo
		contentPane.add(panelRombo);
		
		JPanel panelRombo2 = new JPanel() {
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
		panelRombo2.setBackground(new Color(3, 65, 37));
		panelRombo2.setOpaque(false);
		panelRombo2.setBounds(78, 371, 20, 20); // Posición del rombo
		contentPane.add(panelRombo2);
		
		JPanel panelRombo3 = new JPanel() {
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
		panelRombo3.setOpaque(false);
		panelRombo3.setBounds(78, 237, 20, 20); // Posición del rombo
		contentPane.add(panelRombo3);
		
		JPanel panelRombo4 = new JPanel() {
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
		panelRombo4.setOpaque(false);
		panelRombo4.setBounds(400, 371, 20, 20); // Posición del rombo
		contentPane.add(panelRombo4);
		
		JPanel panelRombo5 = new JPanel() {
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
		panelRombo5.setOpaque(false);
		panelRombo5.setBounds(400, 237, 20, 20); // Posición del rombo
		contentPane.add(panelRombo5);
		
		JPanel panelRombo6 = new JPanel() {
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
		panelRombo6.setOpaque(false);
		panelRombo6.setBounds(400, 518, 20, 20); // Posición del rombo
		contentPane.add(panelRombo6);
				
		JLabel lblNewLabel = new JLabel("TightPoker");
		lblNewLabel.setForeground(new Color(235, 227, 194));
		lblNewLabel.setBounds(133, 109, 228, 95);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 54));
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(235, 227, 194));
		separator.setBackground(new Color(235, 227, 194));
		separator.setBounds(108, 380, 282, 3);
		contentPane.add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(235, 227, 194));
		separator_2.setBackground(new Color(235, 227, 194));
		separator_2.setBounds(108, 527, 282, 5);
		contentPane.add(separator_2);
		
		lblfondo.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoPoker.png")));
		lblfondo.setBounds(-11, 0, 518, 757);
		contentPane.add(lblfondo);
		
		
		
		contentPane.setVisible(true);
		setUndecorated(true);
	    setVisible(true);
	         
	    JOptionPane.showMessageDialog(this, "Bienvenido " + actual.getNombre(), "Mensaje de bienvenida", JOptionPane.INFORMATION_MESSAGE);

	    setVisible(true);
	       
	        
	    
	}
}