/*
 * Created on Apr 14, 2005
 */

package examples.methodsEditor.editor.pages;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.amdocs.studio.editors.forms.ADEFormEditor;
import com.amdocs.studio.editors.forms.ADEFormPage;

import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class ParametersPage extends ADEFormPage {

	public static final String PAGE_ID = "parameters_page_id";

	/**
	 * @param editor
	 */
	public ParametersPage(ADEFormEditor editor) {
		super(editor, PAGE_ID, ExamplesPlugin.getDefault().getString("parameters.page.title"));
	}

	/**
	 * @param managedForm
	 * @param toolkit
	 * @return Composite
	 */
	public void fillBody(IManagedForm managedForm) {
		Composite composite = managedForm.getForm().getBody();

		GridLayout layout = new GridLayout();
		layout.marginHeight = 10;
		layout.marginWidth = 10;
		layout.numColumns = 2;
		layout.verticalSpacing = 10;
		layout.horizontalSpacing = 10;
		composite.setLayout(layout);

		GridData data = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(data);

		FormToolkit toolkit = managedForm.getToolkit();
		// sections
		//		createMethodsListSection(managedForm, composite, toolkit);
		//		createGeneralMethodsInformationSection(managedForm, composite, toolkit);

		//createDescriptionSection(managedForm, composite, toolkit);

		//initSections();

	}
}