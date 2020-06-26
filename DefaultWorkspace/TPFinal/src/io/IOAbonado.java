package io;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import modelo.Abonado;

/**Entrada y Salida de datos de archivo implementada para objetos de tipo Abonado
 */
public class IOAbonado implements IReader<Abonado>,IWriter<Abonado> {
	private FileInputStream inputFile=null;
	private ObjectInputStream inputObject=null;
	private FileOutputStream outputFile=null;
	private ObjectOutputStream outputObject=null;
		
	
	public IOAbonado() {
		// TODO Auto-generated constructor stub
	}

		public void abrirInput() throws IOException {
			inputFile=new FileInputStream("Abonados.dat");
			inputObject =new ObjectInputStream(inputFile);
		}
		
		public void cerrarInput() throws IOException {
			if(inputFile!=null) {
				inputFile.close();
			}
		}

    /**Lectura de datos de archivo de abonados
     * @return lista de abonados
     * @throws ClassNotFoundException : si la clase no coincide con las declaradas propaga excepción
     * @throws IOException : Si existe un error en el archivo propaga excepción
     * PRE:-
     * POS: retorna lista de abonados
     */
    public ArrayList<Abonado> leer() throws ClassNotFoundException, IOException  {
			ArrayList<Abonado> abonados=null;
			if(inputFile!=null) {
				ArrayList<Abonado> readObject = (ArrayList<Abonado>)inputObject.readObject();
				abonados = readObject;
			}
			return abonados;
		}

			public void abrirOutput() throws IOException {
				outputFile=new FileOutputStream("Abonados.dat");
				outputObject=new ObjectOutputStream(outputFile);
			}
			public void cerrarOutput() throws IOException {
				if(outputObject!=null) {
					outputObject.close();
				}
			}

    /**Escribe una lista de abonados en el buffer de salida
     * @param o : lista de abonados
     * @throws IOException : propaga excepción si hubo un error con el archivo
     * PRE: lista de abonados distinta de null
     * POS: se escribe exitosamente en archivo
     */
    public void escribir(ArrayList<Abonado> o) throws IOException {
				if(outputObject!=null) {
					outputObject.writeObject(o);
				}
			}
}

