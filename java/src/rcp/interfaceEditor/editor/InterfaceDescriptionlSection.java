/*
 * Created on May 2, 2005
 */

package examples.interfaceEditor.editor;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.amdocs.studio.editors.forms.ADEFormPage;
import com.amdocs.studio.editors.forms.ADESectionPart;
import com.amdocs.studio.editors.widgets.TextPreview;

import examples.interfaceEditor.core.Interface;
import examples.interfaceEditor.core.InterfaceModel;
import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class InterfaceDescriptionlSection extends ADESectionPart {

	private TextPreview textPreviewDescription;

	/**
	 * @param page
	 * @param parent
	 * @param toolkit
	 */
	public InterfaceDescriptionlSection(ADEFormPage page, Composite parent, FormToolkit toolkit) {
		super(page, parent, toolkit, Section.TITLE_BAR | Section.DESCRIPTION);
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADESectionPart#createClient(org.eclipse.ui.forms.widgets.Section,
	 *      org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	protected void createClient(Section section, Composite composite, FormToolkit toolkit) {
		section.setText(ExamplesPlugin.getDefault().getString("InterfaceDescriptionlSection.section.title")); //$NON-NLS-1$
		section.setDescription("Describe the Interface. Use HTML format.");

		GridData gd = new GridData(GridData.FILL_BOTH);
		section.setLayoutData(gd);

		GridLayout layout = new GridLayout();
		composite.setLayout(layout);

		createControls(composite, toolkit);
		toolkit.paintBordersFor(composite);

		initSection();
	}

	/**
	 * @param parent
	 * @param toolkit
	 */
	private void createControls(Composite parent, FormToolkit toolkit) {
		textPreviewDescription = new TextPreview(parent, SWT.FLAT, toolkit);
		textPreviewDescription.setLayoutData(new GridData(GridData.FILL_BOTH));
		textPreviewDescription.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				InterfaceModel model = ((InterfaceGeneralPage) getPage()).getModel();
				model.setDescription(textPreviewDescription.getText());
			}
		});

		toolkit.adapt(textPreviewDescription);
		toolkit.paintBordersFor(textPreviewDescription);
	}

	private void initSection() {
		InterfaceModel model = ((InterfaceGeneralPage) getPage()).getModel();
		Interface lInterface = model.getInterface();
		if (lInterface != null) { //if in editor
			if (lInterface.getDescription().length() > 0) {
				textPreviewDescription.updateEditorInput(lInterface.getDescription());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADESectionPart#validateSection()
	 */
	public IStatus[] validateSection() {
		InterfaceModel model = ((InterfaceGeneralPage) getPage()).getModel();
		return model.validate(InterfaceModel.VALIDATE_DESCRIPTION);
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADESectionPart#refreshEnable(org.eclipse.ui.forms.widgets.Section,
	 *      boolean)
	 */
	public void refreshEnable(Section section, boolean enable) {
		textPreviewDescription.setEnabled(enable);
	}

}