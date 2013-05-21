/*
 * Created on May 2, 2005
 */

package examples.interfaceEditor.editor;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.amdocs.studio.editors.forms.ADEFormEditor;
import com.amdocs.studio.editors.forms.ADEFormPage;

import examples.interfaceEditor.core.InterfaceModel;
import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class InterfaceGeneralPage extends ADEFormPage {

	public static final String PAGE_ID = "interface_general_page_id";
	private InterfaceGeneralSection generalSection;
	private InterfaceDescriptionlSection descriptionlSection;
	private InterfaceModel model;
	private IProject project;

	/**
	 * @param editor
	 * @param interfaceModel
	 */
	public InterfaceGeneralPage(ADEFormEditor editor, InterfaceModel interfaceModel) {
		super(editor, PAGE_ID, ExamplesPlugin.getDefault().getString("interface.general.page.title"));
		model = interfaceModel;
	}

	/**
	 * @param interfaceModel
	 */
	public InterfaceGeneralPage(InterfaceModel interfaceModel) {
		super(PAGE_ID, ExamplesPlugin.getDefault().getString("interface.general.page.title"));
		this.model = interfaceModel;
	}

	public void fillBody(IManagedForm managedForm) {
		// composite
		Composite composite = managedForm.getForm().getBody();
		GridLayout layout = new GridLayout();
		layout.marginHeight = 10;
		layout.marginWidth = 10;
		layout.verticalSpacing = 10;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		FormToolkit toolkit = managedForm.getToolkit();

		// sections
		createGeneralSection(composite, toolkit);
		createHelpHyperlink("/org.eclipse.pde.doc.user/guide/pde_running.htm", generalSection, toolkit);

		createDescriptionSection(composite, toolkit);
		createHelpHyperlink("/org.eclipse.pde.doc.user/guide/pde_manifest_extensionpoints.htm",
			descriptionlSection, toolkit);
	}

	/**
	 * @param parent
	 * @param lToolkit
	 */
	private void createDescriptionSection(Composite parent, FormToolkit lToolkit) {
		descriptionlSection = new InterfaceDescriptionlSection(this, parent, lToolkit);
		addSection(descriptionlSection);
	}

	/**
	 * @param parent
	 * @param lToolkit
	 */
	private void createGeneralSection(Composite parent, FormToolkit lToolkit) {
		int style = 0;
		if (editor != null) {
			style = Section.TITLE_BAR | Section.DESCRIPTION | Section.TWISTIE;
		} else {
			style = Section.TITLE_BAR | Section.DESCRIPTION;
		}
		generalSection = new InterfaceGeneralSection(this, parent, lToolkit, style);
		addSection(generalSection);
	}

	/**
	 * @return InterfaceModel
	 */
	public InterfaceModel getModel() {
		return model;
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