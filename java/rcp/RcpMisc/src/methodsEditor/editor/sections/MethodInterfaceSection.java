/*
 * Created on May 5, 2005
 */

package examples.methodsEditor.editor.sections;

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

import examples.interfaceEditor.indexer.InterfaceIndexerService;
import examples.interfaceEditor.indexer.InterfaceToolTipListener;
import examples.interfaceEditor.indexer.InterfaceUIContributer;
import examples.methodsEditor.core.MethodsModel;
import examples.methodsEditor.editor.pages.MethodsPage;
import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class MethodInterfaceSection extends ADESectionPart implements IPropertyChangeListener {

	private Button buttonSelect;
	private TextEntry entryInterface;

	/**
	 * @param page
	 * @param parent
	 * @param toolkit
	 */
	public MethodInterfaceSection(ADEFormPage page, Composite parent, FormToolkit toolkit) {
		super(page, parent, toolkit, Section.TITLE_BAR | Section.DESCRIPTION);
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADESectionPart#createClient(org.eclipse.ui.forms.widgets.Section,
	 *      org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	protected void createClient(Section section, Composite composite, FormToolkit toolkit) {
		section.setText(ExamplesPlugin.getDefault().getString("MethodInterfaceSection.section.title")); //$NON-NLS-1$
		section.setDescription("Select the Interface these methods belongs to.");

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		section.setLayoutData(gd);

		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		composite.setLayout(layout);

		createControls(composite, toolkit);
		toolkit.paintBordersFor(composite);

		MethodsModel model = ((MethodsPage) getPage()).getModel();
		model.addPropertyChangedListener(this);

		initSection();

	}

	private void createControls(Composite parent, FormToolkit toolkit) {
		GridData data;

		Label label = new Label(parent, SWT.NONE);
		label.setText("Interface Name:");
		toolkit.adapt(label, false, false);
		Color colorTitle = toolkit.getColors().getColor(FormColors.TITLE);
		label.setForeground(colorTitle);

		Text text = new Text(parent, SWT.FLAT);
		text.setEditable(false);
		data = new GridData(GridData.FILL_HORIZONTAL);
		text.setLayoutData(data);
		toolkit.adapt(text, false, false);
		entryInterface = new TextEntry(text);

		ADEFormEditor editor = (ADEFormEditor) ((MethodsPage) getPage()).getEditor();
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
				IProject project = ((MethodsPage) getPage()).getProject();
				if (project == null) {
					return;
				}

				ISearchScope scope = SearchEngine.createModuleSearchScope(project, SearchEngine.SEARCH_FULL);
				OpenTypeSelectionDialog dialog = InterfaceUIContributer.getInterfaceSelectionDialog(scope);
				ADEFormEditor editor = (ADEFormEditor) ((MethodsPage) getPage()).getEditor();
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
				MethodsModel model = ((MethodsPage) getPage()).getModel();
				model.setInterfaceName(interfaceName);
			}
		});

		parent.addPaintListener(new BorderPainter());
	}

	private void initSection() {
		MethodsModel model = ((MethodsPage) getPage()).getModel();
		String interfaceName = model.getInterfaceName();
		if (interfaceName.length() > 0) {
			entryInterface.setValue(interfaceName, true);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADESectionPart#validateSection()
	 */
	public IStatus[] validateSection() {
		MethodsModel model = ((MethodsPage) getPage()).getModel();
		return model.validate(MethodsModel.VALIDATE_INTERFACE);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getProperty().equals(MethodsModel.INTERFACE_CHANGED)) {
			entryInterface.setValue((String) event.getNewValue(), true);
		}
	}

	private void enableWidgets(boolean enable) {
		entryInterface.getControl().setEnabled(enable);
		buttonSelect.setEnabled(enable);
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADESectionPart#refreshEnable(org.eclipse.ui.forms.widgets.Section,
	 *      boolean)
	 */
	public void refreshEnable(Section section, boolean enable) {
		enableWidgets(enable);
	}
}