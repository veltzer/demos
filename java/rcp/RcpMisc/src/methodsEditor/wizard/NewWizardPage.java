
package examples.methodsEditor.wizard;

import org.eclipse.swt.widgets.Text;

import com.amdocs.studio.editors.forms.ADENewWizardPage;
import com.amdocs.studio.editors.widgets.JavaFieldEntry;
import com.amdocs.studio.editors.widgets.TextEntry;
import com.amdocs.studio.editors.widgets.TextStyle;
import com.amdocs.studio.indexer.search.SearchType;

import examples.methodsEditor.indexer.MethodsIndexerService;
import examples.ui.ExamplesPlugin;

/**
 * The "New" wizard page allows setting the container for the new file as well as the file name. The page will only
 * accept file name without the extension.
 */

public class NewWizardPage extends ADENewWizardPage {

	/**
	 * Constructor for SampleNewWizardPage.
	 */
	public NewWizardPage() {
		super("Methods", ExamplesPlugin.getDefault().getImageDescriptor(ExamplesPlugin.NEWMETH_WIZ));
		setDescription("This wizard creates a new file with *.methods extension that can be opened by a Methods editor.");
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADENewWizardPage#createFileTextEntry(org.eclipse.swt.widgets.Text)
	 */
	protected TextEntry createFileTextEntry(Text text) {
		return new JavaFieldEntry(text, nameLabel.toLowerCase() + "Methods", TextStyle.createTypeStyle()); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADENewWizardPage#getSearchType()
	 */
	protected SearchType getSearchType() {
		return MethodsIndexerService.METHODS_TYPE;
	}

}