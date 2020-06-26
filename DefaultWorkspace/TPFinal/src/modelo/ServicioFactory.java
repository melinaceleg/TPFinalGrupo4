package modelo;


/**Se encarga de instanciar las clases de cada tipo que implementan IServicio
 * y añadir dispositivos a su comportamiento
 */
public class ServicioFactory {

    /**Crea un tipo de servicio segun el String que recibe por parametro
     * PRE: no tiene precondiciones
     * POS: devolverá una nueva instancia de un objeto que impementa IServicio o null en caso contrario
     * @param tipo :Internet que desea contratar
     * @return nuevo :objeto que implementa Iservicio
     */
    public static IServicio crearServicio(String tipo) {
        IServicio s = null;
        if (tipo.equalsIgnoreCase("Internet100"))
            s = new Internet100();
        else if (tipo.equalsIgnoreCase("Internet500"))
            s = new Internet500();
        return s;
    }

    /**PRE:Servicio debe ser distinto de null
     * POS:devuelve instancia con un nuevo decorator agregado si coincide con tipo
     * @param servicio :objeto que implementa IServicio
     * @param tipo :Servicio que se desea contratar
     * @return objeto :que implementa IServicio con un dispositivo añadido a su comportamiento
     */
    public static IServicio agregarDispositivo(IServicio servicio, String tipo) {
        IServicio d = null;
        if (tipo.equalsIgnoreCase("Celular"))
            d = new DecoratorCelular(servicio);
        else if (tipo.equalsIgnoreCase("Telefono"))
            d = new DecoratorTelefono(servicio);
        else if (tipo.equalsIgnoreCase("TV"))
            d = new DecoratorTV_Cable(servicio);
        
        return d;
    }
}
