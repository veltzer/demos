package com.example.addressbook.view.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.example.addressbook.view.ViewPlugin;

/**
 * Class used to initialize default preference values.
 */
public class AddressBookPreferenceInitializer extends
		AbstractPreferenceInitializer {
	public void initializeDefaultPreferences() {
		IPreferenceStore store = ViewPlugin.getDefault().getPreferenceStore();
		store.setDefault(AddressBookPreferences.P_SHOW_PHONE, true);
		store.setDefault(AddressBookPreferences.P_IM_CLIENT_TYPES,
				"ICQ\0AIM\0MSN Messager");
	}

}
