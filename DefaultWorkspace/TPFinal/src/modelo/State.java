package modelo;

import java.io.Serializable;

import Excepciones.ImposibleCrearContratacion;
import Excepciones.ImposibleDarDeBajaException;
import Excepciones.ImposibleEmitirFacturaException;

/**Patrón State para cambiar comportamiento de los objetos de tipo Persona física
 */
public interface State extends Serializable{
	void pagarFactura(String medioPago,Factura f) throws ImposibleEmitirFacturaException;
	void contratarServicio(Contratacion contrato) throws ImposibleCrearContratacion;
	void darDeBaja(Contratacion contrato) throws ImposibleDarDeBajaException;
        int getTipo();

}