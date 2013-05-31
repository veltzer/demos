package swing.text_components;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

@SuppressWarnings("serial")
public class BrowserDemo extends JPanel {
	private JTextField address = new JTextField("http://www.vprise.com/");;
	private JEditorPane pane = new JEditorPane();

	public BrowserDemo() {
		setLayout(new BorderLayout());
		add("North", address);
		add("Center", new JScrollPane(pane));
		address.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					pane.setPage(address.getText());
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}
			}
		});
		pane.setEditable(false);
		pane.addHyperlinkListener(new HyperlinkListener() {
			public void hyperlinkUpdate(HyperlinkEvent ev) {
				if (ev.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					JEditorPane ipane = (JEditorPane) ev.getSource();
					if (ev instanceof HTMLFrameHyperlinkEvent) {
						HTMLFrameHyperlinkEvent evt = (HTMLFrameHyperlinkEvent) ev;
						HTMLDocument doc = (HTMLDocument) pane.getDocument();
						doc.processHTMLFrameHyperlinkEvent(evt);
					} else {
						try {
							ipane.setPage(ev.getURL());
						} catch (Throwable e) {
							throw new RuntimeException(e);
						}
					}
				}
			}
		});
	}

	public static void main(String[] argv) {
		BrowserDemo layout = new BrowserDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}
}
