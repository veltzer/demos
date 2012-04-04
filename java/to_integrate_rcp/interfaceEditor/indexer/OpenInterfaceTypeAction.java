/*
 * Created on May 9, 2005
 */

package examples.interfaceEditor.indexer;

import com.amdocs.studio.indexer.ui.OpenTypeAction;

import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class OpenInterfaceTypeAction extends OpenTypeAction {

	public OpenInterfaceTypeAction() {
		super(new OpenInterfaceAction(), "Interface", ExamplesPlugin.getDefault().getImageDescriptor(
			ExamplesPlugin.INT_EDITOR));
	}
}