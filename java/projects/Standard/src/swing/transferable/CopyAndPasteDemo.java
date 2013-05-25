package swing.transferable;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.TransferHandler;

@SuppressWarnings("serial")
public class CopyAndPasteDemo extends JPanel {
	abstract static class TransferableAction extends AbstractAction implements
			PropertyChangeListener {
		private JComponent current;

		public TransferableAction() {
			setEnabled(false);
			KeyboardFocusManager.getCurrentKeyboardFocusManager()
					.addPropertyChangeListener("focusOwner", this);
		}

		private void exportToClipboard(int type) {
			current.getTransferHandler().exportToClipboard(current,
					current.getToolkit().getSystemClipboard(), type);
		}

		protected void cut() {
			exportToClipboard(TransferHandler.MOVE);
		}

		protected void copy() {
			exportToClipboard(TransferHandler.COPY);
		}

		protected void paste() {
			current.getTransferHandler()
					.importData(
							current,
							current.getToolkit().getSystemClipboard()
									.getContents(null));
		}

		protected boolean enableAction(JComponent cmp) {
			return true;
		}

		public void propertyChange(PropertyChangeEvent evt) {
			Component cmp = KeyboardFocusManager
					.getCurrentKeyboardFocusManager().getFocusOwner();
			if (cmp instanceof JComponent) {
				if (((JComponent) cmp).getTransferHandler() != null) {
					current = (JComponent) cmp;
					setEnabled(enableAction(current));
					return;
				}
			}
			setEnabled((current != null) && enableAction(current));
		}
	}

	static class CopyAction extends TransferableAction {
		public CopyAction() {
			putValue(NAME, "Copy");
			putValue(SMALL_ICON,
					new ImageIcon(getClass().getResource("/Copy24.gif")));
		}

		public void actionPerformed(ActionEvent ev) {
			copy();
		}
	}

	static class CutAction extends TransferableAction {
		public CutAction() {
			putValue(NAME, "Cut");
			putValue(SMALL_ICON,
					new ImageIcon(getClass().getResource("/Cut24.gif")));
		}

		public void actionPerformed(ActionEvent ev) {
			cut();
		}
	}

	static class PasteAction extends TransferableAction {
		public PasteAction() {
			putValue(NAME, "Paste");
			putValue(SMALL_ICON,
					new ImageIcon(getClass().getResource("/Paste24.gif")));
		}

		protected boolean enableAction(JComponent cmp) {
			Transferable t = cmp.getToolkit().getSystemClipboard()
					.getContents(null);
			return (t != null)
					&& (cmp.getTransferHandler().canImport(cmp,
							t.getTransferDataFlavors()));
		}

		public void actionPerformed(ActionEvent ev) {
			paste();
		}
	}

	public CopyAndPasteDemo() {
		JFileChooser chooser = new JFileChooser();
		chooser.setControlButtonsAreShown(false);
		chooser.setDragEnabled(true);
		add(chooser);
		add(new JTextField("Try to copy and paste from/to here"));
	}

	private static JToolBar createToolBar() {
		JToolBar bar = new JToolBar();
		bar.add(new CutAction());
		bar.add(new CopyAction());
		bar.add(new PasteAction());
		return bar;
	}

	public static void main(String[] argv) {
		JFrame dlg = new JFrame();
		dlg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dlg.getContentPane().add("Center", new CopyAndPasteDemo());
		dlg.getContentPane().add("North", createToolBar());
		dlg.pack();
		dlg.setVisible(true);
	}
}
