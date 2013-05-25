package swing.undo;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.undo.CannotRedoException;

@SuppressWarnings("serial")
class RedoAction extends AbstractAction {
	private static RedoAction INSTANCE = new RedoAction();

	private RedoAction() {
		super("Redo", new ImageIcon(UndoAction.class.getResource("Redo24.gif")));
		setEnabled(false);
	}

	public static RedoAction getInstance() {
		return INSTANCE;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			UndoAction.getManager().redo();
		} catch (CannotRedoException ex) {
			System.out.println("Unable to redo: " + ex);
			ex.printStackTrace();
		}
		update();
		UndoAction.getInstance().update();
	}

	void update() {
		if (UndoAction.getManager().canRedo()) {
			setEnabled(true);
			putValue(Action.NAME, UndoAction.getManager()
					.getRedoPresentationName());
		} else {
			setEnabled(false);
			putValue(Action.NAME, "Redo");
		}
	}
}
