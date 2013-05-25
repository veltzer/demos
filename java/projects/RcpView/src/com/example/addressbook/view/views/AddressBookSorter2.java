package com.example.addressbook.view.views;

import org.eclipse.jface.viewers.Viewer;

import com.example.addressbook.view.views.model.Person;

public class AddressBookSorter2 extends AddressBookSorter1 {
	public int compare(Viewer viewer, Object obj1, Object obj2) {
		// We only intervene in person-to-person comparisons.
		if (!((obj1 instanceof Person) && (obj2 instanceof Person))) {
			return super.compare(viewer, obj1, obj2);
		}

		Person p1 = (Person) obj1;
		Person p2 = (Person) obj2;

		int compareLastName = p1.getLastName().compareTo(p2.getLastName());
		if (compareLastName != 0) {
			return compareLastName;
		}

		return p1.getFirstName().compareTo(p2.getFirstName());
	}
}
