package vista;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import modelo.State;

/**Implementa colores en  las celdas del margen inferior para diferenciar los estados 
 * de los abonados de tipo "Persona física"
 */
public class ColorRenderer extends DefaultListCellRenderer {

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (value instanceof State) {
			State abonado = (State) value;

			if (abonado.getTipo() == 0) {
				c.setBackground(Color.white);
			} else if (abonado.getTipo() == 1) {
				c.setBackground(Color.red);
			} else
				c.setBackground(Color.yellow);
		}
		return c;
	}
}
