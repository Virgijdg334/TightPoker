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

public class Torneos extends JFrame {

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
					Torneos frame = new Torneos();
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
	public Torneos() {
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


		
		JButton btn_KO_Progresivo = new JButton("KO Progresivo");
		btn_KO_Progresivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		ImageIcon icon = new ImageIcon("C:\\Users\\Alumno1\\Documents\\TightPoker\\imagenes\\fotoperfil3.png");
		Image image = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		


		JButton botonRedondo = new JButton(icon) {
		    @Override
		    protected void paintComponent(Graphics g) {
		        if (getModel().isArmed()) {
		           
		        } else {
		            
		        }
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

		botonRedondo.setBounds(213, 54, 60, 60);
		botonRedondo.setContentAreaFilled(false);
		botonRedondo.setFocusPainted(false);
		botonRedondo.setBorderPainted(false);
		botonRedondo.setForeground(new Color(235, 227, 194));
		botonRedondo.setFont(new Font("Arial", Font.BOLD, 16));

		getContentPane().add(botonRedondo);

		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setForeground(new Color(235, 227, 194));
		lblSaldo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 22));
		lblSaldo.setBounds(124, 631, 60, 29);
		contentPane.add(lblSaldo);
		
		btn_KO_Progresivo.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_KO_Progresivo.setForeground(Color.WHITE);
		btn_KO_Progresivo.setBackground(new Color(196, 49, 25));	
		btn_KO_Progresivo.setFocusPainted(false);
		btn_KO_Progresivo.setBorderPainted(false);
		btn_KO_Progresivo.setContentAreaFilled(false);
		btn_KO_Progresivo.setOpaque(false);
		btn_KO_Progresivo.setBounds(132, 365, 228, 78);		
		
		
		// Dibujar el fondo redondeado
		btn_KO_Progresivo.setUI(new BasicButtonUI() {
		    @Override
		    public void paint(Graphics g, JComponent c) {
		        Graphics2D g2 = (Graphics2D) g.create();
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(btn_KO_Progresivo.getBackground());
		        g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
		        g2.dispose();
		        super.paint(g, c);
		    }
		});
		
		contentPane.add(btn_KO_Progresivo);

		JButton btn_Mystery_Bountys = new JButton("Mystery Bountys");
		btn_Mystery_Bountys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Mystery_Bountys.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_Mystery_Bountys.setForeground(Color.WHITE);
		btn_Mystery_Bountys.setBackground(new Color(196, 49, 25));	
		btn_Mystery_Bountys.setFocusPainted(false);
		btn_Mystery_Bountys.setBorderPainted(false);
		btn_Mystery_Bountys.setContentAreaFilled(false);
		btn_Mystery_Bountys.setOpaque(false);
		btn_Mystery_Bountys.setBounds(132, 225, 228, 78);
		contentPane.add(btn_Mystery_Bountys);
		
		btn_Mystery_Bountys.setUI(new BasicButtonUI() {
		    @Override
		    public void paint(Graphics g, JComponent c) {
		        Graphics2D g2 = (Graphics2D) g.create();
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(btn_Mystery_Bountys.getBackground());
		        g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
		        g2.dispose();
		        super.paint(g, c);
		    }
		});
		
		JButton btn_Monster_Stack = new JButton("Monster Stack");
		btn_Monster_Stack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Monster_Stack.setOpaque(false);
		btn_Monster_Stack.setForeground(Color.WHITE);
		btn_Monster_Stack.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_Monster_Stack.setFocusPainted(false);
		btn_Monster_Stack.setContentAreaFilled(false);
		btn_Monster_Stack.setBorderPainted(false);
		btn_Monster_Stack.setBackground(new Color(196, 49, 25));
		btn_Monster_Stack.setBounds(132, 498, 228, 78);
		contentPane.add(btn_Monster_Stack);
		
	
		btn_Monster_Stack.setUI(new BasicButtonUI() {
		    @Override
		    public void paint(Graphics g, JComponent c) {
		        Graphics2D g2 = (Graphics2D) g.create();
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(btn_Monster_Stack.getBackground());
		        g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
		        g2.dispose();
		        super.paint(g, c);
		    }
		});		
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(235, 227, 194));
		separator_1.setBackground(new Color(235, 227, 194));
		separator_1.setBounds(107, 196, 282, 2);
		contentPane.add(separator_1);
	
		btn_Mystery_Bountys.setUI(new BasicButtonUI() {
		    @Override
		    public void paint(Graphics g, JComponent c) {
		        Graphics2D g2 = (Graphics2D) g.create();
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(btn_Mystery_Bountys.getBackground());
		        g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
		        g2.dispose();
		        super.paint(g, c);
		    }
		});				
		
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
		panelRombo.setBounds(77, 458, 20, 20); // Posición del rombo
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
		panelRombo2.setOpaque(false);
		panelRombo2.setBounds(77, 320, 20, 20); // Posición del rombo
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
		panelRombo3.setBounds(77, 186, 20, 20); // Posición del rombo
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
		panelRombo4.setBounds(399, 320, 20, 20); // Posición del rombo
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
		panelRombo5.setBounds(399, 186, 20, 20); // Posición del rombo
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
		panelRombo6.setBounds(399, 458, 20, 20); // Posición del rombo
		contentPane.add(panelRombo6);
				
		JLabel lblNewLabel = new JLabel("Torneos");
		lblNewLabel.setForeground(new Color(235, 227, 194));
		lblNewLabel.setBounds(155, 103, 171, 95);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 54));
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(235, 227, 194));
		separator.setBackground(new Color(235, 227, 194));
		separator.setBounds(107, 330, 282, 3);
		contentPane.add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(235, 227, 194));
		separator_2.setBackground(new Color(235, 227, 194));
		separator_2.setBounds(107, 468, 282, 5);
		contentPane.add(separator_2);
		
		lblfondo.setIcon(new ImageIcon("C:\\Users\\Alumno1\\Documents\\TightPoker\\imagenes\\fondoPoker2.png"));
		lblfondo.setBounds(-11, 0, 518, 757);
		contentPane.add(lblfondo);
		
		contentPane.setVisible(true);
		setUndecorated(true);
	        setVisible(true);
	    
	}
}
