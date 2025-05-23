package programa;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * La clase `PaginaPrincipal` representa la ventana principal de la aplicación
 * después de que el usuario ha iniciado sesión. Muestra opciones para navegar
 * a diferentes secciones como partidas cercanas, torneos y el cajero,
 * además de proporcionar acceso al perfil del usuario.
 */
public class PaginaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JLabel lblfondo = new JLabel(""); // Etiqueta para la imagen de fondo

	// Obtiene la instancia del usuario actual de la sesión.
	Usuario actual = SesionUsuario.getUsuario();

	/**
	 * Punto de entrada principal para la aplicación.
	 * Crea y muestra la ventana de la `PaginaPrincipal` en el hilo de despacho de eventos de Swing.
	 *
	 */
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

	/**
	 * Constructor de la clase `PaginaPrincipal`.
	 * Inicializa y configura todos los componentes de la interfaz gráfica.
	 */
	public PaginaPrincipal() {
		// Configuración básica de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 484, 743);
		setUndecorated(true); // Elimina la barra de título y los bordes de la ventana

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null); // Diseño nulo para posicionar componentes libremente
		setContentPane(contentPane);

		// --- Botón de Perfil de Usuario ---
		// Carga la imagen para el icono del botón de perfil.
		URL profileIconUrl = getClass().getResource("/imagenes/fotoperfil3.png");
		ImageIcon profileIcon = null;

		if (profileIconUrl != null) {
			profileIcon = new ImageIcon(profileIconUrl);
			// Escala la imagen para que se ajuste al tamaño del botón.
			Image image = profileIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			profileIcon = new ImageIcon(image);
		} else {
			System.err.println("Error: Imagen de perfil no encontrada en /imagenes/fotoperfil3.png");
			profileIcon = new ImageIcon(); // Icono vacío si no se encuentra la imagen
		}

		// Crea un botón redondo personalizado con la imagen de perfil.
		JButton btnPerfil = new JButton(profileIcon) {
			@Override
			protected void paintComponent(Graphics g) {
				// Rellena un óvalo para crear el fondo redondo del botón.
				g.fillOval(0, 0, getWidth(), getHeight());
				super.paintComponent(g); // Asegura que el icono y el texto se pinten.
			}

			@Override
			protected void paintBorder(Graphics g) {
				// Dibuja un óvalo para el borde redondo del botón.
				g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
			}

			@Override
			public boolean contains(int x, int y) {
				// Define la zona de clic del botón como un círculo.
				int radius = getWidth() / 2;
				return (Math.pow(x - radius, 2) + Math.pow(y - radius, 2)) <= Math.pow(radius, 2);
			}
		};

		btnPerfil.setBounds(213, 58, 60, 60); // Posiciona y dimensiona el botón.
		btnPerfil.setContentAreaFilled(false); // No pinta el área de contenido estándar.
		btnPerfil.setFocusPainted(false); // Elimina el borde de foco.
		btnPerfil.setBorderPainted(false); // Elimina el borde.
		btnPerfil.setForeground(new Color(235, 227, 194)); // Color del texto (aunque no hay texto).
		btnPerfil.setFont(new Font("Arial", Font.BOLD, 16));
		contentPane.add(btnPerfil);

		// Agrega un `ActionListener` para abrir la ventana de perfil de usuario.
		btnPerfil.addActionListener(e -> {
			PerfilUsuario p1 = new PerfilUsuario();
			dispose(); // Cierra la ventana actual (`PaginaPrincipal`).
			p1.setVisible(true); // Muestra la ventana `PerfilUsuario`.
		});

		// --- Título de la Aplicación ---
		JLabel lblTitulo = new JLabel("TightPoker");
		lblTitulo.setForeground(new Color(235, 227, 194));
		lblTitulo.setBounds(133, 109, 228, 95);
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 54));
		contentPane.add(lblTitulo);

		// --- Separadores ---
		JSeparator separator1 = new JSeparator();
		separator1.setForeground(new Color(235, 227, 194));
		separator1.setBackground(new Color(235, 227, 194));
		separator1.setBounds(108, 246, 282, 2);
		contentPane.add(separator1);

		JSeparator separator2 = new JSeparator();
		separator2.setForeground(new Color(235, 227, 194));
		separator2.setBackground(new Color(235, 227, 194));
		separator2.setBounds(108, 380, 282, 3);
		contentPane.add(separator2);

		JSeparator separator3 = new JSeparator();
		separator3.setForeground(new Color(235, 227, 194));
		separator3.setBackground(new Color(235, 227, 194));
		separator3.setBounds(108, 527, 282, 5);
		contentPane.add(separator3);

		// --- Botón "Torneos" ---
		// Nota: El botón `btn_login1` estaba duplicado y se ha eliminado.
		// Se ha dejado solo el botón `btn_Torneos` que ocupa su misma posición y función.
		JButton btnTorneos = new JButton("Torneos");
		btnTorneos.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnTorneos.setForeground(Color.WHITE);
		btnTorneos.setBackground(new Color(196, 49, 25));
		btnTorneos.setFocusPainted(false);
		btnTorneos.setBorderPainted(false);
		btnTorneos.setContentAreaFilled(false);
		btnTorneos.setOpaque(false);
		btnTorneos.setBounds(133, 275, 228, 78); // Misma posición que el antiguo btn_login1
		contentPane.add(btnTorneos);

		// Personaliza el Look and Feel del botón "Torneos" para que sea redondo.
		btnTorneos.setUI(new BasicButtonUI() {
			@Override
			public void paint(Graphics g, JComponent c) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(btnTorneos.getBackground());
				g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
				g2.dispose();
				super.paint(g, c);
			}
		});

		// Agrega un `ActionListener` para abrir la ventana de torneos.
		btnTorneos.addActionListener(e -> {
			Torneos T1 = new Torneos();
			dispose(); // Cierra la ventana actual.
			T1.setVisible(true); // Muestra la ventana `Torneos`.
		});

		// --- Botón "Partidas Cercanas" ---
		JButton btnPartidasCercanas = new JButton("Partidas Cercanas");
		btnPartidasCercanas.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnPartidasCercanas.setForeground(Color.WHITE);
		btnPartidasCercanas.setBackground(new Color(196, 49, 25));
		btnPartidasCercanas.setFocusPainted(false);
		btnPartidasCercanas.setBorderPainted(false);
		btnPartidasCercanas.setContentAreaFilled(false);
		btnPartidasCercanas.setOpaque(false);
		btnPartidasCercanas.setBounds(133, 415, 228, 78);
		contentPane.add(btnPartidasCercanas);

		// Personaliza el Look and Feel del botón "Partidas Cercanas" para que sea redondo.
		btnPartidasCercanas.setUI(new BasicButtonUI() {
			@Override
			public void paint(Graphics g, JComponent c) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(btnPartidasCercanas.getBackground());
				g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
				g2.dispose();
				super.paint(g, c);
			}
		});

		// Agrega un `ActionListener` para abrir la ventana de partidas cercanas.
		btnPartidasCercanas.addActionListener(e -> {
			PartidasCercanas P1 = new PartidasCercanas();
			dispose(); // Cierra la ventana actual.
			P1.setVisible(true); // Muestra la ventana `PartidasCercanas`.
		});

		// --- Botón "Cajero" ---
		JButton btnCajero = new JButton("Cajero");
		btnCajero.setOpaque(false);
		btnCajero.setForeground(Color.WHITE);
		btnCajero.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCajero.setFocusPainted(false);
		btnCajero.setContentAreaFilled(false);
		btnCajero.setBorderPainted(false);
		btnCajero.setBackground(new Color(196, 49, 25));
		btnCajero.setBounds(167, 559, 151, 43);
		contentPane.add(btnCajero);

		// Personaliza el Look and Feel del botón "Cajero" para que sea redondo.
		btnCajero.setUI(new BasicButtonUI() {
			@Override
			public void paint(Graphics g, JComponent c) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(btnCajero.getBackground());
				g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
				g2.dispose();
				super.paint(g, c);
			}
		});

		// Agrega un `ActionListener` para abrir la ventana del cajero.
		btnCajero.addActionListener(e -> {
			Cajero C1 = new Cajero();
			dispose(); // Cierra la ventana actual.
			C1.setVisible(true); // Muestra la ventana `Cajero`.
		});

		// --- Rombos Decorativos ---
		// Se ha refactorizado la creación de los rombos en un método auxiliar para evitar código repetido.
		contentPane.add(createRhombusPanel(78, 518));
		contentPane.add(createRhombusPanel(78, 371));
		contentPane.add(createRhombusPanel(78, 237));
		contentPane.add(createRhombusPanel(400, 371));
		contentPane.add(createRhombusPanel(400, 237));
		contentPane.add(createRhombusPanel(400, 518));

		// --- Imagen de Fondo Principal ---
		URL fondoImageUrl = getClass().getResource("/imagenes/fondoPoker.png");
		if (fondoImageUrl != null) {
			lblfondo.setIcon(new ImageIcon(fondoImageUrl));
		} else {
			System.err.println("Error: Imagen de fondo no encontrada en /imagenes/fondoPoker.png");
		}
		lblfondo.setBounds(-11, 0, 518, 757); // Ajuste de posición para cubrir la ventana
		contentPane.add(lblfondo);

		// Asegura que la ventana sea visible al iniciar.
		setVisible(true);

		// Muestra un mensaje de bienvenida al usuario.
		JOptionPane.showMessageDialog(this, "Bienvenido " + actual.getNombre(), "Mensaje de bienvenida", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Método auxiliar para crear un panel con forma de rombo.
	 *
	 * @return Un JPanel configurado para dibujar un rombo.
	 */
	private JPanel createRhombusPanel(int x, int y) {
		JPanel panelRombo = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				int width = getWidth();
				int height = getHeight();

				int[] xPoints = {width / 2, width, width / 2, 0};
				int[] yPoints = {0, height / 2, height, height / 2};

				g2.setColor(new Color(235, 227, 194)); // Color del rombo.
				g2.fillPolygon(xPoints, yPoints, 4); // Dibuja el rombo.
			}
		};
		panelRombo.setOpaque(false); // Hace el panel transparente excepto por la forma dibujada.
		panelRombo.setBounds(x, y, 20, 20); // Posición y tamaño fijo del rombo.
		return panelRombo;
	}
}