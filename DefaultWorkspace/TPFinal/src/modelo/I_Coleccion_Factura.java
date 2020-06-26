package modelo;

import Excepciones.ImposibleEmitirFacturaException;

public interface I_Coleccion_Factura {

public void agregarFactura(Factura f) throws ImposibleEmitirFacturaException;
public void quitarFactura(Factura f);
public int nFacturasImpagas();

}
