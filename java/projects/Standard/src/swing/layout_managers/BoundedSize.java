package swing.layout_managers;

import java.awt.Component;
import java.awt.Container;
import java.io.Serializable;
import java.util.List;

/**
 * Describes sizes that provide lower and upper bounds as used by the JGoodies
 * {@link swing.layout_managers.jgoodies.forms.layout.FormLayout}.
 * @author Mark Veltzer <mark@veltzer.net>
 */

@SuppressWarnings("serial")
final class BoundedSize implements Size, Serializable {
	/**
	 * Holds the base size.
	 */
	private final Size basis;

	/**
	 * Holds an optional lower bound.
	 */
	private Size lowerBound;

	/**
	 * Holds an optional upper bound.
	 */
	private Size upperBound;

	// Instance Creation ****************************************************

	/**
	 * Constructs a <code>BoundedSize</code> for the given basis using the
	 * specified lower and upper bounds.
	 * @param basis the base size
	 * @param lowerBound the lower bound size
	 * @param upperBound the upper bound size
	 * @throws NullPointerException if the basis is null
	 */
	BoundedSize(Size ibasis, Size ilowerBound, Size iupperBound) {
		if (ibasis == null) {
			throw new NullPointerException(
					"The basis of a bounded size must not be null.");
		}
		basis = ibasis;
		lowerBound = ilowerBound;
		upperBound = iupperBound;
	}

	// Implementation of the Size Interface *********************************

	/**
	 * Returns this size as pixel size. Neither requires the component list nor
	 * the specified measures. Honors the lower and upper bound. <p> Invoked by
	 * <code>FormSpec</code> to determine the size of a column or row.
	 * @param container the layout container
	 * @param components the list of components to measure
	 * @param minMeasure the measure used to determine the minimum size
	 * @param prefMeasure the measure used to determine the preferred size
	 * @param defaultMeasure the measure used to determine the default size
	 * @return the maximum size in pixels
	 * @see FormSpec#maximumSize(Container, List, FormLayout.Measure,
	 * FormLayout.Measure, FormLayout.Measure)
	 */
	public int maximumSize(Container container, List<Component> components,
			FormLayout.Measure minMeasure, FormLayout.Measure prefMeasure,
			FormLayout.Measure defaultMeasure) {
		int size = basis.maximumSize(container, components, minMeasure,
				prefMeasure, defaultMeasure);
		if (lowerBound != null) {
			size = Math.max(size, lowerBound.maximumSize(container, components,
					minMeasure, prefMeasure, defaultMeasure));
		}
		if (upperBound != null) {
			size = Math.min(size, upperBound.maximumSize(container, components,
					minMeasure, prefMeasure, defaultMeasure));
		}
		return size;
	}

	// Overriding Object Behavior *******************************************

	/**
	 * Indicates whether some other BoundedSize is "equal to" this one.
	 * @param object the object with which to compare
	 * @return <code>true</code> if this object is the same as the object
	 * argument, <code>false</code> otherwise.
	 * @see Object#hashCode()
	 * @see java.util.Hashtable
	 */
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof BoundedSize)) {
			return false;
		}
		BoundedSize size = (BoundedSize) object;
		return basis.equals(size.basis)
				&& ((lowerBound == null && size.lowerBound == null) || (lowerBound != null && lowerBound
						.equals(size.lowerBound)))
				&& ((upperBound == null && size.upperBound == null) || (upperBound != null && upperBound
						.equals(size.upperBound)));
	}

	/**
	 * Returns a hash code value for the object. This method is supported for
	 * the benefit of hashtables such as those provided by
	 * <code>java.util.Hashtable</code>.
	 * @return a hash code value for this object.
	 * @see Object#equals(Object)
	 * @see java.util.Hashtable
	 */
	public int hashCode() {
		int hashValue = basis.hashCode();
		if (lowerBound != null) {
			hashValue = hashValue * 37 + lowerBound.hashCode();
		}
		if (upperBound != null) {
			hashValue = hashValue * 37 + upperBound.hashCode();
		}
		return hashValue;
	}

	/**
	 * Returns a string representation of this size object.
	 * <strong>Note:</strong> The string representation may change at any time.
	 * It is strongly recommended to not use this string for parsing purposes.
	 * @return a string representation of the constant size
	 */
	public String toString() {
		if (lowerBound != null) {
			if (upperBound == null) {
				return "max(" + basis + ';' + lowerBound + ')';
			} else {
				return "max(" + basis + ';' + "min(" + lowerBound + ';'
						+ upperBound + "))";
			}
		} else if (upperBound != null) {
			return "min(" + basis + ';' + upperBound + ')';
		} else {
			return "bounded(" + basis + ')';
		}
	}

}
