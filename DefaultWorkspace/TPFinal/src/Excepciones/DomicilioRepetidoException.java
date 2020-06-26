package Excepciones;

public class DomicilioRepetidoException extends ImposibleCrearContratacion {
    private String DomicilioRepetido;
    
    public DomicilioRepetidoException(String arg0,String domicilioRepetido)
        {
            super(arg0);
            this.DomicilioRepetido=domicilioRepetido;
            
        }
    
    public String getDomicilioRepetido() {
        return DomicilioRepetido;
    }
    
}
