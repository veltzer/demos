package swing.keyboard;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;
import javax.swing.text.Keymap;
import javax.swing.text.TextAction;

@SuppressWarnings("serial")
public class KeyMapDemo extends JPanel {
	private JTextField field = new JTextField("Try writing in lower case...");
	private static Keymap keymap;
	private static final JTextComponent.KeyBinding[] UPCASE_BINDINGS = {
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('a'),
					"insert-me A"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('b'),
					"insert-me B"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('c'),
					"insert-me C"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('d'),
					"insert-me D"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('e'),
					"insert-me E"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('f'),
					"insert-me F"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('g'),
					"insert-me G"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('h'),
					"insert-me H"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('i'),
					"insert-me I"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('j'),
					"insert-me J"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('k'),
					"insert-me K"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('l'),
					"insert-me L"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('m'),
					"insert-me M"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('n'),
					"insert-me N"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('o'),
					"insert-me O"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('p'),
					"insert-me P"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('q'),
					"insert-me Q"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('r'),
					"insert-me R"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('s'),
					"insert-me S"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('t'),
					"insert-me T"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('u'),
					"insert-me U"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('v'),
					"insert-me V"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('w'),
					"insert-me W"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('x'),
					"insert-me X"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('y'),
					"insert-me Y"),
			new JTextComponent.KeyBinding(KeyStroke.getKeyStroke('z'),
					"insert-me Z"),
	};

	private static final Action[] UPCASE_ACTIONS = {
			new InsertMeAction("A"), new InsertMeAction("B"),
			new InsertMeAction("C"), new InsertMeAction("D"),
			new InsertMeAction("E"), new InsertMeAction("F"),
			new InsertMeAction("G"), new InsertMeAction("H"),
			new InsertMeAction("I"), new InsertMeAction("J"),
			new InsertMeAction("K"), new InsertMeAction("L"),
			new InsertMeAction("M"), new InsertMeAction("N"),
			new InsertMeAction("O"), new InsertMeAction("P"),
			new InsertMeAction("Q"), new InsertMeAction("R"),
			new InsertMeAction("S"), new InsertMeAction("T"),
			new InsertMeAction("U"), new InsertMeAction("V"),
			new InsertMeAction("W"), new InsertMeAction("X"),
			new InsertMeAction("Y"), new InsertMeAction("Z"),
	};

	public KeyMapDemo() {
		keymap = JTextComponent.addKeymap("Upcase", field.getKeymap());
		// we didn;t have to add the key map. Instead of the previous line, one
		// could alternatively use the following line of code:
		// keymap = field.getKeymap();
		JTextComponent.loadKeymap(keymap, UPCASE_BINDINGS, UPCASE_ACTIONS);
		field.setKeymap(keymap);
		add(field);
	}

	public static void main(String[] argv) {
		KeyMapDemo demo = new KeyMapDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", demo);
		frm.pack();
		frm.setVisible(true);
	}

	static class InsertMeAction extends TextAction {
		/**
		 * Creates this object with the appropriate identifier.
		 * @param s Description of the Parameter
		 */
		public InsertMeAction(String is) {
			super("insert-me " + is);
			s = is;
		}

		/**
		 * The operation to perform when this action is triggered.
		 * @param e the action event
		 */
		public void actionPerformed(ActionEvent e) {
			JTextComponent target = getTextComponent(e);
			if (target != null) {
				target.replaceSelection(s);
			}
		}

		private String s;
	}

}
