package programa;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class PartidasCercanas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private final JLabel lblfondo = new JLabel("");

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PartidasCercanas frame = new PartidasCercanas();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PartidasCercanas() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 621, 414);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        URL imageUrl1 = getClass().getResource("/imagenes/fotoperfil3.png");
        ImageIcon icon1 = null;

        if (imageUrl1 != null) {
            icon1 = new ImageIcon(imageUrl1);
            Image image = icon1.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            icon1 = new ImageIcon(image);
        } else {
            System.out.println("Imagen no encontrada");
            icon1 = new ImageIcon();
        }

        JButton botonRedondo1 = new JButton(icon1) {
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
        botonRedondo1.setBounds(431, 54, 45, 45);
        botonRedondo1.setContentAreaFilled(false);
        botonRedondo1.setFocusPainted(false);
        botonRedondo1.setBorderPainted(false);
        botonRedondo1.setForeground(new Color(235, 227, 194));
        botonRedondo1.setFont(new Font("Arial", Font.BOLD, 16));
        getContentPane().add(botonRedondo1);
        botonRedondo1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PerfilUsuario p1 = new PerfilUsuario();
                dispose();
                p1.setVisible(true);
            }
        });

        JLabel lbl_Mystery_Bountys = new JLabel("Partidas Cercanas");
        lbl_Mystery_Bountys.setForeground(new Color(235, 227, 194));
        lbl_Mystery_Bountys.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 34));
        lbl_Mystery_Bountys.setBounds(148, 32, 262, 95);
        contentPane.add(lbl_Mystery_Bountys);

        JPanel panelContenedor = new JPanel();
        panelContenedor.setBorder(null);
        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));
        panelContenedor.setBackground(new Color(0, 102, 51));

        ConexionMySQL con = new ConexionMySQL("root", "password", "sql7780337");

        try {
            con.conectar();
            ResultSet rs = con.ejecutarSelect("SELECT * FROM casino");
            int i = 0;
            while (rs.next() && i < 6) {
                String nombre = rs.getString("nombre");
                String lugar = rs.getString("lugar");
                int mesas = rs.getInt("n_mesas");


                JPanel panelTorneo = new JPanel();
                panelTorneo.setLayout(null);
                panelTorneo.setPreferredSize(new java.awt.Dimension(440, 90));
                panelTorneo.setBackground(new Color(8, 68, 44));
                panelTorneo.setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

                JLabel lblNombre = new JLabel("Nombre: " + nombre);
                lblNombre.setBounds(10, 10, 200, 20);
                lblNombre.setForeground(Color.WHITE);
                panelTorneo.add(lblNombre);

                JLabel lblLugar = new JLabel("Lugar: " + lugar);
                lblLugar.setBounds(10, 30, 200, 20);
                lblLugar.setForeground(Color.WHITE);
                panelTorneo.add(lblLugar);

                JLabel lblPremio = new JLabel("Numero de mesas: " + mesas);
                lblPremio.setBounds(10, 50, 200, 20);
                lblPremio.setForeground(Color.WHITE);
                panelTorneo.add(lblPremio);

                URL imageUrl = getClass().getResource("/imagenes/volver1.png");
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
                JButton botonRedondo = new JButton(icon) {
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
                botonRedondo.setBounds( 279, 312, 60, 60);
                botonRedondo.setContentAreaFilled(false);
                botonRedondo.setFocusPainted(false);
                botonRedondo.setBorderPainted(false);
                botonRedondo.setForeground(new Color(5, 66, 47));
                botonRedondo.setFont(new Font("Arial", Font.BOLD, 16));
                botonRedondo.addActionListener(new ActionListener(){
                	public void actionPerformed(ActionEvent e) {
                		PaginaPrincipal T1 = new PaginaPrincipal();
                		dispose();
                		T1.setVisible(true);
                	}
                });

                
                getContentPane().add(botonRedondo);
                panelContenedor.add(panelTorneo);
                i++;
            }
            rs.close();
            con.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(panelContenedor);
        scrollPane.setBounds(80, 150, 470, 150);
        scrollPane.setBorder(new LineBorder(new Color(235, 227, 194), 2));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        contentPane.add(scrollPane);

        lblfondo.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoPokerHorizontal.png")));
        lblfondo.setBounds(0, -171, 631, 757);
        contentPane.add(lblfondo);

        setUndecorated(true);
        setVisible(true);
    }
}