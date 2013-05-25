package com.example.addressbook.view.views;

import org.eclipse.jface.viewers.ViewerSorter;

import com.example.addressbook.view.views.model.Company;
import com.example.addressbook.view.views.model.Person;

public class AddressBookSorter1 extends ViewerSorter {
	public int category(Object obj) {
		if (obj instanceof Person) {
			return 0;
		}
		if (obj instanceof Company) {
			return 1;
		}

		// should never happen...
		return 2;
	}
}
