package modelo;

import java.util.Iterator;
import java.util.Observable;

import Excepciones.ImposibleCrearContratacion;
import Excepciones.ImposibleDarDeBajaException;
import Excepciones.ImposibleEmitirFacturaException;

/**
 * tipo abonado Físico
 */
public class PersonaFisica extends Abonado implements State {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected State Estado;

	public PersonaFisica(String nombre, String dni) {
		super(nombre, dni);
		this.setEstado(new SinContratacionState(this));
	}

	public State getEstado() {
		return Estado;
	}

	public void setEstado(State estado) {
		this.Estado = estado;
	}

	@Override
	public double cheque() {
		return 1.1;
	}

	@Override
	public double efectivo() {
		// TODO Auto-generated method stub
		return 0.8;
	}

	@Override
	public double tarjeta() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean cloneable() {
		// TODO Auto-generated method stub
		return true;
	}

    /**
     * @param medioPago : String medio de pago
     * @param f : factura que se desea pagar
     * @throws ImposibleEmitirFacturaException : propaga excepción si el comporamiento del objeto no permite
     * pagar una factura
     * PRE: medioPago distinto null, Factura f distinto de null
     * POS: -
     */
    @Override
	public void pagarFactura(String medioPago, Factura f) throws ImposibleEmitirFacturaException {
		this.getEstado().pagarFactura(medioPago, f);
	}

    /**El abonado contrata un servicio
     * @param contrato : Contratacion que se desea agregar
     * @throws ImposibleCrearContratacion : propaga excepción si el comportamiento del objeto no permite
     * agregar una contratación
     * PRE: contrato distinto de null
     * POS: realiza acción de contratar servicio
     */
    @Override
	public void contratarServicio(Contratacion contrato) throws ImposibleCrearContratacion {
		this.getEstado().contratarServicio(contrato);
	}

    /**El abonado da de baja un servicio
     * @param contrato : contratación que se desea dar de baja
     * @throws ImposibleDarDeBajaException : propaga excepción si el comportamiento del objeto no permite dar de baja la contratación
     * PRE: contrato distinto de null
     * POS: realiza acción de dar de baja el contrato
     */
    @Override
	public void darDeBaja(Contratacion contrato) throws ImposibleDarDeBajaException {
		this.getEstado().darDeBaja(contrato);
	}

    /**Agrega una factura a la lista del abonado
     * @param f : Factura que se desea agregar
     * PRE: Factura f distinta de null
     * POS: si las facturas impagas son 2 cambiará el comportamiento 
     * del objeto a través del State, sino se agrega nueva factura
     */
    @Override
	public void agregarFactura(Factura f) {
		this.facturas.add(f);
		if (nFacturasImpagas() == 2) {
			this.setEstado(new MorosoState(this));
		}
	}

    /**Cuenta facturas impagas pertenecientes al abonado
     * @return cantidad mayor o igual a 0
     * PRE: -
     * POS: retorna cantidad positiva o 0
     */
    public int nFacturasImpagas() {
		Iterator<Factura> it = this.facturas.iterator();
		int c = 0;
		while (it.hasNext()) {
			if (!it.next().isEstadoDePago()) {
				c++;
			}
		}
		return c;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

    @Override
    public int getTipo() {
        return this.getEstado().getTipo();
    }
}
