package swing.threads;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class TimerDemo extends JPanel {
	private JTextField loginField;
	private Timer typingTimer;
	private JPasswordField passwordField;
	private Action submitAction;

	public TimerDemo() {
		super(new BorderLayout());
	}

	private void init() {
		Box textBox = new Box(BoxLayout.Y_AXIS);

		loginField = new JTextField();
		loginField.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				boolean isValid = loginField.getText().matches("[\\w]{4,}");
				return isValid;
			}
		});
		textBox.add(loginField);

		passwordField = new JPasswordField();
		textBox.add(passwordField);
		textBox.setBorder(BorderFactory.createEtchedBorder());
		textBox.add(Box.createGlue());

		Box submitBox = new Box(BoxLayout.Y_AXIS);
		submitAction = new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				String login = loginField.getText();
				String password = new String(passwordField.getPassword());
				boolean passwordValid = login.equals(password);
				if (passwordValid) {
					JOptionPane.showMessageDialog(TimerDemo.this, "Welcome, "
							+ login + "!");
					resetFields();
				} else {
					loginField.setEnabled(false);
					passwordField.setEnabled(false);
					submitAction.setEnabled(false);
					SwingWorker worker = new SwingWorker() {

						@Override
						public Object construct() {
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								throw new RuntimeException(e);
							}
							return null;
						}

						@Override
						public void finished() {
							resetFields();
							loginField.setEnabled(true);
							passwordField.setEnabled(true);
							submitAction.setEnabled(true);
						}
					};
					worker.start();

				}

			}
		};
		submitAction.putValue(Action.NAME, "Login");
		JButton submitButton = new JButton(submitAction);

		submitBox.add(submitButton);

		add(textBox, BorderLayout.NORTH);
		add(submitBox, BorderLayout.EAST);

		setBorder(BorderFactory.createLineBorder(getBackground(), 10));

		typingTimer = new Timer(5000, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				resetFields();
			}
		});

		loginField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				typingTimer.restart();
			}
		});
		passwordField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				typingTimer.restart();
			}
		});
	}

	private void resetFields() {
		loginField.setText("");
		passwordField.setText("");

		loginField.grabFocus();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				TimerDemo demo = new TimerDemo();
				demo.init();

				JFrame.setDefaultLookAndFeelDecorated(true);
				JFrame frame = new JFrame("Timer + Verifier demo");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(demo, BorderLayout.CENTER);
				frame.setSize(400, 200);
				frame.setVisible(true);
			}
		});

	}

}
