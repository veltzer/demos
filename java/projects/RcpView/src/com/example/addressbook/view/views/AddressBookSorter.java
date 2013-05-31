package com.example.addressbook.view.views;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public class AddressBookSorter extends ViewerSorter {
	private int mode;
	private boolean ascend;
	private ITableLabelProvider labelProvider;

	public AddressBookSorter(int imode, boolean iascend,
			ITableLabelProvider ilabelProvider) {
		mode = imode;
		ascend = iascend;
		labelProvider = ilabelProvider;
	}

	public int compare(Viewer viewer, Object obj1, Object obj2) {
		String num1 = labelProvider.getColumnText(obj1, mode);
		String num2 = labelProvider.getColumnText(obj2, mode);

		if (ascend) {
			return num1.compareTo(num2);
		} else {
			return num2.compareTo(num1);
		}
	}

}
