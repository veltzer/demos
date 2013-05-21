/*
 * Created on May 2, 2005
 */

package examples.interfaceEditor.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.amdocs.studio.editors.forms.ADEFormEditor;
import com.amdocs.studio.editors.forms.ADEFormPage;
import com.amdocs.studio.editors.forms.ADESectionPart;
import com.amdocs.studio.editors.util.BorderPainter;
import com.amdocs.studio.editors.widgets.TextEntry;
import com.amdocs.studio.indexer.search.IMatch;
import com.amdocs.studio.indexer.search.ISearchScope;
import com.amdocs.studio.indexer.search.SearchEngine;
import com.amdocs.studio.indexer.ui.OpenTypeSelectionDialog;
import com.amdocs.studio.indexer.ui.actions.OpenDeclarationByIndexer;

import examples.interfaceEditor.core.Interface;
import examples.interfaceEditor.core.InterfaceModel;
import examples.interfaceEditor.indexer.InterfaceIndexerService;
import examples.interfaceEditor.indexer.InterfaceToolTipListener;
import examples.interfaceEditor.indexer.InterfaceUIContributer;
import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class InterfaceGeneralSection extends ADESectionPart implements IPropertyChangeListener {

	private Button buttonExtends;
	private TextEntry entryExtends;
	private Button buttonSelect;

	/**
	 * @param page
	 * @param parent
	 * @param toolkit
	 * @param style
	 */
	public InterfaceGeneralSection(ADEFormPage page, Composite parent, FormToolkit toolkit, int style) {
		super(page, parent, toolkit, style);
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADESectionPart#createClient(org.eclipse.ui.forms.widgets.Section,
	 *      org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	protected void createClient(Section section, Composite composite, FormToolkit toolkit) {
		section.setText(ExamplesPlugin.getDefault().getString("InterfaceGeneralSection.section.title")); //$NON-NLS-1$
		section.setDescription("Select an Interface to extend");

		GridData gd = new GridData(GridData.FILL_BOTH);
		section.setLayoutData(gd);

		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		composite.setLayout(layout);

		createControls(composite, toolkit);
		composite.addPaintListener(new BorderPainter());

		InterfaceModel model = ((InterfaceGeneralPage) getPage()).getModel();
		model.addPropertyChangedListener(this);

		initSection();
	}

	/**
	 * @param parent
	 * @param toolkit
	 */
	private void createControls(Composite parent, FormToolkit toolkit) {
		GridData data;

		buttonExtends = new Button(parent, SWT.CHECK | SWT.FLAT);
		buttonExtends.setText(ExamplesPlugin.getDefault().getString("InterfaceGeneralSection.btn.extends.label")); //$NON-NLS-1$
		buttonExtends.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				InterfaceModel model = ((InterfaceGeneralPage) getPage()).getModel();
				model.extendsSelected();
			}
		});
		data = new GridData();
		data.horizontalSpan = 3;
		buttonExtends.setLayoutData(data);
		toolkit.adapt(buttonExtends, true, true);

		Label label = new Label(parent, SWT.NONE);
		label.setText("Extended Interface Name:");
		toolkit.adapt(label, false, false);
		Color colorTitle = toolkit.getColors().getColor(FormColors.TITLE);
		label.setForeground(colorTitle);

		Text text = new Text(parent, SWT.FLAT);
		text.setEditable(false);
		data = new GridData(GridData.FILL_HORIZONTAL);
		text.setLayoutData(data);
		toolkit.adapt(text, false, false);
		entryExtends = new TextEntry(text);

		ADEFormEditor editor = (ADEFormEditor) ((InterfaceGeneralPage) getPage()).getEditor();
		if (editor != null) {
			IProject project = editor.getContext().getProject();
			text
				.addKeyListener(new OpenDeclarationByIndexer(project, InterfaceIndexerService.INTERFACE_EXTENSION));
			text.addMouseTrackListener(new InterfaceToolTipListener(project,
				InterfaceIndexerService.INTERFACE_EXTENSION));
		}

		buttonSelect = new Button(parent, SWT.PUSH | SWT.FLAT);
		buttonSelect.setText("...");
		toolkit.adapt(buttonSelect, true, true);
		buttonSelect.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				IProject project = ((InterfaceGeneralPage) getPage()).getProject();
				if (project == null) {
					return;
				}

				ISearchScope scope = SearchEngine.createModuleSearchScope(project, SearchEngine.SEARCH_FULL);
				OpenTypeSelectionDialog dialog = InterfaceUIContributer.getInterfaceSelectionDialog(scope);
				ADEFormEditor editor = (ADEFormEditor) ((InterfaceGeneralPage) getPage()).getEditor();
				if (editor != null) {
					List list = new ArrayList();

					String name = (editor).getContext().getName();
					int index = name.lastIndexOf('.');
					if (index > 0) {
						name = name.substring(0, index);
					}
					list.add(name);
					dialog.setFilterNames(list);
				}
				if (dialog.open() != IDialogConstants.OK_ID)
					return;

				Object[] types = dialog.getResult();
				IMatch match = (IMatch) types[0];
				String interfaceName = match.getName();
				InterfaceModel model = ((InterfaceGeneralPage) getPage()).getModel();
				model.setExtendedInterfaceName(interfaceName);
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADESectionPart#validateSection()
	 */
	public IStatus[] validateSection() {
		InterfaceModel model = ((InterfaceGeneralPage) getPage()).getModel();
		return model.validate(InterfaceModel.VALIDATE_EXTENDS);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getProperty().equals(InterfaceModel.EXTEND_SELECTED)) {
			if (((Boolean) event.getNewValue()).booleanValue()) {
				enableWidgets(true);
			} else {
				entryExtends.setValue("", true);
				enableWidgets(false);
			}
			entryExtends.getControl().getParent().redraw();
			entryExtends.getControl().getParent().layout();
		} else if (event.getProperty().equals(InterfaceModel.EXTEND_NAME_CHANGED)) {
			entryExtends.setValue((String) event.getNewValue(), true);
		}
	}

	private void initSection() {
		InterfaceModel model = ((InterfaceGeneralPage) getPage()).getModel();
		Interface lInterface = model.getInterface();
		if (lInterface != null) { //if in editor
			if (lInterface.getExtendedInterfaceName().length() > 0) {
				entryExtends.setValue(lInterface.getExtendedInterfaceName(), true);
				buttonExtends.setSelection(true);
			} else {
				enableWidgets(false);
			}
		}
	}

	private void enableWidgets(boolean enable) {
		entryExtends.getControl().setEnabled(enable);
		buttonSelect.setEnabled(enable);
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADESectionPart#refreshEnable(org.eclipse.ui.forms.widgets.Section,
	 *      boolean)
	 */
	public void refreshEnable(Section section, boolean enable) {
		buttonExtends.setEnabled(enable);
		if (enable) {
			enableWidgets(buttonExtends.getSelection());
		} else {
			enableWidgets(enable);
		}

	}

}