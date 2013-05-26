package swing.layout_managers.htmllayout;

/**
 * BadTableHtmlExceptions are thrown when an HtmlLayout is created with illegal
 * table-html.
 * @see htmllayout.HtmlLayout
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class BadTableHtmlException extends IllegalArgumentException {
	public BadTableHtmlException(String message) {
		super(message);
	}
}
