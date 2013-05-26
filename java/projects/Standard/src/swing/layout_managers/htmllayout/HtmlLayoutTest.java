package swing.layout_managers.htmllayout;

import java.awt.Button;
import java.awt.Event;
import java.awt.Frame;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Reads in files containing table-html and creates a window for each layed out
 * according to the html. Buttons are created in the place of each component.
 * Used for testing table-html and layout behavior.
 * @see htmllayout.HtmlLayout
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class HtmlLayoutTest extends Frame {
	HtmlLayoutTest(String title, String html) {
		super(title);

		HtmlLayout hl = new HtmlLayout(html);
		setLayout(hl);
		addComps(hl.cells);
	}

	void addComps(Cell cells[]) {
		for (int i = 0; i < cells.length; i++) {
			if (cells[i].name != null) {
				Button b = new Button(cells[i].name);
				add(b, cells[i].name);
			} else if (cells[i].nested != null) {
				addComps(cells[i].nested.cells);
			}
		}
	}

	/** uses deprecated api for compatibility with web browsers */
	@SuppressWarnings("deprecation")
	public boolean handleEvent(Event evt) {
		if (evt.id == Event.WINDOW_DESTROY) {
			hide();
			return true;
		} else {
			return false;
		}
	}

	public static void main(String args[]) throws IOException {
		if (args.length == 0) {
			System.err.println("HtmlLayoutTest filename [...]");
			System.err
					.println("\tReads in each file \"filename\" containing table html\n"
							+ "\tand creates a window layed out according to that html.\n"
							+ "\tButtons are created to fill in for the components.");
		}

		for (int i = 0; i < args.length; i++) {
			RandomAccessFile raf = new RandomAccessFile(args[i], "r");
			byte data[] = new byte[(int) raf.length()];
			raf.readFully(data);
			raf.close();

			Frame f = new HtmlLayoutTest(args[i], new String(data));
			f.pack();
			f.setVisible(true);
		}
	}
}
