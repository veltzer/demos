package com.example.addressbook.view.editors;

import java.io.BufferedReader;
import java.io.StringReader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;

import com.example.addressbook.view.ViewPlugin;
import com.example.addressbook.view.preferences.AddressBookPreferences;

/**
 * An example showing how to create a multi-page editor. This example has 3
 * pages: <ul> <li>page 0 contains a nested text editor. <li>page 1 allows you
 * to change the font used in page 2 <li>page 2 shows the words in page 0 in
 * sorted order </ul>
 */
public class AddressBookEditor extends MultiPageEditorPart implements
		IResourceChangeListener {
	// Widgets for the Contact page:
	private Form contactForm;
	// Name section
	private Text firstName;
	private Text lastName;
	// Internet section
	private Text email;
	private Text screenName;
	private CCombo imClient;
	private Text imName;
	// Phones section
	private Text homePhone;
	private Text workPhone;
	private Text fax;
	private Text mobile;

	// Widgets for the Source page:
	private TextEditor sourceEditor;

	// Pages indexes:
	private int contactPageIndex;
	private int sourcePageIndex;

	// Status
	private boolean wasModified;

	void createContactPage() {
		FormToolkit toolkit = new FormToolkit(getContainer().getDisplay());

		contactForm = toolkit.createForm(getContainer());
		toolkit.paintBordersFor(contactForm.getBody());

		contactForm.setText("Contact Details");
		GridLayout layout = new GridLayout(2, true);
		contactForm.getBody().setLayout(layout);

		// Build the Name section:
		Section section = toolkit.createSection(contactForm.getBody(),
				SWT.DEFAULT);
		section.setText("Name");

		GridData gd = new GridData();
		gd.verticalAlignment = SWT.TOP;
		section.setLayoutData(gd);

		Composite sectionClient = toolkit.createComposite(section);
		toolkit.paintBordersFor(sectionClient);
		GridLayout sectionLayout = new GridLayout();
		sectionLayout.numColumns = 2;
		sectionClient.setLayout(sectionLayout);
		toolkit.createLabel(sectionClient, "First name:");
		firstName = toolkit.createText(sectionClient, "");
		gd = new GridData();
		gd.widthHint = 170;
		gd.grabExcessHorizontalSpace = true;
		firstName.setLayoutData(gd);
		toolkit.createLabel(sectionClient, "Last name:");
		lastName = toolkit.createText(sectionClient, "");
		lastName.setLayoutData(gd);

		section.setClient(sectionClient);

		// Build the Internet section:
		section = toolkit.createSection(contactForm.getBody(), SWT.DEFAULT);
		section.setText("Internet");
		section.setDescription("How to contact this person, the modern way.");

		gd = new GridData();
		gd.verticalAlignment = SWT.TOP;
		section.setLayoutData(gd);

		sectionClient = toolkit.createComposite(section);
		toolkit.paintBordersFor(sectionClient);
		sectionLayout = new GridLayout();
		sectionLayout.numColumns = 2;
		sectionClient.setLayout(sectionLayout);
		toolkit.createLabel(sectionClient, "E-mail address:");
		email = toolkit.createText(sectionClient, "");
		gd = new GridData();
		gd.widthHint = 170;
		gd.grabExcessHorizontalSpace = true;
		email.setLayoutData(gd);
		toolkit.createLabel(sectionClient, "Screen name:");
		screenName = toolkit.createText(sectionClient, "");
		screenName.setLayoutData(gd);
		toolkit.createLabel(sectionClient, "IM Client:");
		imClient = new CCombo(sectionClient, SWT.READ_ONLY | SWT.FLAT);
		toolkit.adapt(imClient, false, false);
		imClient.setItems(new String[] {
				"None", "ICQ", "AIM", "MSN"
		});
		imClient.setText("None"); // Default
		imClient.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				imName.setEnabled(!(imClient.getText().equals("None")));
				imName.getParent().redraw(); // Solve refresh bug in Eclipse 3.1
												// on XP
			}
		});
		toolkit.createLabel(sectionClient, "IM Name:");
		imName = toolkit.createText(sectionClient, "");
		gd = new GridData();
		gd.widthHint = 170;
		gd.grabExcessHorizontalSpace = true;
		imName.setLayoutData(gd);
		imName.setEnabled(false);

		section.setClient(sectionClient);

		IPreferenceStore store = ViewPlugin.getDefault().getPreferenceStore();
		if (store.getBoolean(AddressBookPreferences.P_SHOW_PHONE)) {
			// Build the Phones section:
			section = toolkit.createSection(contactForm.getBody(), SWT.DEFAULT);
			section.setText("Phones");
			section.setDescription("How to contact this person, the old-fashioned way.");

			gd = new GridData();
			gd.verticalAlignment = SWT.TOP;
			section.setLayoutData(gd);

			sectionClient = toolkit.createComposite(section);
			toolkit.paintBordersFor(sectionClient);
			sectionLayout = new GridLayout();
			sectionLayout.numColumns = 2;
			sectionClient.setLayout(sectionLayout);
			toolkit.createLabel(sectionClient, "Home:");
			homePhone = toolkit.createText(sectionClient, "");
			gd = new GridData();
			gd.widthHint = 170;
			gd.grabExcessHorizontalSpace = true;
			homePhone.setLayoutData(gd);
			toolkit.createLabel(sectionClient, "Work:");
			workPhone = toolkit.createText(sectionClient, "");
			workPhone.setLayoutData(gd);
			toolkit.createLabel(sectionClient, "Fax:");
			fax = toolkit.createText(sectionClient, "");
			fax.setLayoutData(gd);
			toolkit.createLabel(sectionClient, "Mobile:");
			mobile = toolkit.createText(sectionClient, "");
			mobile.setLayoutData(gd);

			section.setClient(sectionClient);
		}

		// Build the Options section:

		section = toolkit.createSection(contactForm.getBody(), SWT.DEFAULT);
		section.setText("Options");

		gd = new GridData();
		gd.verticalAlignment = SWT.TOP;
		section.setLayoutData(gd);

		sectionClient = toolkit.createComposite(section);
		toolkit.paintBordersFor(sectionClient);
		sectionLayout = new GridLayout();
		sectionClient.setLayout(sectionLayout);

		Hyperlink link = toolkit.createHyperlink(sectionClient, "View source",
				SWT.WRAP);
		link.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				setActivePage(sourcePageIndex);
			}
		});

		link = toolkit.createHyperlink(sectionClient, "Save changes", SWT.WRAP);
		link.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				doSave(null);
			}
		});

		section.setClient(sectionClient);

		contactPageIndex = addPage(contactForm);
		setPageText(contactPageIndex, "Contact");

		// Set the change listeners:
		ModifyListener modifyListener = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (!(wasModified)) {
					wasModified = true;
					firePropertyChange(IEditorPart.PROP_DIRTY);
				}
			}
		};

		firstName.addModifyListener(modifyListener);
		lastName.addModifyListener(modifyListener);
		email.addModifyListener(modifyListener);
		screenName.addModifyListener(modifyListener);
		imClient.addModifyListener(modifyListener);
		imName.addModifyListener(modifyListener);
		if (store.getBoolean(AddressBookPreferences.P_SHOW_PHONE)) {
			homePhone.addModifyListener(modifyListener);
			workPhone.addModifyListener(modifyListener);
			fax.addModifyListener(modifyListener);
			mobile.addModifyListener(modifyListener);
		}
	}

	void createSourcePage() {
		try {
			sourceEditor = new TextEditor();
			sourcePageIndex = addPage(sourceEditor, getEditorInput());
			setPageText(sourcePageIndex, "Source");
		} catch (PartInitException e) {
			throw new RuntimeException(e);
		}
	}

	protected void createPages() {
		createContactPage();
		createSourcePage();

		// Initialize the field with the editor's content
		updateContactFromSource();
		wasModified = false;
	}

	public void doSave(IProgressMonitor monitor) {
		if (getActivePage() == contactPageIndex) {
			updateSourceFromContact();
		}
		wasModified = false;
		sourceEditor.doSave(monitor);
	}

	public void doSaveAs() {
		if (getActivePage() == contactPageIndex) {
			updateSourceFromContact();
		}
		wasModified = false;
		sourceEditor.doSaveAs();
		setInput(sourceEditor.getEditorInput());
		refreshPartName();
	}

	public void init(IEditorSite site, IEditorInput editorInput)
			throws PartInitException {
		super.init(site, editorInput);
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this,
				IResourceChangeEvent.POST_CHANGE);
		refreshPartName();
	}

	public boolean isSaveAsAllowed() {
		return true;
	}

	protected void pageChange(int newPageIndex) {
		if (newPageIndex == sourcePageIndex) {
			updateSourceFromContact();
		} else if (newPageIndex == contactPageIndex) {
			updateContactFromSource();
		}

		super.pageChange(newPageIndex);
	}

	private void updateSourceFromContact() {
		String source = firstName.getText() + "\n";
		source += lastName.getText() + "\n";
		source += email.getText() + "\n";
		source += screenName.getText() + "\n";
		source += imClient.getText() + "\n";
		source += imName.getText() + "\n";
		if (homePhone != null) {
			source += homePhone.getText() + "\n";
			source += workPhone.getText() + "\n";
			source += fax.getText() + "\n";
			source += mobile.getText() + "\n";
		}

		getDocumentSource().set(source);
	}

	private void updateContactFromSource() {
		try {
			BufferedReader in = new BufferedReader(new StringReader(
					getDocumentSource().get()));
			firstName.setText(in.readLine());
			lastName.setText(in.readLine());
			email.setText(in.readLine());
			screenName.setText(in.readLine());
			imClient.setText(in.readLine());
			imName.setText(in.readLine());
			if (homePhone != null) {
				homePhone.setText(in.readLine());
				workPhone.setText(in.readLine());
				fax.setText(in.readLine());
				mobile.setText(in.readLine());
			}
		} catch (Exception e) {
			// Assuming the rest of the data is missing...
			throw new RuntimeException(e);
		}
	}

	private IDocument getDocumentSource() {
		return sourceEditor.getDocumentProvider().getDocument(
				sourceEditor.getEditorInput());
	}

	public boolean isDirty() {
		return wasModified || super.isDirty();
	}

	public void refreshPartName() {
		setPartName(getEditorInput().getName());
		setTitleToolTip(getEditorInput().getToolTipText());
	}

	public void resourceChanged(IResourceChangeEvent event) {
		checkDelta(event.getDelta());
	}

	protected void checkDelta(IResourceDelta delta) {
		if (delta.getKind() == IResourceDelta.REMOVED) {
			if (delta.getResource().equals(getEditedFileResource())) {
				getSite().getPage().closeEditor(this, false);
			}
		}

		for (int i = 0; i < delta.getAffectedChildren().length; i++) {
			checkDelta(delta.getAffectedChildren()[i]);
		}
	}

	protected IFile getEditedFileResource() {
		if (getEditorInput() instanceof FileEditorInput) {
			return ((FileEditorInput) getEditorInput()).getFile();
		}

		// We're not editing an open file
		return null;
	}

	@Override
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.dispose();
	}
}
