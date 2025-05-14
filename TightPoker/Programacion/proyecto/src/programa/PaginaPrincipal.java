package programa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class PaginaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private final JLabel lblfondo = new JLabel("");
    ConexionMySQL bd = new ConexionMySQL("sql7774423", "sxpS4Av81l", "sql7774423");

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PaginaPrincipal frame = new PaginaPrincipal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PaginaPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 484, 743);
        setUndecorated(true);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Botón "Home" con icono de casa para volver al menú principal
        

        // Botón de perfil redondo
        ImageIcon icon = new ImageIcon("C:\\Users\\Alumno1\\Documents\\ProyectoPoker\\TightPoker\\imagenes\\fotoperfil3.png");
        Image image = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        JButton botonRedondo = new JButton(icon) {


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
        botonRedondo.setOpaque(false);
        contentPane.add(botonRedondo);

        // Título
        JLabel lblNewLabel = new JLabel("TightPoker");
        lblNewLabel.setForeground(new Color(235, 227, 194));
        lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 54));
        lblNewLabel.setBounds(133, 109, 228, 95);
        contentPane.add(lblNewLabel);

        // Botón Torneos
        JButton btn_Torneos = crearBotonRedondeado("Torneos", 133, 275, 228, 78);
        contentPane.add(btn_Torneos);

        // Botón Partidas Cercanas
        JButton btn_Partidas_Cercanas = crearBotonRedondeado("Partidas Cercanas", 133, 415, 228, 78);
        contentPane.add(btn_Partidas_Cercanas);

        // Botón Cajero
        JButton btn_cajero = crearBotonRedondeado("Cajero", 167, 559, 151, 43);
        contentPane.add(btn_cajero);

        // Saldo
        JLabel lblSaldo = new JLabel("Saldo:");
        lblSaldo.setForeground(new Color(235, 227, 194));
        lblSaldo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 22));
        lblSaldo.setBounds(124, 631, 60, 29);
        contentPane.add(lblSaldo);

        // Separadores decorativos
        agregarSeparador(108, 246);
        agregarSeparador(108, 380);
        agregarSeparador(108, 527);

        // Rombo decorativo
        agregarRombo(78, 237);
        agregarRombo(78, 371);
        agregarRombo(78, 518);
        agregarRombo(400, 237);
        agregarRombo(400, 371);
        agregarRombo(400, 518);

        // Fondo
        lblfondo.setIcon(new ImageIcon("C:\\Users\\Alumno1\\Documents\\ProyectoPoker\\TightPoker\\imagenes\\fondoPoker.png"));
        lblfondo.setBounds(-11, 0, 518, 757);
        contentPane.add(lblfondo);

        contentPane.setVisible(true);
    }

    // Método auxiliar para crear botones redondeados
    private JButton crearBotonRedondeado(String texto, int x, int y, int ancho, int alto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Tahoma", Font.BOLD, 18));
        boton.setForeground(Color.WHITE);
        boton.setBackground(new Color(196, 49, 25));
        boton.setBounds(x, y, ancho, alto);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setOpaque(false);

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

        return boton;
    }

    // Método auxiliar para agregar rombos decorativos
    private void agregarRombo(int x, int y) {
        JPanel panelRombo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                int[] xPoints = { width / 2, width, width / 2, 0 };
                int[] yPoints = { 0, height / 2, height, height / 2 };
                g2.setColor(new Color(235, 227, 194));
                g2.fillPolygon(xPoints, yPoints, 4);
            }
        };
        panelRombo.setOpaque(false);
        panelRombo.setBounds(x, y, 20, 20);
        contentPane.add(panelRombo);
    }

    // Método auxiliar para agregar separadores
    private void agregarSeparador(int x, int y) {
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(235, 227, 194));
        separator.setBackground(new Color(235, 227, 194));
        separator.setBounds(x, y, 282, 2);
        contentPane.add(separator);
    }
}
