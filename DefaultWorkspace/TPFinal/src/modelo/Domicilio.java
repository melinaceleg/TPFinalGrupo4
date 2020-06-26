package modelo;

import java.io.Serializable;

/**Clase Domicilio de una contratación
 */
public class Domicilio implements Serializable {
	private String lugar;


	public Domicilio(String lugar) {
		super();
		this.lugar = lugar;
	}


	public String getLugar() {
		return lugar;
	}

	@Override
	public String toString() {
		return "Domicilio= "+lugar;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lugar == null) ? 0 : lugar.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Domicilio other = (Domicilio) obj;
		if (lugar == null) {
			if (other.lugar != null)
				return false;
		} else if (!lugar.equals(other.lugar))
			return false;
		return true;
	}


}
