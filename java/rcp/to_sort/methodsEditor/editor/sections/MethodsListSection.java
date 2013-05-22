/*
 * Created on Apr 17, 2005
 */

package examples.methodsEditor.editor.sections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.amdocs.studio.editors.forms.ADEFormPage;
import com.amdocs.studio.editors.forms.ADESectionPart;
import com.amdocs.studio.editors.util.EditorsUtils;
import com.amdocs.studio.editors.widgets.CompositeTableWidget;
import com.amdocs.studio.editors.widgets.ICompositeTableWidgetListener;

import examples.methodsEditor.core.Method;
import examples.methodsEditor.core.MethodsModel;
import examples.methodsEditor.editor.MethodsLabelProvider;
import examples.methodsEditor.editor.pages.MethodsPage;
import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class MethodsListSection extends ADESectionPart implements IPropertyChangeListener {

	private final class MethodsViewerListener implements ICompositeTableWidgetListener {

		MethodsModel model = ((MethodsPage) getPage()).getModel();

		/*
		 * (non-Javadoc)
		 * @see com.amdocs.studio.editors.widgets.ICompositeTableWidgetListener#addButtonPushed()
		 */
		public void addButtonPushed() {
			if (addMethodDialog == null) {
				addMethodDialog = new AddMethodDialog(getSection().getShell());
			}
			addMethodDialog.setMethodList(methods);
			if (addMethodDialog.open() != Window.OK) {
				return;
			}
			Method method = new Method();
			method.setName(ExamplesPlugin.getDefault().getString("MethodsListSection.method.name.pattern")); //$NON-NLS-1$
			method.setName(addMethodDialog.getMethodName());
			model.addMethod(method, methodsViewer);
		}

		/*
		 * (non-Javadoc)
		 * @see com.amdocs.studio.editors.widgets.ICompositeTableWidgetListener#removeButtonPushed(java.lang.Object)
		 */
		public boolean removeButtonPushed(Object object) {
			model.removeMethod((Method) object, methodsViewer);
			return true;
		}

		/*
		 * (non-Javadoc)
		 * @see com.amdocs.studio.editors.widgets.ICompositeTableWidgetListener#CompositeTableWidgetSelected(java.lang.Object)
		 */
		public void CompositeTableWidgetSelected(Object object) {
			model.methodSelected((Method) methodsViewer.getSelection(), methodsViewer);
		}

		/*
		 * (non-Javadoc)
		 * @see com.amdocs.studio.editors.widgets.ICompositeTableWidgetListener#CompositeTableValueChanged()
		 */
		public void CompositeTableValueChanged() {}
	}

	private final class MethodNameModifier implements ICellModifier {

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
		 */
		public boolean canModify(Object element, String property) {
			return true;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
		 */
		public Object getValue(Object element, String property) {
			int selectionIndex = methodsViewer.getSelectionIndex();
			return ((Method) methods.get(selectionIndex)).getName();
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String,
		 *      java.lang.Object)
		 */
		public void modify(Object element, String property, Object value) {
			String name = (String) value;
			if (name.length() == 0) {
				String message = ExamplesPlugin.getDefault().getString(
					"MethodsListSection.rename.empty.error.message"); //$NON-NLS-1$
				MessageDialog.openError(page.getSite().getShell(), ExamplesPlugin.getDefault().getString(
					"MethodsListSection.rename.error.title"), message); //$NON-NLS-1$
				return;
			}
			int selectionIndex = methodsViewer.getSelectionIndex();
			Method selectedMethod = (Method) methods.get(selectionIndex);
			String oldName = selectedMethod.getName();
			if (name.equals(oldName)) {
				return;
			}

			//check uniqueness of the new name
			int index = 0;
			for (Iterator iter = methods.iterator(); iter.hasNext();) {
				if (index == selectionIndex) {
					index++;
					continue;
				}
				index++;
				Method method = (Method) iter.next();
				if (method.getName().equals(value)) {
					String message = ExamplesPlugin.getDefault().format(
						"MethodsListSection.rename.error.message", oldName); //$NON-NLS-1$
					MessageDialog.openError(page.getSite().getShell(), ExamplesPlugin.getDefault().getString(
						"MethodsListSection.rename.error.title"), message); //$NON-NLS-1$
					return;
				}
			}
			MethodsModel model = ((MethodsPage) getPage()).getModel();
			model.changeMethodName(name);
		}

	}

	private ArrayList methods;

	private CompositeTableWidget methodsViewer;
	private CellEditor[] cellEditors;
	private CellEditor[] emptyCellEditors = new CellEditor[]{null};
	private AddMethodDialog addMethodDialog;

	/**
	 * @param page
	 * @param parent
	 * @param toolkit
	 */
	public MethodsListSection(ADEFormPage page, Composite parent, FormToolkit toolkit) {
		super(page, parent, toolkit, Section.TITLE_BAR | Section.DESCRIPTION);
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADESectionPart#createClient(org.eclipse.ui.forms.widgets.Section,
	 *      org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	protected void createClient(Section section, Composite composite, FormToolkit toolkit) {
		section.setText(ExamplesPlugin.getDefault().getString("MethodsListSection.section.title")); //$NON-NLS-1$
		section.setDescription("Add new methods. Rename existing methods.");

		GridData gd = new GridData(GridData.FILL_BOTH);
		section.setLayoutData(gd);

		GridLayout layout = new GridLayout();
		composite.setLayout(layout);

		createMethodsViewer(composite, toolkit);
		toolkit.paintBordersFor(composite);

		MethodsModel model = ((MethodsPage) getPage()).getModel();
		model.addPropertyChangedListener(this);

		initSection();
	}

	/**
	 * 
	 */
	private void initSection() {
		MethodsModel model = ((MethodsPage) getPage()).getModel();
		methods = new ArrayList();
		methods.addAll(Arrays.asList(model.getMethods()));
		methodsViewer.setInput(methods);
	}

	/**
	 * @param parent
	 * @param toolkit
	 */
	private void createMethodsViewer(Composite parent, FormToolkit toolkit) {
		methodsViewer = new CompositeTableWidget(parent, ""); //$NON-NLS-1$
		methodsViewer.setContentProvider(new ArrayContentProvider());
		methodsViewer.setLabelProvider(new MethodsLabelProvider());
		methodsViewer.addCompositeTableWidgetListener(new MethodsViewerListener());

		methodsViewer.setCellModifier(new MethodNameModifier());
		TextCellEditor cellEditor = new TextCellEditor(methodsViewer.getTableViewer().getTable());
		cellEditors = new CellEditor[]{cellEditor};
		methodsViewer.getTableViewer().setColumnProperties(new String[]{"name"}); //$NON-NLS-1$
		setCellEditors(page.isEnabled());

		GridData data = new GridData(GridData.FILL_BOTH);
		methodsViewer.setLayoutData(data);
		toolkit.adapt(methodsViewer);
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADESectionPart#validateSection()
	 */
	public IStatus[] validateSection() {
		MethodsModel model = ((MethodsPage) getPage()).getModel();
		return model.validate(MethodsModel.VALIDATE_METHODS);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getProperty().equals(MethodsModel.METHOD_ADDED)) {
			MethodsModel model = ((MethodsPage) getPage()).getModel();
			methods.removeAll(methods);
			methods.addAll(Arrays.asList(model.getMethods()));
			methodsViewer.refresh();
		} else if (event.getProperty().equals(MethodsModel.METHOD_REMOVED)) {
			MethodsModel model = ((MethodsPage) getPage()).getModel();
			methods.removeAll(methods);
			methods.addAll(Arrays.asList(model.getMethods()));
			methodsViewer.refresh();
		} else if (event.getProperty().equals(MethodsModel.METHOD_NAME_CHANGED)) {
			methodsViewer.update(event.getSource());
		} else if (event.getProperty().equals(MethodsModel.METHOD_ACCESS_CHANGED)) {
			methodsViewer.update(event.getSource());
		} else if (event.getProperty().equals(MethodsModel.METHOD_MODIFIER_CHANGED)) {
			methodsViewer.update(event.getSource());
		}
	}

	/**
	 * @param object
	 * @return boolean
	 */
	public boolean selectReveal(Object object) {
		methodsViewer.getTableViewer().setSelection(new StructuredSelection(object));
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADESectionPart#refreshEnable(org.eclipse.ui.forms.widgets.Section,
	 *      boolean)
	 */
	public void refreshEnable(Section section, boolean enable) {
		EditorsUtils.enableWidgets(methodsViewer, enable);
		setCellEditors(enable);
	}

	private void setCellEditors(boolean enable) {
		if (enable) {
			methodsViewer.getTableViewer().setCellEditors(cellEditors);
		} else {
			methodsViewer.getTableViewer().setCellEditors(emptyCellEditors);
		}
	}

}