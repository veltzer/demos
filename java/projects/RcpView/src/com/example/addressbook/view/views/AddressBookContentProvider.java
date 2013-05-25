package com.example.addressbook.view.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.example.addressbook.view.views.model.AddressBook;

public class AddressBookContentProvider implements IStructuredContentProvider {
	public Object[] getElements(Object inputElement) {
		if (!(inputElement instanceof AddressBook)) {
			return new Object[0]; // Empty model
		}

		AddressBook book = (AddressBook) inputElement;

		// Build the union of both lists:
		List<Object> result = new ArrayList<Object>();
		result.addAll(book.getFriends());
		result.addAll(book.getCompanies());

		return result.toArray();
	}

	public void dispose() {
		// Do nothing
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// Do nothing
	}

}
