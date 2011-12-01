/*
 * Created on Apr 19, 2005
 */

package examples.methodsEditor.wizard;

import examples.methodsEditor.core.Method;
import examples.methodsEditor.core.MethodsModel;

/**
 * @author michaek
 */
public class MethodsWizardModel extends MethodsModel {

	private MethodsNewWizard wizard;

	/**
	 * @param wizard
	 */
	public MethodsWizardModel(MethodsNewWizard wizard) {
		this.wizard = wizard;
	}

	/*
	 * (non-Javadoc)
	 * @see examples.methodsEditor.core.MethodsModel#addMethod(examples.methodsEditor.core.Method,
	 *      java.lang.Object)
	 */
	public void addMethod(Method method, Object source) {
		super.addMethod(method, source);
		wizard.methodsPage.setPageComplete();
	}

	/*
	 * (non-Javadoc)
	 * @see examples.methodsEditor.core.MethodsModel#removeMethod(examples.methodsEditor.core.Method,
	 *      java.lang.Object)
	 */
	public void removeMethod(Method method, Object source) {
		super.removeMethod(method, source);
		wizard.methodsPage.setPageComplete();
	}

	public void setInterfaceName(String newValue) {
		super.setInterfaceName(newValue);
		wizard.methodsPage.setPageComplete();
	}
}