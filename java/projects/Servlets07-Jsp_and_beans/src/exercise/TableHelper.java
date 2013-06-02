package exercise;

/**
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class TableHelper implements java.io.Serializable {

	/** Holds value of property html. */
	private String[][] data;

	/** Holds value of property bgcolor. */
	private String bgcolor;

	/** Creates new TableHelper */
	public TableHelper() {
		bgcolor = "white";
	}

	/**
	 * Getter for property html.
	 * @return Value of property html.
	 */
	public String getHtml() {
		String html = "";
		html += "<table border=1 bgcolor=" + bgcolor + ">";
		for (int i = 0; i < data.length; i++) {
			html += "<tr>";
			for (int j = 0; j < data[i].length; j++) {
				html += "<td>" + data[i][j] + "</td>";
			}
			html += "</tr>";
		}
		html += "</table>";
		return html;
	}

	public void setData(String[][] idata) {
		data = idata;
	}

	/**
	 * Getter for property bgcolor.
	 * @return Value of property bgcolor.
	 */
	public String getBgcolor() {
		return bgcolor;
	}

	/**
	 * Setter for property bgcolor.
	 * @param bgcolor New value of property bgcolor.
	 */
	public void setBgcolor(String ibgcolor) {
		bgcolor = ibgcolor;
	}

}
