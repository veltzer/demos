/*
 * Created on May 8, 2005
 */

package examples.methodsEditor.indexer;

import com.amdocs.studio.indexer.ui.OpenTypeAction;

import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class OpenMethodsTypeAction extends OpenTypeAction {

	public OpenMethodsTypeAction() {
		super(new OpenMethodsAction(), "Methods", ExamplesPlugin.getDefault().getImageDescriptor(
			ExamplesPlugin.METH_EDITOR));
	}

}