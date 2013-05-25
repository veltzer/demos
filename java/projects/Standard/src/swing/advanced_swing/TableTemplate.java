package swing.advanced_swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TableTemplate extends AbstractTemplate {
	private JComponent buttons;

	public TableTemplate() {
		JComponent panel = getView();
		panel.setLayout(new BorderLayout());
		buttons = new JPanel(new FlowLayout());
		panel.add(BorderLayout.SOUTH, buttons);
	}

	public void setComponents(String location, JComponent[] components) {
		if (location.equals("table")) {
			getView().add(BorderLayout.CENTER, new JScrollPane(components[0]));
		}

		if (location.equals("buttons")) {
			buttons.removeAll();
			for (int iter = 0; iter < components.length; iter++) {
				buttons.add(components[iter]);
			}
			return;
		}
	}
}
