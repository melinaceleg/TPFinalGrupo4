package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.Observable;
import java.util.Observer;

import Excepciones.ImposibleCrearContratacion;
import Excepciones.ImposibleDarDeBajaException;
import Excepciones.ImposibleEmitirFacturaException;

/**
 * Abonado que posee una lista de contrataciones y facturas
 */
public abstract class Abonado implements Observer, IPagarFactura, I_Coleccion_Factura,Serializable {
	protected String nombre;
	protected String dni;

	/**
	 * @aggregation composite
	 */
	protected ArrayList<Contratacion> contrataciones;
	protected ArrayList<Factura> facturas;

	/**
	 * Crea una instancia de abonado e inicializa la lista de contrataciones
	 * 
	 * @param nombre : nombre de abonado
	 * @param dni    : dni de abonado
	 */
	public Abonado(String nombre, String dni) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.contrataciones = new ArrayList<Contratacion>();
		this.facturas = new ArrayList<Factura>();
	}

    /**
     * @param medioPago válido correspondiente
     * @param f factura a pagar distinta de null
     * @throws ImposibleEmitirFacturaException :si no puede emitir factura debido a su estado (para abonado físico)
     * PRE: medioPago distinto null, factura distinto null
     * POS: -
     */
    public void pagarFactura(String medioPago, Factura f) throws ImposibleEmitirFacturaException {
		f.transaccion(medioPago, this);
	}

    /**
     * @param contrato : de tipo Contratacion a agregarse
     * @throws ImposibleCrearContratacion : Lanza si estado siendo abonado físico no permite agregar contrataciones
     *PRE: contrato distinto null
     * POS: -
     */
    public void contratarServicio(Contratacion contrato) throws ImposibleCrearContratacion {
		this.agregarContratacion(contrato);
	}

    /**
     * @param contrato : contrato a dar de baja
     * @throws ImposibleDarDeBajaException : si el contrato no puede ser dado de baja (Abonado físico en MorosoState)
     *PRE: contrato distinto null
     *POS: -
     */
    public void darDeBaja(Contratacion contrato) throws ImposibleDarDeBajaException {
		this.quitarContratacion(contrato);

	}

    /**
     * @param contrato contrato a quitar
     * @throws ImposibleDarDeBajaException : se lanza excepción si el abonado es de tipo persona física con MorosoState
     * PRE: contrato distinto null
     * POS: -
     */
    private void quitarContratacion(Contratacion contrato) throws ImposibleDarDeBajaException {
		if (this.contrataciones.size() > 0)
			this.contrataciones.remove(contrato);
		else
			throw new ImposibleDarDeBajaException("No posee contrataciones");

	}

	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}

	public ArrayList<Contratacion> getContrataciones() {
		return contrataciones;
	}

	public void agregarContratacion(Contratacion contrato) {
		this.contrataciones.add(contrato);
	}

	public abstract boolean cloneable();

	@Override
	public String toString() {
		return "Nombre: " + this.nombre + "\tDNI: " + this.dni;
	}
         int getTipo() {
             return 3;
         }

	@Override
	public void agregarFactura(Factura f) {
		if (f != null)
		this.facturas.add(f);
	}

	@Override
	public void quitarFactura(Factura f) {
		this.facturas.remove(f);

	}


    /**
     * @param domicilio : String domicilio a buscar
     * @return true existe domicilio en la lista de contrataciones, false no existe
     * PRE: domicilio distinto null
     * POS: devuelve booleano luego de la búsqueda
     */
    public boolean existeDomicilio(String domicilio)
	{
		Iterator<Contratacion> itContr = null;
		ArrayList<Contratacion> contrataciones = null;
		Boolean noExiste=true;

		Contratacion contratacion = null;
		contrataciones = this.getContrataciones();
		if (contrataciones != null) {
			itContr = contrataciones.iterator();
			while (itContr.hasNext() && noExiste == true) {
				contratacion = itContr.next();
				if (contratacion.getDomicilio().getLugar().equalsIgnoreCase(domicilio) == true) {
					noExiste = false;
				}
			}
		}
		return noExiste;
	}

	@Override
	public double cheque() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double efectivo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double tarjeta() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Factura crearFactura() {
		Factura f=null;
		if (this.contrataciones.size() > 0) {
			f = new Factura(this);
		}
		return f;
	}

    /**
     * @param pagada : de tipo booleano que especifica si se desea buscar facturas pagadas(true) o no pagadas (false)
     * @return lista de facturas que cumplen con el parametro
     * PRE: pagada inicializada en algun valor
     * POS: lista de facturas o null.
     */
    public Iterator<Factura> getFacturas(boolean pagada) {
		Iterator<Factura> it = this.facturas.iterator();
		ArrayList<Factura> lista = new ArrayList<Factura>();
		Factura factura;
		while (it.hasNext()) {
			factura = (Factura) it.next();
			if (factura.isEstadoDePago() == pagada)
				lista.add(factura);
		}
		return lista.iterator();
	}

    /**Envía parametro de tipo de facturas a listar: no pagadas
     * @return lista de facturas
     * PRE: -
     * POS: lista de facturas o null
     */
    public Iterator<Factura> getFacturasPendientes() {
		return this.getFacturas(false);
	}

    /**Envía parametro de tipo de facturas a listar: pagadas
     * @return lista de facturas
     * PRE: -
     * POS: lista de facturas o null
     */
    public Iterator<Factura> getFacturasPagadas() {
		return this.getFacturas(true);
	}

	/**
	 * Se hace un clon de tipo factura
	 * PRE: objeto factura distinto de null
	 * POS:retorna una nueva instancia clonada de factura o null si lanzó excepción
	 * 
	 * @param factura : objeto de tipo factura
	 * @return : clon de tipo factura
	 */
	private Factura ClonarUnaFactura(Factura factura) throws CloneNotSupportedException {
		Factura clon = null;
                clon = (Factura) factura.clone();


		return clon;
	}
	
	/**
	 * Se realiza la clonación de una factura seleccionada de la vista. PRE: No hay
	 * precondiciones POS: Se agrega a la lista de facturas de la clase una nueva
	 * instancia de factura clonada. De lo contrario se mostrará un MessageDialog
	 * 
	 */
	public ArrayList<Factura> clonarFacturas() throws CloneNotSupportedException {
		Factura clon = null;
		ArrayList<Factura> nuevaLista = null;
		Iterator<Factura> it = facturas.iterator();
                if (it.hasNext())		   
		{      
			nuevaLista = new ArrayList<Factura>();
                        while (it.hasNext()) {
                        clon = ClonarUnaFactura(it.next());
			if (clon != null) {
				nuevaLista.add(clon);
                        }
			}
		}
                
                return nuevaLista;
        }
		

	}
	