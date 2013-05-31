package swing.table_cell_renderer;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTable;

public final class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Test();
	}

	private Test() {
		JTable tab = new JTable(new DisksTableModel());
		tab.getColumnModel().getColumn(2).setCellRenderer(new MyCellRenderer());
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add(BorderLayout.CENTER, tab);
		frm.setSize(400, 400);
		frm.setVisible(true);
		frm.pack();
	}

}
