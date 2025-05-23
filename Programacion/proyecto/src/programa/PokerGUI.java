package programa;

// Importaciones necesarias
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

// Clase principal que extiende JFrame
public class PokerGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    // Conexión a la base de datos
    ConexionMySQL con = new ConexionMySQL("root", "", "sql7780337");

    // Componentes de la GUI
    private JPanel contentPane;
    private final JLabel lblfondo = new JLabel("");

    // Método principal: lanza la aplicación
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PokerGUI frame = new PokerGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Constructor del JFrame
    public PokerGUI() {
        // Configuración básica de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 484, 743);
        setUndecorated(true);

        // Inicialización del panel principal
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Botón "Log in"
        JButton btn_login = new JButton("Log in");
        btn_login.setFont(new Font("Tahoma", Font.BOLD, 24));
        btn_login.setBackground(new Color(196, 49, 25));
        btn_login.setForeground(Color.WHITE);
        btn_login.setBounds(133, 274, 228, 78);

        // Eliminar bordes y hacer el botón redondeado
        estiloBotonRedondeado(btn_login);

        // Acción al hacer clic en "Log in"
        btn_login.addActionListener(e -> {
            LoginForm login = new LoginForm();
            login.setVisible(true);
            dispose();
        });

        contentPane.add(btn_login);

        // Botón "Register"
        JButton btn_register = new JButton("Register");
        btn_register.setFont(new Font("Tahoma", Font.BOLD, 24));
        btn_register.setForeground(Color.WHITE);
        btn_register.setBackground(new Color(196, 49, 25));
        btn_register.setBounds(133, 412, 228, 78);

        // Eliminar bordes y hacer el botón redondeado
        estiloBotonRedondeado(btn_register);

        // Acción al hacer clic (actualmente vacía)
        btn_register.addActionListener(e -> {
        	RegisterForm f1 = new RegisterForm();
            f1.setVisible(true);
            dispose();
        });

        contentPane.add(btn_register);

        // Etiqueta con el título del juego
        JLabel lblNewLabel = new JLabel("TightPoker");
        lblNewLabel.setForeground(new Color(235, 227, 194));
        lblNewLabel.setBounds(133, 66, 228, 95);
        lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 54));
        contentPane.add(lblNewLabel);

        // Separador decorativo
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(225, 227, 194));
        separator.setBackground(new Color(225, 227, 194));
        separator.setBounds(101, 380, 282, 3);
        contentPane.add(separator);

        // Panel con forma de rombo (izquierda)
        JPanel panelRombo = crearPanelRombo();
        panelRombo.setBackground(new Color(3, 65, 37));
        panelRombo.setBounds(61, 370, 20, 20);
        contentPane.add(panelRombo);

        // Panel con forma de rombo (derecha)
        JPanel panelRombo_1 = crearPanelRombo();
        panelRombo_1.setBounds(405, 370, 20, 20);
        contentPane.add(panelRombo_1);

        // Imagen de fondo 
        lblfondo.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoPoker2.png")));
        lblfondo.setBounds(-11, 0, 518, 757);
        contentPane.add(lblfondo);

        contentPane.setVisible(true);
    }

    /**
     * Aplica un estilo redondeado y sin bordes a un botón.
     */
    private void estiloBotonRedondeado(JButton boton) {
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setOpaque(false);

        // Personalización del UI del botón
        boton.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(boton.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
                g2.dispose();
                super.paint(g, c);
            }
        });
    }

    /**
     * Crea un panel con forma de rombo personalizado.
     */
    private JPanel crearPanelRombo() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                int width = getWidth();
                int height = getHeight();

                int[] xPoints = { width / 2, width, width / 2, 0 };
                int[] yPoints = { 0, height / 2, height, height / 2 };

                g2.setColor(new Color(235, 227, 194)); // Color similar al título
                g2.fillPolygon(xPoints, yPoints, 4);
            }
        };
    }
}