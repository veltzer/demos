/*
 * Created on May 4, 2005
 */

package examples.methodsEditor.indexer;

import org.eclipse.swt.graphics.Image;

import com.amdocs.studio.indexer.search.SearchType;
import com.amdocs.studio.indexer.ui.IIndexerUI;

import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class MethodsUIContributer implements IIndexerUI {

	private SearchType[] TYPES = new SearchType[]{MethodsIndexerService.METHODS_TYPE};

	public Image getImage(SearchType type) {
		return ExamplesPlugin.getDefault().getImage(ExamplesPlugin.METH_EDITOR);
	}

	public SearchType[] getSupportedTypes() {
		return TYPES;
	}
}