package modelo;


/**Clase que representa Medio de Pago efectivo
 */
public class Efectivo implements IMedioDePago {
    /**
     * PRE: pagarFactura debe ser distinto de null
     * POS:devuelve llamada metodo efectivo de pagarFactura
     * @param pagarFactura recibe un objeto que implementa IPagarFactura
     * @return llamada a metodo efectivo del objeto pagarFactura
     */
	@Override
	public double getTarifa(IPagarFactura pagarFactura) {
		return pagarFactura.efectivo();
	}
}
