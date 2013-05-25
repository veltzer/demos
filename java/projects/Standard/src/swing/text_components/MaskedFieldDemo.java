package swing.text_components;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

@SuppressWarnings("serial")
public class MaskedFieldDemo extends JPanel {
	public MaskedFieldDemo() {
		JTextField field = new JTextField("0123456789");
		((AbstractDocument) field.getDocument())
				.setDocumentFilter(new DocumentFilter() {
					public void insertString(DocumentFilter.FilterBypass fb,
							int offset, String string, AttributeSet attr)
							throws BadLocationException {
						if (isValid(string)) {
							fb.insertString(offset, string, attr);
						}
					}

					public void remove(DocumentFilter.FilterBypass fb,
							int offset, int length) throws BadLocationException {
						fb.remove(offset, length);
					}

					public void replace(DocumentFilter.FilterBypass fb,
							int offset, int length, String text,
							AttributeSet attrs) throws BadLocationException {
						if (isValid(text)) {
							fb.replace(offset, length, text, attrs);
						}
					}

					private boolean isValid(String str) {
						return (str.matches("\\d*"));
					}
				});
		add(field);
	}

	public static void main(String[] argv) {
		MaskedFieldDemo layout = new MaskedFieldDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}
}
