package modelo;


/**
 * Clase que representa Medio de Pago Cheque
 */
public class Cheque implements IMedioDePago {

    /**   
     * PRE: pagarFactura debe ser distinto de null
     * POS: devuelve llamada metodo cheque de pagarFactura
     * @param pagarFactura :recibe un objeto que implementa IPagarFactura
     * @return llamada a metodo cheque del objeto pagarFactura
     */
    @Override
	public double getTarifa(IPagarFactura pagarFactura) {
		return pagarFactura.cheque();
	}

}
