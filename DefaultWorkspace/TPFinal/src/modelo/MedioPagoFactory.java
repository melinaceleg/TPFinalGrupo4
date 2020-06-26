package modelo;


/**
 * Se encarga de instanciar la clase que implementan IMedioDePago
 */
public class MedioPagoFactory {
	/**
	 * @param tipo : String con tipo de medio de pago
	 * @return nuevo : objeto que implementa IMedioPago
	 */
	public static IMedioDePago crearModoDePago(String tipo) {
		IMedioDePago s = null;
		if (tipo.equalsIgnoreCase("Efectivo"))
			s = new Efectivo();
		else if (tipo.equalsIgnoreCase("Tarjeta"))
			s = new Tarjeta();
		else if (tipo.equalsIgnoreCase("Cheque"))
			s = new Cheque();
		return s;
	}
}
