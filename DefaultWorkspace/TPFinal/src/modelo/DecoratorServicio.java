package modelo;

import java.io.Serializable;

/**
 * Decoradora que agrega funcionalidades al objeto encapsulado que implementa
 * IServicio
 */
public abstract class DecoratorServicio implements IServicio,Serializable {
	protected double precioAdicional;
	protected IServicio encapsulado;

	public DecoratorServicio(IServicio encapsulado) {
		this.encapsulado = encapsulado;
	}
}
