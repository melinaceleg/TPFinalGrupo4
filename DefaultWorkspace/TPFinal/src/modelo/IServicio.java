package modelo;

import java.io.Serializable;

/**Comportamiento que implementan los servicios concretos
 */
public interface IServicio extends Serializable {
	double getPrecio();
	String toString();
}
