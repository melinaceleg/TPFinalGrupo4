package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;


import modelo.Abonado;
import modelo.Contratacion;
import modelo.EstadoMensaje;
import modelo.Factura;
import modelo.Sistema;
import vista.IVista;
import vista.Ventana;

/**Comunica y devuelve validaciones entre Vista y Modelo
 */
public class Controlador implements Observer, ActionListener {

	/**
	 */
	private IVista vista;

	/**
	 */
	private Sistema sistema;

	public Controlador() {

		try {
			this.sistema = new Sistema();
		} catch (ClassNotFoundException e) {
			///DEBEMOSTRARUN MESSAGE DIALOG
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.sistema.addObserver(this);

		this.vista = new Ventana();
		this.vista.setActionListener(this);
	}

	/**
	 * Llama a los métodos de la clase según el botón accionado en la vista PRE: el
	 * botón accionado no puede ser null POS: llamará al método asociado si el
	 * String ingresado coincide, actualizará la lista de abonados
	 * 
	 * @param arg0 : botón accionado
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("MOSTRAR_ABONADOS")) {
			this.sistema.mostrarAbonados();
		} else if (arg0.getActionCommand().equals("AGREGAR_ABONADO")) {
			this.sistema.agregarNuevoAbonado(this.vista.getNombreAbonado(), this.vista.getDNIAbonado(), this.vista.getTipoAbonado());
		} else if (arg0.getActionCommand().equals("AGREGAR_DOMICILIO")) {
			this.sistema.agregarContratacion(this.vista.getAbonadoSeleccionado(), this.vista.getDireccionDomicilio(), this.vista.getTipoContratacion());
		} else if (arg0.getActionCommand().equals("AGREGAR_SERVICIO")) {
			this.sistema.agregarNuevoServicio(this.vista.getContratacionSeleccionado(), this.vista.getTipoServicio());
		} else if (arg0.getActionCommand().equals("AGREGAR_SERVICIO")) {
			this.sistema.agregarNuevoServicio(this.vista.getContratacionSeleccionado(), this.vista.getTipoServicio());
		} else if (arg0.getActionCommand().equals("PAGAR_FACTURAS")) {
			this.sistema.pagarFacturas(this.vista.getAbonadoFacturacionSeleccionado(), this.vista.getFacturasPendientesSeleccionadas(), this.vista.getTipoMedioPago());
		} else if (arg0.getActionCommand().equals("PASAR_UN_MES")) {
			this.sistema.avanzarMes();
			this.sistema.mostrarListaFacturasPendientes(this.vista.getAbonadoFacturacionSeleccionado());
			this.sistema.mostrarListaFacturasPagadas(this.vista.getAbonadoFacturacionSeleccionado());
		} else if (arg0.getActionCommand().equals("LISTA_ABONADOS")) {
			this.sistema.mostrarListaDomicilios(this.vista.getAbonadoSeleccionado());
			this.sistema.mostrarListaContratacion(this.vista.getContratacionSeleccionado());
		} else if (arg0.getActionCommand().equals("LISTA_ABONADOS_FACTURACION")) {
			this.sistema.mostrarListaFacturasPendientes(this.vista.getAbonadoFacturacionSeleccionado());
			this.sistema.mostrarListaFacturasPagadas(this.vista.getAbonadoFacturacionSeleccionado());
		} else if (arg0.getActionCommand().equals("LISTA_CONTRATACIONES")) {
			this.sistema.mostrarListaContratacion(vista.getContratacionSeleccionado());
		} else if (arg0.getActionCommand().contentEquals("GENERAR_REPORTE_AFIP")) {
			this.sistema.realizarReporteAFIP();
		} else if (arg0.getActionCommand().contentEquals("CERRAR_REPORTE_AFIP")) {
			this.sistema.cerrarReporteAFIP();
		} else if (arg0.getActionCommand().contentEquals("MOSTRAR_FACTURAS")) {
			this.sistema.mostrarFacturas(this.vista.getFacturasPagadasSeleccionadas());
		} else if (arg0.getActionCommand().contentEquals("CERRANDO_PROGRAMA")) {
			try {
				this.sistema.finalizarJornada();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Recibe los mensajes de Observable (sitema) y a notifica a vista de los cambios
	 * PRE: arg0 y arg1 distintos de null
	 * POS: llamará al método asociado si el String ingresado coincide
	 * 
	 * @param arg0 : de tipo Observable
	 * @param arg1 : de tipo EstadoMensaje
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 != this.sistema)
			throw new InvalidParameterException();
		EstadoMensaje<?> estado = (EstadoMensaje<?>) arg1;
		if (estado.getMensaje().contentEquals("ERROR")) {
			this.vista.MostrarErrorEmergente(estado.getObjecto().toString());
		} else if (estado.getMensaje().contentEquals("ABONADOS")) {
			this.vista.actualizarListasAbonados((Iterator<Abonado>) estado.getObjecto());
			this.vista.actualizarTextServicios(null);
		} else if (estado.getMensaje().contentEquals("CONTRATACIONES")) {
			this.vista.actualizarListasContrataciones((Iterator<Contratacion>) estado.getObjecto());
		} else if (estado.getMensaje().contentEquals("SERVICIOS")) {
			this.vista.actualizarTextServicios(estado.getObjecto().toString());
		} else if (estado.getMensaje().contentEquals("FACTURAS_PENDIENTES")) {
			this.vista.actualizarListasFacturasPendientes((Iterator<Factura>) estado.getObjecto());
                        this.vista.actualizarListasAbonados((Iterator<Abonado>) estado.getObjecto());
		} else if (estado.getMensaje().contentEquals("FACTURAS_PAGADAS")) {
			this.vista.actualizarListasFacturasPagadas((Iterator<Factura>) estado.getObjecto());
		} else if (estado.getMensaje().contentEquals("MOSTRAR_REPORTE_AFIP")) {
			this.vista.actualizarReporteAFIP(estado.getObjecto().toString());
		} else if (estado.getMensaje().contentEquals("GENERAR_REPORTE_AFIP")) {
			this.vista.generarReporteAFIP();
		} else if (estado.getMensaje().contentEquals("TERMINAR_REPORTE_AFIP")) {
			this.vista.terminarReporteAFIP(estado.getObjecto().toString());
		} else if (estado.getMensaje().contentEquals("CERRAR_REPORTE_AFIP")) {
			this.vista.cerrarReporteAFIP();
		} else if (estado.getMensaje().contentEquals("MOSTRAR_FACTURAS")) {
			this.vista.imprimirFacturas(estado.getObjecto().toString());
		}
	}
}
