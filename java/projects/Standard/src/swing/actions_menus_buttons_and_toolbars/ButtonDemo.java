package swing.actions_menus_buttons_and_toolbars;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class ButtonDemo extends JPanel {
	private JMenuBar menuBar = new JMenuBar();
	private JToolBar toolbar = new JToolBar();

	private AbstractAction action1 = new AbstractAction() {
		public void actionPerformed(ActionEvent ev) {
			setEnabled(false);
			putValue(NAME, "OK now what?");
		}
	};
	private AbstractAction action2 = new AbstractAction() {
		public void actionPerformed(ActionEvent ev) {
			JOptionPane.showMessageDialog(((JComponent) ev.getSource()),
					"Second button pressed", "Pressed",
					JOptionPane.PLAIN_MESSAGE);
		}
	};

	public ButtonDemo() {
		super(new GridLayout(2, 1));
		action1.putValue(Action.NAME, "First");
		action2.putValue(Action.NAME, "Second");
		action1.putValue(Action.SHORT_DESCRIPTION, "This is the first Button");
		action2.putValue(Action.SHORT_DESCRIPTION, "This is the second Button");
		action1.putValue(Action.SMALL_ICON, new ImageIcon("Copy24.gif"));
		action2.putValue(Action.SMALL_ICON, new ImageIcon("Paste24.gif"));
		action1.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_F));
		action2.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_S));
		toolbar.add(action1);
		toolbar.add(action2);
		JMenu menu = new JMenu("Menu");
		JMenuItem item1 = new JMenuItem(action1);
		JMenuItem item2 = new JMenuItem(action2);
		menuBar.add(menu);
		menu.add(item1);
		menu.add(item2);
		JButton button1 = new JButton(action1);
		JButton button2 = new JButton(action2);
		add(button1);
		add(button2);
	}

	public static void main(String[] argv) {
		ButtonDemo demo = new ButtonDemo();
		JFrame frm = new JFrame();
		frm.getContentPane().add("North", demo.toolbar);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", demo);
		frm.setJMenuBar(demo.menuBar);
		frm.pack();
		frm.setVisible(true);
	}
}
