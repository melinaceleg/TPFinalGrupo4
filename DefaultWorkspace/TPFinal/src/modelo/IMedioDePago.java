package modelo;


/**Comportamiento asociado a los medios de pago de una factura
 */
public interface IMedioDePago {
	double getTarifa(IPagarFactura pagarFactura);
}
