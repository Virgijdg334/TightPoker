package programa;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

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

/**
 * Clase PartidasCercanas que extiende JFrame para mostrar las partidas de casino
 * cercanas en la aplicación.
 */
public class PartidasCercanas extends JFrame {

    private static final long serialVersionUID = 1L; // Identificador de versión para serialización.
    private JPanel contentPane; // Panel principal de la ventana.
    private final JLabel lblfondo = new JLabel(""); // Etiqueta para la imagen de fondo.

    /**
     * Método principal que inicia la aplicación.
     * Ejecuta la interfaz de usuario en el Event Dispatch Thread (EDT).
     *
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PartidasCercanas frame = new PartidasCercanas();
                frame.setVisible(true); // Hace visible la ventana.
            } catch (Exception e) {
                e.printStackTrace(); // Imprime la traza de la excepción si ocurre un error.
            }
        });
    }

    /**
     * Constructor de la clase PartidasCercanas.
     * Configura la ventana y sus componentes.
     */
    public PartidasCercanas() {
        // Configuración básica de la ventana.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana.
        setBounds(100, 100, 621, 414); // Establece la posición y tamaño de la ventana.
        setUndecorated(true); // Elimina los bordes y la barra de título de la ventana.

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Establece un borde vacío.
        setContentPane(contentPane); // Asigna el panel como el contenido principal.
        contentPane.setLayout(null); // Desactiva el layout manager para posicionar componentes manualmente.

        // --- Botón de perfil de usuario ---
        // Carga la imagen para el botón de perfil.
        URL imageUrl1 = getClass().getResource("/imagenes/fotoperfil3.png");
        ImageIcon icon1 = null;

        if (imageUrl1 != null) {
            icon1 = new ImageIcon(imageUrl1);
            // Escala la imagen para el botón.
            Image image = icon1.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            icon1 = new ImageIcon(image);
        } else {
            System.out.println("Imagen no encontrada"); // Mensaje si la imagen no se encuentra.
            icon1 = new ImageIcon(); // Asigna un ImageIcon vacío.
        }

        // Crea un botón redondo personalizado con la imagen de perfil.
        JButton botonPerfil = new JButton(icon1) {
            @Override
            protected void paintComponent(Graphics g) {
                g.fillOval(0, 0, getWidth(), getHeight()); // Dibuja el fondo circular.
                super.paintComponent(g); // Llama al método original para dibujar el contenido (imagen).
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.drawOval(0, 0, getWidth() - 1, getHeight() - 1); // Dibuja el borde circular.
            }

            @Override
            public boolean contains(int x, int y) {
                // Define la forma circular para la detección de clics.
                int radius = getWidth() / 2;
                return (Math.pow(x - radius, 2) + Math.pow(y - radius, 2)) <= Math.pow(radius, 2);
            }
        };
        botonPerfil.setBounds(431, 54, 45, 45); // Establece la posición y tamaño del botón.
        botonPerfil.setContentAreaFilled(false); // No rellena el área de contenido (para que el óvalo sea visible).
        botonPerfil.setFocusPainted(false); // No pinta el recuadro de foco.
        botonPerfil.setBorderPainted(false); // No pinta el borde predeterminado.
        botonPerfil.setForeground(new Color(235, 227, 194)); // Color del texto (no visible en este caso, pero buena práctica).
        botonPerfil.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente del texto (no visible).
        contentPane.add(botonPerfil); // Añade el botón al panel principal.

        // Agrega un ActionListener al botón de perfil para navegar a la ventana PerfilUsuario.
        botonPerfil.addActionListener(e -> {
            PerfilUsuario p1 = new PerfilUsuario();
            dispose(); // Cierra la ventana actual.
            p1.setVisible(true); // Abre la ventana PerfilUsuario.
        });

        // --- Título de la ventana ---
        JLabel lblTitulo = new JLabel("Partidas Cercanas");
        lblTitulo.setForeground(new Color(235, 227, 194)); // Color del texto.
        lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 34)); // Fuente y tamaño del texto.
        lblTitulo.setBounds(148, 32, 262, 95); // Posición y tamaño del título.
        contentPane.add(lblTitulo); // Añade el título al panel principal.

        // --- Panel contenedor para las partidas ---
        JPanel panelContenedor = new JPanel();
        panelContenedor.setBorder(null); // Sin borde.
        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS)); // Layout vertical para añadir paneles.
        panelContenedor.setBackground(new Color(0, 102, 51)); // Color de fondo del panel.

        // --- Conexión a la base de datos y carga de partidas ---
        ConexionMySQL con = new ConexionMySQL("root", "password", "sql7780337");

        try {
            con.conectar(); // Establece la conexión con la base de datos.
            ResultSet rs = con.ejecutarSelect("SELECT * FROM casino"); // Ejecuta la consulta para obtener los casinos.

            int i = 0; // Contador para limitar el número de resultados mostrados.
            while (rs.next() && i < 6) { // Itera sobre los resultados de la consulta, hasta un máximo de 6.
                String nombre = rs.getString("nombre"); // Obtiene el nombre del casino.
                String lugar = rs.getString("lugar"); // Obtiene el lugar del casino.
                int mesas = rs.getInt("n_mesas"); // Obtiene el número de mesas del casino.

                // Crea un panel individual para cada partida (casino).
                JPanel panelPartida = new JPanel();
                panelPartida.setLayout(null); // Desactiva el layout manager.
                panelPartida.setPreferredSize(new java.awt.Dimension(440, 90)); // Tamaño preferido del panel.
                panelPartida.setBackground(new Color(8, 68, 44)); // Color de fondo.
                panelPartida.setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); // Borde inferior.

                // Crea y añade las etiquetas con la información de la partida.
                JLabel lblNombre = new JLabel("Nombre: " + nombre);
                lblNombre.setBounds(10, 10, 200, 20);
                lblNombre.setForeground(Color.WHITE);
                panelPartida.add(lblNombre);

                JLabel lblLugar = new JLabel("Lugar: " + lugar);
                lblLugar.setBounds(10, 30, 200, 20);
                lblLugar.setForeground(Color.WHITE);
                panelPartida.add(lblLugar);

                JLabel lblMesas = new JLabel("Número de mesas: " + mesas);
                lblMesas.setBounds(10, 50, 200, 20);
                lblMesas.setForeground(Color.WHITE);
                panelPartida.add(lblMesas);

                panelContenedor.add(panelPartida); // Añade el panel de la partida al contenedor principal de partidas.
                i++; // Incrementa el contador.
            }
            rs.close(); // Cierra el ResultSet.
            con.desconectar(); // Cierra la conexión a la base de datos.
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime la traza de la excepción si hay un error de SQL.
        }

        // --- Botón de volver ---
        // Carga la imagen para el botón de volver.
        URL imageUrlVolver = getClass().getResource("/imagenes/volver1.png");
        ImageIcon iconVolver = null;

        if (imageUrlVolver != null) {
            iconVolver = new ImageIcon(imageUrlVolver);
            // Escala la imagen.
            Image imageVolver = iconVolver.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            iconVolver = new ImageIcon(imageVolver);
        } else {
            System.out.println("Imagen no encontrada"); // Mensaje si la imagen no se encuentra.
            iconVolver = new ImageIcon(); // Asigna un ImageIcon vacío.
        }

        // Crea un botón redondo personalizado con la imagen de volver.
        JButton botonVolver = new JButton(iconVolver) {
            @Override
            protected void paintComponent(Graphics g) {
                g.fillOval(0, 0, getWidth(), getHeight()); // Dibuja el fondo circular.
                super.paintComponent(g); // Dibuja la imagen.
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.drawOval(0, 0, getWidth() - 1, getHeight() - 1); // Dibuja el borde circular.
            }

            @Override
            public boolean contains(int x, int y) {
                // Define la forma circular para la detección de clics.
                int radius = getWidth() / 2;
                return (Math.pow(x - radius, 2) + Math.pow(y - radius, 2)) <= Math.pow(radius, 2);
            }
        };
        botonVolver.setBounds(279, 312, 60, 60); // Posición y tamaño del botón.
        botonVolver.setContentAreaFilled(false);
        botonVolver.setFocusPainted(false);
        botonVolver.setBorderPainted(false);
        botonVolver.setForeground(new Color(5, 66, 47));
        botonVolver.setFont(new Font("Arial", Font.BOLD, 16));
        contentPane.add(botonVolver); // Añade el botón al panel principal.

        // Agrega un ActionListener al botón de volver para navegar a la ventana PaginaPrincipal.
        botonVolver.addActionListener(e -> {
            PaginaPrincipal T1 = new PaginaPrincipal();
            dispose(); // Cierra la ventana actual.
            T1.setVisible(true); // Abre la ventana PaginaPrincipal.
        });

        // --- ScrollPane para el contenedor de partidas ---
        JScrollPane scrollPane = new JScrollPane(panelContenedor);
        scrollPane.setBounds(80, 150, 470, 150); // Posición y tamaño del scrollPane.
        scrollPane.setBorder(new LineBorder(new Color(235, 227, 194), 2)); // Borde del scrollPane.
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Velocidad del scroll vertical.
        contentPane.add(scrollPane); // Añade el scrollPane al panel principal.

        // --- Imagen de fondo ---
        lblfondo.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoPokerHorizontal.png"))); // Carga la imagen de fondo.
        lblfondo.setBounds(0, -171, 631, 757); // Posición y tamaño de la imagen de fondo.
        contentPane.add(lblfondo); // Añade la imagen de fondo al panel principal.

        setVisible(true); // Hace visible la ventana.
    }
}