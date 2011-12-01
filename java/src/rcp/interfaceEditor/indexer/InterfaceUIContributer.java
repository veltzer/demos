/*
 * Created on May 4, 2005
 */

package examples.interfaceEditor.indexer;

import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.amdocs.studio.indexer.search.ISearchScope;
import com.amdocs.studio.indexer.search.SearchType;
import com.amdocs.studio.indexer.ui.IIndexerUI;
import com.amdocs.studio.indexer.ui.OpenTypeSelectionDialog;

import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class InterfaceUIContributer implements IIndexerUI {

	private SearchType[] TYPES = new SearchType[]{InterfaceIndexerService.INTERFACE_TYPE};

	public Image getImage(SearchType type) {
		return ExamplesPlugin.getDefault().getImage(ExamplesPlugin.INT_EDITOR);
	}

	public SearchType[] getSupportedTypes() {
		return TYPES;
	}

	public static final OpenTypeSelectionDialog getInterfaceSelectionDialog(ISearchScope scope) {
		Shell shell = Display.getCurrent().getActiveShell();
		OpenTypeSelectionDialog dialog = new OpenTypeSelectionDialog(shell, new ProgressMonitorDialog(shell),
			scope, InterfaceIndexerService.INTERFACE_TYPE);
		dialog.setMatchEmptyString(true);
		dialog.setTitle("Choose Interface");
		dialog.setMessage("Choose an Interface from list");
		return dialog;
	}
}