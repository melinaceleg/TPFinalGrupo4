package modelo;


/**Crea instancias de clases extendidas de Abonado recibiendo el dni, nombre y tipo de Persona. */
public class AbonadoFactory {

    /**
     * @param dni numero de identificacion del abonado
     * @param nombre de Abonado
     * @param tipo Persona Juridica o Fisica
     * @return nueva instancia de clase extendida de Abonado
     */
    public static Abonado crear(String dni, String nombre, String tipo) {
        Abonado nuevo = null;
        if (tipo.equalsIgnoreCase("Persona Juridica"))
            nuevo = new PersonaJuridica(nombre, dni);
        else if (tipo.equalsIgnoreCase("Persona Fisica"))
            nuevo = new PersonaFisica(nombre, dni);

        return nuevo;
    }
}
