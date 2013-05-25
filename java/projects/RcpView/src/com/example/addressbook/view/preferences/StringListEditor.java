package com.example.addressbook.view.preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.preference.ListEditor;
import org.eclipse.swt.widgets.Composite;

public class StringListEditor extends ListEditor {
	public StringListEditor(String name, String labelText, Composite parent) {
		super(name, labelText, parent);
	}

	@Override
	protected String createList(String[] items) {
		if (items.length == 0) {
			return "";
		}

		StringBuffer result = new StringBuffer(items[0]);
		for (int i = 1; i < items.length; i++) {
			result.append('\0');
			result.append(items[i]);
		}

		return result.toString();
	}

	@Override
	protected String getNewInputObject() {
		InputDialog getImName = new InputDialog(getShell(), "New string value",
				"Enter new value", "", new IInputValidator() {
					public String isValid(String newText) {
						if (newText.length() > 0) {
							return null;
						}
						return "String must not be empty.";
					}
				});
		getImName.open();
		return getImName.getValue();
	}

	@Override
	protected String[] parseString(String stringList) {
		StringTokenizer tokenizer = new StringTokenizer(stringList, "\0");
		List<String> list = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			list.add(tokenizer.nextToken());
		}
		return (String[]) list.toArray(new String[0]);
	}

}
