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

/**
 * La clase MonsterStacks representa la interfaz gráfica para la sección de
 * torneos de tipo "Monster Stacks". Muestra una lista de torneos disponibles,
 * permite la inscripción a ellos y actualiza el saldo del usuario.
 */
public class MonsterStacks extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private final JLabel lblfondo = new JLabel(""); // Etiqueta para la imagen de fondo

    // Instancia del usuario actual y su saldo formateado para visualización
    Usuario actual = SesionUsuario.getUsuario();
    public String saldoTexto = String.format("%.2f €", actual.getSaldo());

    /**
     * Punto de entrada principal para la aplicación de Monster Stacks.
     * Crea y muestra la ventana en el hilo de despacho de eventos de Swing.
     *
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MonsterStacks frame = new MonsterStacks();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Constructor de la clase MonsterStacks.
     * Inicializa y configura todos los componentes de la interfaz gráfica.
     */
    public MonsterStacks() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 621, 414);
        setUndecorated(true); // Elimina la barra de título y los bordes de la ventana

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null); // Diseño nulo para posicionar componentes libremente
        setContentPane(contentPane);

        // --- Botón de Perfil de Usuario ---
        URL profileIconUrl = getClass().getResource("/imagenes/fotoperfil3.png");
        ImageIcon profileIcon = null;

        if (profileIconUrl != null) {
            profileIcon = new ImageIcon(profileIconUrl);
            Image image = profileIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            profileIcon = new ImageIcon(image);
        } else {
            System.out.println("Imagen de perfil no encontrada.");
            profileIcon = new ImageIcon(); // Icono vacío si no se encuentra
        }

        JButton btnPerfil = new JButton(profileIcon) {
            // Personaliza el botón para que sea redondo
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
        btnPerfil.setBounds(431, 54, 45, 45);
        btnPerfil.setContentAreaFilled(false);
        btnPerfil.setFocusPainted(false);
        btnPerfil.setBorderPainted(false);
        btnPerfil.setForeground(new Color(235, 227, 194));
        btnPerfil.setFont(new Font("Arial", Font.BOLD, 16));
        contentPane.add(btnPerfil);
        btnPerfil.addActionListener(e -> {
            PerfilUsuario p1 = new PerfilUsuario();
            dispose(); // Cierra la ventana actual
            p1.setVisible(true); // Muestra la ventana de perfil
        });

        // --- Etiqueta de Saldo Actual ---
        JLabel lbl_Salario = new JLabel("Saldo: " + saldoTexto);
        lbl_Salario.setForeground(new Color(235, 227, 194));
        lbl_Salario.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 22));
        lbl_Salario.setBounds(79, 107, 194, 38);
        contentPane.add(lbl_Salario);

        // --- Título de la Sección ---
        JLabel lbl_Monster_Stacks = new JLabel("Monster Stacks");
        lbl_Monster_Stacks.setForeground(new Color(235, 227, 194));
        lbl_Monster_Stacks.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 40));
        lbl_Monster_Stacks.setBounds(148, 26, 262, 95);
        contentPane.add(lbl_Monster_Stacks);

        // --- Panel Contenedor de Torneos (scrollable) ---
        JPanel panelContenedor = new JPanel();
        panelContenedor.setBorder(null);
        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS)); // Diseño vertical
        panelContenedor.setBackground(new Color(0, 102, 51)); // Color de fondo del panel que contiene los torneos

        // --- Botón de Volver a Torneos ---
        URL backIconUrl = getClass().getResource("/imagenes/volver1.png");
        ImageIcon backIcon = null;

        if (backIconUrl != null) {
            backIcon = new ImageIcon(backIconUrl);
            Image image = backIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            backIcon = new ImageIcon(image);
        } else {
            System.out.println("Imagen para botón de volver no encontrada.");
            backIcon = new ImageIcon(); // Icono vacío si no se encuentra
        }

        JButton btnVolver = new JButton(backIcon) {
            // Personaliza el botón para que sea redondo
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
        btnVolver.setBounds(279, 312, 60, 60);
        btnVolver.setContentAreaFilled(false);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setForeground(new Color(5, 66, 47));
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        contentPane.add(btnVolver);
        btnVolver.addActionListener(e -> {
            Torneos T1 = new Torneos();
            dispose(); // Cierra la ventana actual
            T1.setVisible(true); // Muestra la ventana de torneos
        });

        // --- Carga y Muestra de Torneos desde la Base de Datos ---
        ConexionMySQL con = new ConexionMySQL("root", "password", "sql7780337");

        try {
            con.conectar();
            // Consulta SQL para obtener torneos de tipo 'MonsterStacks'
            ResultSet rs = con.ejecutarSelect("SELECT id, nombre, lugar, bote_premios, `buy-in`, jugadores, limite_jugadores FROM torneo WHERE tipo = 'MonsterStacks'");
            // int i = 0; // Contador eliminado, ya que no se limita a 6 torneos
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String lugar = rs.getString("lugar");
                String premio = rs.getString("bote_premios");
                int precio = rs.getInt("buy-in");
                int jugando = rs.getInt("jugadores");
                int limite_jugadores = rs.getInt("limite_jugadores");

                // Panel individual para cada torneo
                JPanel panelTorneo = new JPanel();
                panelTorneo.setLayout(null); // Diseño nulo para posicionar elementos manualmente
                panelTorneo.setPreferredSize(new java.awt.Dimension(440, 90)); // Tamaño preferido
                panelTorneo.setBackground(new Color(8, 68, 44)); // Color de fondo del torneo
                panelTorneo.setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); // Borde inferior

                // Etiquetas para los detalles del torneo
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

                            // 1. Verificar si el usuario ya está inscrito
                            PreparedStatement checkStmt = conn.prepareStatement("SELECT * FROM inscripciones WHERE nombreUsuario = ? AND id = ?");
                            checkStmt.setString(1, nombreUsuario);
                            checkStmt.setInt(2, id);
                            ResultSet checkRs = checkStmt.executeQuery();
                            if (checkRs.next()) {
                                JOptionPane.showMessageDialog(null, "Ya estás inscrito en este torneo.");
                                // El botón se deshabilita solo si ya está inscrito
                                btnInscribir.setEnabled(false);
                                return;
                            }
                            checkRs.close();
                            checkStmt.close();

                            // 2. Verificar si el torneo está lleno
                            PreparedStatement cupoStmt = conn.prepareStatement("SELECT jugadores, limite_jugadores FROM torneo WHERE id = ?");
                            cupoStmt.setInt(1, id);
                            ResultSet cupoRs = cupoStmt.executeQuery();
                            if (cupoRs.next()) {
                                int inscritos = cupoRs.getInt("jugadores");
                                int max = cupoRs.getInt("limite_jugadores");
                                if (inscritos >= max) {
                                    JOptionPane.showMessageDialog(null, "El torneo ya está lleno.");
                                    btnInscribir.setEnabled(false); // Deshabilitar si el torneo está lleno
                                    return;
                                }
                            }
                            cupoRs.close();
                            cupoStmt.close();

                            // 3. Verificar saldo suficiente
                            if (actual.getSaldo() < precio) {
                                JOptionPane.showMessageDialog(null, "Saldo insuficiente.");
                                return;
                            }

                            // 4. Realizar la inscripción (actualizar la tabla de inscripciones)
                            PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO inscripciones (nombreUsuario, id) VALUES (?, ?)");
                            insertStmt.setString(1, nombreUsuario);
                            insertStmt.setInt(2, id);
                            insertStmt.executeUpdate();
                            insertStmt.close();

                            // 5. Actualizar el número de jugadores en el torneo
                            PreparedStatement updateTorneo = conn.prepareStatement("UPDATE torneo SET jugadores = jugadores + 1 WHERE id = ?");
                            updateTorneo.setInt(1, id);
                            updateTorneo.executeUpdate();
                            updateTorneo.close();

                            // 6. Actualizar el saldo del usuario
                            double nuevoSaldo = actual.getSaldo() - precio;
                            PreparedStatement updateSaldo = conn.prepareStatement("UPDATE usuario SET saldo = ? WHERE nombreUsuario = ?");
                            updateSaldo.setDouble(1, nuevoSaldo);
                            updateSaldo.setString(2, nombreUsuario);
                            updateSaldo.executeUpdate();
                            updateSaldo.close();

                            // 7. Actualizar la interfaz y el objeto de usuario en memoria
                            actual.setSaldo(nuevoSaldo);
                            lbl_Salario.setText("Saldo: " + String.format("%.2f €", nuevoSaldo));
                            // Actualiza la etiqueta de jugadores en el panel específico del torneo
                            lblJugadores.setText("Jugadores: " + (jugando + 1) + "/" + limite_jugadores);

                            JOptionPane.showMessageDialog(null, "¡Inscripción exitosa!");
                            btnInscribir.setEnabled(false); // Deshabilita el botón después de la inscripción
                            con.desconectar(); // Asegura que la conexión se cierre

                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Error al inscribirse al torneo: " + ex.getMessage());
                            ex.printStackTrace();
                        }
                    }
                });

                btnInscribir.setBounds(335, 55, 100, 25);
                btnInscribir.setBackground(new Color(196, 49, 25)); // Color rojo oscuro
                btnInscribir.setForeground(Color.WHITE);
                panelTorneo.add(btnInscribir);

                panelContenedor.add(panelTorneo);
            }
            rs.close(); // Cierra el ResultSet
            con.desconectar(); // Cierra la conexión a la base de datos
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los torneos: " + e.getMessage());
            e.printStackTrace();
        }

        // --- JScrollPane para hacer el panel de torneos deslizable ---
        JScrollPane scrollPane = new JScrollPane(panelContenedor);
        scrollPane.setBounds(80, 150, 470, 150);
        scrollPane.setBorder(new LineBorder(new Color(235, 227, 194), 2)); // Borde para el scroll pane
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Velocidad del scroll
        contentPane.add(scrollPane);

        // --- Imagen de Fondo Principal ---
        URL fondoImageUrl = getClass().getResource("/imagenes/fondoPokerHorizontal.png");
        if (fondoImageUrl != null) {
            lblfondo.setIcon(new ImageIcon(fondoImageUrl));
        } else {
            System.err.println("Error: Imagen de fondo no encontrada en /imagenes/fondoPokerHorizontal.png");
        }
        lblfondo.setBounds(0, -171, 631, 757); // Ajuste de posición para cubrir la ventana
        contentPane.add(lblfondo);
    }
}