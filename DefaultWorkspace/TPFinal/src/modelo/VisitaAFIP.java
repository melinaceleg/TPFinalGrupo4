package modelo;

import java.io.IOException;

/**Thread que corre un reporte de facturas clonadas en una ventana, no puede interferir
 * con otro thread accediendo al método Alta de un Abonado.
 */
public class VisitaAFIP extends Thread {

	private GestionAbonados gestionAbonados;
	private IReporteAFIP reporteAFIP;
	
	public VisitaAFIP(IReporteAFIP reporteAFIP, GestionAbonados gestionAbonados) {
		this.reporteAFIP = reporteAFIP;
		this.gestionAbonados = gestionAbonados;
	}
	


	@Override
	public void run() {
		try {
			gestionAbonados.clonarFacturas(reporteAFIP);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
        this.reporteAFIP.desactivarReporte();
	}
}