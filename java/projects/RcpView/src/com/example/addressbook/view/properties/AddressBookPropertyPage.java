package com.example.addressbook.view.properties;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;

public class AddressBookPropertyPage extends PropertyPage {

	private static final String COMMENTS_PROPERTY = "COMMENTES";
	private static final String NICE_PROPERTY = "NICE";
	private static final String PROPERTY_QUALIFIER = "com.example.addressbook.view";

	private Text commentText;
	private Button niceCheckbox;

	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		GridData data = new GridData(GridData.FILL);
		data.grabExcessHorizontalSpace = true;
		composite.setLayoutData(data);

		Label header = new Label(composite, SWT.NONE);
		header.setText("General comments about this contact:");

		commentText = new Text(composite, SWT.MULTI | SWT.BORDER);
		GridData gd = new GridData();
		gd.widthHint = 300;
		gd.heightHint = 120;
		commentText.setLayoutData(gd);

		niceCheckbox = new Button(composite, SWT.CHECK);
		niceCheckbox.setText("This contact is a nice person.");

		loadValues();

		return composite;
	}

	private void loadValues() {
		try {
			IResource src = (IResource) getElement();
			commentText.setText(src.getPersistentProperty(new QualifiedName(
					PROPERTY_QUALIFIER, COMMENTS_PROPERTY)));
			niceCheckbox.setSelection("true".equals(src
					.getPersistentProperty(new QualifiedName(
							PROPERTY_QUALIFIER, NICE_PROPERTY))));
		} catch (CoreException e) {
			throw new RuntimeException(e);
		}
	}

	protected void performDefaults() {
		commentText.setText("");
		niceCheckbox.setSelection(true);
	}

	public boolean performOk() {
		try {
			IResource src = (IResource) getElement();
			src.setPersistentProperty(new QualifiedName(PROPERTY_QUALIFIER,
					COMMENTS_PROPERTY), commentText.getText());
			src.setPersistentProperty(new QualifiedName(PROPERTY_QUALIFIER,
					COMMENTS_PROPERTY),
					new Boolean(niceCheckbox.getSelection()).toString());
			return true;
		} catch (CoreException e) {
			return false;
		}
	}
}
