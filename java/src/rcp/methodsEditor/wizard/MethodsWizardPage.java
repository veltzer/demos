/*
 * Created on Apr 19, 2005
 */

package examples.methodsEditor.wizard;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;

import com.amdocs.studio.editors.forms.ADEFormWizardPage;

import examples.methodsEditor.editor.pages.MethodsPage;
import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class MethodsWizardPage extends ADEFormWizardPage {

	public MethodsWizardPage(MethodsWizardModel model) {
		super(MethodsWizardPage.class.toString(), "Methods", ExamplesPlugin.getDefault().getImageDescriptor(
			ExamplesPlugin.NEWMETH_WIZ), "Add and edit Methods.", new MethodsPage(model));
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADEFormWizardPage#pageEntered()
	 */
	protected void pageEntered() {
		MethodsPage methodsPage = (MethodsPage) getADEFormPage();
		String containerName = ((NewWizardPage) getPreviousPage()).getContainerName();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			methodsPage.setProject(null);
		}
		methodsPage.setProject(resource.getProject());
	}

}