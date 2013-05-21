/*
 * Created on Apr 17, 2005
 */

package examples.methodsEditor.outline;

import org.eclipse.jface.viewers.ILabelProvider;

import com.amdocs.studio.editors.forms.ADEFormEditor;
import com.amdocs.studio.editors.forms.ADEOutlinePage;
import com.amdocs.studio.editors.sourceeditor.SourcePage;

import examples.methodsEditor.core.Method;
import examples.methodsEditor.core.MethodsModel;
import examples.methodsEditor.core.ReturnValue;
import examples.methodsEditor.editor.MethodsLabelProvider;
import examples.methodsEditor.editor.pages.MethodsPage;

/**
 * @author michaek
 */
public class MethodsContentOutline extends ADEOutlinePage {

	/**
	 * @param editor
	 */
	public MethodsContentOutline(ADEFormEditor editor) {
		super(editor);
	}

	/**
	 * @param editor
	 * @param sourcePage
	 */
	public MethodsContentOutline(ADEFormEditor editor, SourcePage sourcePage) {
		super(editor, sourcePage);
	}

	protected Object[] getChildren(Object parent) {
		if (parent instanceof MethodsPage) {
			MethodsPage page = (MethodsPage) parent;
			MethodsModel model = page.getModel();
			return model.getMethods();
		}
		return super.getChildren(parent);
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADEOutlinePage#getParentPageId(java.lang.Object)
	 */
	protected String getParentPageId(Object item) {
		if (item instanceof Method) {
			return MethodsPage.PAGE_ID;
		}
		if (item instanceof ReturnValue) {
			return MethodsPage.PAGE_ID;
		}
		return super.getParentPageId(item);
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADEOutlinePage#createLabelProvider()
	 */
	protected ILabelProvider createLabelProvider() {
		return new MethodsLabelProvider();
	}
}