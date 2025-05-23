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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * La clase PerfilUsuario representa la ventana principal del perfil de usuario,
 * permitiendo visualizar y modificar datos, así como acceder a opciones de soporte
 * y preguntas frecuentes.
 */
public class PerfilUsuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel mainContentPanel; // El recuadro blanco de contenido donde se muestran las "tarjetas"
    private JPanel menuPanel; // El panel del menú lateral
    private CardLayout cardLayout; // Para gestionar el cambio entre paneles de contenido

    // Componente para el campo de contraseña en el panel de Modificar
    private JTextField textField_contraseña;

    // Obtiene el usuario actual de la sesión. Asume que SesionUsuario está correctamente configurado.
    Usuario actual = SesionUsuario.getUsuario();

    // Variables para formatear la información del usuario
    public String saldoTexto = String.format("%.2f €", actual.getSaldo()); // Formato de saldo
    public String num_tlf = String.format("%s %s %s", // Formato de número de teléfono
            String.valueOf(actual.getTelefono()).substring(0, 3),
            String.valueOf(actual.getTelefono()).substring(3, 6),
            String.valueOf(actual.getTelefono()).substring(6, 9));
    public String tarjetaFormateada = String.format("%04d %04d %04d %04d", // Formato de número de tarjeta
            (actual.getTarjeta() / 1000000000000L) % 10000,
            (actual.getTarjeta()  / 100000000L) % 10000,
            (actual.getTarjeta()  / 10000L) % 10000,
            actual.getTarjeta()  % 10000);

    /**
     * Método principal que inicia la aplicación del perfil de usuario.
     * Carga el LookAndFeel "Nimbus" para una apariencia moderna.
     */
    public static void main(String[] args) {
        // Intenta establecer el LookAndFeel de Nimbus
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.err.println("No se pudo cargar Nimbus LookAndFeel. Usando el por defecto.");
        }

        // Ejecuta la interfaz de usuario en el Event Dispatch Thread (EDT)
        EventQueue.invokeLater(() -> {
            try {
                PerfilUsuario frame = new PerfilUsuario();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Constructor de la clase PerfilUsuario.
     * Configura la ventana principal y sus componentes.
     */
    public PerfilUsuario() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Ajusta el tamaño del JFrame y lo hace sin decoración para un diseño personalizado
        setBounds(100, 100, 621, 414);
        setUndecorated(true);

        // Configura el contentPane principal con un layout nulo para posicionamiento absoluto
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // --- Panel de Fondo ---
        // Panel que dibuja una imagen de fondo.
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Asegura que el fondo se pinte correctamente

                // Carga la imagen de fondo desde los recursos del classpath
                URL imageUrl = getClass().getResource("/imagenes/fondoPokerHorizontal.png");

                if (imageUrl != null) {
                    ImageIcon icon = new ImageIcon(imageUrl);
                    Image img = icon.getImage();

                    int imgWidth = icon.getIconWidth();
                    int imgHeight = icon.getIconHeight();
                    int panelWidth = getWidth();
                    int panelHeight = getHeight();

                    // Escala la imagen proporcionalmente para cubrir todo el panel
                    double scaleX = (double) panelWidth / imgWidth;
                    double scaleY = (double) panelHeight / imgHeight;
                    double scale = Math.max(scaleX, scaleY); // Cubre completamente la imagen

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
        backgroundPanel.setLayout(null); // Layout nulo para posicionamiento absoluto
        backgroundPanel.setBounds(0, 6, getWidth(), getHeight()); // Ocupa todo el JFrame
        contentPane.add(backgroundPanel);

        // --- Botón de Volver (Redondo) ---
        // Carga y escala la imagen para el botón de volver.
        URL volverImageUrl = getClass().getResource("/imagenes/volver1.png");
        ImageIcon volverIcon = null;

        if (volverImageUrl != null) {
            volverIcon = new ImageIcon(volverImageUrl);
            Image image = volverIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            volverIcon = new ImageIcon(image);
        } else {
            System.out.println("Imagen 'volver1.png' no encontrada.");
            volverIcon = new ImageIcon(); // Icono vacío si no se encuentra la imagen
        }

        // Crea un botón redondo personalizado
        JButton botonRedondo = new JButton(volverIcon) {
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
        botonRedondo.setBounds(518, 181, 60, 60); // Posición y tamaño del botón
        backgroundPanel.add(botonRedondo);
        botonRedondo.setContentAreaFilled(false); // No rellena el área rectangular del botón
        botonRedondo.setFocusPainted(false); // No pinta el recuadro de foco
        botonRedondo.setBorderPainted(false); // No pinta el borde por defecto
        botonRedondo.setForeground(new Color(5, 66, 47)); // Color para el borde del botón
        botonRedondo.setFont(new Font("Arial", Font.BOLD, 16));
        // Acción del botón de volver: abre PaginaPrincipal y cierra la ventana actual
        botonRedondo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                PaginaPrincipal T1 = new PaginaPrincipal();
                dispose(); // Cierra la ventana actual (PerfilUsuario)
                T1.setVisible(true); // Hace visible la ventana PaginaPrincipal
            }
        });

        // --- menuPanel: El menú lateral ---
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS)); // Organiza los componentes verticalmente
        menuPanel.setBackground(new Color(5, 66, 47)); // Fondo oscuro del menú
        menuPanel.setOpaque(true); // Asegura que el fondo sea visible
        menuPanel.setBounds(58, 126, 100, 178); // Posición y tamaño del menú
        backgroundPanel.add(menuPanel);

        // Creación de botones para el menú lateral
        JButton btnDatos = createCompactMenuButton("Datos");
        JButton btnBorrar = createCompactMenuButton("Borrar");
        JButton btnModificar = createCompactMenuButton("Modificar");
        JButton btnSoporte = createCompactMenuButton("Soporte");
        JButton btnFAQ = createCompactMenuButton("FAQ");

        // Añade los botones al menuPanel con espacio entre ellos
        menuPanel.add(btnDatos);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        menuPanel.add(btnModificar);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        menuPanel.add(btnBorrar);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        menuPanel.add(btnSoporte);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        menuPanel.add(btnFAQ);

        // --- mainContentPanel: El recuadro de contenido principal ---
        cardLayout = new CardLayout(); // Usa CardLayout para cambiar entre paneles
        mainContentPanel = new JPanel(cardLayout);
        mainContentPanel.setBorder(new LineBorder(new Color(0, 0, 0), 0)); // Borde nulo
        mainContentPanel.setOpaque(true); // Asegura que el fondo sea visible
        mainContentPanel.setBackground(new Color(5, 66, 47)); // Fondo oscuro del panel de contenido

        // Posiciona el mainContentPanel a la derecha del menú
        mainContentPanel.setBounds(170, 129, 351, 192); // Posición y tamaño
        backgroundPanel.add(mainContentPanel);

        // Añade los diferentes paneles de contenido al CardLayout con un nombre identificador
        mainContentPanel.add(createDatosPanel(), "Datos");
        mainContentPanel.add(createModificar(), "Modificar");
        mainContentPanel.add(createBorrarCuentaPanel(), "BorrarCuenta");
        mainContentPanel.add(createSoportePanel(), "Soporte");
        mainContentPanel.add(createFAQPanel(), "FAQ");

        // --- Listeners para los botones del menú ---
        // Al hacer clic en un botón, se muestra el panel correspondiente en el CardLayout
        btnDatos.addActionListener(e -> cardLayout.show(mainContentPanel, "Datos"));
        btnModificar.addActionListener(e -> cardLayout.show(mainContentPanel, "Modificar"));
        btnSoporte.addActionListener(e -> cardLayout.show(mainContentPanel, "Soporte"));
        btnFAQ.addActionListener(e -> cardLayout.show(mainContentPanel, "FAQ"));
        btnBorrar.addActionListener(e -> cardLayout.show(mainContentPanel, "BorrarCuenta"));

        // Muestra inicialmente el panel de "Datos" al iniciar la aplicación
        cardLayout.show(mainContentPanel, "Datos");

        // --- Título principal de la ventana ---
        JLabel lbl_Cajero_Datos = new JLabel("Perfil");
        lbl_Cajero_Datos.setBounds(262, 68, 100, 40); // Posición y tamaño
        backgroundPanel.add(lbl_Cajero_Datos);
        lbl_Cajero_Datos.setForeground(new Color(235, 227, 194)); // Color de texto
        lbl_Cajero_Datos.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 40));
    }

    /**
     * Crea y devuelve el panel que muestra los datos del usuario.
     * @return JPanel con la información del usuario.
     */
    private JPanel createDatosPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null); // Layout nulo para posicionamiento absoluto de los componentes
        panel.setOpaque(false); // Hace el panel transparente para ver el fondo del mainContentPanel

        // Etiqueta del título de la sección
        JLabel lblDatos = new JLabel("Datos");
        lblDatos.setForeground(new Color(235, 227, 194));
        lblDatos.setFont(new Font("Arial", Font.BOLD, 20));
        lblDatos.setBounds(20, 3, 196, 24);
        panel.add(lblDatos);

        // Etiquetas para mostrar la información del usuario
        JLabel lbl_Salario_Texto_1 = new JLabel("Nombre:");
        lbl_Salario_Texto_1.setForeground(new Color(235, 227, 194));
        lbl_Salario_Texto_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_Salario_Texto_1.setBounds(20, 33, 68, 25);
        panel.add(lbl_Salario_Texto_1);

        JLabel lbl_Salario_Texto_1_1 = new JLabel("Apellidos:");
        lbl_Salario_Texto_1_1.setForeground(new Color(235, 227, 194));
        lbl_Salario_Texto_1_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_Salario_Texto_1_1.setBounds(20, 67, 79, 25);
        panel.add(lbl_Salario_Texto_1_1);

        JLabel lbl_Salario_Texto_1_2 = new JLabel("Nombre de Usuario:");
        lbl_Salario_Texto_1_2.setForeground(new Color(235, 227, 194));
        lbl_Salario_Texto_1_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_Salario_Texto_1_2.setBounds(20, 104, 156, 25);
        panel.add(lbl_Salario_Texto_1_2);

        JLabel lbl_Salario_Texto_1_3 = new JLabel("Número de Teléfono:");
        lbl_Salario_Texto_1_3.setForeground(new Color(235, 227, 194));
        lbl_Salario_Texto_1_3.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_Salario_Texto_1_3.setBounds(20, 141, 156, 25);
        panel.add(lbl_Salario_Texto_1_3);

        // Etiquetas que muestran los valores reales de los datos del usuario
        JLabel lbl_nombre = new JLabel(actual.getNombre());
        lbl_nombre.setForeground(new Color(235, 227, 194));
        lbl_nombre.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_nombre.setBounds(117, 33, 117, 25);
        panel.add(lbl_nombre);

        JLabel lbl_apellidos = new JLabel(actual.getApellidos());
        lbl_apellidos.setForeground(new Color(235, 227, 194));
        lbl_apellidos.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_apellidos.setBounds(101, 67, 191, 25);
        panel.add(lbl_apellidos);

        JLabel lbl_nombreUsuario = new JLabel(actual.getUser());
        lbl_nombreUsuario.setForeground(new Color(235, 227, 194));
        lbl_nombreUsuario.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_nombreUsuario.setBounds(175, 104, 117, 25);
        panel.add(lbl_nombreUsuario);

        JLabel lbl_telefono = new JLabel(num_tlf);
        lbl_telefono.setForeground(new Color(235, 227, 194));
        lbl_telefono.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 16));
        lbl_telefono.setBounds(175, 141, 144, 25);
        panel.add(lbl_telefono);

        return panel;
    }

    /**
     * Crea y devuelve el panel para modificar el nombre de usuario.
     * Permite al usuario introducir un nuevo nombre de usuario y confirmar con su contraseña.
     * @return JPanel para modificar datos.
     */
    private JPanel createModificar() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Organiza componentes verticalmente
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Márgenes internos

        // Título de la sección
        JLabel title = new JLabel("Modificar Datos");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(235, 227, 194));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio vertical

        // Instrucción para el nuevo nombre de usuario
        JLabel instruction = new JLabel("Introduce el nuevo nombre de usuario:");
        instruction.setForeground(new Color(235, 227, 194));
        instruction.setFont(new Font("Arial", Font.PLAIN, 12));
        instruction.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(instruction);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Campo de texto para el nuevo nombre de usuario
        JTextField textField_nombreUsuario = new JTextField(15);
        // Se elimina la configuración de foreground y background para evitar problemas de visibilidad
        // con el LookAndFeel Nimbus por defecto, que ya proporciona un buen contraste.
        textField_nombreUsuario.setMaximumSize(new Dimension(150, 22)); // Tamaño fijo
        textField_nombreUsuario.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(textField_nombreUsuario);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Instrucción para la contraseña
        JLabel lblIntroduceLaContraseña = new JLabel("Introduce tu contraseña para confirmar:");
        lblIntroduceLaContraseña.setForeground(new Color(235, 227, 194));
        lblIntroduceLaContraseña.setFont(new Font("Arial", Font.PLAIN, 12));
        lblIntroduceLaContraseña.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblIntroduceLaContraseña);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Campo de texto para la contraseña (se usa el mismo nombre de variable global)
        textField_contraseña = new JTextField(15);
        // Se elimina la configuración de foreground y background por las mismas razones que el anterior.
        textField_contraseña.setMaximumSize(new Dimension(150, 22));
        textField_contraseña.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(textField_contraseña);
        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Espacio antes del botón

        // Botón para confirmar los cambios
        JButton confirmButton = new JButton("Confirmar Cambios");
        styleActionButton(confirmButton); // Aplica el estilo definido para botones de acción
        confirmButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(confirmButton);

        // Listener para el botón de confirmar cambios
        confirmButton.addActionListener(e -> {
            String nuevoUsuario = textField_nombreUsuario.getText().trim();
            String contraseñaIngresada = textField_contraseña.getText().trim();

            if (nuevoUsuario.isEmpty() || contraseñaIngresada.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Por favor, completa todos los campos.");
                return;
            }

            try {
                // Establece conexión con la base de datos
                ConexionMySQL conn = new ConexionMySQL("root", "password", "sql7780337");
                conn.conectar();

                // Verifica la contraseña actual del usuario antes de permitir el cambio
                String sqlCheck = "SELECT * FROM usuario WHERE nombreUsuario = ? AND contraseña = ?";
                java.sql.PreparedStatement stmtCheck = conn.getConnection().prepareStatement(sqlCheck);
                stmtCheck.setString(1, actual.getUser()); // Usuario actual logueado
                stmtCheck.setString(2, contraseñaIngresada);
                java.sql.ResultSet rs = stmtCheck.executeQuery();

                if (!rs.next()) {
                    // Contraseña incorrecta
                    JOptionPane.showMessageDialog(panel, "❌ Contraseña incorrecta. No se puede modificar el nombre de usuario.");
                } else {
                    // La contraseña es correcta, procede a modificar el nombre de usuario
                    String sqlUpdate = "UPDATE usuario SET nombreUsuario = ? WHERE nombreUsuario = ?";
                    java.sql.PreparedStatement stmtUpdate = conn.getConnection().prepareStatement(sqlUpdate);
                    stmtUpdate.setString(1, nuevoUsuario);
                    stmtUpdate.setString(2, actual.getUser()); // Usuario actual a modificar
                    int filas = stmtUpdate.executeUpdate(); // Ejecuta la actualización

                    if (filas > 0) {
                        JOptionPane.showMessageDialog(panel, "✅ Nombre de usuario modificado con éxito.");
                        actual.setNombre_Usuario(nuevoUsuario);  // Actualiza el objeto Usuario en memoria
                        // Opcional: limpiar los campos después de una operación exitosa
                        textField_nombreUsuario.setText("");
                        textField_contraseña.setText("");
                    } else {
                        JOptionPane.showMessageDialog(panel, "⚠️ No se pudo actualizar el nombre de usuario.");
                    }
                    stmtUpdate.close(); // Cierra el PreparedStatement de actualización
                }
                rs.close(); // Cierra el ResultSet
                stmtCheck.close(); // Cierra el PreparedStatement de verificación
                conn.desconectar(); // Desconecta de la base de datos

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel, "❌ Error al modificar nombre de usuario: " + ex.getMessage());
            }
        });
        return panel;
    }

    /**
     * Crea y devuelve el panel para borrar la cuenta de usuario.
     * Muestra advertencias y solicita confirmación antes de la eliminación.
     * @return JPanel para borrar la cuenta.
     */
    private JPanel createBorrarCuentaPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título del panel
        JLabel title = new JLabel("Borrar Cuenta");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(235, 227, 194));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Mensajes de advertencia
        JLabel warning1 = new JLabel("Estás a punto de borrar tu cuenta.");
        warning1.setFont(new Font("Arial", Font.PLAIN, 14));
        warning1.setForeground(new Color(235, 227, 194));
        warning1.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(warning1);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel warning2 = new JLabel("Esta acción es irreversible y perderás todos tus datos.");
        warning2.setFont(new Font("Arial", Font.BOLD, 12));
        warning2.setForeground(new Color(255, 100, 100)); // Rojo claro para advertencia
        warning2.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(warning2);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Botón de confirmación para borrar
        JButton btnConfirmarBorrar = new JButton("Confirmar Borrado");
        styleActionButton(btnConfirmarBorrar);
        btnConfirmarBorrar.setBackground(new Color(178, 34, 34)); // Rojo oscuro para confirmar
        btnConfirmarBorrar.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(btnConfirmarBorrar);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Listener para el botón de confirmar borrado
        btnConfirmarBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Muestra un cuadro de diálogo de confirmación adicional
                int confirm = JOptionPane.showConfirmDialog(panel,
                        "¿REALMENTE estás seguro de que quieres borrar tu cuenta? No hay vuelta atrás.",
                        "Confirmar Borrado de Cuenta",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        // Conecta a la base de datos
                        ConexionMySQL conn = new ConexionMySQL("root", "password", "sql7780337");
                        conn.conectar();

                        // Prepara y ejecuta la sentencia SQL para eliminar el usuario
                        String sqlDelete = "DELETE FROM usuario WHERE nombreUsuario = ?";
                        java.sql.PreparedStatement stmtDelete = conn.getConnection().prepareStatement(sqlDelete);
                        stmtDelete.setString(1, actual.getUser()); // Elimina el usuario actualmente logueado

                        int filas = stmtDelete.executeUpdate(); // Ejecuta la eliminación
                        stmtDelete.close(); // Cierra el PreparedStatement
                        conn.desconectar(); // Desconecta de la base de datos

                        if (filas > 0) {
                            JOptionPane.showMessageDialog(panel, "✅ Tu cuenta ha sido borrada exitosamente.");
                            // Cierra la ventana actual después de borrar la cuenta
                            SwingUtilities.getWindowAncestor(panel).dispose();
                            // Aquí podrías redirigir a la pantalla de inicio de sesión o principal
                            // new Login().setVisible(true); // Ejemplo de redirección
                        } else {
                            JOptionPane.showMessageDialog(panel, "❌ No se encontró la cuenta en la base de datos.");
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(panel, "❌ Error al borrar la cuenta: " + ex.getMessage());
                    }
                }
            }
        });
        return panel;
    }

    /**
     * Crea y devuelve el panel con la información de contacto de soporte.
     * @return JPanel con datos de soporte.
     */
    private JPanel createSoportePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Título de la sección
        JLabel title = new JLabel("Contacto de Soporte");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(235, 227, 194));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Información de contacto
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
     * Crea y devuelve el panel con Preguntas Frecuentes (FAQ).
     * @return JPanel con preguntas y respuestas.
     */
    private JPanel createFAQPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Título de la sección
        JLabel title = new JLabel("Preguntas Frecuentes (FAQ)");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(235, 227, 194));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Pregunta 1 y su respuesta
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

        // Pregunta 2 y su respuesta
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

    /**
     * Método de ayuda para crear y estilizar botones del menú lateral.
     * @return JButton con el estilo predefinido.
     */
    private JButton createCompactMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setForeground(new Color(235, 227, 194)); // Color de texto
        button.setBackground(new Color(138, 35, 17)); // Color de fondo del botón
        // Borde compuesto: uno vacío exterior y uno de línea interior (linea nula).
        button.setBorder(new CompoundBorder(new LineBorder(new Color(100, 100, 100), 0), new EmptyBorder(6, 8, 6, 8)));
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Centra el botón en el eje X dentro de su contenedor
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // Altura máxima más pequeña

        // Se elimina el MouseListener vacío que no hacía nada
        return button;
    }

    /**
     * Método de ayuda para estilizar botones de acción (ej: Confirmar Ingreso, Confirmar Borrado).
     */
    private void styleActionButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 128, 0)); // Verde oscuro por defecto
        button.setBorder(new LineBorder(new Color(0, 0, 0), 0)); // Borde nulo
        button.setPreferredSize(new Dimension(150, 30)); // Dimensiones preferidas para uniformidad
    }
}