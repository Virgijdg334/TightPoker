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
import java.net.URL;
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

public class PerfilUsuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel mainContentPanel; // El recuadro blanco de contenido
    private JPanel menuPanel; // El panel del menú lateral
    private CardLayout cardLayout;
    private JLabel lbl_Cajero_Datos;
    private JTextField textField_contraseña;
    Usuario actual = SesionUsuario.getUsuario();


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.err.println("No se pudo cargar Nimbus LookAndFeel. Usando el por defecto.");
        }

        EventQueue.invokeLater(() -> {
            try {
            PerfilUsuario frame = new PerfilUsuario();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PerfilUsuario() {
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

                // Cargar imagen del classpath
                URL imageUrl = getClass().getResource("/imagenes/fondoPokerHorizontal.png");

                if (imageUrl != null) {
                    ImageIcon icon = new ImageIcon(imageUrl);
                    Image img = icon.getImage();

                    int imgWidth = icon.getIconWidth();
                    int imgHeight = icon.getIconHeight();
                    int panelWidth = getWidth();
                    int panelHeight = getHeight();

                    // Escalado proporcional para cubrir el panel
                    double scaleX = (double) panelWidth / imgWidth;
                    double scaleY = (double) panelHeight / imgHeight;
                    double scale = Math.max(scaleX, scaleY); // Cubre completamente

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

        backgroundPanel.setLayout(null); // backgroundPanel también con null layout
        backgroundPanel.setBounds(0, 6, getWidth(), getHeight());
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
       
URL imageUrl = getClass().getResource("/imagenes/volver1.png");
ImageIcon icon = null;

if (imageUrl != null) {
   icon = new ImageIcon(imageUrl);
   Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
   icon = new ImageIcon(image);
} else {
   System.out.println("Imagen no encontrada");
   // Puedes usar un icono por defecto si falla
   icon = new ImageIcon(); // o poner null si lo prefieres
}
       
        // Crear el botón redondo con el ícono cargado
        JButton botonRedondo = new JButton(icon) {
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
        botonRedondo.setBounds(518, 181, 60, 60);
        backgroundPanel.add(botonRedondo);
        botonRedondo.setContentAreaFilled(false);
        botonRedondo.setFocusPainted(false);
        botonRedondo.setBorderPainted(false);
        botonRedondo.setForeground(new Color(5, 66, 47));
        botonRedondo.setFont(new Font("Arial", Font.BOLD, 16));
       
        menuPanel.setBounds(58, 126, 100, 178);
        backgroundPanel.add(menuPanel);

        JButton btnDatos = createCompactMenuButton("Datos");
        JButton btnBorrar = createCompactMenuButton("Borrar");
        JButton btnModificar = createCompactMenuButton("Modificar");
        JButton btnSoporte = createCompactMenuButton("Soporte");
        JButton btnFAQ = createCompactMenuButton("FAQ");
       
       
       
        menuPanel.add(btnDatos);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio entre botones reducido
        menuPanel.add(btnModificar);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        menuPanel.add(btnBorrar);
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
        mainContentPanel.add(createModificar(), "Modificar");
        mainContentPanel.add(createBorrarCuentaPanel(), "BorrarCuenta");
        mainContentPanel.add(createSoportePanel(), "Soporte");
        mainContentPanel.add(createFAQPanel(), "FAQ");

        // --- Listeners para los botones del menú ---
        btnDatos.addActionListener(e -> cardLayout.show(mainContentPanel, "Datos"));
        btnModificar.addActionListener(e -> cardLayout.show(mainContentPanel, "Modificar"));
        btnSoporte.addActionListener(e -> cardLayout.show(mainContentPanel, "Soporte"));
        btnFAQ.addActionListener(e -> cardLayout.show(mainContentPanel, "FAQ"));
        btnBorrar.addActionListener(e -> cardLayout.show(mainContentPanel, "BorrarCuenta"));
        // Mostrar inicialmente el panel de "Datos"
        cardLayout.show(mainContentPanel, "Datos");
               
                        // Títulos y separador ahora dentro de este panel de datos
                        lbl_Cajero_Datos = new JLabel("Perfil");
                        lbl_Cajero_Datos.setBounds(262, 68, 100, 40);
                        backgroundPanel.add(lbl_Cajero_Datos);
                        lbl_Cajero_Datos.setForeground(new Color(235, 227, 194));
                        lbl_Cajero_Datos.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 40));
    }

    // --- Métodos para crear los paneles de contenido de cada sección ---

    private JPanel createDatosPanel() {
   
       JPanel panel = new JPanel();
       panel.setLayout(null);
       panel.setOpaque(false);
       
       // --- ¡Clave para hacer el separador más gordo! ---
       // Establece la altura preferida del separador.
       // Para un separador horizontal, solo necesitas modificar la altura.
       int desiredSeparatorHeight = 5;
       
        JLabel lblDatos = new JLabel("Datos");
        lblDatos.setForeground(new Color(235, 227, 194));
        lblDatos.setFont(new Font("Arial", Font.BOLD, 20));
        lblDatos.setAlignmentX(0.0f);
        lblDatos.setBounds(20, 3, 196, 24);
        panel.add(lblDatos);
       
        JLabel lbl_Salario_Texto_1 = new JLabel("Nombre");
        lbl_Salario_Texto_1.setForeground(new Color(235, 227, 194));
        lbl_Salario_Texto_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_Salario_Texto_1.setBounds(20, 33, 59, 25);
        panel.add(lbl_Salario_Texto_1);
       
        JLabel lbl_Salario_Texto_1_1 = new JLabel("Apellidos:");
        lbl_Salario_Texto_1_1.setForeground(new Color(235, 227, 194));
        lbl_Salario_Texto_1_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_Salario_Texto_1_1.setBounds(20, 67, 68, 25);
        panel.add(lbl_Salario_Texto_1_1);
       
        JLabel lbl_Salario_Texto_1_2 = new JLabel("Nombre de Usuario:");
        lbl_Salario_Texto_1_2.setForeground(new Color(235, 227, 194));
        lbl_Salario_Texto_1_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_Salario_Texto_1_2.setBounds(20, 104, 124, 25);
        panel.add(lbl_Salario_Texto_1_2);
       
        JLabel lbl_Salario_Texto_1_3 = new JLabel("Numero de Teléfono:");
        lbl_Salario_Texto_1_3.setForeground(new Color(235, 227, 194));
        lbl_Salario_Texto_1_3.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_Salario_Texto_1_3.setBounds(20, 141, 131, 25);
        panel.add(lbl_Salario_Texto_1_3);
       
        JLabel lbl_nombre = new JLabel("");
        lbl_nombre.setForeground(new Color(235, 227, 194));
        lbl_nombre.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_nombre.setBounds(101, 33, 59, 25);
        panel.add(lbl_nombre);
       
        JLabel lbl_apellidos = new JLabel("");
        lbl_apellidos.setForeground(new Color(235, 227, 194));
        lbl_apellidos.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_apellidos.setBounds(101, 67, 59, 25);
        panel.add(lbl_apellidos);
       
        JLabel lbl_nombreUsuario = new JLabel("");
        lbl_nombreUsuario.setForeground(new Color(235, 227, 194));
        lbl_nombreUsuario.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_nombreUsuario.setBounds(163, 104, 59, 25);
        panel.add(lbl_nombreUsuario);
       
        JLabel lbl_telefono = new JLabel("");
        lbl_telefono.setForeground(new Color(235, 227, 194));
        lbl_telefono.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_telefono.setBounds(163, 141, 59, 25);
        panel.add(lbl_telefono);

        return panel;
    }

    private JPanel createModificar() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Modificar Datos");
        title.setFont(new Font("Arial", Font.BOLD, 20)); // Título más pequeño
        title.setForeground(new Color(235, 227, 194));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio ajustado

        JLabel instruction = new JLabel("Introduce el nuevo usuario");
        instruction.setForeground(new Color(235, 227, 194));
        instruction.setFont(new Font("Arial", Font.PLAIN, 12)); // Fuente más pequeña
        instruction.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(instruction);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JTextField textField_nombreUsuario = new JTextField(15); // Tamaño de columna sugerido
        textField_nombreUsuario.setBackground(new Color(235, 227, 194));
        textField_nombreUsuario.setForeground(new Color(235, 227, 194));
        textField_nombreUsuario.setMaximumSize(new Dimension(150, 22)); // Tamaño fijo
        textField_nombreUsuario.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(textField_nombreUsuario);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
               
                JLabel lblIntroduceElNuevo = new JLabel("Introduce una nueva contraseña");
                lblIntroduceElNuevo.setForeground(new Color(235, 227, 194));
                lblIntroduceElNuevo.setFont(new Font("Arial", Font.PLAIN, 12));
                lblIntroduceElNuevo.setAlignmentX(0.0f);
                panel.add(lblIntroduceElNuevo);
               
                Component rigidArea = Box.createRigidArea(new Dimension(0, 5));
                panel.add(rigidArea);
               
                textField_contraseña = new JTextField(15);
                textField_contraseña.setMaximumSize(new Dimension(150, 22));
                textField_contraseña.setForeground(new Color(235, 227, 194));
                textField_contraseña.setBackground(new Color(235, 227, 194));
                textField_contraseña.setAlignmentX(0.0f);
                panel.add(textField_contraseña);
               
                Component rigidArea_1 = Box.createRigidArea(new Dimension(0, 5));
                panel.add(rigidArea_1);
               
                Component rigidArea_2 = Box.createRigidArea(new Dimension(0, 5));
                panel.add(rigidArea_2);
               
                Component rigidArea_5 = Box.createRigidArea(new Dimension(0, 5));
                panel.add(rigidArea_5);
       
                JButton confirmButton = new JButton("Confirmar Cambios");
                panel.add(confirmButton);
                styleActionButton(confirmButton);
               
                 confirmButton.addActionListener(e -> JOptionPane.showMessageDialog(panel, "Datos cambiados con exito: " + textField_nombreUsuario.getText()));

        return panel;
    }
   
   
    private JPanel createBorrarCuentaPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false); // Mantener la transparencia del fondo
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes internos

        // Título del panel
        JLabel title = new JLabel("Borrar Cuenta");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(235, 227, 194)); // Color de texto
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Espacio

        // Mensaje de advertencia
        JLabel warning1 = new JLabel("Estás a punto de borrar tu cuenta.");
        warning1.setFont(new Font("Arial", Font.PLAIN, 14));
        warning1.setForeground(new Color(235, 227, 194));
        warning1.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(warning1);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel warning2 = new JLabel("Esta acción es irreversible y perderás todos tus datos.");
        warning2.setFont(new Font("Arial", Font.BOLD, 12)); // Hazlo un poco más prominente
        warning2.setForeground(new Color(255, 100, 100)); // Un color más de advertencia (rojo claro)
        warning2.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(warning2);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio antes de los botones

        // Botones de acción
        JButton btnConfirmarBorrar = new JButton("Confirmar Borrado");
        styleActionButton(btnConfirmarBorrar); // Reutiliza tu método de estilo
        btnConfirmarBorrar.setBackground(new Color(178, 34, 34)); // Rojo oscuro para confirmar
        btnConfirmarBorrar.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(btnConfirmarBorrar);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Añadir ActionListeners a los botones del panel
        btnConfirmarBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(panel,
                    "¿REALMENTE estás seguro de que quieres borrar tu cuenta? No hay vuelta atrás.",
                    "Confirmar Borrado de Cuenta",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Aquí iría la lógica real para borrar la cuenta
                    // Por ejemplo, llamar a un método en tu lógica de negocio o base de datos.
                    JOptionPane.showMessageDialog(panel, "Tu cuenta ha sido borrada exitosamente.");
                    // Después de borrar, podrías redirigir al usuario, cerrar la aplicación, etc.
                    // Por ejemplo, cerrar la ventana:
                    SwingUtilities.getWindowAncestor(panel).dispose();
                    // O volver al panel de "Datos" si no cierras:
                    // cardLayout.show(mainContentPanel, "Datos");
                }
            }
        });

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

        JLabel q1 = new JLabel("Q: ¿Cómo puedo cambiar mi nombre de usuario?");
        q1.setForeground(new Color(235, 227, 194));
        q1.setFont(new Font("Arial", Font.PLAIN, 12));
        q1.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(q1);
        JLabel a1 = new JLabel("A: Ve a 'Perfil' y busca la opción 'Modificar Datos'.");
        a1.setForeground(new Color(235, 227, 194));
        a1.setFont(new Font("Arial", Font.PLAIN, 12));
        a1.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(a1);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel q2 = new JLabel("Q: ¿Qué hago si olvidé mi contraseña?");
        q2.setForeground(new Color(235, 227, 194));
        q2.setFont(new Font("Arial", Font.PLAIN, 12));
        q2.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(q2);
        JLabel a2 = new JLabel("A: Ve a 'Perfil' y busca la opción 'Modificar Datos'.");
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