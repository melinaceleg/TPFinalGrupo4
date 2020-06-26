package modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

import Excepciones.DomicilioRepetidoException;
import Excepciones.ImposibleCrearAbonadoException;
import Excepciones.ImposibleCrearContratacion;
import Excepciones.ImposibleEmitirFacturaException;
import Excepciones.ServicioNoExisteException;
import util.Util;

public class GestionAbonados {

	private ArrayList<Abonado> abonados;
	private Semaphore semaforo;
	/**
	 * Crea una instancia de abonados y semaforo
	 * 
	 */
	public GestionAbonados()
	{
		this.abonados = new ArrayList<Abonado>();
		this.semaforo = new Semaphore(1);
	}

	/**
	 * devuelve la lista de abonados
	 * PRE: no tiene
	 * POS: no tiene
	 * 
	 * @return ArrayList<Abonado>
	 */
	public ArrayList<Abonado> getAbonados() {
		return abonados;
	}

	/**
	 * Recibe una lista de abonados y guarda la referenccia
	 * PRE: no tiene
	 * POS: no tiene 
	 * 
	 * @param abonados de tipo ArrayList<Factura>
	 */
	public void setAbonados(ArrayList<Abonado> abonados) {
		this.abonados = abonados;
		
	}
	
	/**
	 * Verifica si existe el dni en la lista de abonados PRE: dni distinto de null
	 * POS: si encuentra coincidencia lanzará excepcion de tipo
	 * ImposibleCrearAbonadoException
	 * 
	 * @param dni : String que se desea ingresar en abonado
	 * @throws ImposibleCrearAbonadoException : si el abonado se encuentra en la
	 *                                        lista :" Ya existe el abonado "
	 */
	void existeAbonado(String dni) throws ImposibleCrearAbonadoException {
		Iterator<Abonado> a = abonados.iterator();

		while (a.hasNext()) {
			if (a.next().getDni().equals(dni))
				throw new ImposibleCrearAbonadoException("Ya existe el abonado");
		}
	}

	public void agregarAbonado(String dni, String nombre, String tipo) {
		Abonado a = AbonadoFactory.crear(dni, nombre, tipo);
		this.abonados.add(a);
	}

	/**
	 * Agrega un nuevo abonado a la lista de abonados PRE: No hay precondiciones
	 * POS: agrega un nuevo abonado a la lista de abonados si el nombre o el dni son
	 * distintos de null, de lo contrario mostrará un MessageDialog.
	 */
	public void agregarNuevoAbonado(IEnviarMensaje enviar, String nombre, String dni, String tipo) {
		
		if (!this.semaforo.tryAcquire())
			return;
		
		if (nombre != null && nombre.length() > 0) {
			if (dni != null && dni.length() > 0) {
				try {
					existeAbonado(dni);
					this.agregarAbonado(dni, nombre, tipo);
					
					enviar.enviar(new EstadoMensaje<Iterator<Abonado>>(this.abonados.iterator(), "ABONADOS"));
				} catch (ImposibleCrearAbonadoException e) {					
					enviar.enviar(new EstadoMensaje<String>(e.getMessage(), "ERROR"));
				}

			} else {				
				enviar.enviar(new EstadoMensaje<String>("DNI incorrecto", "ERROR"));
			}
		} else {
			enviar.enviar(new EstadoMensaje<String>("Nombre incorrecto", "ERROR"));
		}
		this.semaforo.release();
	}


	/**
	 * Verifica segun un tipo de dato String que no se repita en la lista de
	 * contrataciones un domicilio con el mismo contenido en el atributo
	 * 
	 * @param domicilio : tipo de dato String distinto de null
	 * @return : devuelve true si no hay coincidencias, de lo contrario false
	 * 
	 */
	private boolean noExisteDomicilioRepetido(String domicilio) {
		boolean noExiste = true;
		Iterator<Abonado> it = this.abonados.iterator();
		Abonado a;
		while (it.hasNext() && noExiste == true) {
			a= it.next();
			noExiste=a.existeDomicilio(domicilio);
		}

		return noExiste;
		
	}
	/**
	 * Crea una contratacion con el domicilio y el tipo de contratacion siempre que
	 * no haya un domicilio repetido
	 * 
	 * @param domicilio : tipo de dato String distinto de null
	 * @param tipo : tipo de dato String distinto de null
	 * @return : devuelve una referencia a una Contratacion
	 * 
	 * @throws ImposibleCrearContratacion : el domicilio ya se encuentra en uso
	 */
	private Contratacion agregarNuevoDomicilioConServicio(String domicilio, String tipo)
			throws ImposibleCrearContratacion {
		Contratacion nueva = null;

		if (domicilio != null && domicilio.length() > 0) {
			IServicio servicio = ServicioFactory.crearServicio(tipo);
			if (servicio != null) {
				if (noExisteDomicilioRepetido(domicilio)) /// metodo de controladora
				{
					Domicilio dom = new Domicilio(domicilio);
					nueva = new Contratacion(dom, servicio);
				} else {
					throw new DomicilioRepetidoException("Domicilio Repetido", domicilio);
				}

			} else {
				throw new ServicioNoExisteException("Servicio Inexistente", tipo);
			}
		} else
			throw new ImposibleCrearContratacion("No se puede crear la Contratación");

		return nueva;
	}

	

	/**
	 * Se agrega una nueva contratacion a la lista de contrataciones de un abonado
	 * PRE: No hay precondiciones POS: Si el abonado es distinto de null y C es
	 * distinto de null se agregará la nueva contratación de lo contrario se realiza
	 * una captura de la excepción propagada.
	 */
	public void agregarContratacion(Abonado a,IEnviarMensaje mensaje, String domicilio, String tipo) {
		Contratacion C;
		if (a != null) {
			try {
				C = agregarNuevoDomicilioConServicio(domicilio, tipo);
				a.contratarServicio(C);

				mensaje.enviar(new EstadoMensaje<Iterator<Contratacion>>(a.getContrataciones().iterator(), "CONTRATACIONES"));
			} catch (ServicioNoExisteException e) {
				mensaje.enviar(new EstadoMensaje<String>(e.getMessage() + " ," + e.getServicioInexistente(), "ERROR"));
			} catch (DomicilioRepetidoException e) {
				mensaje.enviar(new EstadoMensaje<String>(e.getMessage() + " " + e.getDomicilioRepetido(), "ERROR"));
			} catch (ImposibleCrearContratacion e) {
				mensaje.enviar(new EstadoMensaje<String>(e.getMessage(), "ERROR"));
			}
		} else {
			mensaje.enviar(new EstadoMensaje<String>("Debe seleccionar un abonado", "ERROR"));
		}
	}


	/**
	 * Agrega un nuevo servicio con su tipo a contratacion 
	 * PRE: mensaje debe ser distinto de null
	 * POS: le cede la responsabiliad de agregar la contratacion al objeto Contratacion
	 * 
	 * @param mensaje : tipo de dato IEnviarMensaje
	 * @param contratacion : tipo de dato Contratacion
	 * @param tipo : tipo de dato String
	 */
	
	public void agregarNuevoServicio(IEnviarMensaje mensaje, Contratacion contratacion, String tipo) {
		if (contratacion != null) {
			contratacion.agregarServicio(tipo);
		}
		 else {
			mensaje.enviar(new EstadoMensaje<String>("Debe seleccionar un domicilio", "ERROR"));
		}
	}

	/**
	 * Agrega una nueva factura a la lista de facturas segun los datos provenientes
	 * de la lista PRE: No hay precondiciones POS: se añade una nueva factura,si la
	 * factura que se desea añadir ya fue anteriormente creada se mostrará un
	 * MessageDialog, si el abonado es null o no posee contrataciones no se generará
	 * la factura
	 * @throws ImposibleEmitirFacturaException 
	 */

	public void agregarFactura(IEnviarMensaje mensaje,Abonado abonado) {
		if (abonado != null) {
			if (abonado.getContrataciones().size() != 0) 
				abonado.agregarFactura(abonado.crearFactura());
		}
	}		

	/**
	 * Crea una nueva factura para cada abonado
	 * PRE: mensaje debe ser distinto de null
	 * POS: cada abonado cambiara su estado y tendrá una nueva factura
	 * 
	 * @param mensaje : tipo de dato IEnviarMensaje
	 */
	public void avanzarMes(IEnviarMensaje mensaje) {
		Iterator<Abonado> it = this.abonados.iterator();
		Abonado abonado;
		while (it.hasNext()) {
			abonado = (Abonado) it.next();
			try
			{			
			this.agregarFactura(mensaje,abonado);
			}
			catch(Exception e) {
				
			}
		}
	}

	/**
	 * Clona todas las facturas de cada abonado y bloquea el metodo agregarNuevoAbonado
	 * PRE: reporteAFIP debe ser distinto de null
	 * POS: avisa a IReporteAFIP de los mensajes y de la finalizacion de la ejecucion
	 * 
	 * @param reporteAFIP : tipo de dato IReporteAFIP
	 * 
	 * @throws ClassNotFoundException : no pudo cargar la clase
	 * @throws IOException            : fallo en la operacion de input/output
	 */
	public void clonarFacturas(IReporteAFIP reporteAFIP) throws ClassNotFoundException, IOException {		
		try {
			this.semaforo.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<Abonado> it = this.abonados.iterator();
		Abonado abonado;
		ArrayList<Factura> facturas=null;
		ArrayList<Factura> totales = null;
		while (it.hasNext()) {
			Util.espera(1000);
			abonado = it.next();
            try {
                facturas = abonado.clonarFacturas();
            } catch (CloneNotSupportedException e) {
            }
            if (facturas != null && facturas.size()>0)
			{
			if (totales == null)
			{
				totales = facturas;
			}
			else
				totales.addAll(facturas);
			reporteAFIP.mandarReporte(abonado.toString() + " -> " + facturas.size() + " facturas\n");
			}
		}
		if (totales != null)
		{
		reporteAFIP.reporteSalida(totales);
		}
		this.semaforo.release();
	}
}