package programa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * La clase `PantallaCarga` (Splash Screen) muestra una ventana de carga
 * con una barra de progreso mientras la aplicación se inicializa o realiza
 * alguna tarea de fondo, para luego lanzar la ventana principal.
 */
public class PantallaCarga extends JFrame {

    private static final long serialVersionUID = 1L; // Identificador de serialización

    /**
     * Punto de entrada principal para la pantalla de carga.
     * Crea y muestra la ventana de carga en el hilo de despacho de eventos de Swing.
     *
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // Al crear una nueva instancia, el constructor se encarga de mostrar la ventana.
                new PantallaCarga();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Constructor de la clase `PantallaCarga`.
     * Configura y muestra la ventana de carga con una barra de progreso animada.
     */
    public PantallaCarga() {
        // --- 1. Cargar la imagen de fondo ---
        URL fondoUrl = getClass().getResource("/imagenes/fondoPokerHorizontal.png");
        ImageIcon fondoIcon = null;

        if (fondoUrl != null) {
            fondoIcon = new ImageIcon(fondoUrl);
        } else {
            // Manejo de error si la imagen no se encuentra.
            System.err.println("Error: Imagen de fondo no encontrada en /imagenes/fondoPokerHorizontal.png");
            // Se puede optar por salir o usar un fondo de color si la imagen es crucial.
            // En este caso, el constructor lanzará una excepción si fondoIcon es null.
            return;
        }

        Image fondoImg = fondoIcon.getImage();

        // Obtener dimensiones de la imagen.
        // Es importante verificar que las dimensiones sean válidas antes de usarlas.
        int ancho = fondoIcon.getIconWidth();
        int alto = fondoIcon.getIconHeight();

        if (ancho <= 0 || alto <= 0) {
            System.err.println("La imagen de fondo tiene dimensiones inválidas: Ancho=" + ancho + ", Alto=" + alto);
            return;
        }

        // --- 2. Crear la ventana de splash (JWindow) ---
        // JWindow es ideal para splash screens ya que no tiene bordes ni barra de título.
        JWindow splash = new JWindow();
        splash.getContentPane().setLayout(null); // Diseño nulo para posicionar el fondo.

        // --- 3. Configurar el fondo de la ventana ---
        JLabel fondoLabel = new JLabel(fondoIcon);
        fondoLabel.setBounds(0, 0, ancho, alto); // Ajusta al tamaño de la imagen.
        fondoLabel.setLayout(new BorderLayout()); // Usa BorderLayout para el contenido centrado.

        // --- 4. Panel central para el título y la barra de progreso ---
        JPanel centroPanel = new JPanel();
        centroPanel.setForeground(new Color(255, 255, 255));
        centroPanel.setOpaque(false); // Hace el panel transparente para ver el fondo.
        centroPanel.setLayout(new GridBagLayout()); // Usar GridBagLayout para centrado y espaciado.

        // --- 5. Título "Cargando..." ---
        JLabel tituloLabel = new JLabel("Cargando...", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 40));
        tituloLabel.setForeground(new Color(235, 227, 194));
        tituloLabel.setBorder(new EmptyBorder(20, 0, 20, 0)); // Padding superior/inferior.

        GridBagConstraints gbc_titulo = new GridBagConstraints();
        gbc_titulo.insets = new Insets(0, 0, 5, 0); // Espacio debajo del título.
        gbc_titulo.gridx = 0;
        gbc_titulo.gridy = 0;
        centroPanel.add(tituloLabel, gbc_titulo);

        // --- 6. Barra de progreso ---
        JProgressBar barraProgreso = new JProgressBar();
        barraProgreso.setMinimum(0);
        barraProgreso.setMaximum(100);
        barraProgreso.setStringPainted(true); // Muestra el porcentaje.
        barraProgreso.setPreferredSize(new Dimension(400, 30));
        barraProgreso.setForeground(new Color(196, 49, 25)); // Color de la barra de progreso.
        barraProgreso.setBackground(new Color(235, 227, 194)); // Color de fondo de la barra.
        barraProgreso.setBorder(new LineBorder(new Color(3, 65, 37), 4, true)); // Borde redondeado.

        GridBagConstraints gbc_barra = new GridBagConstraints();
        gbc_barra.gridx = 0;
        gbc_barra.gridy = 2; // Posiciona debajo del título (dejando un espacio implícito).
        centroPanel.add(barraProgreso, gbc_barra);

        // Añadir el panel central al fondo.
        fondoLabel.add(centroPanel, BorderLayout.CENTER);

        // Añadir el fondo (con el centro dentro) al contenido de la JWindow.
        splash.getContentPane().add(fondoLabel);
        splash.setSize(ancho, alto);
        splash.setLocationRelativeTo(null); // Centra la ventana en la pantalla.
        splash.setVisible(true);

        // --- 7. Timer para simular la carga ---
        // Simula un progreso de carga a lo largo del tiempo.
        Timer timer = new Timer(32, new ActionListener() { // Cada 32 ms (aprox. 30 fps).
            int progreso = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                progreso += 2; // Incrementa el progreso.
                barraProgreso.setValue(progreso); // Actualiza la barra.

                if (progreso >= 100) {
                    ((Timer) e.getSource()).stop(); // Detiene el timer.
                    splash.dispose(); // Cierra la ventana de carga.
                    lanzarVentanaPrincipal(); // Lanza la siguiente ventana.
                }
            }
        });

        timer.start(); // Inicia el timer.
    }

    /**
     * Lanza la ventana principal de la aplicación (`PaginaPrincipal`) una vez
     * que la pantalla de carga ha terminado su proceso.
     */
    public static void lanzarVentanaPrincipal() {
        PaginaPrincipal P1 = new PaginaPrincipal();
        P1.setVisible(true);
    }
}