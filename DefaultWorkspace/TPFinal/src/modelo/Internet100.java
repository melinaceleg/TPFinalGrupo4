package modelo;


public class Internet100 implements IServicio {
    private final double precio=850;
    

	@Override
	public double getPrecio() {
		return this.precio;
	}

	@Override
	public String toString() {
		return "Internet100 = $" + this.precio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Internet100 other = (Internet100) obj;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		return true;
	}
	
}
