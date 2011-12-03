/*
 * Created on May 4, 2005
 */

package examples.interfaceEditor.indexer;

import org.eclipse.swt.widgets.Shell;

import com.amdocs.studio.indexer.search.SearchEngine;
import com.amdocs.studio.indexer.ui.OpenTypeSelectionDialog;
import com.amdocs.studio.indexer.ui.actions.AbstractOpenAction;

/**
 * @author michaek
 */
public class OpenInterfaceAction extends AbstractOpenAction {

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.indexer.ui.actions.AbstractOpenAction#getOpenTypeSelectionDialog(org.eclipse.swt.widgets.Shell)
	 */
	public OpenTypeSelectionDialog getOpenTypeSelectionDialog(Shell shell) {
		return InterfaceUIContributer.getInterfaceSelectionDialog(SearchEngine.createWorkspaceScope());
	}

}