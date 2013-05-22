/*
 * Created on Apr 17, 2005
 */

package examples.methodsEditor.editor;

import org.eclipse.swt.graphics.Image;

import com.amdocs.studio.editors.forms.BasicLabelProvider;

import examples.methodsEditor.core.Method;

/**
 * @author michaek
 */
public class MethodsLabelProvider extends BasicLabelProvider {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object element) {
		if (element instanceof Method) {
			return ((Method) element).getName();
		}
		return super.getText(element);
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.BasicLabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object element) {
		if (element instanceof Method) {
			return ((Method) element).getImage();
		}
		return super.getImage(element);
	}
}