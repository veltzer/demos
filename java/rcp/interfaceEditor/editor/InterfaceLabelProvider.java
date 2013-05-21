/*
 * Created on May 2, 2005
 */

package examples.interfaceEditor.editor;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import examples.interfaceEditor.core.Interface;
import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class InterfaceLabelProvider extends LabelProvider {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object element) {
		if (element instanceof Interface) {
			return ExamplesPlugin.getDefault().getImage(ExamplesPlugin.INT_EDITOR);
		}
		return super.getImage(element);
	}
}