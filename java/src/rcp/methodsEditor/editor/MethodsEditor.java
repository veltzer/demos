/*
 * Created on Apr 14, 2005
 */

package examples.methodsEditor.editor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.amdocs.studio.editors.forms.ADEFormEditor;
import com.amdocs.studio.editors.forms.ADEStatus;
import com.amdocs.studio.editors.forms.IModelChangeProvider;
import com.amdocs.studio.utils.ADEPlugin;

import examples.methodsEditor.core.Method;
import examples.methodsEditor.core.MethodsModel;
import examples.methodsEditor.editor.pages.MethodsPage;
import examples.methodsEditor.outline.MethodsContentOutline;
import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class MethodsEditor extends ADEFormEditor {

	private MethodsPage methodsPage;
	//	private ParametersPage parametersPage;
	int[] pageIndexes = new int[]{-1, -1};
	private MethodsModel model;

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADEFormEditor#addPages()
	 */
	protected void addPages() {
		try {
			methodsPage = new MethodsPage(this, model);
			addEditorPage(methodsPage, ExamplesPlugin.getDefault().getString("methods.page.label"));

			//			parametersPage = new ParametersPage(this);
			//			addEditorPage(parametersPage,
			// ExamplesPlugin.getDefault().getString("parameters.page.label"));
		} catch (PartInitException e) {
			for (int i = 0; i < pageIndexes.length; i++) {
				if (pageIndexes[i] > -1) {
					removePage(pageIndexes[i]);
				}
			}
			e.printStackTrace();
		}
		super.addPages();

		if (isEnabled()) {
			sourcePage.addParticipant(model); //add model to be updated in order to keep the source outline page
			// updated.
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADEFormEditor#validate()
	 */
	public ADEStatus[] validate() {
		List statuses = new ArrayList();

		IStatus[] statusList = model.validate(MethodsModel.VALIDATE_METHODS);
		if (statusList.length > 0) {
			statuses.add(new ADEStatus(statusList[0], 0, 0, 1));
		}

		statusList = model.validate(MethodsModel.VALIDATE_INTERFACE);
		if (statusList.length > 0) {
			statuses.add(new ADEStatus(statusList[0], 0, 2, 1));
		}

		return (ADEStatus[]) statuses.toArray(new ADEStatus[statuses.size()]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADEFormEditor#needValidateOnOpen()
	 */
	public boolean needValidateOnOpen() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADEFormEditor#needValidateOnSave()
	 */
	public boolean needValidateOnSave() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADEFormEditor#getMarkerType()
	 */
	protected String getMarkerType() {
		return "com.amdocs.studio.examples.method_example";
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADEFormEditor#loadModel()
	 */
	protected void loadModel() throws Exception {
		model = new MethodsModel(this);
		model.load(getContext().getInputStream());
		model.addPropertyChangedListener(new IPropertyChangeListener() {

			//update selection (for outline)
			public void propertyChange(PropertyChangeEvent event) {
				if (event.getProperty().equals(MethodsModel.METHOD_SELECTED)) {
					int index = ((Integer) event.getNewValue()).intValue();
					Method[] methods = model.getMethods();
					if (index < methods.length) {
						Method method = methods[index];
						setSelection(new StructuredSelection(method));
					}
				}
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADEFormEditor#getContent()
	 */
	protected String getContent() {
		StringWriter swriter = new StringWriter();
		PrintWriter writer = new PrintWriter(swriter);
		model.save(writer);
		writer.flush();
		try {
			swriter.close();
			return swriter.toString();
		} catch (IOException e) {
			ExamplesPlugin.getDefault().logError(e);
		}
		return "";
	}

	protected ADEPlugin getPlugin() {
		return ExamplesPlugin.getDefault();
	}

	public IModelChangeProvider getModelChangeProvider() {
		return model;
	}

	protected IContentOutlinePage createContentOutline() {
		return new MethodsContentOutline(this);
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADEFormEditor#createSourceOutline()
	 */
	protected IContentOutlinePage createSourceOutline() {
		return new MethodsContentOutline(this, sourcePage);
	}

}