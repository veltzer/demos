package swing.text;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

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
import javax.swing.text.MaskFormatter;

@SuppressWarnings("serial")
public class FormattedDemo extends JPanel {
	private JFormattedTextField dateField;
	private JLabel enteredTextLabel;
	private JButton submitButton;

	public FormattedDemo() {
		super(new BorderLayout());
	}

	private void init() {
		Box textBox = new Box(BoxLayout.Y_AXIS);
		textBox.add(new JLabel("Please enter date: MM/dd/yyyy hh:mm"));

		try {
			dateField = new JFormattedTextField(new MaskFormatter(
					"##/##/#### ##:##"));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		dateField.setText("010119700000");
		dateField.select(0, dateField.getText().length());
		dateField.setCaretPosition(0);

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
				enteredTextLabel.setText(dateField.getText());
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

	/*
	 * private MaskFormatter createFormatter(String s) { MaskFormatter formatter
	 * = null; try { formatter = new MaskFormatter(s); } catch
	 * (java.text.ParseException exc) { System.err.println("formatter is bad: "
	 * + exc.getMessage()); System.exit(-1); } return formatter; }
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FormattedDemo demo = new FormattedDemo();
		demo.init();

		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Formatter demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(demo, BorderLayout.CENTER);
		frame.setSize(400, 200);
		frame.setVisible(true);
	}

}
