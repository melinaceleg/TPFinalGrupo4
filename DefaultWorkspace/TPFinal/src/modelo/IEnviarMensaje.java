package modelo;

/**Interfaz de comportamiento para enviar mensajes entre clases del paquete Modelo y Controlador
 */
public interface IEnviarMensaje {

		void enviar(EstadoMensaje<?> mensaje);


}
