package swing.advanced_swing;

import java.io.File;
import java.lang.reflect.Proxy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class ReflectionProxyDemo extends JPanel {
	public ReflectionProxyDemo() {
		FileTableModel m = new FileTableModel();
		m.setDirectory(new File("."));
		TableModel model = (TableModel) Proxy.newProxyInstance(
				TableModel.class.getClassLoader(), new Class[] {
					TableModel.class
				}, new ReflectionProxy(m));
		add(new JScrollPane(new JTable(model)));
	}

	public static void main(String[] argv) {
		ReflectionProxyDemo layout = new ReflectionProxyDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}
}
