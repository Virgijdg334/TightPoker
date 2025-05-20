package programa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PantallaCarga extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new PantallaCarga();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PantallaCarga() {
        // Cargar imagen del fondo
        ImageIcon fondoIcon = new ImageIcon("C:\\\\Users\\\\Alumno1\\\\Documents\\\\ProyectoPoker\\\\TightPoker\\\\imagenes\\\\fondoPokerHorizontal.png");
        Image fondoImg = fondoIcon.getImage();

        int ancho = fondoImg.getWidth(null);
        int alto = fondoImg.getHeight(null);

        if (ancho <= 0 || alto <= 0) {
            System.err.println("No se pudo cargar la imagen o tiene dimensiones inválidas.");
            return;
        }

        // Crear splash
        JWindow splash = new JWindow();
        splash.getContentPane().setLayout(null);

        // Fondo con imagen
        JLabel fondo = new JLabel(fondoIcon);
        fondo.setBounds(0, 0, 621, 414);
        fondo.setLayout(new BorderLayout());

        // -------- PANEL CENTRAL CON LA BARRA --------
        JPanel centro = new JPanel();
        centro.setForeground(new Color(255, 255, 255));
        centro.setOpaque(false); // Transparente
        centro.setLayout(new GridBagLayout()); // Centrar vertical y horizontal
        
                // -------- TÍTULO EN LA PARTE SUPERIOR --------
                JLabel titulo = new JLabel("Cargando...", SwingConstants.CENTER);
                GridBagConstraints gbc_titulo = new GridBagConstraints();
                gbc_titulo.insets = new Insets(0, 0, 5, 0);
                gbc_titulo.gridx = 0;
                gbc_titulo.gridy = 0;
                centro.add(titulo, gbc_titulo);
                titulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 40));
                titulo.setForeground(new Color(235, 227, 194));
                titulo.setBorder(new EmptyBorder(20, 0, 20, 0));

        JProgressBar barra = new JProgressBar();
        barra.setMinimum(0);
        barra.setMaximum(100);
        barra.setStringPainted(true);
        barra.setPreferredSize(new Dimension(400, 30));
        barra.setForeground(new Color(196, 49, 25));
        barra.setBackground(new Color(235, 227, 194));
        barra.setBorder(new LineBorder(new Color(3, 65, 37), 4, true));

        GridBagConstraints gbc_barra = new GridBagConstraints();
        gbc_barra.gridx = 0;
        gbc_barra.gridy = 2;
        centro.add(barra, gbc_barra); // Añadir barra al centro
        fondo.add(centro, BorderLayout.CENTER);

        splash.getContentPane().add(fondo);
        splash.setSize(ancho, alto);
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);

        // Timer para simular carga
        Timer timer = new Timer(32, null);
        timer.addActionListener(new ActionListener() {
            int progreso = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                progreso += 2;
                barra.setValue(progreso);
                if (progreso >= 100) {
                    timer.stop();
                    splash.dispose();
                    lanzarVentanaPrincipal();
                }
            }
        });

        timer.start();
    }

    public static void lanzarVentanaPrincipal() {
        PaginaPrincipal P1 = new PaginaPrincipal();
        P1.setVisible(true);
    }
}
