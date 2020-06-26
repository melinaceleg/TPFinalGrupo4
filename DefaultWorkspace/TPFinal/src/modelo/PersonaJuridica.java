package modelo;

import java.util.Observable;

/**Tipo abonado Jurídico
 */
public class PersonaJuridica extends Abonado {

	public PersonaJuridica(String nombre, String dni) {
		super(nombre, dni);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double cheque() {
		return 1.15;
	}

	@Override
	public double efectivo() {
		// TODO Auto-generated method stub
		return 0.9;
	}

	@Override
	public double tarjeta() {
		// TODO Auto-generated method stub
		return 1.2;
	}

	@Override
	public boolean cloneable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int nFacturasImpagas() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}