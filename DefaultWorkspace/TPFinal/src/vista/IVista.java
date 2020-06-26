package vista;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import modelo.Abonado;
import modelo.Contratacion;
import modelo.Factura;

public interface IVista {

	void setActionListener(ActionListener actionListener);
	
	String getNombreLista(Object list);
	
	void actualizarListasAbonados(Iterator<Abonado> iterator);	
	void actualizarListasContrataciones(Iterator<Contratacion> iterator);	
	void actualizarListasFacturasPendientes(Iterator<Factura> iterator);	
	void actualizarListasFacturasPagadas(Iterator<Factura> iterator);	
	void actualizarTextServicios(String servicios);	

	String getNombreAbonado();
	String getDNIAbonado();
	String getTipoAbonado();	
	
	String getDireccionDomicilio();
	String getTipoContratacion();

	String getTipoServicio();
	
	String getTipoMedioPago();
		
	Abonado getAbonadoSeleccionado();
	Abonado getAbonadoFacturacionSeleccionado();
	Contratacion getContratacionSeleccionado();	
	ArrayList<Factura> getFacturasPendientesSeleccionadas();
	ArrayList<Factura> getFacturasPagadasSeleccionadas();	
	
	void MostrarErrorEmergente(String mensaje);
	void imprimirFacturas(String mensaje);
	
	void generarReporteAFIP();
	void actualizarReporteAFIP(String mensaje);
	void terminarReporteAFIP(String mensaje);
	void cerrarReporteAFIP();
}