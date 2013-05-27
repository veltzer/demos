package swing.layout_managers;

import java.awt.Component;
import java.awt.Container;
import java.util.List;

/**
 * An interface that describes sizes as used by the {@link FormLayout}:
 * component measuring sizes, constant sizes with value and unit, and bounded
 * sizes that provide lower and upper bounds for a size. <p> You can find a
 * motivation for the different <code>Size</code> types in the Forms article
 * that is part of the product documentation and that is available online too,
 * see <a href="http://www.jgoodies.com/articles/forms.pdf" >
 * http://www.jgoodies.com/articles/forms.pdf</a>.
 * @author Mark Veltzer <mark@veltzer.net>
 * @see Sizes
 * @see ConstantSize
 */

public interface Size {

	/**
	 * Computes and returns my maximum size applied to the given list of
	 * components using the specified measures. <p> Invoked by
	 * {@link swing.layout_managers.jgoodies.forms.layout.FormSpec} to determine
	 * the size of a column or row. This method is not intended to be called by
	 * API users, and it uses API invisible parameter types.
	 * @param container the layout container
	 * @param components the list of components used to compute the size
	 * @param minMeasure the measure that determines the minimum sizes
	 * @param prefMeasure the measure that determines the preferred sizes
	 * @param defaultMeasure the measure that determines the default sizes
	 * @return the maximum size in pixels for the given list of components
	 */
	int maximumSize(Container container, List<Component> components,
			FormLayout.Measure minMeasure, FormLayout.Measure prefMeasure,
			FormLayout.Measure defaultMeasure);

}
