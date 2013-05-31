package com.example.addressbook.view.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.example.addressbook.view.ViewPlugin;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself. <p> This page
 * is used to modify preferences only. They are stored in the preference store
 * that belongs to the main plug-in class. That way, preferences can be accessed
 * directly via the preference store.
 */

public class AddressBookPreferencePage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {

	public AddressBookPreferencePage() {
		super(GRID);
		setPreferenceStore(ViewPlugin.getDefault().getPreferenceStore());
		setDescription("Configuration options for the Address Book editor.");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	@Override
	public void createFieldEditors() {
		addField(new BooleanFieldEditor(AddressBookPreferences.P_SHOW_PHONE,
				"Show &Phone numbers section", getFieldEditorParent()));

		addField(new StringListEditor(AddressBookPreferences.P_IM_CLIENT_TYPES,
				"Supported &IM client programs:", getFieldEditorParent()));
	}

	public void init(IWorkbench workbench) {
	}
}
