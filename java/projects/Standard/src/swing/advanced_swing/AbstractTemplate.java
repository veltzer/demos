package swing.advanced_swing;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public abstract class AbstractTemplate implements Template {
	private JComponent panel;

	public AbstractTemplate() {
		panel = createBasicPanel();
	}

	protected JComponent createBasicPanel() {
		return (new JPanel());
	}

	public JComponent getView() {
		return (panel);
	}

	public void setAction(String location, Action action) {
		setComponents(location, new JComponent[] {
			new JButton(action)
		});
	}

	protected AbstractButton createButton(Action action) {
		return (new JButton(action));
	}

	public void setActions(String location, Action[] actions) {
		JComponent[] arr = new JComponent[actions.length];
		for (int iter = 0; iter < actions.length; iter++) {
			arr[iter] = createButton(actions[iter]);
		}
		setComponents(location, arr);
	}

	public abstract void setComponents(String location, JComponent[] components);
}
