package modelo;

import Excepciones.ImposibleDarDeBajaException;
import Excepciones.ImposibleEmitirFacturaException;

/**Comportamiento que implementa el objeto de tipo Persona física cuando no posee contrataciones
 */
@SuppressWarnings("serial")
public class SinContratacionState implements State{
	private PersonaFisica a;
	public SinContratacionState(PersonaFisica a) {
		this.a=a;
	}

	@Override
	public void pagarFactura(String medioPago,Factura f) throws ImposibleEmitirFacturaException{
		throw new ImposibleEmitirFacturaException("Se deben contratar servicios");
	}

	@Override
	public void contratarServicio(Contratacion contrato) {
		a.agregarContratacion(contrato);
		a.setEstado(new ConContratacionState(a));
	}

	@Override
	public void darDeBaja(Contratacion contrato) throws ImposibleDarDeBajaException{
		throw new ImposibleDarDeBajaException("Se deben contratar servicios primero");
	}


    @Override
    public int getTipo() {
        return 2;
    }
}
