package modelo;

/**Clase que permite comunicación entre Controlador y Modelo
 * @param <T>
 */
public class EstadoMensaje<T> {
	private String mensaje;
	private T objeto;

	public EstadoMensaje(T objeto, String mensaje) {
		this.mensaje = mensaje;
		this.objeto = objeto;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public T getObjecto() {
		return this.objeto;
	}
}