package swing.table_rendering;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class RenderDemo extends JPanel {
	public RenderDemo() {
		TableModel m = new TotalProxyTableModel(new BusinessTableModel(), 2, 0,
				"The total value of the column");
		CTable t = new CTable(m);
		t.setSpan(new TotalRowSpanModel(m));
		t.setTableColorModel(new FinanceColorModel(m));
		add(new JScrollPane(t));
	}

	public static void main(String[] argv) {
		RenderDemo layout = new RenderDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}
}
