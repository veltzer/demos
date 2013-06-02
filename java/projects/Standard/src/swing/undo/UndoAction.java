package swing.undo;

import java.awt.event.ActionEvent;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

@SuppressWarnings("serial")
final class UndoAction extends AbstractAction {
	private List<UndoableEdit> script = new ArrayList<UndoableEdit>();
	private RecordAction record = new RecordAction();
	private StopAction stop = new StopAction();
	private PlayAction play = new PlayAction();
	private StoreAction store = new StoreAction();
	private LoadAction load = new LoadAction();
	private boolean recording = false;

	private static final UndoAction INSTANCE = new UndoAction();
	private UndoManager manager = new UndoManager();

	private UndoAction() {
		super("Undo", new ImageIcon(UndoAction.class.getResource("Undo24.gif")));
		setEnabled(false);
	}

	public static void add(UndoableEdit edit) {
		INSTANCE.manager.addEdit(edit);
		INSTANCE.update();
		RedoAction.getInstance().update();
		if (INSTANCE.recording) {
			INSTANCE.script.add(edit);
		}
	}

	static UndoManager getManager() {
		return INSTANCE.manager;
	}

	public static UndoAction getInstance() {
		return INSTANCE;
	}

	public void actionPerformed(ActionEvent ev) {
		try {
			getManager().undo();
		} catch (CannotUndoException e) {
			throw new RuntimeException(e);
		}
		update();
		RedoAction.getInstance().update();
	}

	void update() {
		if (getManager().canUndo()) {
			setEnabled(true);
			putValue(Action.NAME, getManager().getUndoPresentationName());
		} else {
			setEnabled(false);
			putValue(Action.NAME, "Undo");
		}
	}

	public Action getRecordAction() {
		return record;
	}

	class RecordAction extends AbstractAction {
		RecordAction() {
			super("Record", new ImageIcon(
					UndoAction.class.getResource("Movie24.gif")));
		}

		public void actionPerformed(ActionEvent ev) {
			recording = true;
			setEnabled(false);
			stop.setEnabled(true);
		}
	}

	public Action getStopAction() {
		return stop;
	}

	class StopAction extends AbstractAction {
		StopAction() {
			super("Stop", new ImageIcon(
					UndoAction.class.getResource("Stop24.gif")));
		}

		public void actionPerformed(ActionEvent ev) {
			recording = false;
			setEnabled(false);
			record.setEnabled(true);
		}
	}

	public Action getPlayAction() {
		return play;
	}

	class PlayAction extends AbstractAction {
		PlayAction() {
			super("Play", new ImageIcon(
					UndoAction.class.getResource("Play24.gif")));
		}

		public void actionPerformed(ActionEvent ev) {
			Iterator<UndoableEdit> iter = script.iterator();
			while (iter.hasNext()) {
				UndoableEdit edit = iter.next();
				edit.redo();
			}
		}
	}

	public Action getStoreAction() {
		return store;
	}

	class StoreAction extends AbstractAction implements ExceptionListener {
		StoreAction() {
			super("Store", new ImageIcon(
					UndoAction.class.getResource("Save24.gif")));
		}

		public void actionPerformed(ActionEvent ev) {
			try {
				JFileChooser chooser = new JFileChooser();
				if (JFileChooser.APPROVE_OPTION == chooser
						.showSaveDialog((JComponent) ev.getSource())) {
					XMLEncoder e = new XMLEncoder(new BufferedOutputStream(
							new FileOutputStream(chooser.getSelectedFile())));
					e.setExceptionListener(this);
					e.writeObject(script);
					e.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public void exceptionThrown(Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Action getLoadAction() {
		return load;
	}

	class LoadAction extends AbstractAction {
		LoadAction() {
			super("Load", new ImageIcon(
					UndoAction.class.getResource("Open24.gif")));
		}

		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent ev) {
			try {
				JFileChooser chooser = new JFileChooser();
				if (JFileChooser.APPROVE_OPTION == chooser
						.showOpenDialog((JComponent) ev.getSource())) {
					XMLDecoder d = new XMLDecoder(new BufferedInputStream(
							new FileInputStream(chooser.getSelectedFile())));
					script = (List<UndoableEdit>) d.readObject();
					d.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
