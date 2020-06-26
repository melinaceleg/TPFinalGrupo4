package Excepciones;

/**Excepción que es lanzada cuando un objeto de tipo Persona física posee comportamiento que no permite
 * emitir facturas, utilizando patron State.
 */
public class ImposibleEmitirFacturaException extends Exception {
    public ImposibleEmitirFacturaException(String arg0) {
        super(arg0);
    }
}
