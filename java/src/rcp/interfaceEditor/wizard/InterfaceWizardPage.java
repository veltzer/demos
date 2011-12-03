/*
 * Created on May 2, 2005
 */

package examples.interfaceEditor.wizard;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;

import com.amdocs.studio.editors.forms.ADEFormWizardPage;

import examples.interfaceEditor.editor.InterfaceGeneralPage;
import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class InterfaceWizardPage extends ADEFormWizardPage {

	/**
	 * @param model
	 */
	public InterfaceWizardPage(InterfaceWizardModel model) {
		super(InterfaceWizardPage.class.toString(), "General", ExamplesPlugin.getDefault().getImageDescriptor(
			ExamplesPlugin.NEWINT_WIZ), "Set general information about the Interface", new InterfaceGeneralPage(
			model));
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADEFormWizardPage#pageEntered()
	 */
	protected void pageEntered() {
		InterfaceGeneralPage interfaceGeneralPage = (InterfaceGeneralPage) getADEFormPage();
		String containerName = ((InterfaceNewWizardPage) getPreviousPage()).getContainerName();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			interfaceGeneralPage.setProject(null);
		}
		interfaceGeneralPage.setProject(resource.getProject());
	}
}