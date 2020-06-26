package vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;

//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;

import modelo.Abonado;
import modelo.Contratacion;
import modelo.Factura;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;

import java.awt.Color;

import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.FlowLayout;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import javax.swing.UIManager;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;

/**
 * Clase que genera la ventana
 */
public class Ventana extends JFrame implements IVista, ActionListener, ListSelectionListener, WindowListener {

	private JPanel contentPane;
	private JPanel panelAbonado;
	private JTextField TextNombreAbonado;
	private JLabel LabelNombreAbonado;
	private JLabel LabelTituloNuevoAbonado;
	private JLabel LabelDNIAbonado;
	private JTextField textDNIAbonado;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton ButtonAgregarAbonado;
	private JPanel panel_4;

	private JRadioButton rdbtnPersonaFisica;
	private JRadioButton rdbtnPersonaJuridica;
	private JPanel panel_3;
	private JPanel panelABONADOS;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panelContratacion;
	private JLabel LabelListaAbonados;

	private JList<Abonado> list_Abonados;
	private JList<Contratacion> list_Contratacion;

	private DefaultListModel<Abonado> listModelAbonados = new DefaultListModel<Abonado>();
	private DefaultListModel<Contratacion> listModelContrataciones = new DefaultListModel<Contratacion>();
	private DefaultListModel<Factura> listModelFacturasPendientes = new DefaultListModel<Factura>();
	private DefaultListModel<Factura> listModelFacturasPagadas = new DefaultListModel<Factura>();
	private JPanel panelServicios;
	private JPanel panel_11;
	private JPanel panel_12;
	private JLabel LabelListaDomicilios;
	private JPanel panel;
	private JPanel panel_6;
	private JLabel lblDomicilios;
	private JPanel panel_7;
	private JLabel LabelDireccionDomicilio;
	private JTextField textDireccionDomicilio;
	private JRadioButton rdbtnInternet100;
	private JRadioButton rdbtnInternet500;
	private JPanel panel_8;
	private JButton ButtonAgregarContratacion;
	private JLabel lblNewLabel;
	private JPanel panel_9;
	private JPanel panel_5;
	private JRadioButton rdbtnServicioCelular;
	private JRadioButton rdbtnServicioTelefono;
	private JRadioButton rdbtnServicioTV_Cable;
	private JPanel panel_10;
	private JButton ButtonAgregarServicio;
	private JLabel LabelListaServicios;
	private JTextPane textPaneServicios;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private JPanel panel_14;
	private JRadioButton rdbtnEfectivo;
	private JRadioButton rdbtnCeque;
	private JRadioButton rdbtnTarjeta;
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();
	private JButton ButtonImprimirFacturas;
	private JPanel General;
	private JPanel panelFACTURACION;
	private JPanel panel_22;
	private JPanel panel_23;
	private JPanel panel_24;
	private JList<Abonado> FACTURACION_list_Abonados;
	private JLabel lblNewLabel_4;
	private JList<Factura> FACTURACION_list_FacturasPendientes;
	private JList<Factura> FACTURACION_list_FacturasPagadas;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JPanel panel_25;
	private JButton btnPagarFacturas;
	private JPanel panel_26;
	private JPanel Navegador;
	private JButton btnAbonados_Panel;
	private JButton btnFacturacion_Panel;
	private JButton btnGenerarReporteAFIP;
	private JButton btnPasarUnMes;
	private JPanel panel_20;
	private JPanel panel_21;

	private static boolean listaUsada = false;
	private IVentanaAFIP ventanaAFIP = null;

	private ActionListener actionListener;
	private JPanel panel_15;
	private JPanel panelAbonadosVista;
	private JPanel panelContratacionVista;
	private JPanel panelServiciosVista;
	private JPanel panelAbonadosFacturacion;
	private JPanel panelFacturasPendientesFacturacion;
	private JPanel panelFacturasPagadasFacturacion;
	private JPanel panel_13;
	private JPanel panel_16;
	private JPanel panel_17;
	private JPanel panel_18;
	private JPanel panel_19;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JPanel panel_27;
	private JPanel panel_28;
	private JLabel lblNewLabel_7;
	private JPanel panel_29;
	private JPanel panel_30;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;

	/**
	 * Realiza la comunicacion entre vista y controlador a través de los actionListener de los botones
	 * PRE: actionListener distinto de null POS: No hay poscondiciones
	 * 
	 * @param actionListener     de tipo ActionListener
	 */
	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnPasarUnMes.addActionListener(actionListener);
		this.ButtonImprimirFacturas.addActionListener(actionListener);
		this.ButtonAgregarServicio.addActionListener(actionListener);
		this.ButtonAgregarAbonado.addActionListener(actionListener);
		this.ButtonAgregarContratacion.addActionListener(actionListener);
		this.btnPagarFacturas.addActionListener(actionListener);
		this.btnGenerarReporteAFIP.addActionListener(actionListener);

		this.actionListener = actionListener;
	}

	/**
	 * Crea e inicializa todos los elementos de la pantalla
	 */
	public Ventana() {
		addWindowListener(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		this.General = new JPanel();
		this.contentPane.add(this.General);
		this.General.setLayout(new CardLayout(0, 0));

		this.panelABONADOS = new JPanel();
		this.General.add(this.panelABONADOS, "name_444446682836600");
		this.panelABONADOS.setLayout(new GridLayout(0, 3, 0, 0));

		this.panelAbonado = new JPanel();
		this.panelABONADOS.add(this.panelAbonado);
		this.panelAbonado.setLayout(new GridLayout(2, 1, 0, 1));

		this.panel_1 = new JPanel();
		this.panelAbonado.add(this.panel_1);
		this.panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		this.LabelTituloNuevoAbonado = new JLabel("Abonados");
		this.LabelTituloNuevoAbonado.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_1.add(this.LabelTituloNuevoAbonado);

		this.panel_2 = new JPanel();
		this.panel_1.add(this.panel_2);
		this.panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		this.LabelNombreAbonado = new JLabel("Nombre abonado");
		this.panel_2.add(this.LabelNombreAbonado);

		this.TextNombreAbonado = new JTextField();
		this.panel_2.add(this.TextNombreAbonado);
		this.TextNombreAbonado.setColumns(10);

		this.LabelDNIAbonado = new JLabel("DNI");
		this.panel_2.add(this.LabelDNIAbonado);

		this.textDNIAbonado = new JTextField();
		this.panel_2.add(this.textDNIAbonado);
		this.textDNIAbonado.setColumns(10);

		this.rdbtnPersonaFisica = new JRadioButton("Persona fisica");
		this.rdbtnPersonaFisica.setActionCommand("Persona Fisica");
		buttonGroup.add(this.rdbtnPersonaFisica);
		this.rdbtnPersonaFisica.setSelected(true);
		this.panel_2.add(this.rdbtnPersonaFisica);

		this.rdbtnPersonaJuridica = new JRadioButton("Persona juridica");
		this.rdbtnPersonaJuridica.setActionCommand("Persona Juridica");
		buttonGroup.add(this.rdbtnPersonaJuridica);
		this.panel_2.add(this.rdbtnPersonaJuridica);

		this.panel_3 = new JPanel();
		this.panel_1.add(this.panel_3);

		this.ButtonAgregarAbonado = new JButton("Agregar Abonado");
		this.panel_3.add(this.ButtonAgregarAbonado);
		this.ButtonAgregarAbonado.setActionCommand("AGREGAR_ABONADO");

		this.panel_4 = new JPanel();
		this.panelAbonado.add(this.panel_4);
		this.panel_4.setLayout(new BorderLayout(0, 0));

		this.LabelListaAbonados = new JLabel("Lista abonados");
		this.LabelListaAbonados.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_4.add(this.LabelListaAbonados, BorderLayout.NORTH);
		
		this.panel_13 = new JPanel();
		this.panel_4.add(this.panel_13, BorderLayout.CENTER);
				this.panel_13.setLayout(new BorderLayout(0, 0));
		
				this.panelAbonadosVista = new JPanel();
				this.panel_13.add(this.panelAbonadosVista);
				this.panelAbonadosVista.setLayout(new BorderLayout(0, 0));
				
						this.list_Abonados = new JList<Abonado>();
						this.panelAbonadosVista.add(this.list_Abonados);
						this.list_Abonados.addListSelectionListener(this);
						this.list_Abonados.setToolTipText("");
						this.list_Abonados.setModel(listModelAbonados);
						// this.FACTURACION_list_FacturasPendientes.setModel(this.listModelFacturas);

						JScrollPane sp = new JScrollPane(this.list_Abonados, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
								ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
						this.panelAbonadosVista.add(sp);
						
								this.list_Abonados.setCellRenderer( new ColorRenderer() );
								
								this.panel_16 = new JPanel();
								this.panel_13.add(this.panel_16, BorderLayout.SOUTH);
								this.panel_16.setLayout(new BoxLayout(this.panel_16, BoxLayout.X_AXIS));
								
								this.panel_17 = new JPanel();
								this.panel_17.setBackground(Color.WHITE);
								this.panel_16.add(this.panel_17);
								this.panel_17.setLayout(new GridLayout(0, 1, 0, 0));
								
								this.lblNewLabel_3 = new JLabel("Normal");
								this.lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
								this.panel_17.add(this.lblNewLabel_3);
								
								this.panel_18 = new JPanel();
								this.panel_18.setBackground(Color.YELLOW);
								this.panel_16.add(this.panel_18);
								this.panel_18.setLayout(new GridLayout(0, 1, 0, 0));
								
								this.lblNewLabel_5 = new JLabel("Sin contrataciones");
								this.lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
								this.panel_18.add(this.lblNewLabel_5);
								
								this.panel_19 = new JPanel();
								this.panel_19.setBackground(Color.RED);
								this.panel_16.add(this.panel_19);
								this.panel_19.setLayout(new GridLayout(0, 1, 0, 0));
								
								this.lblNewLabel_6 = new JLabel("Moroso");
								this.lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
								this.panel_19.add(this.lblNewLabel_6);

		this.panelContratacion = new JPanel();
		this.panelContratacion.setBackground(new Color(255, 255, 255));
		this.panelABONADOS.add(this.panelContratacion);
		this.panelContratacion.setLayout(new GridLayout(0, 1, 0, 0));

		this.panel = new JPanel();
		this.panelContratacion.add(this.panel);
		this.panel.setLayout(new GridLayout(0, 1, 0, 0));

		this.panel_6 = new JPanel();
		this.panel.add(this.panel_6);
		this.panel_6.setLayout(new GridLayout(0, 1, 0, 0));

		this.lblDomicilios = new JLabel("Domicilios");
		this.lblDomicilios.setHorizontalTextPosition(SwingConstants.CENTER);
		this.lblDomicilios.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_6.add(this.lblDomicilios);

		this.panel_7 = new JPanel();
		this.panel.add(this.panel_7);
		this.panel_7.setLayout(new GridLayout(0, 2, 0, 0));

		this.LabelDireccionDomicilio = new JLabel("Direccion");
		this.panel_7.add(this.LabelDireccionDomicilio);

		this.textDireccionDomicilio = new JTextField();
		this.textDireccionDomicilio.setColumns(10);
		this.panel_7.add(this.textDireccionDomicilio);

		this.rdbtnInternet100 = new JRadioButton("Internet 100");
		buttonGroup_2.add(this.rdbtnInternet100);
		this.rdbtnInternet100.setSelected(true);
		this.rdbtnInternet100.setActionCommand("Internet100");
		this.panel_7.add(this.rdbtnInternet100);

		this.rdbtnInternet500 = new JRadioButton("Internet 500");
		buttonGroup_2.add(this.rdbtnInternet500);
		this.rdbtnInternet500.setActionCommand("Internet500");
		this.panel_7.add(this.rdbtnInternet500);

		this.panel_8 = new JPanel();
		this.panel.add(this.panel_8);

		this.ButtonAgregarContratacion = new JButton("Agregar domicilio");
		this.ButtonAgregarContratacion.setHorizontalTextPosition(SwingConstants.CENTER);
		this.ButtonAgregarContratacion.setActionCommand("AGREGAR_DOMICILIO");
		this.panel_8.add(this.ButtonAgregarContratacion);

		this.panel_12 = new JPanel();
		this.panelContratacion.add(this.panel_12);
		this.panel_12.setLayout(new BorderLayout(0, 0));

		this.LabelListaDomicilios = new JLabel("Lista domicilios");
		this.LabelListaDomicilios.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_12.add(this.LabelListaDomicilios, BorderLayout.NORTH);

		this.panel_14 = new JPanel();
		this.panel_14.setBackground(SystemColor.inactiveCaption);
		this.panel_12.add(this.panel_14, BorderLayout.SOUTH);
		this.panel_14.setLayout(new GridLayout(0, 1, 0, 0));

		this.panelContratacionVista = new JPanel();
		this.panel_12.add(this.panelContratacionVista, BorderLayout.CENTER);
		this.panelContratacionVista.setLayout(new BorderLayout(0, 0));

		this.list_Contratacion = new JList<Contratacion>();
		this.panelContratacionVista.add(this.list_Contratacion);
		this.list_Contratacion.addListSelectionListener(this);
		this.list_Contratacion.setBackground(Color.LIGHT_GRAY);
		this.list_Contratacion.setModel(this.listModelContrataciones);

		this.panelServicios = new JPanel();
		this.panelABONADOS.add(this.panelServicios);
		this.panelServicios.setLayout(new GridLayout(0, 1, 0, 1));

		this.panel_11 = new JPanel();
		this.panelServicios.add(this.panel_11);
		this.panel_11.setLayout(new GridLayout(0, 1, 0, 0));

		this.lblNewLabel = new JLabel("Servicios");
		this.lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_11.add(this.lblNewLabel);

		this.panel_5 = new JPanel();
		this.panel_11.add(this.panel_5);
		this.panel_5.setLayout(new GridLayout(1, 0, 0, 0));

		this.rdbtnServicioCelular = new JRadioButton("Celular");
		buttonGroup_1.add(this.rdbtnServicioCelular);
		this.rdbtnServicioCelular.setSelected(true);
		this.panel_5.add(this.rdbtnServicioCelular);

		this.rdbtnServicioTelefono = new JRadioButton("Telefono");
		buttonGroup_1.add(this.rdbtnServicioTelefono);
		this.panel_5.add(this.rdbtnServicioTelefono);

		this.rdbtnServicioTV_Cable = new JRadioButton("TV_Cable");
		buttonGroup_1.add(this.rdbtnServicioTV_Cable);
		this.panel_5.add(this.rdbtnServicioTV_Cable);

		this.panel_10 = new JPanel();
		this.panel_11.add(this.panel_10);

		this.ButtonAgregarServicio = new JButton("Agregar servicio");
		this.ButtonAgregarServicio.setActionCommand("AGREGAR_SERVICIO");
		this.panel_10.add(this.ButtonAgregarServicio);

		this.panel_9 = new JPanel();
		this.panelServicios.add(this.panel_9);
		this.panel_9.setLayout(new BorderLayout(0, 0));

		this.LabelListaServicios = new JLabel("Lista servicios");
		this.LabelListaServicios.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_9.add(this.LabelListaServicios, BorderLayout.NORTH);

		this.panelServiciosVista = new JPanel();
		this.panel_9.add(this.panelServiciosVista, BorderLayout.CENTER);
		this.panelServiciosVista.setLayout(new GridLayout(1, 0, 0, 0));

		this.textPaneServicios = new JTextPane();
		this.panelServiciosVista.add(this.textPaneServicios);
		this.textPaneServicios.setEditable(false);

		this.panelFACTURACION = new JPanel();
		this.General.add(this.panelFACTURACION, "name_445259470820400");
		this.panelFACTURACION.setLayout(new GridLayout(0, 3, 0, 0));

		this.panel_22 = new JPanel();
		this.panelFACTURACION.add(this.panel_22);
		this.panel_22.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel_4 = new JLabel("Abonados");
		this.lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_22.add(this.lblNewLabel_4, BorderLayout.NORTH);

		this.panel_15 = new JPanel();
		this.panel_22.add(this.panel_15, BorderLayout.CENTER);
		this.panel_15.setLayout(new BorderLayout(0, 0));
		
				this.panelAbonadosFacturacion = new JPanel();
				this.panel_15.add(this.panelAbonadosFacturacion, BorderLayout.CENTER);
				
						this.FACTURACION_list_Abonados = new JList<Abonado>();
						this.panelAbonadosFacturacion.add(this.FACTURACION_list_Abonados);
						this.FACTURACION_list_Abonados.addListSelectionListener(this);
						this.panelAbonadosFacturacion.setLayout(new BorderLayout(0, 0));
						this.FACTURACION_list_Abonados.setModel(listModelAbonados);
						
								JScrollPane sp3 = new JScrollPane(this.FACTURACION_list_Abonados, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
										JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
								this.panelAbonadosFacturacion.add(sp3);
								this.FACTURACION_list_Abonados.setCellRenderer( new ColorRenderer() );
								
								this.panel_27 = new JPanel();
								this.panel_15.add(this.panel_27, BorderLayout.SOUTH);
								this.panel_27.setLayout(new BoxLayout(this.panel_27, BoxLayout.X_AXIS));
								
								this.panel_28 = new JPanel();
								this.panel_28.setBackground(Color.WHITE);
								this.panel_27.add(this.panel_28);
								this.panel_28.setLayout(new GridLayout(0, 1, 0, 0));
								
								this.lblNewLabel_7 = new JLabel("Normal");
								this.lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
								this.panel_28.add(this.lblNewLabel_7);
								
								this.panel_29 = new JPanel();
								this.panel_29.setBackground(Color.YELLOW);
								this.panel_27.add(this.panel_29);
								this.panel_29.setLayout(new GridLayout(0, 1, 0, 0));
								
								this.lblNewLabel_9 = new JLabel("Sin contrataciones");
								this.lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
								this.panel_29.add(this.lblNewLabel_9);
								
								this.panel_30 = new JPanel();
								this.panel_30.setBackground(Color.RED);
								this.panel_27.add(this.panel_30);
								this.panel_30.setLayout(new GridLayout(0, 1, 0, 0));
								
								this.lblNewLabel_8 = new JLabel("Moroso");
								this.lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
								this.panel_30.add(this.lblNewLabel_8);

		this.panel_23 = new JPanel();
		this.panelFACTURACION.add(this.panel_23);
		this.panel_23.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel_1 = new JLabel("Facturas pendientes");
		this.lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_23.add(this.lblNewLabel_1, BorderLayout.NORTH);

		this.panel_25 = new JPanel();
		this.panel_23.add(this.panel_25, BorderLayout.SOUTH);
		this.panel_25.setLayout(new GridLayout(0, 1, 0, 0));

		this.panel_20 = new JPanel();
		this.panel_25.add(this.panel_20);
		this.panel_20.setLayout(new GridLayout(0, 3, 0, 0));

		this.rdbtnEfectivo = new JRadioButton("Efectivo");
		this.panel_20.add(this.rdbtnEfectivo);
		buttonGroup_3.add(this.rdbtnEfectivo);
		this.rdbtnEfectivo.setSelected(true);

		this.rdbtnTarjeta = new JRadioButton("Tarjeta");
		this.panel_20.add(this.rdbtnTarjeta);
		buttonGroup_3.add(this.rdbtnTarjeta);

		this.rdbtnCeque = new JRadioButton("Cheque");
		this.panel_20.add(this.rdbtnCeque);
		buttonGroup_3.add(this.rdbtnCeque);

		this.panel_21 = new JPanel();
		this.panel_25.add(this.panel_21);

		this.btnPagarFacturas = new JButton("Pagar factura/s");
		this.btnPagarFacturas.setActionCommand("PAGAR_FACTURAS");
		this.panel_21.add(this.btnPagarFacturas);

		this.panelFacturasPendientesFacturacion = new JPanel();
		this.panel_23.add(this.panelFacturasPendientesFacturacion, BorderLayout.CENTER);
		this.panelFacturasPendientesFacturacion.setLayout(new GridLayout(0, 1, 0, 0));

		this.FACTURACION_list_FacturasPendientes = new JList<Factura>();
		this.panelFacturasPendientesFacturacion.add(this.FACTURACION_list_FacturasPendientes);
		this.FACTURACION_list_FacturasPendientes.addListSelectionListener(this);
		this.FACTURACION_list_FacturasPendientes.setBackground(Color.LIGHT_GRAY);

		this.FACTURACION_list_FacturasPendientes.setModel(this.listModelFacturasPendientes);

		this.panel_24 = new JPanel();
		this.panelFACTURACION.add(this.panel_24);
		this.panel_24.setLayout(new BorderLayout(0, 0));

		this.lblNewLabel_2 = new JLabel("Facturas pagadas");
		this.lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		this.panel_24.add(this.lblNewLabel_2, BorderLayout.NORTH);

		this.panel_26 = new JPanel();
		this.panel_24.add(this.panel_26, BorderLayout.SOUTH);

		this.ButtonImprimirFacturas = new JButton("Imprimir facturas");
		this.panel_26.add(this.ButtonImprimirFacturas);
		this.ButtonImprimirFacturas.setActionCommand("MOSTRAR_FACTURAS");

		this.panelFacturasPagadasFacturacion = new JPanel();
		this.panel_24.add(this.panelFacturasPagadasFacturacion, BorderLayout.CENTER);
		this.panelFacturasPagadasFacturacion.setLayout(new GridLayout(0, 1, 0, 0));

		this.FACTURACION_list_FacturasPagadas = new JList<Factura>();
		this.panelFacturasPagadasFacturacion.add(this.FACTURACION_list_FacturasPagadas);
		this.FACTURACION_list_FacturasPagadas.addListSelectionListener(this);
		this.FACTURACION_list_FacturasPagadas.setModel(this.listModelFacturasPagadas);

		this.Navegador = new JPanel();
		this.contentPane.add(this.Navegador, BorderLayout.NORTH);

		this.btnAbonados_Panel = new JButton("Abonados");
		this.btnAbonados_Panel.addActionListener(this);
		this.Navegador.add(this.btnAbonados_Panel);

		this.btnFacturacion_Panel = new JButton("Facturacion");
		this.btnFacturacion_Panel.addActionListener(this);
		this.Navegador.add(this.btnFacturacion_Panel);

		this.btnGenerarReporteAFIP = new JButton("Generar reporte de AFIP");
		this.btnGenerarReporteAFIP.setActionCommand("GENERAR_REPORTE_AFIP");
		this.Navegador.add(this.btnGenerarReporteAFIP);

		this.btnPasarUnMes = new JButton("Pasar un mes");
		this.btnPasarUnMes.setActionCommand("PASAR_UN_MES");
		this.Navegador.add(this.btnPasarUnMes);

		JScrollPane sp1 = new JScrollPane(this.list_Contratacion, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.panelContratacionVista.add(sp1);

		JScrollPane sp2 = new JScrollPane(this.textPaneServicios, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.panelServiciosVista.add(sp2);

		JScrollPane sp4 = new JScrollPane(this.FACTURACION_list_FacturasPendientes,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.panelFacturasPendientesFacturacion.add(sp4);

		JScrollPane sp5 = new JScrollPane(this.FACTURACION_list_FacturasPagadas,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.panelFacturasPagadasFacturacion.add(sp5);
		
		this.setVisible(true);
	}
	
	/**
	 * borra y carga la lista de abonados, guardando el indice seleccionado por el
	 * usuario para volverlo a marcar una vez cargada la lista con los nuevos
	 * abonados PRE:iterator distinto de null
	 * 
	 * @param iterator de la lista de abonados
	 */

	@Override
	public void actualizarListasAbonados(Iterator<Abonado> iterator) {
		listaUsada = true;
		int indice = this.list_Abonados.getSelectedIndex(),
				indiceFacturacion = this.FACTURACION_list_Abonados.getSelectedIndex();
		this.listModelAbonados.clear();
        
		while (iterator.hasNext()) {
			this.listModelAbonados.addElement(iterator.next());
		}
		this.repaint();
		listaUsada = false;
		this.list_Abonados.setSelectedIndex(indice);
		this.FACTURACION_list_Abonados.setSelectedIndex(indiceFacturacion);
	}

	/**
	 * borra y carga la lista de contrataciones de un abonado, guardando el indice
	 * seleccionado por el usuario para volverlo a marcar una vez cargada la lista
	 * con las nuevas contrataciones PRE:iterator distinto de null
	 * 
	 * @param iterator de la lista de abonados
	 */
	@Override
	public void actualizarListasContrataciones(Iterator<Contratacion> iterator) {
		listaUsada = true;
		int indice = this.list_Contratacion.getSelectedIndex();
		this.listModelContrataciones.clear();

		while (iterator.hasNext())
			this.listModelContrataciones.addElement(iterator.next());
		this.repaint();
		listaUsada = false;
		this.list_Contratacion.setSelectedIndex(indice);
	}

	/**
	 * borra y carga la lista de facturas pendientes, guardando el indice
	 * seleccionado por el usuario para volverlo a marcar una vez cargada la lista
	 * con las nuevas facturas PRE:iterator distinto de null
	 * 
	 * @param iterator de la lista de abonados
	 */
	@Override
	public void actualizarListasFacturasPendientes(Iterator<Factura> iterator) {
		listaUsada = true;
		int indice = this.FACTURACION_list_FacturasPendientes.getSelectedIndex();
		this.listModelFacturasPendientes.clear();
		while (iterator.hasNext())
			this.listModelFacturasPendientes.addElement(iterator.next());
		this.repaint();
		listaUsada = false;
		this.FACTURACION_list_FacturasPendientes.setSelectedIndex(indice);
	}

	/**
	 * borra y carga la lista de facturas pagadas, guardando el indice seleccionado
	 * por el usuario para volverlo a marcar una vez cargada la lista con las nuevas
	 * facturas PRE:iterator distinto de null
	 * 
	 * @param iterator de la lista de abonados
	 */
	@Override
	public void actualizarListasFacturasPagadas(Iterator<Factura> iterator) {
		listaUsada = true;
		int indice = this.FACTURACION_list_FacturasPagadas.getSelectedIndex();
		this.listModelFacturasPagadas.clear();

		while (iterator.hasNext())
			this.listModelFacturasPagadas.addElement(iterator.next());
		this.repaint();
		listaUsada = false;

		this.FACTURACION_list_FacturasPagadas.setSelectedIndex(indice);
	}

	/**
	 * POS:devuelve el tipo de Persona elegida por el usuario a partir de los
	 * radioButtons
	 * 
	 * @return String con el tipo
	 */
	@Override
	public String getTipoAbonado() {

		String tipo = null;
		if (this.rdbtnPersonaFisica.isSelected())
			tipo = "Persona Fisica";
		else if (this.rdbtnPersonaJuridica.isSelected())
			tipo = "Persona Juridica";

		return tipo;
	}

	/**
	 * POS:devuelve el nombre escrito por el usuario
	 * 
	 * @return String con el tipo
	 */
	@Override
	public String getNombreAbonado() {
		return this.TextNombreAbonado.getText();
	}

	/**
	 * POS:devuelve el DNI escrito por el usuario
	 * 
	 * @return String con el tipo
	 */
	@Override
	public String getDNIAbonado() {
		return this.textDNIAbonado.getText();
	}

	/**
	 * POS:devuelve la direccion escrita por el usuario
	 * 
	 * @return String con el tipo
	 */
	@Override
	public String getDireccionDomicilio() {
		return this.textDireccionDomicilio.getText();
	}

	/**
	 * POS:devuelve el tipo de Servicio elegido por el usuario a partir de los
	 * radioButtons
	 * 
	 * @return String con el tipo
	 */
	@Override
	public String getTipoServicio() {
		String tipo = null;
		if (this.rdbtnServicioCelular.isSelected())
			tipo = "Celular";
		else if (this.rdbtnServicioTelefono.isSelected())
			tipo = "Telefono";
		else
			tipo = "TV";
		return tipo;
	}

	/**
	 * POS:devuelve el medio de pago elegido por el usuario a partir de los
	 * radioButtons
	 * 
	 * @return String con el tipo
	 */
	@Override
	public String getTipoMedioPago() {
		String tipo = null;
		if (this.rdbtnEfectivo.isSelected())
			tipo = "Efectivo";
		else if (this.rdbtnTarjeta.isSelected())
			tipo = "Tarjeta";
		else
			tipo = "Cheque";
		return tipo;
	}

	/**
	 * POS:devuelve el abonado seleccionado por el usuario en la lista de Abonados
	 * 
	 * @return referencia al objeto abonado
	 */
	@Override
	public Abonado getAbonadoSeleccionado() {
		return this.list_Abonados.getSelectedValue();
	}

	/**
	 * POS:devuelve el abonado de facturacion seleccionado por el usuario en la
	 * lista de Abonados
	 * 
	 * @return referencia al objeto abonado
	 */
	@Override
	public Abonado getAbonadoFacturacionSeleccionado() {
		return this.FACTURACION_list_Abonados.getSelectedValue();
	}

	/**
	 * POS:devuelve la contratacion seleccionada por el usuario en la lista de
	 * Contrataciones
	 * 
	 * @return referencia al objeto contratacion
	 */
	@Override
	public Contratacion getContratacionSeleccionado() {
		return this.list_Contratacion.getSelectedValue();
	}

	/**
	 * borra y carga el String de todos los servicios elegidos por el usuario por
	 * pantalla
	 * 
	 * @param String con los servicios que tiene la contratacion
	 */
	@Override
	public void actualizarTextServicios(String servicios) {
		this.textPaneServicios.setText(servicios);
	}

	/**
	 * POS:devuelve el tipo de servicio elegido por el usuario a partir de los
	 * radioButtons
	 * 
	 * @return String con el tipo
	 */
	@Override
	public String getTipoContratacion() {
		String tipo = null;
		if (this.rdbtnInternet100.isSelected())
			tipo = "Internet100";
		else if (this.rdbtnInternet500.isSelected())
			tipo = "Internet500";
		return tipo;
	}

	/**
	 * POS:devuelve la o las facturas seleccionadas por el usuario en la lista de
	 * facturas
	 * 
	 * @return referencia al objeto ArrayList<Factura>
	 */
	@Override
	public ArrayList<Factura> getFacturasPendientesSeleccionadas() {
		ArrayList<Factura> F = null;

		if (this.FACTURACION_list_FacturasPendientes.getSelectedValuesList().size() > 0)
			F = (ArrayList<Factura>) this.FACTURACION_list_FacturasPendientes.getSelectedValuesList();

		return F;
	}

	/**
	 * POS:devuelve la o las facturas seleccionadas por el usuario en la lista de
	 * facturas
	 * 
	 * @return referencia al objeto ArrayList<Factura>
	 */
	@Override
	public ArrayList<Factura> getFacturasPagadasSeleccionadas() {
		ArrayList<Factura> F = null;

		if (this.FACTURACION_list_FacturasPagadas.getSelectedValuesList().size() > 0)
			F = (ArrayList<Factura>) this.FACTURACION_list_FacturasPagadas.getSelectedValuesList();

		return F;
	}

	/**
	 * POS:devuelve el nombre del jlist que seleccionó el usuario por pantalla
	 * 
	 * @param arg0 de tipo Object
	 * 
	 * @return String con el nombre o null en ningun caso
	 */
	@Override
	public String getNombreLista(Object arg0) {
		if (this.list_Abonados.equals(arg0))
			return "LISTA_ABONADOS";
		else if (this.FACTURACION_list_Abonados.equals(arg0))
			return "LISTA_ABONADOS_FACTURACION";
		else if (this.list_Contratacion.equals(arg0))
			return "LISTA_CONTRATACIONES";
		else if (this.FACTURACION_list_FacturasPendientes.equals(arg0))
			return "LISTA_FACTURAS_PENDIENTES";
		else if (this.FACTURACION_list_FacturasPagadas.equals(arg0))
			return "LISTA_FACTURAS_PAGADAS";
		return null;
	}

	////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Le envia a la ventana encargada del reporte de la afip los mensajes que debe
	 * mostra por pantalla PRE: No hay precondiciones POS: No hay poscondiciones
	 * 
	 * @param arg0 de tipo String
	 */
	@Override
	public void actualizarReporteAFIP(String mensaje) {
		this.ventanaAFIP.agregarReporteAFIP(mensaje);
	}

	/**
	 * Instancia el objeto VentanaAFIP, siempre que no este creado PRE: No hay
	 * precondiciones POS: No hay poscondiciones
	 */
	@Override
	public void generarReporteAFIP() {
		if (this.ventanaAFIP == null) {
			this.ventanaAFIP = new VentanaAFIP();
			this.ventanaAFIP.setActionListener(this.actionListener);
		}
	}

	/**
	 * Le envia a la ventana el mensaje de que termino el reporte PRE: No hay
	 * precondiciones POS: No hay poscondiciones
	 * 
	 * @param arg0 de tipo String
	 */
	@Override
	public void terminarReporteAFIP(String mensaje) {
		this.ventanaAFIP.terminarReporteAFIP(mensaje);
	}

	/**
	 * Le avisa a la ventana que debe cerrarse y pone en null al atributo
	 * ventanaAFIP PRE: el objeto ventanaAFIP debe ser distinto de null POS: No hay
	 * poscondiciones
	 */
	@Override
	public void cerrarReporteAFIP() {
		this.ventanaAFIP.cerrarReporteAFIP();
		this.ventanaAFIP = null;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Muestra por pantalla un mensaje PRE: No hay precondiciones POS: No hay
	 * poscondiciones
	 * 
	 * @param arg0 de tipo String
	 */
	@Override
	public void MostrarErrorEmergente(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}

	/**
	 * Muestra por pantalla un mensaje PRE: No hay precondiciones POS: No hay
	 * poscondiciones
	 * 
	 * @param arg0 de tipo String
	 */
	@Override
	public void imprimirFacturas(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Toma el boton presionado y muestra la seccion del programa que corresponda
	 * PRE: arg0 debe ser distinto de null POS: panelFACTURACION y panelABONADOS
	 * deben ser distintos de null
	 * 
	 * @param arg0 de tipo ActionEvent
	 */

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().contentEquals("Abonados")) {
			panelFACTURACION.setVisible(false);
			panelABONADOS.setVisible(true);
		} else if (arg0.getActionCommand().contentEquals("Facturacion")) {
			panelABONADOS.setVisible(false);
			panelFACTURACION.setVisible(true);
		}
	}

	/**
	 * Toma los cambios producidos en la Jlist y los convierte en actionListener
	 * PRE: arg0 distinto de null POS: No hay poscondiciones
	 * 
	 * @param arg0 de tipo ListSelectionEvent
	 */
	public void valueChanged(ListSelectionEvent arg0) {
		if (!listaUsada) {
			ActionEvent event;
			String command = getNombreLista(arg0.getSource());
			event = new ActionEvent(arg0.getSource(), 0, command);
			this.actionListener.actionPerformed(event);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////
	public void windowActivated(WindowEvent arg0) {
	}

	public void windowClosed(WindowEvent arg0) {
	}

	/**
	 * Avisa a controlador cuando se cierra la ventana 
	 * PRE: arg0 distinto de null
	 * POS: No hay poscondiciones
	 * 
	 * @param arg0 de tipo WindowEvent
	 */
	public void windowClosing(WindowEvent arg0) {
		ActionEvent event;
		String command = "CERRANDO_PROGRAMA";
		event = new ActionEvent(arg0.getSource(), 0, command);
		this.actionListener.actionPerformed(event);
	}

	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}

	public void windowIconified(WindowEvent arg0) {
	}

	public void windowOpened(WindowEvent arg0) {
		ActionEvent event;
		String command = "MOSTRAR_ABONADOS";
		event = new ActionEvent(arg0.getSource(), 0, command);
		this.actionListener.actionPerformed(event);
	}
}