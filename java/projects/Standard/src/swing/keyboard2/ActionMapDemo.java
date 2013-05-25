package swing.keyboard2;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ActionMapDemo extends JPanel {
	public ActionMapDemo() {
		Action a = new ActionMapEditor();
		JButton button = new JButton(a);
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"),
				"showActionScreen");
		getActionMap().put("showActionScreen", a);
		add(button);
	}

	public static void main(String[] argv) {
		ActionMapDemo demo = new ActionMapDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", demo);
		frm.pack();
		frm.setVisible(true);
	}

	class ActionMapEditor extends AbstractAction {
		public ActionMapEditor() {
			putValue(NAME, "Action Edit");
		}

		public void actionPerformed(ActionEvent ev) {
			JDialog dlg = new JDialog(JFrame.getFrames()[0]);
			ActionMapModel model = new ActionMapModel();
			dlg.getContentPane().add("Center",
					new JScrollPane(new JTable(model)));
			dlg.pack();
			dlg.setVisible(true);
		}
	}

	private static final String[] COLUMNS = {
			"Key", "Action"
	};

	class ActionMapModel extends AbstractTableModel {
		private InputMap input;
		private KeyStroke[] keys;

		public ActionMapModel() {
			input = ActionMapDemo.this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
			ActionMapDemo.this.getActionMap();
			keys = input.allKeys();
		}

		public Class<?> getColumnClass(int param) {
			return (String.class);
		}

		public int getColumnCount() {
			return (COLUMNS.length);
		}

		public String getColumnName(int param) {
			return (COLUMNS[param]);
		}

		public int getRowCount() {
			return (keys.length);
		}

		private String keyToString(KeyStroke key) {
			String s = key.toString();
			s = s.replaceAll("-R", "").replaceAll("-P", "")
					.replaceAll("keyChar ", "").replaceAll("keyCode", "");
			return (s);
		}

		public Object getValueAt(int row, int column) {
			if (column == 0) {
				return (keyToString(keys[row]));
			} else {
				return (input.get(keys[row]));
			}
		}

		public boolean isCellEditable(int param, int param1) {
			return (true);
		}

		public void setValueAt(Object obj, int row, int column) {
			if (column == 0) {
				Object label = input.get(keys[row]);
				input.remove(keys[row]);
				input.put(KeyStroke.getKeyStroke((String) obj), label);
			} else {
				input.put(keys[row], obj);
			}
		}
	}
}
