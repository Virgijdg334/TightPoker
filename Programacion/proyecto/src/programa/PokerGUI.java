package programa;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI; // Necesario para personalizar el UI del botón

/**
 * La clase PokerGUI representa la ventana principal de la aplicación de póker.
 * Actúa como la pantalla de inicio, ofreciendo opciones para "Log in" o "Register".
 */
public class PokerGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    // Instancia de la clase de conexión a la base de datos.
    // Aunque se inicializa aquí, no se usa directamente en esta clase,
    // pero se mantiene por si en el futuro se añade alguna funcionalidad
    // que requiera una conexión directa desde esta GUI.
    ConexionMySQL con = new ConexionMySQL("root", "password", "sql7780337");

    // Panel principal de contenido de la ventana
    private JPanel contentPane;
    // Etiqueta para el fondo de imagen
    private final JLabel lblfondo = new JLabel("");

    /**
     * Método principal que lanza la aplicación.
     * Asegura que la interfaz de usuario se ejecute en el Event Dispatch Thread (EDT).
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PokerGUI frame = new PokerGUI();
                frame.setVisible(true); // Hace visible la ventana principal
            } catch (Exception e) {
                e.printStackTrace(); // Imprime cualquier error que ocurra durante la inicialización
            }
        });
    }

    /**
     * Constructor de la clase PokerGUI.
     * Configura la ventana principal y añade sus componentes.
     */
    public PokerGUI() {
        // Configuración básica de la ventana JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana
        setBounds(100, 100, 484, 743); // Establece la posición y el tamaño de la ventana
        setUndecorated(true); // Elimina la barra de título y los bordes de la ventana para un diseño personalizado

        // Inicialización del panel de contenido principal
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Borde vacío para espacio interno
        contentPane.setLayout(null); // Usa un layout nulo para posicionamiento absoluto de los componentes
        setContentPane(contentPane); // Establece este panel como el contenido de la ventana

        // --- Botón "Log in" ---
        JButton btn_login = new JButton("Log in");
        btn_login.setFont(new Font("Tahoma", Font.BOLD, 24)); // Fuente del texto del botón
        btn_login.setBackground(new Color(196, 49, 25)); // Color de fondo del botón
        btn_login.setForeground(Color.WHITE); // Color del texto del botón
        btn_login.setBounds(133, 274, 228, 78); // Posición y tamaño del botón

        // Aplica el estilo redondeado personalizado al botón de login
        estiloBotonRedondeado(btn_login);

        // Añade un ActionListener al botón "Log in"
        btn_login.addActionListener(e -> {
            LoginForm login = new LoginForm(); // Crea una nueva instancia de LoginForm
            login.setVisible(true); // Hace visible la ventana de login
            dispose(); // Cierra la ventana actual (PokerGUI)
        });
        contentPane.add(btn_login); // Añade el botón al panel de contenido

        // --- Botón "Register" ---
        JButton btn_register = new JButton("Register");
        btn_register.setFont(new Font("Tahoma", Font.BOLD, 24));
        btn_register.setForeground(Color.WHITE);
        btn_register.setBackground(new Color(196, 49, 25));
        btn_register.setBounds(133, 412, 228, 78);

        // Aplica el estilo redondeado personalizado al botón de registro
        estiloBotonRedondeado(btn_register);

        // Añade un ActionListener al botón "Register"
        btn_register.addActionListener(e -> {
            RegisterForm f1 = new RegisterForm(); // Crea una nueva instancia de RegisterForm
            f1.setVisible(true); // Hace visible la ventana de registro
            dispose(); // Cierra la ventana actual (PokerGUI)
        });
        contentPane.add(btn_register); // Añade el botón al panel de contenido

        // --- Etiqueta del título del juego "TightPoker" ---
        JLabel lblNewLabel = new JLabel("TightPoker");
        lblNewLabel.setForeground(new Color(235, 227, 194)); // Color del texto
        lblNewLabel.setBounds(133, 66, 228, 95); // Posición y tamaño
        lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 54)); // Fuente y tamaño
        contentPane.add(lblNewLabel); // Añade la etiqueta al panel de contenido

        // --- Separador decorativo ---
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(225, 227, 194)); // Color del separador
        separator.setBackground(new Color(225, 227, 194)); // Color de fondo del separador
        separator.setBounds(101, 380, 282, 3); // Posición y tamaño del separador
        contentPane.add(separator); // Añade el separador al panel de contenido

        // --- Panel con forma de rombo (izquierda) ---
        JPanel panelRombo = crearPanelRombo(); // Llama al método para crear el rombo
        panelRombo.setBackground(new Color(3, 65, 37)); // Color de fondo (aunque será cubierto por paintComponent)
        panelRombo.setBounds(61, 370, 20, 20); // Posición y tamaño del rombo
        contentPane.add(panelRombo); // Añade el rombo al panel de contenido

        // --- Panel con forma de rombo (derecha) ---
        JPanel panelRombo_1 = crearPanelRombo(); // Llama al método para crear el rombo
        panelRombo_1.setBounds(405, 370, 20, 20); // Posición y tamaño del rombo
        contentPane.add(panelRombo_1); // Añade el rombo al panel de contenido

        // --- Imagen de fondo ---
        // Carga la imagen de fondo desde los recursos del classpath
        lblfondo.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoPoker2.png")));
        lblfondo.setBounds(-11, 0, 518, 757); // Posición y tamaño de la imagen de fondo
        contentPane.add(lblfondo); // Añade la etiqueta de fondo al panel de contenido

        contentPane.setVisible(true); // Hace visible el panel de contenido
    }

    /**
     * Aplica un estilo redondeado y sin bordes a un JButton.
     * Esto se logra personalizando el UI del botón para dibujar una forma redondeada.
     */
    private void estiloBotonRedondeado(JButton boton) {
        boton.setFocusPainted(false);      // Elimina el borde de foco cuando el botón está seleccionado
        boton.setBorderPainted(false);     // Elimina el borde por defecto del botón
        boton.setContentAreaFilled(false); // No rellena el área rectangular del botón
        boton.setOpaque(false);            // Hace que el botón sea transparente por defecto (necesario para el pintado personalizado)

        // Establece un UI personalizado para el botón
        boton.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                // Crea una copia del objeto Graphics para no afectar el contexto de pintado original
                Graphics2D g2 = (Graphics2D) g.create();
                // Habilita el antialiasing para bordes más suaves
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Establece el color de fondo del botón para el relleno
                g2.setColor(boton.getBackground());
                // Rellena un rectángulo con esquinas redondeadas
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
                g2.dispose(); // Libera los recursos gráficos
                super.paint(g, c); // Llama al método paint de la superclase para dibujar el texto y el icono del botón
            }
        });
    }

    /**
     * Crea y devuelve un JPanel que se pinta con forma de rombo.
     * @return Un JPanel con un dibujo personalizado de rombo.
     */
    private JPanel crearPanelRombo() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Asegura que el fondo del panel se pinte correctamente
                Graphics2D g2 = (Graphics2D) g; // Convierte a Graphics2D para usar características avanzadas

                int width = getWidth();    // Ancho del panel
                int height = getHeight();  // Alto del panel

                // Define los puntos para dibujar el rombo
                int[] xPoints = { width / 2, width, width / 2, 0 };
                int[] yPoints = { 0, height / 2, height, height / 2 };

                g2.setColor(new Color(235, 227, 194)); // Establece el color de relleno del rombo
                g2.fillPolygon(xPoints, yPoints, 4); // Rellena el polígono (rombo) con los puntos definidos
            }
        };
    }
}