package programa;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;
import javax.swing.Icon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KO_Progresivo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private final JLabel lblfondo = new JLabel("");

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	KO_Progresivo frame = new KO_Progresivo();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public KO_Progresivo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 621, 414);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton botonRedondo = new JButton((Icon) null) {
        	protected void paintComponent(Graphics g) {
        	}
        	protected void paintBorder(Graphics g) {
        	}
        	public boolean contains(int x, int y) {
        		return false;
        	}
        };
        
		ImageIcon icon = new ImageIcon("C:\\Users\\Alumno1\\Documents\\TightPoker\\imagenes\\fotoperfil3.png");
		Image image = icon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);

		JButton botonRedondo1 = new JButton(icon) {
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
		botonRedondo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		botonRedondo1.setBounds(431, 54, 45, 45);
		botonRedondo1.setContentAreaFilled(false);
		botonRedondo1.setFocusPainted(false);
		botonRedondo1.setBorderPainted(false);
		botonRedondo1.setForeground(new Color(235, 227, 194));
		botonRedondo1.setFont(new Font("Arial", Font.BOLD, 16));

		getContentPane().add(botonRedondo1);
        
        JLabel lbl_Salario = new JLabel("Salario");
        lbl_Salario.setForeground(new Color(235, 227, 194));
        lbl_Salario.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 22));
        lbl_Salario.setBounds(79, 107, 66, 38);
        contentPane.add(lbl_Salario);

        JLabel lbl_Mystery_Bountys = new JLabel("KO Progresivo");
        lbl_Mystery_Bountys.setForeground(new Color(235, 227, 194));
        lbl_Mystery_Bountys.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 40));
        lbl_Mystery_Bountys.setBounds(148, 26, 262, 95);
        contentPane.add(lbl_Mystery_Bountys);

        // PANEL SCROLL CON TORNEOS (DATOS DE EJEMPLO)
        JPanel panelContenedor = new JPanel();
        panelContenedor.setBorder(null);
        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));
        panelContenedor.setBackground(new Color(0, 102, 51));

        
        
        // Añadiendo paneles de torneo de ejemplo
        for (int i = 1; i <= 6; i++) {
            JPanel panelTorneo = new JPanel();
            panelTorneo.setLayout(null);
            panelTorneo.setPreferredSize(new java.awt.Dimension(440, 90));
            panelTorneo.setBackground(new Color(8, 68, 44));
            panelTorneo.setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

            JLabel lblNombre = new JLabel("Nombre: Torneo " + i);
            lblNombre.setBounds(10, 10, 200, 20);
            lblNombre.setForeground(Color.WHITE);
            panelTorneo.add(lblNombre);

            JLabel lblFecha = new JLabel("Fecha: 01-01-2025");
            lblFecha.setBounds(10, 30, 200, 20);
            lblFecha.setForeground(Color.WHITE);
            panelTorneo.add(lblFecha);

            JLabel lblPremio = new JLabel("Premio: 100€");
            lblPremio.setBounds(10, 50, 200, 20);
            lblPremio.setForeground(Color.WHITE);
            panelTorneo.add(lblPremio);

            JButton btnInscribir = new JButton("Inscribir");
            btnInscribir.setBounds(220, 55, 100, 25);
            btnInscribir.setBackground(new Color(196, 49, 25));
            btnInscribir.setForeground(Color.WHITE);
            panelTorneo.add(btnInscribir);

            panelContenedor.add(panelTorneo);
        }

        JScrollPane scrollPane = 
        		new JScrollPane(panelContenedor);
        scrollPane.setBounds(80, 150, 470, 150);
        scrollPane.setBorder(new LineBorder(new Color(235, 227, 194), 2));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        contentPane.add(scrollPane);

        lblfondo.setIcon(new ImageIcon("C:\\Users\\Alumno1\\Documents\\TightPoker\\imagenes\\fondoPokerHorizontal.png"));
        lblfondo.setBounds(0, -171, 631, 757);
        contentPane.add(lblfondo);

        setUndecorated(true);
        setVisible(true);
    }
}