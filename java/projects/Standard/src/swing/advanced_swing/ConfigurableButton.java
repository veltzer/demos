package swing.advanced_swing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class ConfigurableButton extends JButton {
	public void configurePropertiesFromAction(Action action) {
		super.configurePropertiesFromAction(action);
		Object pressed = action.getValue("PRESSED_ICON");
		if (pressed != null) {
			setPressedIcon((Icon) pressed);
		}
	}

	protected PropertyChangeListener createActionPropertyChangeListener(Action a) {
		return new MultiPropertyChangeListener(
				super.createActionPropertyChangeListener(a));
	}

	class MultiPropertyChangeListener implements PropertyChangeListener {
		private PropertyChangeListener parent;

		public MultiPropertyChangeListener(PropertyChangeListener iparent) {
			parent = iparent;
		}

		public void propertyChange(PropertyChangeEvent e) {
			parent.propertyChange(e);
			if (e.getPropertyName().equals("PRESSED_ICON")) {
				setPressedIcon((Icon) e.getNewValue());
			}
		}
	}
}
