package com.example.addressbook.view.views;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.example.addressbook.view.views.model.Company;
import com.example.addressbook.view.views.model.Person;

public class AddressBookViewerFilter extends ViewerFilter {
	private boolean showFriends;
	private boolean showCompanies;

	public AddressBookViewerFilter(boolean companies, boolean friends) {
		showCompanies = companies;
		showFriends = friends;
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element instanceof Person) {
			return showFriends;
		} else if (element instanceof Company) {
			return showCompanies;
		}
		return true;
	}
}
