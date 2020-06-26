package modelo;

import Excepciones.ImposibleCrearContratacion;
import Excepciones.ImposibleDarDeBajaException;

/**tipo comportamiento State asociado a abonado de tipo Físico
 */
public class MorosoState implements State{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PersonaFisica a;
	
	public MorosoState(PersonaFisica a) {
		this.a=a;
	}

	@Override
	public void pagarFactura(String medioPago,Factura f) {
		f.pagaMoroso(medioPago, a);
		 if (a.nFacturasImpagas() < 2)
			this.a.setEstado(new ConContratacionState(a));
	}

	@Override
	public void contratarServicio(Contratacion contrato) throws ImposibleCrearContratacion{
		throw new ImposibleCrearContratacion("EL abonado no puede contratar servicios hasta que pague las facturas que debe");
	}

	@Override
	public void darDeBaja(Contratacion contrato) throws ImposibleDarDeBajaException{
		throw new ImposibleDarDeBajaException("EL abonado no puede dar de baja servicios hasta que pague las facturas que debe");
	}

    @Override
    public int getTipo() {
        return 1;
    }
}
