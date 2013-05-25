package swing.layout_managers.htmllayout;

/**
 * BadTableHtmlExceptions are thrown when an HtmlLayout is created with illegal
 * table-html.
 * 
 * @see htmllayout.HtmlLayout
 * @author Paul Buchheit
 */
public class BadTableHtmlException extends IllegalArgumentException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadTableHtmlException(String message) {
		super(message);
	}
}
