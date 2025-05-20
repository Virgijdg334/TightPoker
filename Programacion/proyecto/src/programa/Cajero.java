package programa;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component; // Necesario para BoxLayout.X_AXIS / Y_AXIS alignment
import java.awt.Dimension; // Para Box.createRigidArea
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class Cajero extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel mainContentPanel; // El recuadro blanco de contenido
    private JPanel menuPanel; // El panel del menú lateral
    private CardLayout cardLayout;

    // Etiquetas y componentes que se moverán al panel de "Datos"
    private JLabel lbl_Salario_Texto;
    private JLabel lbl_Cajero_Datos;


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.err.println("No se pudo cargar Nimbus LookAndFeel. Usando el por defecto.");
        }

        EventQueue.invokeLater(() -> {
            try {
                Cajero frame = new Cajero();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Cajero() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Ajustamos el tamaño del JFrame para que el fondo y los paneles encajen bien
        setBounds(100, 100, 621, 414); // Un poco más ancho para el menú y el espacio
        setUndecorated(true);

        contentPane = new JPanel();
        contentPane.setLayout(null); // contentPane sigue con null layout para el fondo
        setContentPane(contentPane);

        // --- Panel de Fondo ---
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C:\\Users\\Alumno1\\Documents\\TightPoker\\imagenes\\fondoPokerHorizontal.png");
                Image img = icon.getImage();

                if (img != null) {
                    int imgWidth = img.getWidth(this);
                    int imgHeight = img.getHeight(this);
                    int panelWidth = getWidth();
                    int panelHeight = getHeight();

                    double scaleX = (double) panelWidth / imgWidth;
                    double scaleY = (double) panelHeight / imgHeight;
                    double scale = Math.max(scaleX, scaleY); // Escala para cubrir completamente

                    int scaledWidth = (int) (imgWidth * scale);
                    int scaledHeight = (int) (imgHeight * scale);

                    int x = (panelWidth - scaledWidth) / 2;
                    int y = (panelHeight - scaledHeight) / 2;

                    g.drawImage(img, x, y, scaledWidth, scaledHeight, this);
                }
            }
        };
        backgroundPanel.setLayout(null); // backgroundPanel también con null layout
        backgroundPanel.setBounds(0, 0, getWidth(), getHeight());
        contentPane.add(backgroundPanel);

        // --- menuPanel: El menú lateral (separado del contenido principal) ---
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(5, 66, 47)); // Fondo del menú (oscuro)
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5)); // Márgenes reducidos
        menuPanel.setOpaque(true);

        // Posicionamos el menuPanel a la izquierda, ajusta 'x' y 'y' si quieres moverlo
        int menuWidth = 100; // Ancho del menú
        int menuHeight = 220; // Alto del menú (ajustado para los botones)
        int menuX = 50; // Margen izquierdo
        int menuY = (getHeight() / 2) - (menuHeight / 2); // Centrado verticalmente
        menuPanel.setBounds(58, 129, 100, 178);
        backgroundPanel.add(menuPanel);

        JButton btnDatos = createCompactMenuButton("Datos");
        JButton btnIngreso = createCompactMenuButton("Ingreso");
        JButton btnRetirada = createCompactMenuButton("Retirada");
        JButton btnSoporte = createCompactMenuButton("Soporte");
        JButton btnFAQ = createCompactMenuButton("FAQ");

        menuPanel.add(btnDatos);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio entre botones reducido
        menuPanel.add(btnIngreso);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        menuPanel.add(btnRetirada);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        menuPanel.add(btnSoporte);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        menuPanel.add(btnFAQ);
       
        // --- mainContentPanel: El recuadro blanco de contenido ---
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout); // Ahora este panel contendrá las "tarjetas"
        mainContentPanel.setBorder(new LineBorder(new Color(0, 0, 0), 0));
        mainContentPanel.setOpaque(true);
        mainContentPanel.setBackground(new Color(5, 66, 47)); // Blanco semitransparente

        // Posicionamos el mainContentPanel a la derecha del menú con un espacio
        int contentX = menuX + menuWidth + 20; // 20px de espacio entre menú y contenido
        int contentWidth = 500; // Ancho del recuadro blanco
        int contentHeight = 350; // Alto del recuadro blanco
        int contentY = (getHeight() / 2) - (contentHeight / 2); // Centrado verticalmente con el menú
        mainContentPanel.setBounds(170, 129, 351, 192);
        backgroundPanel.add(mainContentPanel);

        // Creamos y añadimos los paneles de contenido individuales
        mainContentPanel.add(createDatosPanel(), "Datos");
        mainContentPanel.add(createIngresoPanel(), "Ingreso");
        mainContentPanel.add(createRetiradaPanel(), "Retirada");
        mainContentPanel.add(createSoportePanel(), "Soporte");
        mainContentPanel.add(createFAQPanel(), "FAQ");

        // --- Listeners para los botones del menú ---
        btnDatos.addActionListener(e -> cardLayout.show(mainContentPanel, "Datos"));
        btnIngreso.addActionListener(e -> cardLayout.show(mainContentPanel, "Ingreso"));
        btnRetirada.addActionListener(e -> cardLayout.show(mainContentPanel, "Retirada"));
        btnSoporte.addActionListener(e -> cardLayout.show(mainContentPanel, "Soporte"));
        btnFAQ.addActionListener(e -> cardLayout.show(mainContentPanel, "FAQ"));
       
        // Mostrar inicialmente el panel de "Datos"
        cardLayout.show(mainContentPanel, "Datos");
               
                        // Títulos y separador ahora dentro de este panel de datos
                        lbl_Cajero_Datos = new JLabel("Cajero");
                        lbl_Cajero_Datos.setBounds(242, 68, 100, 40);
                        backgroundPanel.add(lbl_Cajero_Datos);
                        lbl_Cajero_Datos.setForeground(new Color(235, 227, 194));
                        lbl_Cajero_Datos.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 40));
                       
                                lbl_Salario_Texto = new JLabel("Saldo:");
                                lbl_Salario_Texto.setBounds(272, 321, 70, 25);
                                backgroundPanel.add(lbl_Salario_Texto);
                                lbl_Salario_Texto.setForeground(new Color(235, 227, 194));
                                lbl_Salario_Texto.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 23));
                               
                                JLabel lbl_Salario_Datos = new JLabel("0");
                                lbl_Salario_Datos.setForeground(new Color(235, 227, 194));
                                lbl_Salario_Datos.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 23));
                                lbl_Salario_Datos.setBounds(289, 348, 17, 25);
                                backgroundPanel.add(lbl_Salario_Datos);
    }

    // --- Métodos para crear los paneles de contenido de cada sección ---

    private JPanel createDatosPanel() {
   
       JPanel panel = new JPanel();
       panel.setLayout(null);
       panel.setOpaque(false);
       
       // --- ¡Clave para hacer el separador más gordo! ---
       // Establece la altura preferida del separador.
       // Para un separador horizontal, solo necesitas modificar la altura.
       int desiredSeparatorHeight = 5; // Por ejemplo, 5 píxeles de grosor

       // ... (El resto de tu código para infoLabel, dataField1, dataField2) ...


        JLabel infoLabel = new JLabel("Esta es la sección de Datos. Aquí puedes ver tu información general.");
        infoLabel.setForeground(new Color(235, 227, 194));
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12)); // Texto más pequeño
        infoLabel.setBounds(20, 33, 349, 18); // Posición y altura ajustadas
        panel.add(infoLabel);
       
        JLabel lblDatos = new JLabel("Datos");
        lblDatos.setForeground(new Color(235, 227, 194));
        lblDatos.setFont(new Font("Arial", Font.BOLD, 20));
        lblDatos.setAlignmentX(0.0f);
        lblDatos.setBounds(20, 6, 196, 24);
        panel.add(lblDatos);
       
        JLabel lbl_Salario_Texto_1 = new JLabel("Numero de Tarjeta:");
        lbl_Salario_Texto_1.setForeground(new Color(235, 227, 194));
        lbl_Salario_Texto_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_Salario_Texto_1.setBounds(20, 63, 124, 25);
        panel.add(lbl_Salario_Texto_1);
       
        JLabel lbl_Salario_Texto_1_1 = new JLabel("0000-0000-0000-0000");
        lbl_Salario_Texto_1_1.setForeground(new Color(235, 227, 194));
        lbl_Salario_Texto_1_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_Salario_Texto_1_1.setBounds(20, 94, 155, 25);
        panel.add(lbl_Salario_Texto_1_1);

        return panel;
    }

    private JPanel createIngresoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Sección de Ingreso");
        title.setFont(new Font("Arial", Font.BOLD, 20)); // Título más pequeño
        title.setForeground(new Color(235, 227, 194));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio ajustado

        JLabel instruction = new JLabel("Introduce la cantidad a ingresar:");
        instruction.setForeground(new Color(235, 227, 194));
        instruction.setFont(new Font("Arial", Font.PLAIN, 12)); // Fuente más pequeña
        instruction.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(instruction);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JTextField amountField = new JTextField(15); // Tamaño de columna sugerido
        amountField.setBackground(new Color(235, 227, 194));
        amountField.setForeground(new Color(235, 227, 194));
        amountField.setMaximumSize(new Dimension(150, 22)); // Tamaño fijo
        amountField.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(amountField);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton confirmButton = new JButton("Confirmar Ingreso");
        styleActionButton(confirmButton);
        panel.add(confirmButton);
       
        confirmButton.addActionListener(e -> JOptionPane.showMessageDialog(panel, "Ingresando: " + amountField.getText()));

        return panel;
    }

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
        confirmButton.setBackground(new Color(235, 227, 194));
        confirmButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        styleActionButton(confirmButton);
        panel.add(confirmButton);
       
        confirmButton.addActionListener(e -> JOptionPane.showMessageDialog(panel, "Retirando: " + amountField.getText()));

        return panel;
    }

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

    // --- Método de ayuda para crear y estilizar botones del menú más compactos ---
    private JButton createCompactMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12)); // Fuente aún más pequeña
        button.setForeground(new Color(235, 227, 194));
        button.setBackground(new Color(138, 35, 17));
        button.setBorder(new CompoundBorder(new LineBorder(new Color(100, 100, 100), 0), new EmptyBorder(6, 8, 6, 8)));
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el texto en el botón
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // Altura máxima más pequeña

        button.addMouseListener(new java.awt.event.MouseAdapter() {
         
        });
        return button;
    }
   
    // Método para estilizar botones de acción (ej: Confirmar Ingreso)
    private void styleActionButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 12)); // Un poco más pequeña
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 128, 0));
        button.setBorder(new LineBorder(new Color(0, 0, 0), 0));
        button.setPreferredSize(new Dimension(40, 40)); // Más compactos
    }
}