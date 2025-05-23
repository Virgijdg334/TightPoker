package programa;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * La clase Cajero representa la interfaz gráfica principal del cajero automático.
 * Permite al usuario interactuar con diferentes secciones como datos de cuenta,
 * ingreso, retirada, soporte y preguntas frecuentes.
 */
public class Cajero extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel mainContentPanel; // Panel que contiene las diferentes vistas (Datos, Ingreso, etc.)
    private JPanel menuPanel; // Panel del menú lateral
    private CardLayout cardLayout; // Para cambiar entre las diferentes vistas del mainContentPanel

    // Etiquetas para mostrar la información del usuario
    private JLabel lbl_tarjetaActual;
    private JLabel lbl_saldoActual;

    // Instancia del usuario actual obtenida de la sesión
    Usuario actual = SesionUsuario.getUsuario();

    // Formato del saldo y número de tarjeta para visualización
    public String saldoTexto = String.format("%.2f €", actual.getSaldo());
    public String tarjetaFormateada = formatCardNumber(actual.getTarjeta());

    /**
     * Punto de entrada principal de la aplicación.
     * Configura el LookAndFeel y lanza la interfaz del cajero.
     */
    public static void main(String[] args) {
        // Intenta establecer el LookAndFeel "Nimbus" para una apariencia moderna
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.err.println("No se pudo cargar Nimbus LookAndFeel. Usando el por defecto.");
        }

        // Ejecuta la interfaz gráfica en el hilo de despacho de eventos de Swing
        EventQueue.invokeLater(() -> {
            try {
                Cajero frame = new Cajero();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Constructor de la clase Cajero.
     * Inicializa y configura todos los componentes de la interfaz gráfica.
     */
    public Cajero() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 621, 414);
        setUndecorated(true); // Elimina la barra de título y los bordes de la ventana

        contentPane = new JPanel();
        contentPane.setLayout(null); // Diseño nulo para posicionar componentes libremente
        setContentPane(contentPane);

        // --- Panel de Fondo ---
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Carga y dibuja la imagen de fondo escalada
                URL imageUrl = getClass().getResource("/imagenes/fondoPokerHorizontal.png");
                if (imageUrl != null) {
                    ImageIcon icon = new ImageIcon(imageUrl);
                    Image img = icon.getImage();

                    int imgWidth = icon.getIconWidth();
                    int imgHeight = icon.getIconHeight();
                    int panelWidth = getWidth();
                    int panelHeight = getHeight();

                    // Escala la imagen para cubrir todo el panel
                    double scale = Math.max((double) panelWidth / imgWidth, (double) panelHeight / imgHeight);
                    int scaledWidth = (int) (imgWidth * scale);
                    int scaledHeight = (int) (imgHeight * scale);

                    int x = (panelWidth - scaledWidth) / 2;
                    int y = (panelHeight - scaledHeight) / 2;

                    g.drawImage(img, x, y, scaledWidth, scaledHeight, this);
                } else {
                    System.out.println("Imagen no encontrada en /imagenes/fondoPokerHorizontal.png");
                }
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, getWidth(), getHeight());
        contentPane.add(backgroundPanel);

        // --- Botón de Volver a la Página Principal ---
        URL backIconUrl = getClass().getResource("/imagenes/volver1.png");
        ImageIcon backIcon = null;
        if (backIconUrl != null) {
            backIcon = new ImageIcon(backIconUrl);
            Image image = backIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            backIcon = new ImageIcon(image);
        } else {
            System.out.println("Imagen no encontrada para el botón de volver.");
            backIcon = new ImageIcon(); // Icono vacío si no se encuentra la imagen
        }

        JButton btnVolver = new JButton(backIcon);
        btnVolver.setBounds(518, 181, 60, 60);
        btnVolver.setContentAreaFilled(false); // Hace que el área de contenido no sea pintada
        btnVolver.setFocusPainted(false); // Elimina el recuadro de foco
        btnVolver.setBorderPainted(false); // Elimina el borde del botón
        btnVolver.setForeground(new Color(5, 66, 47));
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            PaginaPrincipal p1 = new PaginaPrincipal();
            dispose(); // Cierra la ventana actual
            p1.setVisible(true); // Muestra la página principal
        });
        backgroundPanel.add(btnVolver);

        // --- menuPanel: El menú lateral ---
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(5, 66, 47));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        menuPanel.setOpaque(true);
        menuPanel.setBounds(58, 129, 100, 178);
        backgroundPanel.add(menuPanel);

        // Creación y adición de los botones del menú
        JButton btnDatos = createCompactMenuButton("Datos");
        JButton btnIngreso = createCompactMenuButton("Ingreso");
        JButton btnRetirada = createCompactMenuButton("Retirada");
        JButton btnSoporte = createCompactMenuButton("Soporte");
        JButton btnFAQ = createCompactMenuButton("FAQ");

        menuPanel.add(btnDatos);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio entre botones
        menuPanel.add(btnIngreso);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        menuPanel.add(btnRetirada);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        menuPanel.add(btnSoporte);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        menuPanel.add(btnFAQ);

        // --- mainContentPanel: El recuadro blanco de contenido principal ---
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout); // Usa CardLayout para cambiar vistas
        mainContentPanel.setBorder(new LineBorder(new Color(0, 0, 0), 0));
        mainContentPanel.setOpaque(true);
        mainContentPanel.setBackground(new Color(5, 66, 47));
        mainContentPanel.setBounds(170, 129, 351, 192);
        backgroundPanel.add(mainContentPanel);

        // Añade los paneles de contenido individuales al mainContentPanel
        mainContentPanel.add(createDatosPanel(), "Datos");
        mainContentPanel.add(createIngresoPanel(), "Ingreso");
        mainContentPanel.add(createRetiradaPanel(), "Retirada");
        mainContentPanel.add(createSoportePanel(), "Soporte");
        mainContentPanel.add(createFAQPanel(), "FAQ");

        // --- Listeners para los botones del menú (cambian la vista del CardLayout) ---
        btnDatos.addActionListener(e -> cardLayout.show(mainContentPanel, "Datos"));
        btnIngreso.addActionListener(e -> cardLayout.show(mainContentPanel, "Ingreso"));
        btnRetirada.addActionListener(e -> cardLayout.show(mainContentPanel, "Retirada"));
        btnSoporte.addActionListener(e -> cardLayout.show(mainContentPanel, "Soporte"));
        btnFAQ.addActionListener(e -> cardLayout.show(mainContentPanel, "FAQ"));

        // Muestra el panel de "Datos" al iniciar
        cardLayout.show(mainContentPanel, "Datos");

        // --- Etiquetas de información general en el fondo ---
        JLabel lbl_Cajero_Titulo = new JLabel("Cajero");
        lbl_Cajero_Titulo.setBounds(242, 68, 100, 40);
        backgroundPanel.add(lbl_Cajero_Titulo);
        lbl_Cajero_Titulo.setForeground(new Color(235, 227, 194));
        lbl_Cajero_Titulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 40));

        JLabel lbl_Salario_Titulo = new JLabel("Saldo:");
        lbl_Salario_Titulo.setBounds(272, 321, 70, 25);
        backgroundPanel.add(lbl_Salario_Titulo);
        lbl_Salario_Titulo.setForeground(new Color(235, 227, 194));
        lbl_Salario_Titulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 23));

        lbl_saldoActual = new JLabel(saldoTexto);
        lbl_saldoActual.setForeground(new Color(235, 227, 194));
        lbl_saldoActual.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 23));
        lbl_saldoActual.setBounds(262, 348, 100, 25);
        backgroundPanel.add(lbl_saldoActual);
    }

    /**
     * Crea el panel de la sección "Datos".
     * Muestra la información de la tarjeta y permite registrar una nueva.
     *
     * @return El JPanel configurado para la sección de datos.
     */
    private JPanel createDatosPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null); // Diseño nulo para posicionar componentes libremente
        panel.setOpaque(false); // Fondo transparente para ver el color del mainContentPanel

        JLabel infoLabel = new JLabel("Esta es la sección de Datos. Aquí puedes ver tu información general.");
        infoLabel.setForeground(new Color(235, 227, 194));
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoLabel.setBounds(20, 33, 349, 18);
        panel.add(infoLabel);

        JLabel lblDatos = new JLabel("Datos");
        lblDatos.setForeground(new Color(235, 227, 194));
        lblDatos.setFont(new Font("Arial", Font.BOLD, 20));
        lblDatos.setBounds(20, 6, 196, 24);
        panel.add(lblDatos);

        JLabel lbl_NumeroTarjeta_Titulo = new JLabel("Numero de Tarjeta:");
        lbl_NumeroTarjeta_Titulo.setForeground(new Color(235, 227, 194));
        lbl_NumeroTarjeta_Titulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_NumeroTarjeta_Titulo.setBounds(20, 63, 124, 25);
        panel.add(lbl_NumeroTarjeta_Titulo);

        lbl_tarjetaActual = new JLabel(tarjetaFormateada);
        lbl_tarjetaActual.setForeground(new Color(235, 227, 194));
        lbl_tarjetaActual.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_tarjetaActual.setBounds(20, 94, 163, 25);
        panel.add(lbl_tarjetaActual);

        JButton btnRegistrarTarjeta = new JButton("Registrar Tarjeta");
        btnRegistrarTarjeta.setBounds(195, 96, 150, 25);
        btnRegistrarTarjeta.setBackground(new Color(0, 128, 0));
        btnRegistrarTarjeta.setForeground(Color.WHITE);
        btnRegistrarTarjeta.addActionListener(e -> {
            String inputTarjeta = JOptionPane.showInputDialog("Introduce el número de tu tarjeta:");
            // Valida que la entrada sea de 16 dígitos numéricos
            if (inputTarjeta != null && inputTarjeta.matches("\\d{16}")) {
                try {
                    long tarjetaLong = Long.parseLong(inputTarjeta);

                    // Conexión y actualización de la tarjeta en la base de datos
                    ConexionMySQL conn = new ConexionMySQL("root", "password", "sql7780337");
                    conn.conectar();

                    String sql = "UPDATE usuario SET n_tarjeta = ? WHERE nombreUsuario = ?";
                    java.sql.PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
                    stmt.setLong(1, tarjetaLong);
                    stmt.setString(2, actual.getUser());

                    int filas = stmt.executeUpdate();
                    if (filas > 0) {
                        actual.setTarjeta(tarjetaLong); // Actualiza el objeto usuario en memoria
                        conn.updateTarjeta(actual.getUser(), tarjetaLong); // Llama a tu método específico de actualización
                        JOptionPane.showMessageDialog(null, "✅ Tarjeta registrada correctamente.");
                        actualizarDatosVisuales(); // Actualiza la interfaz
                    } else {
                        JOptionPane.showMessageDialog(null, "⚠️ No se pudo registrar la tarjeta.");
                    }

                    stmt.close();
                    conn.desconectar();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "❌ El número de tarjeta debe ser numérico.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "❌ Error en la base de datos.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "❌ Número de tarjeta inválido. Deben ser exactamente 16 dígitos.");
            }
        });
        panel.add(btnRegistrarTarjeta);

        return panel;
    }

    /**
     * Crea el panel de la sección "Ingreso".
     * Permite al usuario ingresar una cantidad de dinero en su cuenta.
     *
     * @return El JPanel configurado para la sección de ingreso.
     */
    private JPanel createIngresoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Diseño en vertical
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Sección de Ingreso");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(235, 227, 194));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel instruction = new JLabel("Introduce la cantidad a ingresar:");
        instruction.setForeground(new Color(235, 227, 194));
        instruction.setFont(new Font("Arial", Font.PLAIN, 12));
        instruction.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(instruction);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JTextField amountField = new JTextField(15);
        amountField.setBackground(new Color(235, 227, 194));
        amountField.setForeground(new Color(0, 0, 0));
        amountField.setMaximumSize(new Dimension(150, 22)); // Tamaño fijo para el campo de texto
        amountField.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(amountField);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton confirmButton = new JButton("Confirmar Ingreso");
        styleActionButton(confirmButton); // Aplica estilo al botón
        panel.add(confirmButton);
        confirmButton.addActionListener(e -> {
            // Verifica si hay una tarjeta registrada antes de ingresar
            if (actual.getTarjeta() == 0) {
                JOptionPane.showMessageDialog(panel, "❌ Debes registrar una tarjeta antes de ingresar dinero.");
                return;
            }
            try {
                double cantidad = Double.parseDouble(amountField.getText());
                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(panel, "❌ La cantidad a ingresar debe ser positiva.");
                    return;
                }
                actual.setSaldo(actual.getSaldo() + cantidad); // Actualiza el saldo en memoria

                // Actualiza el saldo en la base de datos
                ConexionMySQL conn = new ConexionMySQL("root", "password", "sql7780337");
                conn.conectar();
                String sql = "UPDATE usuario SET saldo = ? WHERE nombreUsuario = ?";
                java.sql.PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
                stmt.setDouble(1, actual.getSaldo());
                stmt.setString(2, actual.getUser());
                stmt.executeUpdate();
                stmt.close();
                conn.desconectar();

                JOptionPane.showMessageDialog(panel, "✅ Ingreso realizado correctamente.");
                actualizarDatosVisuales(); // Actualiza la interfaz
                amountField.setText(""); // Limpia el campo de texto
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "❌ Por favor, introduce una cantidad numérica válida.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "❌ Error al ingresar: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        return panel;
    }

    /**
     * Crea el panel de la sección "Retirada".
     * Permite al usuario retirar una cantidad de dinero de su cuenta.
     *
     * @return El JPanel configurado para la sección de retirada.
     */
    private JPanel createRetiradaPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Sección de Retirada");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(235, 227, 194));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel instruction = new JLabel("Introduce la cantidad a retirar:");
        instruction.setForeground(new Color(235, 227, 194));
        instruction.setFont(new Font("Arial", Font.PLAIN, 12));
        instruction.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(instruction);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JTextField amountField = new JTextField(15);
        amountField.setBackground(new Color(235, 227, 194));
        amountField.setMaximumSize(new Dimension(150, 22));
        amountField.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(amountField);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton confirmButton = new JButton("Confirmar Retirada");
        confirmButton.setBackground(new Color(235, 227, 194)); // Este color será sobrescrito por styleActionButton
        confirmButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        styleActionButton(confirmButton); // Aplica estilo al botón
        panel.add(confirmButton);
        confirmButton.addActionListener(e -> {
            try {
                double cantidad = Double.parseDouble(amountField.getText());
                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(panel, "❌ La cantidad a retirar debe ser positiva.");
                    return;
                }
                if (actual.getSaldo() >= cantidad) {
                    actual.setSaldo(actual.getSaldo() - cantidad); // Actualiza el saldo en memoria

                    // Actualiza el saldo en la base de datos
                    ConexionMySQL conn = new ConexionMySQL("root", "password", "sql7780337");
                    conn.conectar();
                    String sql = "UPDATE usuario SET saldo = ? WHERE nombreUsuario = ?";
                    java.sql.PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
                    stmt.setDouble(1, actual.getSaldo());
                    stmt.setString(2, actual.getUser());
                    stmt.executeUpdate();
                    stmt.close();
                    conn.desconectar();

                    JOptionPane.showMessageDialog(panel, "✅ Retirada completada.");
                    actualizarDatosVisuales(); // Actualiza la interfaz
                    amountField.setText(""); // Limpia el campo de texto
                } else {
                    JOptionPane.showMessageDialog(panel, "❌ Saldo insuficiente.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "❌ Por favor, introduce una cantidad numérica válida.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "❌ Error al retirar: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        return panel;
    }

    /**
     * Crea el panel de la sección "Soporte".
     * Muestra información de contacto para soporte al usuario.
     *
     * @return El JPanel configurado para la sección de soporte.
     */
    private JPanel createSoportePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Contacto de Soporte");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(235, 227, 194));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel emailLabel = new JLabel("Email: soporte@tuempresa.com");
        emailLabel.setForeground(new Color(235, 227, 194));
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(emailLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel phoneLabel = new JLabel("Teléfono: +34 616 778 911");
        phoneLabel.setForeground(new Color(235, 227, 194));
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        phoneLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(phoneLabel);

        return panel;
    }

    /**
     * Crea el panel de la sección "Preguntas Frecuentes (FAQ)".
     * Muestra una lista de preguntas y respuestas comunes.
     *
     * @return El JPanel configurado para la sección de FAQ.
     */
    private JPanel createFAQPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Preguntas Frecuentes (FAQ)");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(235, 227, 194));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel q1 = new JLabel("Q: ¿Cómo puedo cambiar mi contraseña?");
        q1.setForeground(new Color(235, 227, 194));
        q1.setFont(new Font("Arial", Font.PLAIN, 12));
        q1.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(q1);
        JLabel a1 = new JLabel("A: Ve a 'Datos' y busca la opción de seguridad.");
        a1.setForeground(new Color(235, 227, 194));
        a1.setFont(new Font("Arial", Font.PLAIN, 12));
        a1.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(a1);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel q2 = new JLabel("Q: ¿Cuál es el límite de retiro diario?");
        q2.setForeground(new Color(235, 227, 194));
        q2.setFont(new Font("Arial", Font.PLAIN, 12));
        q2.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(q2);
        JLabel a2 = new JLabel("A: El límite es de 1000€ por día.");
        a2.setForeground(new Color(235, 227, 194));
        a2.setFont(new Font("Arial", Font.PLAIN, 12));
        a2.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(a2);

        return panel;
    }

    /**
     * Método auxiliar para crear y estilizar los botones del menú lateral.
     *
     * @param text El texto del botón.
     * @return El JButton estilizado.
     */
    private JButton createCompactMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setForeground(new Color(235, 227, 194));
        button.setBackground(new Color(138, 35, 17));
        button.setBorder(new CompoundBorder(new LineBorder(new Color(100, 100, 100), 0), new EmptyBorder(6, 8, 6, 8)));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // No es necesario un MouseAdapter vacío. Se puede eliminar si no se le añade funcionalidad.
        // button.addMouseListener(new java.awt.event.MouseAdapter() {});
        return button;
    }

    /**
     * Método auxiliar para estilizar los botones de acción (ej. "Confirmar Ingreso").
     *
     * @param button El JButton a estilizar.
     */
    private void styleActionButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 128, 0));
        button.setBorder(new LineBorder(new Color(0, 0, 0), 0));
        button.setPreferredSize(new Dimension(40, 40));
    }

    /**
     * Formatea el número de tarjeta en grupos de cuatro dígitos.
     *
     * @param cardNumber El número de tarjeta largo.
     * @return El número de tarjeta formateado como String.
     */
    private String formatCardNumber(long cardNumber) {
        return String.format("%04d %04d %04d %04d",
                (cardNumber / 1000000000000L) % 10000,
                (cardNumber / 100000000L) % 10000,
                (cardNumber / 10000L) % 10000,
                cardNumber % 10000);
    }

    /**
     * Actualiza visualmente el saldo y el número de tarjeta mostrados en la interfaz.
     */
    private void actualizarDatosVisuales() {
        tarjetaFormateada = formatCardNumber(actual.getTarjeta());
        saldoTexto = String.format("%.2f €", actual.getSaldo());

        lbl_tarjetaActual.setText(tarjetaFormateada);
        lbl_saldoActual.setText(saldoTexto);
    }
}