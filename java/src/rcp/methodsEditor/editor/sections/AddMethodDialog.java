/*
 * Created on May 1, 2005
 */

package examples.methodsEditor.editor.sections;

import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.amdocs.studio.editors.ui.AbstractNewDialog;

import examples.methodsEditor.core.Method;
import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class AddMethodDialog extends AbstractNewDialog {

	private Text textMethod;
	private String methodName;
	private List methodList;

	/**
	 * @param parent
	 */
	public AddMethodDialog(Shell parent) {
		super(parent);
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.ui.AbstractNewDialog#getObjectName()
	 */
	protected String getObjectName() {
		return "Method";
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.ui.AbstractNewDialog#getTitle()
	 */
	protected String getTitle() {
		return "insert title here";
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.ui.AbstractNewDialog#getRegularMessage()
	 */
	protected String getRegularMessage() {
		return "insert message here";
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.ui.AbstractNewDialog#createMiddleArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Composite createMiddleArea(Composite composite) {
		setTitleImage(ExamplesPlugin.getDefault().getImage(ExamplesPlugin.NEWMETH_WIZ));

		final Composite area = new Composite(composite, SWT.NONE);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.marginWidth = 20;
		gridLayout.marginHeight = 20;
		gridLayout.numColumns = 2;
		area.setLayout(gridLayout);
		area.setLayoutData(new GridData(GridData.FILL_BOTH));

		final Label labelMethodName = new Label(area, SWT.NONE);
		labelMethodName.setText("Method Name:");

		textMethod = new Text(area, SWT.BORDER);
		textMethod.setTextLimit(30);
		textMethod.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		textMethod.setTextLimit(30);

		return area;
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.ui.AbstractNewDialog#addListeners()
	 */
	protected void addListeners() {

		textMethod.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				methodName = textMethod.getText();
				validate();
			}
		});
	}

	private boolean validate() {
		if (methodName.length() == 0) {
			setTitleArea(AbstractNewDialog.ERROR_STATE, ExamplesPlugin.getDefault().getString(
				"empty.method.error.message")); //$NON-NLS-1$
			return false;
		}

		for (Iterator iter = methodList.iterator(); iter.hasNext();) {
			Method method = (Method) iter.next();
			if (method.getName().equals(methodName)) {
				setTitleArea(AbstractNewDialog.ERROR_STATE, ExamplesPlugin.getDefault().getString(
					"duplicate.method.error.message")); //$NON-NLS-1$
				return false;
			}
		}
		setTitleArea(AbstractNewDialog.NORMAL_STATE, AbstractNewDialog.PRESS_OK);
		getButton(OK).setEnabled(true);
		return true;
	}

	/**
	 * @return Returns the methodName.
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodList The methodList to set.
	 */
	public void setMethodList(List methodList) {
		this.methodList = methodList;
	}
}