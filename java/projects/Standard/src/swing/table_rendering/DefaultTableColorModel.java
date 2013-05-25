package swing.table_rendering;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTable;
import javax.swing.UIManager;

public class DefaultTableColorModel implements TableColorModel {
	private static Color background;
	private static Color foreground;
	private static Color selectedBackground;
	private static Color selectedForeground;

	public DefaultTableColorModel() {
		updateColors();
		// listen to UI change events so we can update the colors if the PLAF is
		// changed dynamically
		UIManager.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				updateColors();
			}
		});
	}

	private void updateColors() {
		JTable table = new JTable();
		background = table.getBackground();
		foreground = table.getForeground();
		selectedBackground = table.getSelectionBackground();
		selectedForeground = table.getSelectionForeground();
	}

	public Color getBackground(int row, int column) {
		return (background);
	}

	public Color getForeground(int row, int column) {
		return (foreground);
	}

	public Color getSelectedBackground(int row, int column) {
		return (selectedBackground);
	}

	public Color getSelectedForeground(int row, int column) {
		return (selectedForeground);
	}
}
