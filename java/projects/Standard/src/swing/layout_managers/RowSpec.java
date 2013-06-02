package swing.layout_managers;

import java.util.StringTokenizer;

/**
 * Specifies rows in in {@link FormLayout} by their default orientation, start
 * size and resizing behavior. <p> <strong>Examples:</strong><br> The following
 * examples specify a centered row with a size of 14&nbsp;dlu that won't grow.
 * <pre> new RowSpec(Sizes.dluX(14)); new RowSpec(RowSpec.CENTER,
 * Sizes.dluX(14), 0.0); new RowSpec(rowSpec.CENTER, Sizes.dluX(14),
 * RowSpec.NO_GROW); new RowSpec(&quot;14dlu&quot;); new
 * RowSpec(&quot;14dlu:0&quot;); new RowSpec(&quot;center:14dlu:0&quot;); </pre>
 * <p> The {@link com.jgoodies.forms.factories.FormFactory} provides predefined
 * frequently used <code>RowSpec</code> instances.
 * @author Mark Veltzer <mark@veltzer.net>
 * @see com.jgoodies.forms.factories.FormFactory
 */

@SuppressWarnings("serial")
public final class RowSpec extends FormSpec {

	// Vertical Orientations ************************************************

	/**
	 * By default put the components in the top.
	 */
	public static final DefaultAlignment TOP = FormSpec.TOP_ALIGN;

	/**
	 * By default put the components in the center.
	 */
	public static final DefaultAlignment CENTER = FormSpec.CENTER_ALIGN;

	/**
	 * By default put the components in the bottom.
	 */
	public static final DefaultAlignment BOTTOM = FormSpec.BOTTOM_ALIGN;

	/**
	 * By default fill the component into the row.
	 */
	public static final DefaultAlignment FILL = FormSpec.FILL_ALIGN;

	/**
	 * Unless overridden the default alignment for a row is CENTER.
	 */
	public static final DefaultAlignment DEFAULT = CENTER;

	// Instance Creation ****************************************************

	/**
	 * Constructs a <code>RowSpec</code> from the given default orientation,
	 * size, and resize weight. <p> The resize weight must be a non-negative
	 * double; you can use <code>NO_FILL</code> as a convenience value for no
	 * resize.
	 * @param defaultAlignment the row's default alignment
	 * @param size the row's size as value with unit
	 * @param resizeWeight the row's resize weight
	 */
	public RowSpec(DefaultAlignment defaultAlignment, Size size,
			double resizeWeight) {
		super(defaultAlignment, size, resizeWeight);
	}

	/**
	 * Constructs a <code>RowSpec</code> for the given size using the default
	 * alignment, and no resizing.
	 * @param size constant size, component size, or bounded size
	 * @throws IllegalArgumentException if the pixel size is invalid or the
	 * resize weight is negative
	 */
	public RowSpec(Size size) {
		super(DEFAULT, size, NO_GROW);
	}

	/**
	 * Constructs a <code>RowSpec</code> from the specified encoded description.
	 * The description will be parsed to set initial values.
	 * @param encodedDescription the encoded description
	 */
	public RowSpec(String encodedDescription) {
		super(DEFAULT, encodedDescription);
	}

	// Implementing Abstract Behavior ***************************************

	/**
	 * Returns if this is a horizontal specification (vs. vertical). Used to
	 * distinct between horizontal and vertical dialog units, which have
	 * different conversion factors.
	 * @return true for horizontal, false for vertical
	 */
	protected boolean isHorizontal() {
		return false;
	}

	// Parsing and Decoding of Row Descriptions *****************************

	/**
	 * Parses and splits encoded row specifications and returns an array of
	 * <code>RowSpec</code> objects.
	 * @param encodedRowSpecs comma separated encoded row specifications
	 * @return an array of decoded row specifications
	 * @throws NullPointerException if the encoded row specifications string is
	 * <code>null</code>
	 * @see RowSpec#RowSpec(String)
	 */
	public static RowSpec[] decodeSpecs(String encodedRowSpecs) {
		if (encodedRowSpecs == null) {
			throw new NullPointerException(
					"The row description must not be null.");
		}

		StringTokenizer tokenizer = new StringTokenizer(encodedRowSpecs, ", ");
		int rowCount = tokenizer.countTokens();
		RowSpec[] rowSpecs = new RowSpec[rowCount];
		for (int i = 0; i < rowCount; i++) {
			rowSpecs[i] = new RowSpec(tokenizer.nextToken());
		}
		return rowSpecs;
	}

}
