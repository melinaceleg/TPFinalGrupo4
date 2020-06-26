package vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.WindowConstants;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class VentanaAFIP extends JFrame implements IVentanaAFIP {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JPanel panel_2;
	private JButton btnSalir;

	private ActionListener actionListener;
	private JTextPane txtReporte;
	private JPanel panel_3;

	/**
	 * Realiza la comunicacion entre vista y controlador a través de los actionListener de los botones
	 * PRE: actionListener distinto de null POS: No hay poscondiciones
	 * 
	 * @param actionListener     de tipo ActionListener
	 */
	public void setActionListener(ActionListener actionListener) {
		this.btnSalir.addActionListener(actionListener);
		this.actionListener = actionListener;
	}

	/**
	 * Crea e inicializa todos los elementos de la pantalla
	 */
	public VentanaAFIP() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BoxLayout(this.contentPane, BoxLayout.X_AXIS));

		this.panel = new JPanel();
		this.contentPane.add(this.panel);
		this.panel.setLayout(new BorderLayout(0, 0));

		this.panel_1 = new JPanel();
		this.panel.add(this.panel_1, BorderLayout.NORTH);

		this.lblNewLabel = new JLabel("Reporte de AFIP");
		this.panel_1.add(this.lblNewLabel);

		this.panel_2 = new JPanel();
		this.panel.add(this.panel_2, BorderLayout.SOUTH);

		this.btnSalir = new JButton("Salir");
		this.btnSalir.setActionCommand("CERRAR_REPORTE_AFIP");
		this.panel_2.add(this.btnSalir);

		this.panel_3 = new JPanel();
		this.panel.add(this.panel_3, BorderLayout.CENTER);
		this.panel_3.setLayout(new BorderLayout(0, 0));

		this.txtReporte = new JTextPane();
		this.panel_3.add(this.txtReporte);
		this.txtReporte.setEditable(false);
		this.txtReporte.setToolTipText("");

		this.btnSalir.setEnabled(false);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		JScrollPane sp = new JScrollPane(this.txtReporte, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.panel_3.add(sp);

		this.setVisible(true);
	}

	/**
	 * Muestra a través de un JTextpane el contenido recibido por parametro
	 * PRE: arg0 distinto de null POS: No hay poscondiciones
	 * 
	 * @param arg0     de tipo String
	 */
	@Override
	public void agregarReporteAFIP(String mensaje) {
		txtReporte.setText(txtReporte.getText() + mensaje);
	}

	/**
	 * Muestra a través de un JTextpane el contenido recibido por parametro y habilita el boton de Salida
	 * PRE: arg0 distinto de null POS: No hay poscondiciones
	 * 
	 * @param arg0     de tipo String
	 */
	@Override
	public void terminarReporteAFIP(String mensaje) {
		this.agregarReporteAFIP(mensaje);
		this.btnSalir.setEnabled(true);
	}

	@Override
	public void cerrarReporteAFIP() {
		dispose();
	}
}