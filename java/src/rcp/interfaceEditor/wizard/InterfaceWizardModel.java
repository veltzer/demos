/*
 * Created on May 2, 2005
 */

package examples.interfaceEditor.wizard;

import examples.interfaceEditor.core.InterfaceModel;

/**
 * @author michaek
 */
public class InterfaceWizardModel extends InterfaceModel {

	private InterfaceNewWizard wizard;

	/**
	 * @param interfaceNewWizard
	 */
	public InterfaceWizardModel(InterfaceNewWizard interfaceNewWizard) {
		wizard = interfaceNewWizard;
	}

	/*
	 * (non-Javadoc)
	 * @see examples.interfaceEditor.core.InterfaceModel#extendsSelected()
	 */
	public void extendsSelected() {
		super.extendsSelected();
		wizard.interfaceWizardPage.setPageComplete();
	}

	/*
	 * (non-Javadoc)
	 * @see examples.interfaceEditor.core.InterfaceModel#setExtendedInterfaceName(java.lang.String)
	 */
	public void setExtendedInterfaceName(String newValue) {
		super.setExtendedInterfaceName(newValue);
		wizard.interfaceWizardPage.setPageComplete();
	}

	/*
	 * (non-Javadoc)
	 * @see examples.interfaceEditor.core.InterfaceModel#setDescription(java.lang.String)
	 */
	public void setDescription(String newValue) {
		super.setDescription(newValue);
		wizard.interfaceWizardPage.setPageComplete();
	}

}