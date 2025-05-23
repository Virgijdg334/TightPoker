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
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI; // Para personalizar la apariencia de los botones

/**
 * La clase Torneos representa la ventana principal donde el usuario puede
 * seleccionar diferentes tipos de torneos (Mystery Bountys, KO Progresivo, Monster Stack).
 * También incluye botones para navegar al perfil de usuario y a la página principal.
 */
public class Torneos extends JFrame {

	// Instancia de la clase de conexión a la base de datos.
	// Declarada aquí, pero no utilizada directamente en esta clase para operaciones.
	ConexionMySQL bd = new ConexionMySQL("root", "password" ,"sql7780337");

	private static final long serialVersionUID = 1L; // Identificador para la serialización
	private JPanel contentPane; // Panel principal de la ventana
	private final JLabel lblfondo = new JLabel(""); // Etiqueta para la imagen de fondo

	/**
	 * Método principal que lanza la aplicación.
	 * Asegura que la interfaz de usuario se ejecute en el Event Dispatch Thread (EDT).
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Torneos frame = new Torneos();
					frame.setVisible(true); // Hace visible la ventana de torneos
				} catch (Exception e) {
					e.printStackTrace(); // Imprime la traza de la excepción si ocurre un error
				}
			}
		});
	}

	/**
	 * Constructor de la clase Torneos.
	 * Inicializa y configura todos los componentes de la interfaz de usuario.
	 */
	public Torneos() {
		// Configuración básica de la ventana JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana
		setBounds(100, 100, 484, 743); // Establece la posición y el tamaño de la ventana
		setUndecorated(true); // Elimina la barra de título y los bordes de la ventana para un diseño personalizado
		setVisible(true); // Hace visible la ventana al inicializarse

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Borde vacío para espacio interno
		contentPane.setLayout(null); // Usa un layout nulo para posicionamiento absoluto de los componentes
		setContentPane(contentPane); // Establece este panel como el contenido de la ventana

		// --- Título principal de la ventana "Torneos" ---
		JLabel lblNewLabel = new JLabel("Torneos");
		lblNewLabel.setForeground(new Color(235, 227, 194)); // Color del texto
		lblNewLabel.setBounds(155, 103, 171, 95); // Posición y tamaño
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 54)); // Fuente y tamaño
		contentPane.add(lblNewLabel); // Añade el título al panel de contenido

		// --- Botón de acceso al Perfil de Usuario ---
		URL imageUrl1 = getClass().getResource("/imagenes/fotoperfil3.png"); // Ruta de la imagen del icono de perfil
		ImageIcon icon1 = null;
		if (imageUrl1 != null) {
			icon1 = new ImageIcon(imageUrl1);
			// Escala la imagen para que encaje en el botón
			Image image = icon1.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			icon1 = new ImageIcon(image);
		} else {
			System.err.println("Error: Imagen 'fotoperfil3.png' no encontrada para el botón de perfil.");
			icon1 = new ImageIcon(); // Icono vacío si no se encuentra la imagen
		}

		JButton botonRedondo = new JButton(icon1) {
			@Override
			protected void paintComponent(Graphics g) {
				// Pinta el fondo del botón como un círculo. El color de fondo se establecerá antes.
				g.fillOval(0, 0, getWidth(), getHeight());
				super.paintComponent(g); // Pinta el icono y el texto (si los hubiera)
			}

			@Override
			protected void paintBorder(Graphics g) {
				// Dibuja el borde del botón como un círculo
				g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
			}

			@Override
			public boolean contains(int x, int y) {
				// Define el área de clic del botón como un círculo para clicks precisos
				int radius = getWidth() / 2;
				return (Math.pow(x - radius, 2) + Math.pow(y - radius, 2)) <= Math.pow(radius, 2);
			}
		};
		botonRedondo.setBounds(213, 54, 60, 60); // Posición y tamaño del botón
		botonRedondo.setContentAreaFilled(false); // No rellena el área rectangular del botón
		botonRedondo.setFocusPainted(false); // No pinta el recuadro de foco
		botonRedondo.setBorderPainted(false); // No pinta el borde por defecto
		botonRedondo.setForeground(new Color(235, 227, 194)); // Color del borde del círculo
		botonRedondo.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente (aunque no haya texto)

		botonRedondo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PerfilUsuario p1 = new PerfilUsuario(); // Crea una nueva instancia de PerfilUsuario
				dispose(); // Cierra la ventana actual (Torneos)
				p1.setVisible(true); // Hace visible la ventana del perfil
			}
		});
		contentPane.add(botonRedondo); // Añade el botón al panel de contenido

		// --- Botón para volver a la Página Principal (Icono de casa) ---
		URL imageUrl = getClass().getResource("/imagenes/casa.png"); // Ruta de la imagen del icono de casa
		ImageIcon icon = null;
		if (imageUrl != null) {
			icon = new ImageIcon(imageUrl);
			Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
		} else {
			System.err.println("Error: Imagen 'casa.png' no encontrada para el botón de inicio.");
			icon = new ImageIcon(); // Icono vacío si no se encuentra la imagen
		}

		JButton botonRedondo3 = new JButton(icon) {
			@Override
			protected void paintComponent(Graphics g) {
				g.fillOval(0, 0, getWidth(), getHeight()); // Rellena el óvalo como fondo del botón
				super.paintComponent(g); // Pinta el icono
			}

			@Override
			protected void paintBorder(Graphics g) {
				g.drawOval(0, 0, getWidth() - 1, getHeight() - 1); // Dibuja el borde del óvalo
			}

			@Override
			public boolean contains(int x, int y) {
				// Define el área de clic del botón como un círculo
				int radius = getWidth() / 2;
				return (Math.pow(x - radius, 2) + Math.pow(y - radius, 2)) <= Math.pow(radius, 2);
			}
		};
		botonRedondo3.setBounds(213, 620, 60, 60); // Posición y tamaño del botón
		botonRedondo3.setContentAreaFilled(false);
		botonRedondo3.setFocusPainted(false);
		botonRedondo3.setBorderPainted(false);
		botonRedondo3.setForeground(new Color(6, 66, 47)); // Color para el borde del botón
		botonRedondo3.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente (aunque no haya texto)

		botonRedondo3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaginaPrincipal p1 = new PaginaPrincipal(); // Crea una nueva instancia de PaginaPrincipal
				dispose(); // Cierra la ventana actual (Torneos)
				p1.setVisible(true); // Hace visible la ventana principal
			}
		});
		contentPane.add(botonRedondo3); // Añade el botón al panel de contenido

		// --- Botón "Mystery Bountys" ---
		JButton btn_Mystery_Bountys = new JButton("Mystery Bountys");
		btn_Mystery_Bountys.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_Mystery_Bountys.setForeground(Color.WHITE);
		btn_Mystery_Bountys.setBackground(new Color(196, 49, 25));
		btn_Mystery_Bountys.setBounds(132, 225, 228, 78);

		// Aplica estilo redondeado al botón "Mystery Bountys"
		estiloBotonRedondeado(btn_Mystery_Bountys);

		btn_Mystery_Bountys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MysteryBountys MB1 = new MysteryBountys(); // Crea una nueva instancia de MysteryBountys
				dispose(); // Cierra la ventana actual
				MB1.setVisible(true); // Hace visible la ventana de Mystery Bountys
			}
		});
		contentPane.add(btn_Mystery_Bountys); // Añade el botón al panel de contenido

		// --- Botón "KO Progresivo" ---
		JButton btn_KO_Progresivo = new JButton("KO Progresivo");
		btn_KO_Progresivo.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_KO_Progresivo.setForeground(Color.WHITE);
		btn_KO_Progresivo.setBackground(new Color(196, 49, 25));
		btn_KO_Progresivo.setBounds(132, 365, 228, 78);

		// Aplica estilo redondeado al botón "KO Progresivo"
		estiloBotonRedondeado(btn_KO_Progresivo);

		btn_KO_Progresivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KO_Progresivo K1 = new KO_Progresivo(); // Crea una nueva instancia de KO_Progresivo
				dispose(); // Cierra la ventana actual
				K1.setVisible(true); // Hace visible la ventana de KO Progresivo
			}
		});
		contentPane.add(btn_KO_Progresivo); // Añade el botón al panel de contenido

		// --- Botón "Monster Stack" ---
		JButton btn_Monster_Stack = new JButton("Monster Stack");
		btn_Monster_Stack.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_Monster_Stack.setForeground(Color.WHITE);
		btn_Monster_Stack.setBackground(new Color(196, 49, 25));
		btn_Monster_Stack.setBounds(132, 498, 228, 78);

		// Aplica estilo redondeado al botón "Monster Stack"
		estiloBotonRedondeado(btn_Monster_Stack);

		btn_Monster_Stack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonsterStacks MS1 = new MonsterStacks(); // Crea una nueva instancia de MonsterStacks
				dispose(); // Cierra la ventana actual
				MS1.setVisible(true); // Hace visible la ventana de Monster Stacks
			}
		});
		contentPane.add(btn_Monster_Stack); // Añade el botón al panel de contenido

		// --- Separadores decorativos ---
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(235, 227, 194));
		separator_1.setBackground(new Color(235, 227, 194));
		separator_1.setBounds(107, 196, 282, 2); // Posición y tamaño del separador
		contentPane.add(separator_1);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(235, 227, 194));
		separator.setBackground(new Color(235, 227, 194));
		separator.setBounds(107, 330, 282, 3);
		contentPane.add(separator);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(235, 227, 194));
		separator_2.setBackground(new Color(235, 227, 194));
		separator_2.setBounds(107, 468, 282, 5);
		contentPane.add(separator_2);

		// --- Paneles con forma de rombo (decorativos) ---
		// Creación de múltiples rombos utilizando el método auxiliar
		contentPane.add(crearPanelRombo(77, 186));   // Rombo superior izquierdo
		contentPane.add(crearPanelRombo(399, 186));  // Rombo superior derecho
		contentPane.add(crearPanelRombo(77, 320));   // Rombo medio izquierdo
		contentPane.add(crearPanelRombo(399, 320));  // Rombo medio derecho
		contentPane.add(crearPanelRombo(77, 458));   // Rombo inferior izquierdo
		contentPane.add(crearPanelRombo(399, 458));  // Rombo inferior derecho

		// --- Imagen de fondo ---
		lblfondo.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoPoker2.png"))); // Carga la imagen de fondo
		lblfondo.setBounds(-11, 0, 518, 757); // Posición y tamaño de la imagen de fondo
		contentPane.add(lblfondo); // Añade la imagen de fondo al panel de contenido
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
	private JPanel crearPanelRombo(int x, int y) {
		JPanel panelRombo = new JPanel() {
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
		panelRombo.setOpaque(false); // Hace el panel transparente para que solo se vea el rombo
		panelRombo.setBounds(x, y, 20, 20); // Establece la posición y el tamaño del rombo
		return panelRombo;
	}
}