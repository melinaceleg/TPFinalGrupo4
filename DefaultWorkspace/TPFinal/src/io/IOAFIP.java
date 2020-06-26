package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import modelo.Factura;

/**Salida de datos a disco que representa un reporte de facturas de la lista de abonados
 */
public class IOAFIP implements IWriter<Factura> {
	FileOutputStream outputAFIP;
	ObjectOutputStream objetoAFIP;
	

	public IOAFIP() {
		// TODO Auto-generated constructor stub
	}

	
	public void abrirOutput() throws IOException {
		outputAFIP=new FileOutputStream("ReporteAFIP.dat");
		objetoAFIP=new ObjectOutputStream(outputAFIP);
	}
	public void cerrarOutput() throws IOException {
		if(objetoAFIP!=null) {
			objetoAFIP.close();
		}
	}


    /**
     * @param o lista de facturas
     * @throws ClassNotFoundException : se propaga excepci�n si la clase tra�da de archivo no co�ncide con la declarada
     * @throws IOException : se propaga excepci�n si hubo un error con el archivo
     * PRE: lista de facturas distinta de null
     * POS: se escribe exit�samente en archivo
     */
    @Override
	public void escribir(ArrayList<Factura> o) throws ClassNotFoundException, IOException {
		objetoAFIP.writeObject(o);
		
	}
	
}
