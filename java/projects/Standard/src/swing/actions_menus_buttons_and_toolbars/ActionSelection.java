package swing.actions_menus_buttons_and_toolbars;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class ActionSelection extends JPanel {
	public ActionSelection() {
		super(new BorderLayout());
		JTable table = new JTable(new Object[][] {
				{
						"Amy", "Fowler"
				}, {
						"James", "Gosling"
				}, {
						"Jeff", "Dinkins"
				}, {
						"Steve", "Wilson"
				}
		}, new Object[] {
				"Given Name", "Surname"
		});
		add("Center", new JScrollPane(table));
		add("South", new JButton(new RemoveAction(table)));
	}

	public static void main(String[] argv) {
		ActionSelection selection = new ActionSelection();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", selection);
		frm.pack();
		frm.setVisible(true);
	}
}
