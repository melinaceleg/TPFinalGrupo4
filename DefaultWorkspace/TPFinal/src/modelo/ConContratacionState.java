package modelo;

/**Comportamiento asignado por State "con contratación" para un abonado Físico
 */
public class ConContratacionState implements State{
	private PersonaFisica a;
	public ConContratacionState(PersonaFisica a) {
		this.a=a;}

	@Override
	public void pagarFactura(String medioPago,Factura f) {
		f.transaccion(medioPago, a);
	}

	@Override
	public void contratarServicio(Contratacion contrato) {
		a.agregarContratacion(contrato);
	}

	@Override
	public void darDeBaja(Contratacion contrato) {
		if(a.getContrataciones().isEmpty()) {
			a.setEstado(new SinContratacionState(a));
		}
	}
        
    public int getTipo() {
        return 0;
    }

}
