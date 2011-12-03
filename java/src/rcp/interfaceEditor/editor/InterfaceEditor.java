/*
 * Created on May 2, 2005
 */

package examples.interfaceEditor.editor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.amdocs.studio.editors.forms.ADEFormEditor;
import com.amdocs.studio.editors.forms.ADEStatus;
import com.amdocs.studio.utils.ADEPlugin;

import examples.interfaceEditor.core.InterfaceModel;
import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class InterfaceEditor extends ADEFormEditor {

	private InterfaceGeneralPage generalPage;
	private InterfaceModel model;

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADEFormEditor#addPages()
	 */
	protected void addPages() {
		try {
			generalPage = new InterfaceGeneralPage(this, model);
			addEditorPage(generalPage, ExamplesPlugin.getDefault().getString("interface.general.page.label"));
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		super.addPages();
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADEFormEditor#validate()
	 */
	public ADEStatus[] validate() {
		List statuses = new ArrayList();

		IStatus[] statusList = model.validate(InterfaceModel.VALIDATE_EXTENDS);
		if (statusList.length > 0) {
			statuses.add(new ADEStatus(statusList[0], 0, 0, 2));
		}

		statusList = model.validate(InterfaceModel.VALIDATE_DESCRIPTION);
		if (statusList.length > 0) {
			statuses.add(new ADEStatus(statusList[0], 0, 1, 1));
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
		model = new InterfaceModel(this);
		model.load(getContext().getInputStream());
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

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADEFormEditor#getPlugin()
	 */
	protected ADEPlugin getPlugin() {
		return ExamplesPlugin.getDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADEFormEditor#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class key) {
		if (key.equals(IContentOutlinePage.class)) {
			return null;
		}
		return super.getAdapter(key);
	}
}