package io;

import java.io.IOException;
import java.util.ArrayList;


/**Interfase de comportamiento para la lectura de datos
 * @param <T>
 */
public interface IReader<T> {
	public void abrirInput() throws IOException ;
	public ArrayList<T> leer() throws ClassNotFoundException, IOException;
	
}
