package swing.advanced_swing;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

public class RightNavigationTemplate extends AbstractTemplate {
	private JComponent navigation;
	private Template nested;

	public RightNavigationTemplate(Template inested) {
		nested = inested;
		JComponent panel = getView();
		panel.setLayout(new BorderLayout());
		navigation = new Box(BoxLayout.Y_AXIS);
		panel.add(BorderLayout.WEST, navigation);
		panel.add(BorderLayout.CENTER, nested.getView());
	}

	public void setComponents(String location, JComponent[] components) {
		if (location.equals("navigation")) {
			navigation.removeAll();
			for (int iter = 0; iter < components.length; iter++) {
				navigation.add(components[iter]);
			}
			return;
		}
		nested.setComponents(location, components);
	}
}
