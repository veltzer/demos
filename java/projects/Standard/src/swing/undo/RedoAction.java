package swing.undo;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.undo.CannotRedoException;

@SuppressWarnings("serial")
final class RedoAction extends AbstractAction {
	private static final RedoAction INSTANCE = new RedoAction();

	private RedoAction() {
		super("Redo", new ImageIcon(UndoAction.class.getResource("Redo24.gif")));
		setEnabled(false);
	}

	public static RedoAction getInstance() {
		return INSTANCE;
	}

	public void actionPerformed(ActionEvent ev) {
		try {
			UndoAction.getManager().redo();
		} catch (CannotRedoException e) {
			// System.out.println("Unable to redo: " + ex);
			throw new RuntimeException(e);
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
