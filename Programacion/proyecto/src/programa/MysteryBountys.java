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

public class MysteryBountys extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private final JLabel lblfondo = new JLabel("");
    Usuario actual = SesionUsuario.getUsuario();
    public String saldoTexto = String.format("%.2f €", actual.getSaldo());

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MysteryBountys frame = new MysteryBountys();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MysteryBountys() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 621, 414);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        URL imageUrl = getClass().getResource("/imagenes/fotoperfil3.png");
        ImageIcon icon = null;

        if (imageUrl != null) {
            icon = new ImageIcon(imageUrl);
            Image image = icon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);
        } else {
            System.out.println("Imagen no encontrada");
            icon = new ImageIcon();
        }

        JButton botonRedondo1 = new JButton(icon) {
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

        JLabel lbl_Salario = new JLabel("Saldo: " + saldoTexto);
        lbl_Salario.setForeground(new Color(235, 227, 194));
        lbl_Salario.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 22));
        lbl_Salario.setBounds(79, 107, 194, 38);
        contentPane.add(lbl_Salario);

        JLabel lbl_Mystery_Bountys = new JLabel("Mystery Bountys");
        lbl_Mystery_Bountys.setForeground(new Color(235, 227, 194));
        lbl_Mystery_Bountys.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 40));
        lbl_Mystery_Bountys.setBounds(148, 26, 262, 95);
        contentPane.add(lbl_Mystery_Bountys);

        JPanel panelContenedor = new JPanel();
        panelContenedor.setBorder(null);
        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));
        panelContenedor.setBackground(new Color(0, 102, 51));

        ConexionMySQL con = new ConexionMySQL("sql7780337", "fhEXfwYdmM", "sql7780337");

        try {
            con.conectar();
            ResultSet rs = con.ejecutarSelect("SELECT * FROM torneo WHERE tipo = 'MysteryBounty'");
            int i = 0;
            while (rs.next() && i < 6) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String lugar = rs.getString("lugar");
                String premio = rs.getString("bote_premios");
                int precio = rs.getInt("buy-in");
                int jugando = rs.getInt("jugadores");
                int limite_jugadores = rs.getInt("limite_jugadores");

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

                JLabel lblPremio = new JLabel("Premio: " + premio);
                lblPremio.setBounds(10, 50, 200, 20);
                lblPremio.setForeground(Color.WHITE);
                panelTorneo.add(lblPremio);

                JLabel lblJugadores = new JLabel("Jugadores: " + jugando + "/" + limite_jugadores);
                lblJugadores.setBounds(335, 10, 120, 20);
                lblJugadores.setForeground(Color.WHITE);
                panelTorneo.add(lblJugadores);

                JLabel lblPrecio = new JLabel("Precio: " + precio + "€");
                lblPrecio.setBounds(245, 55, 100, 25);
                lblPrecio.setForeground(Color.WHITE);
                panelTorneo.add(lblPrecio);

                JButton btnInscribir = new JButton("Inscribir");
                btnInscribir.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            con.conectar();
                            Connection conn = con.getConnection();
                            String nombreUsuario = SesionUsuario.getUsuario().getUser();

                            PreparedStatement checkStmt = conn.prepareStatement("SELECT * FROM inscripciones WHERE nombreUsuario = ? AND id = ?");
                            checkStmt.setString(1, nombreUsuario);
                            checkStmt.setInt(2, id);
                            ResultSet checkRs = checkStmt.executeQuery();
                            if (checkRs.next()) {
                                btnInscribir.setEnabled(false);
                                JOptionPane.showMessageDialog(null, "Ya estás inscrito en este torneo.");
                                return;
                            }

                            PreparedStatement cupoStmt = conn.prepareStatement("SELECT jugadores, limite_jugadores FROM torneo WHERE id = ?");
                            cupoStmt.setInt(1, id);
                            ResultSet cupoRs = cupoStmt.executeQuery();
                            if (cupoRs.next()) {
                                int inscritos = cupoRs.getInt("jugadores");
                                int max = cupoRs.getInt("limite_jugadores");
                                if (inscritos >= max) {
                                    JOptionPane.showMessageDialog(null, "El torneo ya está lleno.");
                                    btnInscribir.setEnabled(false);
                                    return;
                                }
                            }

                            if (actual.getSaldo() < precio) {
                                JOptionPane.showMessageDialog(null, "Saldo insuficiente.");
                                return;
                            }

                            PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO inscripciones (nombreUsuario, id) VALUES (?, ?)");
                            insertStmt.setString(1, nombreUsuario);
                            insertStmt.setInt(2, id);
                            insertStmt.executeUpdate();

                            PreparedStatement updateTorneo = conn.prepareStatement("UPDATE torneo SET jugadores = jugadores + 1 WHERE id = ?");
                            updateTorneo.setInt(1, id);
                            updateTorneo.executeUpdate();

                            double nuevoSaldo = actual.getSaldo() - precio;
                            PreparedStatement updateSaldo = conn.prepareStatement("UPDATE usuario SET saldo = ? WHERE nombreUsuario = ?");
                            updateSaldo.setDouble(1, nuevoSaldo);
                            updateSaldo.setString(2, nombreUsuario);
                            updateSaldo.executeUpdate();

                            actual.setSaldo(nuevoSaldo);
                            lbl_Salario.setText("Saldo: " + String.format("%.2f €", nuevoSaldo));

                            int nuevosJugadores = jugando + 1;
                            lblJugadores.setText("Jugadores: " + nuevosJugadores + "/" + limite_jugadores);

                            JOptionPane.showMessageDialog(null, "¡Inscripción exitosa!");
                            btnInscribir.setEnabled(false);

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                btnInscribir.setBounds(335, 55, 100, 25);
                btnInscribir.setBackground(new Color(196, 49, 25));
                btnInscribir.setForeground(Color.WHITE);
                panelTorneo.add(btnInscribir);

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