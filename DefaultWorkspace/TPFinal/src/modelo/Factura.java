package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Factura realiza la concatenación de los detalles que serían las
 * contrataciones de un abonado. Almacena dni y nombre y realiza el calculo
 * correspondiente total de los detalles con el descuento segun el tipo de
 * abonado y medio de pago que llegan como información externa. Permite
 * clonación condicional.
 */
public class Factura implements Cloneable,Serializable {
	private String dni;
	private String nombre;
	private boolean EstadoDePago; /// true Pagado false NoPagado

	/**
	 * @aggregation shared
	 */
	private ArrayList<Contratacion> contrataciones;
	private double pagoSinDescuento;
	private double pagoConDescuento;
	private boolean cloneable;

	/**
	 * Se crea una nueva instancia de factura y se asigna el calculo del pago con
	 * descuento PRE:abonado distinto de null, medioDePago distinto de null
	 * 
	 * @param abonado     de tipo Abonado
	 * @param medioDePago que implemente IMedioDePago
	 */
	public Factura(Abonado abonado)  {
		this.dni = abonado.dni;
		this.nombre = abonado.nombre;
		this.contrataciones = abonado.getContrataciones();
		this.pagoSinDescuento = pagoSinDes();
		this.cloneable = abonado.cloneable();
	}

	public double getPagoSinDescuento() {
		return pagoSinDescuento;
	}

	public double getPagoConDescuento() {
		return pagoConDescuento;
	}

	public void setPagoSinDescuento(double pagoSinDescuento) {
		this.pagoSinDescuento = pagoSinDescuento;
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<Contratacion> getContrataciones() {

		return this.contrataciones;
	}

	/**
	 * Realiza el calculo del total de los detalles(contrataciones) sin descuento
	 * PRE: No hay precondiciones POS: devuelve el total de la suma de los detalles
	 * sin descuento aplicado
	 * 
	 * @return total sin descuento
	 */
	private double pagoSinDes() {
		double total = 0;
		int i;
		for (i = 0; i < this.contrataciones.size(); i++) {
			total += this.contrataciones.get(i).getServicio().getPrecio();
		}
		return total;
	}

	/**
	 * Realiza la clonación del objeto Factura y se mantienen las mismas referencias
	 * en las otras variables "Copia superficial" PRE: debe ser el atributo
	 * cloneable=true POS: Devuelve el nuevo objeto de tipo factura duplicado o
	 * excepción en caso de ser cloneable = false
	 * 
	 * @return nuevo objeto Factura duplicado
	 */
	public Object clone() throws CloneNotSupportedException {

		Factura nuevo = null;
		if (this.cloneable) // si no es persona juridica
			nuevo = (Factura) super.clone();
		else
			throw new CloneNotSupportedException("La Factura no se puede Clonar ya que el abonado es Persona Jurídica");

		return nuevo;
	}

        
    /**Se realiza el cambio de estado de una factura a "pagado" (true)
     * @param medioPago : String que coincide con el medio de pago
     * @param a : de tipo Abonado 
     * PRE: parametro a:Abonado distinto de null
     * POS:-
     */
	public void transaccion(String medioPago, Abonado a) {
        if (a.getDni().equals(this.dni)) {
		this.pagoConDescuento = this.pagoSinDescuento * MedioPagoFactory.crearModoDePago(medioPago).getTarifa(a);
		this.setEstadoDePago(true);
        }
	}

    /**método invocado desde un abonado de tipo Físico que implementa MorosoState
     * @param medioPago : string con medio de pago
     * @param a : abonado que pagará factura
     * PRE: medioPago distinto de null, abonado a distinto de null
     * POS: realiza pago
     */
    public void pagaMoroso(String medioPago, Abonado a) {
		this.setPagoSinDescuento(pagoSinDescuento * 1.3);
		this.transaccion(medioPago, a);
	}

	@Override
	public String toString() {
		return "Abonado: " + nombre.toString() + " | DNI: " + dni.toString() + " | Pago sin descuento= $"
				+ pagoSinDescuento + " | Pago con descuento= $" + pagoConDescuento;
	}

	public boolean isEstadoDePago() {
		return EstadoDePago;
	}

	public void setEstadoDePago(boolean estadoDePago) {
		EstadoDePago = estadoDePago;
	}

}