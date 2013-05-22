/*
 * Created on May 2, 2005
 */

package examples.interfaceEditor.wizard;

import com.amdocs.studio.editors.forms.ADENewWizardPage;
import com.amdocs.studio.indexer.search.SearchType;

import examples.interfaceEditor.indexer.InterfaceIndexerService;
import examples.ui.ExamplesPlugin;

/**
 * The "New" wizard page allows setting the container for the new file as well as the file name. The page will only
 * accept file name without the extension.
 */

public class InterfaceNewWizardPage extends ADENewWizardPage {

	/**
	 * @param selection
	 */
	public InterfaceNewWizardPage() {
		super("Interface", ExamplesPlugin.getDefault().getImageDescriptor(ExamplesPlugin.NEWINT_WIZ));
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADENewWizardPage#getSearchType()
	 */
	protected SearchType getSearchType() {
		return InterfaceIndexerService.INTERFACE_TYPE;
	}

}