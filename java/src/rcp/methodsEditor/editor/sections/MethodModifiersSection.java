/*
 * Created on Apr 17, 2005
 */

package examples.methodsEditor.editor.sections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.amdocs.studio.editors.forms.ADEFormPage;
import com.amdocs.studio.editors.forms.ADESectionPart;

import examples.methodsEditor.core.Method;
import examples.methodsEditor.core.MethodsModel;
import examples.methodsEditor.editor.pages.MethodsPage;
import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class MethodModifiersSection extends ADESectionPart implements IPropertyChangeListener {

	private Button buttonAbstract;
	private Button buttonFinal;
	private Button buttonStatic;
	private CCombo comboAccess;

	/**
	 * @param page
	 * @param parent
	 * @param toolkit
	 */
	public MethodModifiersSection(ADEFormPage page, Composite parent, FormToolkit toolkit) {
		super(page, parent, toolkit, Section.TITLE_BAR | Section.DESCRIPTION);
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADESectionPart#createClient(org.eclipse.ui.forms.widgets.Section,
	 *      org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	protected void createClient(Section section, Composite composite, FormToolkit toolkit) {
		section.setText(ExamplesPlugin.getDefault().getString("MethodModifiersSection.section.title")); //$NON-NLS-1$
		section.setDescription("Select method modifiers.");

		GridData gd = new GridData(GridData.FILL_BOTH);
		section.setLayoutData(gd);

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);

		createControls(composite, toolkit);
		toolkit.paintBordersFor(composite);

		MethodsModel model = ((MethodsPage) getPage()).getModel();
		model.addPropertyChangedListener(this);

		initSection();

	}

	private void initSection() {
		comboAccess.removeAll();
		String[] accessModifiers = new String[]{Method.ACCESS_PRIVATE, Method.ACCESS_DEFAULT,
				Method.ACCESS_PROTECTED, Method.ACCESS_PUBLIC};
		for (int i = 0; i < accessModifiers.length; i++) {
			comboAccess.add(accessModifiers[i]);
		}
		MethodsModel model = ((MethodsPage) getPage()).getModel();
		Method[] methods = model.getMethods();
		if (methods.length > 0) {
			Method method = methods[0];
			initValues(method);
			comboAccess.setText(method.getAccess());
		} else {
			enableWidgets(false);
		}
	}

	/**
	 * @param parent
	 * @param toolkit
	 */
	private void createControls(Composite parent, FormToolkit toolkit) {
		GridData data;

		buttonAbstract = new Button(parent, SWT.CHECK | SWT.FLAT);
		buttonAbstract.setText(ExamplesPlugin.getDefault().getString("MethodModifiersSection.btn.abstract.label")); //$NON-NLS-1$
		buttonAbstract.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				MethodsModel model = ((MethodsPage) getPage()).getModel();
				model.abstractSelected();
			}
		});
		data = new GridData();
		data.horizontalSpan = 2;
		buttonAbstract.setLayoutData(data);
		toolkit.adapt(buttonAbstract, true, true);

		buttonFinal = new Button(parent, SWT.CHECK | SWT.FLAT);
		buttonFinal.setText(ExamplesPlugin.getDefault().getString("MethodModifiersSection.btn.final.label")); //$NON-NLS-1$
		buttonFinal.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				MethodsModel model = ((MethodsPage) getPage()).getModel();
				model.finalSelected();
			}
		});
		data = new GridData();
		data.horizontalSpan = 2;
		buttonFinal.setLayoutData(data);
		toolkit.adapt(buttonFinal, true, true);

		buttonStatic = new Button(parent, SWT.CHECK | SWT.FLAT);
		buttonStatic.setText(ExamplesPlugin.getDefault().getString("MethodModifiersSection.btn.static.label")); //$NON-NLS-1$
		buttonStatic.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				MethodsModel model = ((MethodsPage) getPage()).getModel();
				model.staticSelected();
			}
		});
		data = new GridData();
		data.horizontalSpan = 2;
		buttonStatic.setLayoutData(data);
		toolkit.adapt(buttonStatic, true, true);

		Label label = new Label(parent, SWT.NONE);
		label.setText("Access:");
		toolkit.adapt(label, false, false);
		Color colorTitle = toolkit.getColors().getColor(FormColors.TITLE);
		label.setForeground(colorTitle);

		comboAccess = new CCombo(parent, SWT.READ_ONLY | SWT.FLAT);
		toolkit.adapt(comboAccess, true, true);
		data = new GridData(GridData.FILL_HORIZONTAL);
		comboAccess.setLayoutData(data);
		comboAccess.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				MethodsModel model = ((MethodsPage) getPage()).getModel();
				model.setAccess(comboAccess.getText());
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADESectionPart#validateSection()
	 */
	public IStatus[] validateSection() {
		// no validation needed
		return new IStatus[0];
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getProperty().equals(MethodsModel.METHOD_ADDED)) {
			enableWidgets(true);
		} else if (event.getProperty().equals(MethodsModel.METHOD_REMOVED)) {
			MethodsModel model = ((MethodsPage) getPage()).getModel();
			Method[] methods = model.getMethods();
			if (methods.length == 0) {
				enableWidgets(false);
			}
		} else if (event.getProperty().equals(MethodsModel.METHOD_SELECTED)) {
			int index = ((Integer) event.getNewValue()).intValue();
			MethodsModel model = ((MethodsPage) getPage()).getModel();
			Method[] methods = model.getMethods();
			if (index < methods.length) {
				Method method = methods[index];
				initValues(method);
				comboAccess.setText(method.getAccess());
			}
		}
	}

	private void enableWidgets(boolean enable) {
		buttonAbstract.setEnabled(enable);
		buttonFinal.setEnabled(enable);
		buttonStatic.setEnabled(enable);
		comboAccess.setEnabled(enable);
		if (!enable) {
			buttonAbstract.setSelection(false);
			buttonFinal.setSelection(false);
			buttonStatic.setSelection(false);
		}
	}

	/**
	 * @param method
	 */
	private void initValues(Method method) {
		buttonAbstract.setSelection(method.isAbstract());
		buttonFinal.setSelection(method.isFinal());
		buttonStatic.setSelection(method.isStatic());
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADESectionPart#refreshEnable(org.eclipse.ui.forms.widgets.Section,
	 *      boolean)
	 */
	public void refreshEnable(Section section, boolean enable) {
		if (enable) {
			MethodsModel model = ((MethodsPage) getPage()).getModel();
			Method[] methods = model.getMethods();
			enableWidgets(methods.length > 0);
		} else {
			super.refreshEnable(section, enable);
		}
	}
}