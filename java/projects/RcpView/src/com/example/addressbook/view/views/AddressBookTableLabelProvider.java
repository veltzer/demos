package com.example.addressbook.view.views;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.example.addressbook.view.ViewPlugin;
import com.example.addressbook.view.views.model.Company;
import com.example.addressbook.view.views.model.Person;

public class AddressBookTableLabelProvider implements ITableLabelProvider {
	public static final int NAME_COLUMN = 0;
	public static final int NUMBER_COLUMN = 1;

	public Image getColumnImage(Object o, int column) {
		if (column == NAME_COLUMN) {
			if (o instanceof Person) {
				return ViewPlugin.getFriendIcon();
			}

			if (o instanceof Company) {
				return ViewPlugin.getCompanyIcon();
			}
		}

		return null;
	}

	private static final String ERR_STRING1 = "bad column";

	public String getColumnText(Object o, int column) {
		if (o instanceof Person) {
			Person p = (Person) o;
			switch (column) {
			case NAME_COLUMN:
				return p.getLastName() + ", " + p.getFirstName();
			case NUMBER_COLUMN:
				return p.getNumber();
			default:
				throw new RuntimeException(ERR_STRING1);
			}
		}
		if (o instanceof Company) {
			Company c = (Company) o;
			switch (column) {
			case NAME_COLUMN:
				return c.getName();
			case NUMBER_COLUMN:
				return c.getNumber();
			default:
				throw new RuntimeException(ERR_STRING1);
			}
		}

		return null;
	}

	public void addListener(ILabelProviderListener listener) {
	}

	public void dispose() {
	}

	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
	}
}
