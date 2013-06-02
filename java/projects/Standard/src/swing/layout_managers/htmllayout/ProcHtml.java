package swing.layout_managers.htmllayout;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Converts html files into text suitable for inclusion in your Java source.
 * Places tabs (default 2) and a quote at the start of each line, and a quote
 * and plus (semi-colon for the last line) at the end of each line. Quotes are
 * escaped. Output is written to "filename.out", where "filename" is the name of
 * the input file. The number of tabs can be set by the -n option, where n is
 * the number.
 * @author Mark Veltzer <mark@veltzer.net>
 */
public abstract class ProcHtml {
	public static void main(String[] args) {
		try {
			if (args.length == 0 || args[0].length() == 0) {
				System.err.println("ProcHtml [-numtabs] filename [...]");
				System.err
						.println("\tReads in each file \"filename\" and writes it out to a file"
								+ "\n\t\"filename.out\" in a Java String suitable for embedding"
								+ "\n\tin a Java source file.");
			}

			int numtabs = 2;
			int firstArg = 0;

			if (args[0].charAt(0) == '-') {
				numtabs = Integer.parseInt(args[0].substring(1));
				firstArg++;
			}

			for (int i = firstArg; i < args.length; i++) {
				RandomAccessFile in;
				in = new RandomAccessFile(args[i], "r");
				BufferedWriter out = new BufferedWriter(new FileWriter(args[i]
						+ ".out"));

				String s;
				boolean first = true;
				while ((s = in.readLine()) != null) {
					if (!first) {
						out.write(" +\n");
					}

					for (int t = 0; t < numtabs; t++) {
						out.write('\t');
					}

					out.write("\" ");
					int q, p = 0;
					while ((q = s.indexOf("\"", p)) != -1) {
						out.write(s.substring(p, q));
						out.write("\\\"");
						p = q + 1;
					}
					out.write(s.substring(p));
					out.write(" \"");

					first = false;
				}
				out.write(";\n");

				in.close();
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
