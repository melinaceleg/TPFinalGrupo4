package modelo;


/**Agrega funcionalidad al servicio con el dispositivo Celular
 */
public class DecoratorCelular extends DecoratorServicio {

	public DecoratorCelular(IServicio encapsulado) {
		super(encapsulado);
                this.precioAdicional = 300;
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getPrecio() {
		return this.encapsulado.getPrecio() + this.precioAdicional;
	}

	@Override
	public String toString() {
		return this.encapsulado.toString() + "\nCelular = $" + this.precioAdicional;
	}
	
}
