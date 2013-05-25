package programming.labs.lab1501networking;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ChatClientFrame extends JFrame {
	private JTextArea txtArea;
	private JTextField txtField;
	private JPanel panel;
	private JButton btnSend;

	public ChatClientFrame() {
		txtArea = new JTextArea();
		txtField = new JTextField();
		panel = new JPanel();
		btnSend = new JButton("send");
	}

	public void init(TestClient t) {
		getContentPane().add(txtArea, BorderLayout.CENTER);
		getContentPane().add(txtField, BorderLayout.SOUTH);
		getContentPane().add(panel, BorderLayout.EAST);
		panel.add(btnSend);
		btnSend.addActionListener(t);
		txtField.addActionListener(t);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		setSize(400, 300);
		// show(true);
	}

	void addMessage(String message) {
		txtArea.append(message + "\n");
	}

	String getMessage() {
		String str = txtField.getText();
		txtField.setText("");
		return str;
	}
}
