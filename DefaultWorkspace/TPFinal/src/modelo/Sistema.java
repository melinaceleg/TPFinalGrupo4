package modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import io.IOAFIP;
import io.IOAbonado;
import Excepciones.ImposibleEmitirFacturaException;

public class Sistema extends Observable implements IEnviarMensaje, IReporteAFIP {
	private GestionAbonados gestionAbonados;
	private IOAbonado ioManage;
	
	/**
	 * Crea una instancia de gestionAbonados e ioManage
	 * 
	 * @throws ClassNotFoundException : no pudo cargar la clase
	 * @throws IOException            : fallo en la operacion de input/output
	 */
	public Sistema() throws ClassNotFoundException, IOException {
		this.gestionAbonados = new GestionAbonados();
		ioManage = new IOAbonado();
		this.iniciarJornada();
	}

	/**
	 * Carga la lista de abonados del archivo PRE: no tiene POS: no tiene
	 * 
	 * @throws ClassNotFoundException : no pudo cargar la clase
	 * @throws IOException            : fallo en la operacion de input/output
	 */
	public void iniciarJornada() throws ClassNotFoundException // despersistencia
	{
		
		try {
			ioManage.abrirInput();
			gestionAbonados.setAbonados((ArrayList<Abonado>) ioManage.leer());
			ioManage.cerrarInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

	}

	/**
	 * Persiste la lista de abonados del archivo PRE: no tiene POS: no tiene
	 * 
	 * @throws IOException : fallo en la operacion de input/output
	 */
	public void finalizarJornada() throws IOException// persistencia
	{
			ioManage.abrirOutput();
			ioManage.escribir(gestionAbonados.getAbonados());
			ioManage.cerrarOutput();


	}
	
	/**
	 * Muestra la lista de abonados Pendientes 
	 * PRE: No hay precondiciones 
	 * POS: manda un mensaje con todos los abonados
	 */
	public void mostrarAbonados() {
		this.enviar(new EstadoMensaje<Iterator<Abonado>>(this.gestionAbonados.getAbonados().iterator(), "ABONADOS"));		
	}


	/**
	 * Carga la lista de abonados del archivo 
	 * PRE: no tiene 
	 * POS: le cede la
	 * responsabilidad de agregar el nuevo abonado a gestionAbonados
	 * 
	 * @param nombre de tipo String
	 * @param dni    de tipo String
	 * @param tipo   de tipo String
	 */

	public void agregarNuevoAbonado(String nombre, String dni, String tipo) {
		this.gestionAbonados.agregarNuevoAbonado(this, nombre, dni, tipo);
	}

	/**
	 * Carga a un abonado una contratacion
	 * PRE: no tiene 
	 * POS: le cede la responsabilidad de agregar una nueva contratacion a gestionAbonados
	 * 
	 * @param abonado de tipo Abonado
	 * @param domicilio    de tipo String
	 * @param tipo   de tipo String
	 */
	public void agregarContratacion(Abonado abonado, String domicilio, String tipo) {
		this.gestionAbonados.agregarContratacion(abonado, this, domicilio, tipo);
	}
	/**
	 * Carga a un abonado un servicio
	 * PRE: no tiene 
	 * POS: le cede la responsabilidad de agregar un nuevo servicio a gestionAbonados
	 * 
	 * @param contratacion de tipo Contratacion
	 * @param tipo    de tipo String
	 */
	public void agregarNuevoServicio(Contratacion contratacion, String tipo) {
		this.gestionAbonados.agregarNuevoServicio(this, contratacion, tipo);
	}

	/**
	 * Crea una nueva factura para cada abonado
	 * PRE: no tiene 
	 * POS: le cede la responsabilidad de avanzar un mes a gestionAbonados
	 */
	public void avanzarMes() {
		this.gestionAbonados.avanzarMes(this);
	}

	/**
	 * Muestra la Lista de domicilios PRE: No hay precondiciones POS: Muestra la
	 * lista de domicilios si se seleccionó un abonado, sino mostrara un
	 * MessageDialog
	 */
	public void mostrarListaDomicilios(Abonado abonado) {
		if (abonado != null) {
			this.enviar(new EstadoMensaje<Iterator<Contratacion>>(abonado.getContrataciones().iterator(),
					"CONTRATACIONES"));
		}
	}

	/**
	 * Muestra la lista de Contrataciones PRE: No hay precondiciones POS: Si la
	 * contratación fue seleccionada actualizará la lista o mostrará un
	 * MessageDialog de seleccionar un Domicilio
	 */
	public void mostrarListaContratacion(Contratacion contratacion) {
		if (contratacion != null)
			this.enviar(new EstadoMensaje<String>(contratacion.getServicio().toString(), "SERVICIOS"));
		else
			this.enviar(new EstadoMensaje<String>(null, "SERVICIO"));
	}

	/**
	 * Muestra la lista de Facturas PRE: No hay precondiciones POS: muestra la lista
	 * de facturas si la factura seleccionada es distinta de null, de lo contrario
	 * mostrará un MessageDialog
	 */
	/// metodo pertenece a IcoleccionFactura ------ SACAR DE SISTEMA---
	public void mostrarFacturas(ArrayList<Factura> listaFact) {
		if (listaFact != null && listaFact.size() > 0) {
			Factura factura = null;
			Iterator<Factura> it = listaFact.iterator();
			Iterator<Contratacion> itContr = null;
			int numero = 1;

			String cadena = "FACTURAS " + listaFact.size() + ":\n\n";

			while (it.hasNext()) {
				factura = it.next();
				cadena += "\nNro " + (numero++) + "\n" + factura.toString() + "\nContrataciones:\n";
				itContr = factura.getContrataciones().iterator();
				while (itContr.hasNext()) {
					cadena += itContr.next() + "\n";
				}
				cadena += "\n";
			}
			this.enviar(new EstadoMensaje<String>(cadena, "MOSTRAR_FACTURAS"));
		} else {
			this.enviar(new EstadoMensaje<String>("Debe seleccionar al menos una factura", "ERROR"));
		}
	}
	/**
	 * Muestra la lista de Facturas Pendientes PRE: No hay precondiciones POS: muestra la lista
	 * de facturas pendientes si el abonado y la factura seleccionada es distinta de null, de lo contrario
	 * mostrará un MessageDialog
	 */
	public void mostrarListaFacturasPendientes(Abonado abonado) {
		if (abonado != null && abonado.getFacturas(false) != null) {
			this.enviar(new EstadoMensaje<Iterator<Factura>>(abonado.getFacturasPendientes(), "FACTURAS_PENDIENTES"));
		}
	}

	/**
	 * Muestra la lista de Facturas pagadas PRE: No hay precondiciones POS: muestra la lista
	 * de facturas pagadas si el abonado y la factura seleccionada es distinta de null, de lo contrario
	 * mostrará un MessageDialog
	 */
	public void mostrarListaFacturasPagadas(Abonado abonado) {
		if (abonado != null && abonado.getFacturas(true) != null) {
			this.enviar(new EstadoMensaje<Iterator<Factura>>(abonado.getFacturasPagadas(), "FACTURAS_PAGADAS"));
		}
	}
	/**
	 * Paga la o las facturas pendientes del abonado
	 * PRE: no tiene 
	 * POS: cambia el estado de las facturas y del abonado. En caso de que una factura no se pueda pagar se manda un mensaje de error
	 * 
	 * @param abonado de tipo Abonado
	 * @param facturas de tipo ArrayList<Factura>
	 * @param tipoPago de tipo String
	 */
	public void pagarFacturas(Abonado abonado, ArrayList<Factura> facturas, String tipoPago) {
		if (abonado != null && facturas != null) {
			Iterator<Factura> it = facturas.iterator();
			int facturasNoPagables = 0, facturasPagadas = 0;
			while (it.hasNext()) {
				try {
					abonado.pagarFactura(tipoPago, it.next());
					facturasPagadas++;
				} catch (ImposibleEmitirFacturaException e) {
					// TODO Auto-generated catch block
					facturasNoPagables++;
				}
			}
			if (facturasNoPagables > 0) {
				String mensaje = facturasNoPagables + " factura" + ((facturasNoPagables == 1) ? "" : "s");
				mensaje += " no se ha" + ((facturasNoPagables == 1) ? "" : "n") + " podido pagar";
				this.enviar(new EstadoMensaje<String>(mensaje, "ERROR"));
			}
			if (facturasPagadas > 0) {
				mostrarListaFacturasPendientes(abonado);
				mostrarListaFacturasPagadas(abonado);
			}
		}
	}

	////////////////////////////////////ReporteAFIP de Sistema///////////////////////////////////////////////////////////////////////////////
	/**
	 * Crea el hilo visitaAFIP que realizará el reporte
	 * PRE: no tiene 
	 * POS: se ejecuta paralelamente el hilo del reporte, que bloqueará la posibilidad de agregar a un nuevo abonado hasta que finalice
	 */
	public void realizarReporteAFIP() {
		VisitaAFIP visitaAFIP = new VisitaAFIP(this, this.gestionAbonados);
		this.enviar(new EstadoMensaje<String>("Realizando Reporte", "GENERAR_REPORTE_AFIP"));
		visitaAFIP.start();
	}

	/**
	 * Manda un mensaje a controlador que el reporte debe cerrarse
	 * PRE: no tiene 
	 * POS: se envia un EstadoMensaje con informacion necesaria para cerrar el reporte
	 */
	public void cerrarReporteAFIP() {
		this.enviar(new EstadoMensaje<String>("Reporte cerrado!", "CERRAR_REPORTE_AFIP"));
	}

	/**
	 * Manda un mensaje a controlador que el reporte ha finalizado
	 * PRE: no tiene 
	 * POS: se envia un EstadoMensaje para notificar de la finalizacion del hilo
	 */
	@Override
	public void desactivarReporte() {
		this.enviar(new EstadoMensaje<String>("Reporte finalizado correctamente!", "TERMINAR_REPORTE_AFIP"));
	}

	/**
	 * Manda un mensaje a controlador de la informacion del reporte que se esta haciendo
	 * PRE: no tiene 
	 * POS: se envia un EstadoMensaje con la informacion del reporte
	 */
	@Override
	public void mandarReporte(String mensaje) {
		this.enviar(new EstadoMensaje<String>(mensaje, "MOSTRAR_REPORTE_AFIP"));
	}

	/**
	 * Manda un mensaje a controlador
	 * PRE: no tiene 
	 * POS: se envian EstadoMensaje a controlador
	 * 
	 * @param mensaje de tipo EstadoMensaje<?>
	 */
	@Override
	public void enviar(EstadoMensaje<?> mensaje) {
		this.setChanged();
		this.notifyObservers(mensaje);
	}
	

	/**
	 * Se persiste una lista de facturas
	 * PRE: las facturas deben estar inicializadas
	 * POS: cede la responsabilidad de guardar las facturas del reporte de AFIPa IOAFIP
	 * 
	 * @param facturas de tipo ArrayList<Factura>
	 * 
	 * @throws ClassNotFoundException : no pudo cargar la clase
	 * @throws IOException            : fallo en la operacion de input/output
	 */
	public void reporteSalida(ArrayList<Factura> facturas) throws IOException, ClassNotFoundException {
		IOAFIP salida = new IOAFIP();
		salida.abrirOutput();
		salida.escribir(facturas);
		salida.cerrarOutput();

	}
}