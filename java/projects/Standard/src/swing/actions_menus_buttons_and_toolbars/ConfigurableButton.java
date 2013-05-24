package swing.actions_menus_buttons_and_toolbars;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
public class ConfigurableButton extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void configurePropertiesFromAction(Action action) {
        super.configurePropertiesFromAction(action);
        Object pressed = action.getValue("PRESSED_ICON");
        if (pressed != null) {
            setPressedIcon((Icon) pressed);
        }
    }


    protected PropertyChangeListener createActionPropertyChangeListener(Action a) {
        return new MultiPropertyChangeListener(super.createActionPropertyChangeListener(a));
    }


    class MultiPropertyChangeListener implements PropertyChangeListener {
        PropertyChangeListener parent;


        public MultiPropertyChangeListener(PropertyChangeListener parent) {
            this.parent = parent;
        }


        public void propertyChange(PropertyChangeEvent e) {
            parent.propertyChange(e);
            if (e.getPropertyName().equals("PRESSED_ICON")) {
                setPressedIcon((Icon) e.getNewValue());
            }
        }
    }
}

