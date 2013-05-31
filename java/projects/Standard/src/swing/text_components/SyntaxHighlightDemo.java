package swing.text_components;

import java.awt.Color;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

@SuppressWarnings("serial")
public class SyntaxHighlightDemo extends JPanel {
	private Pattern[] keywords = new Pattern[] {
			Pattern.compile("\\bpublic\\b"), Pattern.compile("\\bimport\\b"),
			Pattern.compile("\\bprivate\\b"), Pattern.compile("\\btry\\b"),
			Pattern.compile("\\bcatch\\b"), Pattern.compile("\\bclass\\b"),
			Pattern.compile("\\bint\\b"), Pattern.compile("\\blong\\b"),
			Pattern.compile("\\bshort\\b"), Pattern.compile("\\bbyte\\b")
	};
	private Pattern numbers = Pattern.compile("\\d");
	private Pattern strings = Pattern.compile("\".*\"");
	private MutableAttributeSet keywordAttr = new SimpleAttributeSet();
	private MutableAttributeSet numberAttr = new SimpleAttributeSet();
	private MutableAttributeSet stringAttr = new SimpleAttributeSet();
	private MutableAttributeSet standardAttr = new SimpleAttributeSet();
	private StyledDocument doc;

	public SyntaxHighlightDemo() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JTextPane area = new JTextPane();
		try {
			area.setPage(getClass().getResource("/SyntaxHighlightDemo.java"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		doc = area.getStyledDocument();
		add(new JScrollPane(area));
		StyleConstants.setBold(keywordAttr, true);
		StyleConstants.setForeground(keywordAttr, Color.BLUE);
		StyleConstants.setForeground(numberAttr, Color.PINK);
		StyleConstants.setForeground(stringAttr, Color.RED);
		StyleConstants.setItalic(stringAttr, true);
		((AbstractDocument) doc).setDocumentFilter(new DocumentFilter() {
			public void insertString(DocumentFilter.FilterBypass fb,
					int offset, String string, AttributeSet attr)
					throws BadLocationException {
				fb.insertString(offset, string, attr);
				update();
			}

			public void remove(DocumentFilter.FilterBypass fb, int offset,
					int length) throws BadLocationException {
				fb.remove(offset, length);
				update();
			}

			public void replace(DocumentFilter.FilterBypass fb, int offset,
					int length, String text, AttributeSet attrs)
					throws BadLocationException {
				fb.replace(offset, length, text, attrs);
				update();
			}
		});
		update();
	}

	private void update() {
		try {
			doc.setCharacterAttributes(0, doc.getLength(), standardAttr, true);
			String text = doc.getText(0, doc.getLength());
			for (int iter = 0; iter < keywords.length; iter++) {
				Matcher m = keywords[iter].matcher(text);
				while (m.find()) {
					int start = m.start();
					doc.setCharacterAttributes(start, m.end() - start,
							keywordAttr, true);
				}
			}
			Matcher m = numbers.matcher(text);
			while (m.find()) {
				int start = m.start();
				doc.setCharacterAttributes(start, m.end() - start, numberAttr,
						true);
			}
			m = strings.matcher(text);
			while (m.find()) {
				int start = m.start();
				doc.setCharacterAttributes(start, m.end() - start, stringAttr,
						true);
			}
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] argv) {
		SyntaxHighlightDemo layout = new SyntaxHighlightDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}
}
