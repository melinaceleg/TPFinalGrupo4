package io;

import java.io.IOException;
import java.util.ArrayList;

/**Interfase de comportamiento para la escritura de datos
 * @param <T>
 */
public interface IWriter<T> {
	public void escribir(ArrayList<T> o) throws ClassNotFoundException, IOException;
	public void abrirOutput() throws IOException;


}
