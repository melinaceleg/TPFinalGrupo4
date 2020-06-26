package modelo;


/**
 * Agrega funcionalidad dispositivo TV_Cable
 */
public class DecoratorTV_Cable extends DecoratorServicio {

	public DecoratorTV_Cable(IServicio encapsulado) {
		super(encapsulado);
		// TODO Auto-generated constructor stub
		precioAdicional = 250;

	}

	@Override
	public double getPrecio() {
		return encapsulado.getPrecio() + precioAdicional;
	}

	@Override
	public String toString() {
		return this.encapsulado.toString() + "\nTV_Cable = $" + this.precioAdicional;
	}

}
