package swing.layout_managers;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TableDemo extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableDemo() {
        double size[][] =
            {{0.05, TableLayout.PREFERRED, 0.05, TableLayout.PREFERRED, TableLayout.FILL, 0.05},
             {0.05, TableLayout.PREFERRED, 0.05, TableLayout.PREFERRED, 0.05, TableLayout.PREFERRED, TableLayout.FILL}};
        setLayout(new TableLayout(size));
        JLabel name = new JLabel("Name");
        JLabel password = new JLabel("Password");
        JComponent nameText = new JTextField("Someone");
        JComponent passwordText = new JPasswordField(10);
        JButton login = new JButton("Login");
        JButton cancel = new JButton("Cancel");
        add(name, "1, 1");
        add(password, "1, 3");
        add(nameText, "3, 1, 4, 1");
        add(passwordText, "3, 3, 4, 3");
        add(login, "1, 5");
        add(cancel, "3, 5");
    }

    public static void main(String[] argv) {
        TableDemo layout = new TableDemo();
        JFrame frm = new JFrame();
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.getContentPane().add("Center", layout);
        frm.pack();
        frm.setVisible(true);
    }
}