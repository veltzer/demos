/*
 * Created on Apr 14, 2005
 */

package examples.methodsEditor.editor.pages;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.amdocs.studio.editors.forms.ADEFormEditor;
import com.amdocs.studio.editors.forms.ADEFormPage;

import examples.methodsEditor.core.Method;
import examples.methodsEditor.core.MethodsModel;
import examples.methodsEditor.editor.sections.MethodInterfaceSection;
import examples.methodsEditor.editor.sections.MethodModifiersSection;
import examples.methodsEditor.editor.sections.MethodsListSection;
import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class MethodsPage extends ADEFormPage {

	public static final String PAGE_ID = "methods_page_id";
	private MethodInterfaceSection methodInterfaceSection;
	private MethodsListSection methodsListSection;
	private MethodModifiersSection methodModifiersSection;
	private MethodsModel model;
	private IProject project;

	/**
	 * @param editor
	 * @param model
	 */
	public MethodsPage(ADEFormEditor editor, MethodsModel model) {
		super(editor, PAGE_ID, ExamplesPlugin.getDefault().getString("methods.page.title"));
		this.model = model;
	}

	/**
	 * @param model
	 */
	public MethodsPage(MethodsModel model) {
		super(PAGE_ID, ExamplesPlugin.getDefault().getString("methods.page.title"));
		this.model = model;
	}

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
		createMethodsListSection(composite, toolkit);
		createMethodModifiersSection(composite, toolkit);
		createInterfaceSection(composite, toolkit);
	}

	private void createInterfaceSection(Composite parent, FormToolkit toolkit) {
		methodInterfaceSection = new MethodInterfaceSection(this, parent, toolkit);
		addSection(methodInterfaceSection);
	}

	private void createMethodsListSection(Composite parent, FormToolkit toolkit) {
		methodsListSection = new MethodsListSection(this, parent, toolkit);
		addSection(methodsListSection);
	}

	private void createMethodModifiersSection(Composite parent, FormToolkit toolkit) {
		methodModifiersSection = new MethodModifiersSection(this, parent, toolkit);
		addSection(methodModifiersSection);
	}

	/**
	 * @return MethodsModel
	 */
	public MethodsModel getModel() {
		return model;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormPage#selectReveal(java.lang.Object)
	 */
	public boolean selectReveal(Object object) {
		if (object instanceof Method) {
			return methodsListSection.selectReveal(object);
		}
		return super.selectReveal(object);
	}

	public IProject getProject() {
		if (editor != null) {
			return editor.getContext().getProject();
		}
		return project;
	}

	public void setProject(IProject project) {
		this.project = project;
	}
}