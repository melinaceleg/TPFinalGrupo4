package modelo;

import java.io.Serializable;

/**Clase que genera la contratación de un servicio, es el vinculo entre un abonado y un servicio,
 * tiene un único nro de identificación y domicilio.
 */
public class Contratacion implements Serializable {
	private Domicilio domicilio;
	private int nroIdentificacion;
	private IServicio servicio;


    /**
     * Se crea una nueva contratación
     * PRE:domicilio distinto de null, servicio distinto de null
     * POS:nueva instancia de Contratacion
     * @param domicilio de un abonado
     * @param servicio 
     */
    public Contratacion(Domicilio domicilio, IServicio servicio) {
        this.domicilio = domicilio;
        this.setIdentificacion();
        this.servicio = servicio;
    }


    public Domicilio getDomicilio() {
		return domicilio;
	}
	public int getNroIdentificacion() {
		return nroIdentificacion;
	}
	public IServicio getServicio() {
		return servicio;
	}
        public void setServicio(IServicio servicio) 
        {
           this.servicio = servicio;          
        }

    /**Asigna a nroIdentificacion el numero hashcode formado por el Hashcode de domicilio y el servicio, no genera repetidos en una instancia de ejecución
     * PRE: No hay precondiciones
     * POS: Se asigna al atributo nroIdentificacion un numero entero
     */
    private void setIdentificacion() {
            nroIdentificacion=this.hashCodeID();
        }
        
	@Override
	public String toString() {
		return domicilio.toString() + " | Nro: " + nroIdentificacion + " " + "\n" + this.servicio.toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domicilio == null) ? 0 : domicilio.hashCode());
		result = prime * result + nroIdentificacion;
		result = prime * result + ((servicio == null) ? 0 : servicio.hashCode());
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
		Contratacion other = (Contratacion) obj;
		if (domicilio == null) {
			if (other.domicilio != null)
				return false;
		} else if (!domicilio.equals(other.domicilio))
			return false;
		if (nroIdentificacion != other.nroIdentificacion)
			return false;
		if (servicio == null) {
			if (other.servicio != null)
				return false;
		} else if (!servicio.equals(other.servicio))
			return false;
		return true;
	}

    /**Agrega un nuevo dispositivo a la contratación
     * @param tipo de servicio
     * PRE: String que coincida con un servicio
     * POS: -
     */
    public void agregarServicio(String tipo)
  {
		IServicio servicio = this.getServicio();

		this.setServicio(ServicioFactory.agregarDispositivo(servicio, tipo));

  }


    /**añade de ID el HashCode formado por el resto de los atributos de la clase
     * @return entero Hashcode
     * PRE: -
     * POS: Entero Positivo único
     */
    public int hashCodeID() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domicilio == null) ? 0 : domicilio.hashCode());
		result = prime * result + ((servicio == null) ? 0 : servicio.hashCode());
		result = result & 0x7fffffff;
		return result;
	}


}
