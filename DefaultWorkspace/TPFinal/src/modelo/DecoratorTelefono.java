package modelo;


/**
 * Agrega funcionalidad al servicio con el dispositivo Telefono
 */
public class DecoratorTelefono extends DecoratorServicio {

	public DecoratorTelefono(IServicio encapsulado) {
		super(encapsulado);
		// TODO Auto-generated constructor stub
		precioAdicional = 200;
	}

	@Override
	public double getPrecio() {
		return encapsulado.getPrecio() + precioAdicional;
	}

	@Override
	public String toString() {
		return this.encapsulado.toString() + "\nTelefono = $" + this.precioAdicional;
	}

}
