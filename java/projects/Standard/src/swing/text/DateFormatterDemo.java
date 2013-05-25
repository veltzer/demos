package swing.text;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DateFormatter;

@SuppressWarnings("serial")
public class DateFormatterDemo extends JPanel {
	private JFormattedTextField dateField;
	private JLabel enteredTextLabel;
	private JButton submitButton;

	public DateFormatterDemo() {
		super(new BorderLayout());
	}

	private void init() {
		Box textBox = new Box(BoxLayout.Y_AXIS);
		textBox.add(new JLabel("Please enter date: MM/dd/yyyy hh:mm"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm");

		dateField = new JFormattedTextField(new DateFormatter(dateFormat));
		dateField.setValue(new Date());

		dateField.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent e) {
				updateLabel();
			}

			public void insertUpdate(DocumentEvent e) {
				updateLabel();
			}

			public void removeUpdate(DocumentEvent e) {
				updateLabel();
			}

			private void updateLabel() {
				enteredTextLabel.setText("" + dateField.getValue());
			}
		});
		textBox.add(dateField);

		enteredTextLabel = new JLabel("Entered text...");
		textBox.add(enteredTextLabel);

		Box submitBox = new Box(BoxLayout.Y_AXIS);
		submitButton = new JButton("Submit");

		submitBox.add(submitButton);

		add(textBox, BorderLayout.NORTH);
		add(submitBox, BorderLayout.EAST);

		setBorder(BorderFactory.createLineBorder(getBackground(), 10));

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			}
		});
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DateFormatterDemo demo = new DateFormatterDemo();
		demo.init();

		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Date formatter demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(demo, BorderLayout.CENTER);
		frame.setSize(400, 200);
		frame.setVisible(true);
	}
}
