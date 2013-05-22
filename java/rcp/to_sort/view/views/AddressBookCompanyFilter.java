package com.example.addressbook.view.views;

import model.*;

import org.eclipse.jface.viewers.*;

public class AddressBookCompanyFilter extends ViewerFilter {
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        return (element instanceof Company);
    }
}
