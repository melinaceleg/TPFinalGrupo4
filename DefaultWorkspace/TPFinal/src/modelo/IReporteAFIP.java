package modelo;

import java.io.IOException;
import java.util.ArrayList;

public interface IReporteAFIP {
	void desactivarReporte();
	void mandarReporte(String mensaje);
	public void reporteSalida(ArrayList<Factura> facturas) throws IOException, ClassNotFoundException;
	
	
}