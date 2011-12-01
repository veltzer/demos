/*
 * Created on May 4, 2005
 */

package examples.methodsEditor.indexer;

import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.amdocs.studio.indexer.search.ISearchScope;
import com.amdocs.studio.indexer.search.SearchEngine;
import com.amdocs.studio.indexer.ui.OpenTypeSelectionDialog;
import com.amdocs.studio.indexer.ui.actions.AbstractOpenAction;

/**
 * @author michaek
 */
public class OpenMethodsAction extends AbstractOpenAction {

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.indexer.ui.actions.AbstractOpenAction#getOpenTypeSelectionDialog(org.eclipse.swt.widgets.Shell)
	 */
	public OpenTypeSelectionDialog getOpenTypeSelectionDialog(Shell shell) {
		return getMethodsSelectionDialog(SearchEngine.createWorkspaceScope());
	}

	public static final OpenTypeSelectionDialog getMethodsSelectionDialog(ISearchScope scope) {
		Shell shell = Display.getCurrent().getActiveShell();
		OpenTypeSelectionDialog dialog = new OpenTypeSelectionDialog(shell, new ProgressMonitorDialog(shell),
			scope, MethodsIndexerService.METHODS_TYPE);
		dialog.setMatchEmptyString(true);
		dialog.setTitle("Choose Methods");
		dialog.setMessage("Choose a Methods File from list");
		return dialog;
	}
}