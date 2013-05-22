package com.example.addressbook.view.views;

import model.*;

import org.eclipse.jface.viewers.*;

public class AddressBookViewerFilter extends ViewerFilter {
    private boolean showFriends;
    private boolean showCompanies;
    
    public AddressBookViewerFilter(boolean companies, boolean friends) {
        // TODO Auto-generated constructor stub
        showCompanies = companies;
        showFriends = friends;
    }

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        if (element instanceof Person) {
            return showFriends;
        }
        else if (element instanceof Company) {
            return showCompanies;
        }
        return true;
    }
}
