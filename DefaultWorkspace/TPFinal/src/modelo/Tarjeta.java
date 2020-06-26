package modelo;


public class Tarjeta implements IMedioDePago {
    /**
     * PRE: pagarFactura debe ser distinto de null
     * POS:devuelve llamada metodo tarjeta de pagarFactura
     * @param pagarFactura recibe un objeto que implementa IPagarFactura
     * @return llamada a metodo tarjeta del objeto pagarFactura
     */
    @Override
	public double getTarifa(IPagarFactura pagarFactura) {		
		return pagarFactura.tarjeta();
	}
}
