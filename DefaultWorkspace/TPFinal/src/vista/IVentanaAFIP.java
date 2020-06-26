package vista;

import java.awt.event.ActionListener;

public interface IVentanaAFIP {
	void setActionListener(ActionListener actionListener);
	void agregarReporteAFIP(String mensaje);
	void terminarReporteAFIP(String mensaje);
	void cerrarReporteAFIP();
}