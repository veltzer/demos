package com.example.addressbook.view.views;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.example.addressbook.view.views.model.Company;

public class AddressBookCompanyFilter extends ViewerFilter {
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		return (element instanceof Company);
	}
}
