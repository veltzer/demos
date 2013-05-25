package swing.actions_menus_buttons_and_toolbars;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

public class ActionGroup implements Action {
	private Action[] actions;

	public ActionGroup(Action[] iactions) {
		actions = iactions;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		for (int iter = 0; iter < actions.length; iter++) {
			actions[iter].addPropertyChangeListener(listener);
		}
	}

	public Object getValue(String key) {
		return (actions[0].getValue(key));
	}

	public boolean isEnabled() {
		return (actions[0].isEnabled());
	}

	public void putValue(String key, Object value) {
		for (int iter = 0; iter < actions.length; iter++) {
			actions[iter].putValue(key, value);
		}
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		for (int iter = 0; iter < actions.length; iter++) {
			actions[iter].removePropertyChangeListener(listener);
		}
	}

	public void setEnabled(boolean b) {
		for (int iter = 0; iter < actions.length; iter++) {
			actions[iter].setEnabled(b);
		}
	}

	public void actionPerformed(ActionEvent ev) {
		for (int iter = 0; iter < actions.length; iter++) {
			actions[iter].actionPerformed(ev);
		}
	}
}
