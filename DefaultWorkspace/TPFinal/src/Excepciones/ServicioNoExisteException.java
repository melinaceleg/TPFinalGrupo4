package Excepciones;

public class ServicioNoExisteException extends ImposibleCrearContratacion {
    private String servicioinexistente;
    public ServicioNoExisteException(String arg0, String servicio) {
        super(arg0);
        servicioinexistente=servicio;
    }
    public String getServicioInexistente() {
        
        return servicioinexistente;
    }

}
