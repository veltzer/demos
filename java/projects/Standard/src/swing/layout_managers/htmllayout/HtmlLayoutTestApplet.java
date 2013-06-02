package swing.layout_managers.htmllayout;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Reads the file specified by the "htmlURL" param and, when the "Test filname"
 * button is pressed, opens a window layed out by that table-html, just as with
 * HtmlLayoutTest.
 * @see htmllayout.HtmlLayout
 * @see htmllayout.HtmlLayoutTest
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class HtmlLayoutTestApplet extends Applet {
	private String html;
	private String htmlURL;

	public void init() {
		htmlURL = getParameter("htmlURL");
		if (htmlURL == null) {
			System.err
					.println("HtmlLayoutTestApplet: htmlURL param must be specified");
		} else {
			try {
				URL url = new URL(getCodeBase(), htmlURL);

				/******
				 * replaced for JDK 1.0 browsers BufferedReader br = new
				 * BufferedReader( new InputStreamReader()); StringBuffer sb =
				 * new StringBuffer(); String s; while((s = br.readLine()) !=
				 * null) { sb.append(s); sb.append("\n"); } html =
				 * sb.toString();
				 ******/
				// instead
				InputStream is = url.openStream();
				StringBuffer sb = new StringBuffer();
				String s;
				while ((s = readString(is)) != null) {
					sb.append(s);
				}
				html = sb.toString();

			} catch (Exception e) {
				System.err.println("HtmlLayoutTestApplet: " + e);
			}
		}

		if (html != null) {
			setLayout(new FlowLayout());
			add(new Button("Test " + htmlURL));
		}
	}

	@SuppressWarnings("deprecation")
	private String readString(InputStream is) throws IOException {
		byte[] data = new byte[10000];
		int c, pos = 0;

		while (pos < data.length
				&& (c = is.read(data, pos, data.length - pos)) != -1) {
			pos += c;
		}

		if (pos == 0) {
			return null;
		}

		return new String(data, 0, 0, pos); // for 1.0
	}

	/** uses deprecated api for compatibility with web browsers */
	@SuppressWarnings("deprecation")
	public boolean action(Event evt, Object what) {
		Frame f = new HtmlLayoutTest(htmlURL, html);
		f.pack();
		f.show();
		return true;
	}

	public void paint(Graphics g) {
		if (html == null) {
			g.setColor(Color.red);
			g.drawString("Error", 10, 40);
		}
	}
}
