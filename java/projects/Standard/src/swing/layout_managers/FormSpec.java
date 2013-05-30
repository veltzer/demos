package swing.layout_managers;

import java.awt.Component;
import java.awt.Container;
import java.io.Serializable;
import java.util.List;
import java.util.StringTokenizer;

/**
 * An abstract class that specifies columns and rows in {@link FormLayout} by
 * their default alignment, start size and resizing behavior. API users will use
 * the subclasses {@link ColumnSpec} and {@link RowSpec}.
 * @author Mark Veltzer <mark@veltzer.net>
 * @see ColumnSpec
 * @see RowSpec
 * @see FormLayout
 * @see CellConstraints
 */
@SuppressWarnings("serial")
public abstract class FormSpec implements Serializable {

	// Horizontal and Vertical Default Alignments ***************************

	/**
	 * By default put components in the left.
	 */
	static final DefaultAlignment LEFT_ALIGN = new DefaultAlignment("left");

	/**
	 * By default put components in the right.
	 */
	static final DefaultAlignment RIGHT_ALIGN = new DefaultAlignment("right");

	/**
	 * By default put the components in the top.
	 */
	static final DefaultAlignment TOP_ALIGN = new DefaultAlignment("top");

	/**
	 * By default put the components in the bottom.
	 */
	static final DefaultAlignment BOTTOM_ALIGN = new DefaultAlignment("bottom");

	/**
	 * By default put the components in the center.
	 */
	static final DefaultAlignment CENTER_ALIGN = new DefaultAlignment("center");

	/**
	 * By default fill the column or row.
	 */
	static final DefaultAlignment FILL_ALIGN = new DefaultAlignment("fill");

	/**
	 * An array of all enumeration values used to canonicalize deserialized
	 * default alignments.
	 */
	private static final DefaultAlignment[] VALUES = {
			LEFT_ALIGN, RIGHT_ALIGN, TOP_ALIGN, BOTTOM_ALIGN, CENTER_ALIGN,
			FILL_ALIGN
	};

	// Resizing Weights *****************************************************

	/**
	 * Gives a column or row a fixed size.
	 */
	public static final double NO_GROW = 0.0d;

	/**
	 * The default resize weight.
	 */
	public static final double DEFAULT_GROW = 1.0d;

	// Fields ***************************************************************

	/**
	 * Holds the default alignment that will be used if a cell does not override
	 * this default.
	 */
	private DefaultAlignment defaultAlignment;

	/**
	 * Holds the size that describes how to size this column or row.
	 */
	private Size size;

	/**
	 * Holds the resize weight; is 0 if not used.
	 */
	private double resizeWeight;

	// Instance Creation ****************************************************

	/**
	 * Constructs a <code>FormSpec</code> for the given default alignment, size,
	 * and resize weight. The resize weight must be a non-negative double; you
	 * can use <code>NONE</code> as a convenience value for no resize.
	 * @param defaultAlignment the spec's default alignment
	 * @param size a constant, component or bounded size
	 * @param resizeWeight the spec resize weight
	 * @throws IllegalArgumentException if the resize weight is negative
	 */
	protected FormSpec(DefaultAlignment idefaultAlignment, Size isize,
			double iresizeWeight) {
		defaultAlignment = idefaultAlignment;
		size = isize;
		resizeWeight = iresizeWeight;
		if (resizeWeight < 0) {
			throw new IllegalArgumentException(
					"The resize weight must be non-negative.");
		}
	}

	/**
	 * Constructs a <code>FormSpec</code> from the specified encoded
	 * description. The description will be parsed to set initial values.
	 * @param defaultAlignment the default alignment
	 * @param encodedDescription the encoded description
	 */
	protected FormSpec(DefaultAlignment idefaultAlignment,
			String encodedDescription) {
		this(idefaultAlignment, Sizes.DEFAULT, NO_GROW);
		parseAndInitValues(encodedDescription.toLowerCase());
	}

	// Public API ***********************************************************

	/**
	 * Returns the default alignment.
	 * @return the default alignment
	 */
	public final DefaultAlignment getDefaultAlignment() {
		return defaultAlignment;
	}

	/**
	 * Returns the size.
	 * @return the size
	 */
	public final Size getSize() {
		return size;
	}

	/**
	 * Returns the current resize weight.
	 * @return the resize weight.
	 */
	public final double getResizeWeight() {
		return resizeWeight;
	}

	/**
	 * Checks and answers whether this spec can grow or not. That is the case if
	 * and only if the resize weight is != <code>NO_GROW</code>.
	 * @return true if it can grow, false if it can't grow
	 */
	final boolean canGrow() {
		return getResizeWeight() != NO_GROW;
	}

	// Parsing **************************************************************

	/**
	 * Parses an encoded form spec and initializes all required fields. The
	 * encoded description must be in lower case.
	 * @param encodedDescription the FormSpec in an encoded format
	 * @throws IllegalArgumentException if the string is empty, has no size, or
	 * is otherwise invalid
	 */
	private void parseAndInitValues(String encodedDescription) {
		StringTokenizer tokenizer = new StringTokenizer(encodedDescription, ":");
		if (!tokenizer.hasMoreTokens()) {
			throw new IllegalArgumentException(
					"The form spec must not be empty.");
		}
		String token = tokenizer.nextToken();

		// Check if the first token is an orientation.
		DefaultAlignment alignment = DefaultAlignment.valueOf(token,
				isHorizontal());
		if (alignment != null) {
			defaultAlignment = alignment;
			if (!tokenizer.hasMoreTokens()) {
				throw new IllegalArgumentException(
						"The form spec must provide a size.");
			}
			token = tokenizer.nextToken();
		}

		parseAndInitSize(token);

		if (tokenizer.hasMoreTokens()) {
			resizeWeight = decodeResize(tokenizer.nextToken());
		}
	}

	/**
	 * Parses an encoded size spec and initializes the size fields.
	 * @param token a token that represents a size, either bounded or plain
	 */
	private void parseAndInitSize(String token) {
		if (token.startsWith("max(") && token.endsWith(")")) {
			size = parseAndInitBoundedSize(token, false);
			return;
		}
		if (token.startsWith("min(") && token.endsWith(")")) {
			size = parseAndInitBoundedSize(token, true);
			return;
		}
		size = decodeAtomicSize(token);
	}

	/**
	 * Parses an encoded compound size and sets the size fields. The compound
	 * size has format: max(<atomic size>;<atomic size2>) | min(<atomic
	 * size1>;<atomic size2>) One of the two atomic sizes must be a logical
	 * size, the other must be a size constant.
	 * @param token a token for a bounded size, e.g. "max(50dlu; pref)"
	 * @param setMax if true we set a maximum size, otherwise a minimum size
	 * @return a Size that represents the parse result
	 */
	private Size parseAndInitBoundedSize(String token, boolean setMax) {
		int semicolonIndex = token.indexOf(';');
		String sizeToken1 = token.substring(4, semicolonIndex);
		String sizeToken2 = token.substring(semicolonIndex + 1,
				token.length() - 1);

		Size size1 = decodeAtomicSize(sizeToken1);
		Size size2 = decodeAtomicSize(sizeToken2);

		// Check valid combinations and set min or max.
		if (size1 instanceof ConstantSize) {
			if (size2 instanceof Sizes.ComponentSize) {
				Size p1;
				Size p2;
				if (setMax) {
					p1 = null;
					p2 = size1;
				} else {
					p1 = size1;
					p2 = null;
				}
				return new BoundedSize(size2, p1, p2);
			}
			throw new IllegalArgumentException(
					"Bounded sizes must not be both constants.");
		} else {
			if (size2 instanceof ConstantSize) {
				Size p1;
				Size p2;
				if (setMax) {
					p1 = null;
					p2 = size2;
				} else {
					p1 = size2;
					p2 = null;
				}
				return new BoundedSize(size1, p1, p2);
			}
			throw new IllegalArgumentException(
					"Bounded sizes must not be both logical.");
		}
	}

	/**
	 * Decodes and returns an atomic size that is either a constant size or a
	 * component size.
	 * @param token the encoded size
	 * @return the decoded size either a constant or component size
	 */
	private Size decodeAtomicSize(String token) {
		Sizes.ComponentSize componentSize = Sizes.ComponentSize.valueOf(token);
		if (componentSize != null) {
			return componentSize;
		} else {
			return ConstantSize.valueOf(token, isHorizontal());
		}
	}

	/**
	 * Decodes an encoded resize mode and resize weight and answers the resize
	 * weight.
	 * @param token the encoded resize weight
	 * @return the decoded resize weight
	 * @throws IllegalArgumentException if the string description is an invalid
	 * string representation
	 */
	private double decodeResize(String token) {
		if (token.equals("g") || token.equals("grow")) {
			return DEFAULT_GROW;
		}
		if (token.equals("n") || token.equals("nogrow") || token.equals("none")) {
			return NO_GROW;
		}
		// Must have format: grow(<double>)
		if ((token.startsWith("grow(") || token.startsWith("g("))
				&& token.endsWith(")")) {
			int leftParen = token.indexOf('(');
			int rightParen = token.indexOf(')');
			String substring = token.substring(leftParen + 1, rightParen);
			return Double.parseDouble(substring);
		}
		throw new IllegalArgumentException(
				"The resize argument '"
						+ token
						+ "' is invalid. "
						+ " Must be one of: grow, g, none, n, grow(<double>), g(<double>)");
	}

	// Misc *****************************************************************

	/**
	 * Returns a string representation of this form specification. The string
	 * representation consists of three elements separated by a colon (
	 * <tt>":"</tt>), first the alignment, second the size, and third the resize
	 * spec. <p> This method does <em>not</em> return a decoded version of this
	 * object; the contrary is the case. Many instances will return a string
	 * that cannot be parsed. <p> <strong>Note:</strong> The string
	 * representation may change at any time. It is strongly recommended to not
	 * use this string for parsing purposes.
	 * @return a string representation of the form specification.
	 */
	public final String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(defaultAlignment);

		buffer.append(":");
		buffer.append(size.toString());
		buffer.append(':');
		if (resizeWeight == NO_GROW) {
			buffer.append("noGrow");
		} else if (resizeWeight == DEFAULT_GROW) {
			buffer.append("grow");
		} else {
			buffer.append("grow(");
			buffer.append(resizeWeight);
			buffer.append(')');
		}
		return buffer.toString();
	}

	/**
	 * Returns a string representation of this form specification. The string
	 * representation consists of three elements separated by a colon (
	 * <tt>":"</tt>), first the alignment, second the size, and third the resize
	 * spec. <p> This method does <em>not</em> return a decoded version of this
	 * object; the contrary is the case. Many instances will return a string
	 * that cannot be parsed. <p> <strong>Note:</strong> The string
	 * representation may change at any time. It is strongly recommended to not
	 * use this string for parsing purposes.
	 * @return a string representation of the form specification.
	 */
	public final String toShortString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(defaultAlignment.abbreviation());

		buffer.append(":");
		buffer.append(size.toString());
		buffer.append(':');
		if (resizeWeight == NO_GROW) {
			buffer.append("n");
		} else if (resizeWeight == DEFAULT_GROW) {
			buffer.append("g");
		} else {
			buffer.append("g(");
			buffer.append(resizeWeight);
			buffer.append(')');
		}
		return buffer.toString();
	}

	// Abstract Behavior ****************************************************

	/**
	 * Returns if this is a horizontal specification (vs. vertical). Used to
	 * distinct between horizontal and vertical dialog units, which have
	 * different conversion factors.
	 * @return true for horizontal, false for vertical
	 */
	abstract boolean isHorizontal();

	// Helper Code **********************************************************

	/**
	 * Computes the maximum size for the given list of components, using this
	 * form spec and the specified measure. <p> Invoked by FormLayout to
	 * determine the size of one of my elements
	 * @param container the layout container
	 * @param components the list of components to measure
	 * @param minMeasure the measure used to determine the minimum size
	 * @param prefMeasure the measure used to determine the preferred size
	 * @param defaultMeasure the measure used to determine the default size
	 * @return the maximum size in pixels
	 */
	final int maximumSize(Container container, List<Component> components,
			FormLayout.Measure minMeasure, FormLayout.Measure prefMeasure,
			FormLayout.Measure defaultMeasure) {
		return size.maximumSize(container, components, minMeasure, prefMeasure,
				defaultMeasure);
	}

	/**
	 * An ordinal-based serializable typesafe enumeration for the column and row
	 * default alignment types.
	 */
	public static final class DefaultAlignment implements Serializable {
		private final transient String name;

		private DefaultAlignment(String iname) {
			name = iname;
		}

		/**
		 * Returns a DefaultAlignment that corresponds to the specified string,
		 * null if no such aignment exists.
		 * @param str the encoded alignment
		 * @param isHorizontal indicates the values orientation
		 * @return the corresponding DefaultAlignment or null
		 */
		private static DefaultAlignment valueOf(String str, boolean isHorizontal) {
			if (str.equals("f") || str.equals("fill")) {
				return FILL_ALIGN;
			} else if (str.equals("c") || str.equals("center")) {
				return CENTER_ALIGN;
			} else if (isHorizontal) {
				if (str.equals("r") || str.equals("right")) {
					return RIGHT_ALIGN;
				} else if (str.equals("l") || str.equals("left")) {
					return LEFT_ALIGN;
				} else {
					return null;
				}
			} else {
				if (str.equals("t") || str.equals("top")) {
					return TOP_ALIGN;
				} else if (str.equals("b") || str.equals("bottom")) {
					return BOTTOM_ALIGN;
				} else {
					return null;
				}
			}
		}

		/**
		 * Returns this Alignment's name.
		 * @return this alignment's name.
		 */
		public String toString() {
			return name;
		}

		/**
		 * Returns the first character of this Alignment's name. Used to
		 * identify it in short format strings.
		 * @return the name's first character.
		 */
		public char abbreviation() {
			return name.charAt(0);
		}

		// Serialization *****************************************************

		private static int nextOrdinal = 0;

		private final int ordinal = nextOrdinal++;

		private Object readResolve() {
			return VALUES[ordinal]; // Canonicalize
		}

	}

}
