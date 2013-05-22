package com.example.addressbook.view.views;
import java.util.*;

import model.*;

import org.eclipse.jface.viewers.*;

public class AddressBookContentProvider implements IStructuredContentProvider {
    public Object[] getElements(Object inputElement) {
        if (!(inputElement instanceof AddressBook)) {
            return new Object[0]; // Empty model
        }
        
        AddressBook book = (AddressBook) inputElement;
        
        // Build the union of both lists:
        List result = new ArrayList();
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
