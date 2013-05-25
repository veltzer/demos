package swing.performance;

import java.awt.Component;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class LoadingIndicatorDemo extends JPanel {
	public LoadingIndicatorDemo() {
		SlowFileTableModel m = new SlowFileTableModel();
		File directory = new File(".");
		m.setDirectory(directory);
		JTable table = new JTable(m);
		table.setDefaultRenderer(Icon.class, new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				super.getTableCellRendererComponent(table, "", isSelected,
						hasFocus, row, column);
				setIcon((Icon) value);
				return this;
			}
		});
		add(new JScrollPane(table));
	}

	public static void main(String[] argv) {
		LoadingIndicatorDemo layout = new LoadingIndicatorDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}
}
