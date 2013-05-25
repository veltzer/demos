package swing.advanced_swing;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTable;

public final class TemplateNestingDemo {
	private Template template = new RightNavigationTemplate(new TableTemplate());

	private TemplateNestingDemo() {
		FileTableModel m = new FileTableModel();
		File directory = new File(".");
		m.setDirectory(directory);
		template.setComponents("table", new JComponent[] {
			new JTable(m)
		});
		template.setComponents("buttons", new JComponent[] {
				new JButton("Button 1"), new JButton("Button 2"),
				new JButton("Button 3")
		});
		template.setComponents("navigation", new JComponent[] {
				new JButton("Home"), new JButton("Next"),
				new JButton("Previous")
		});
	}

	public static void main(String[] argv) {
		TemplateNestingDemo layout = new TemplateNestingDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout.template.getView());
		frm.pack();
		frm.setVisible(true);
	}
}
