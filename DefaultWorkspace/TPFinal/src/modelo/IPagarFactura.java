package modelo;


/**Comportamiento que implementa un abonado para asociar medio de pago en la tarifa del servicio (DOUBLE DISPTACH)
 */
public interface IPagarFactura {
	double cheque();
	double efectivo();
	double tarjeta();
}
