package com.example.addressbook.view.views;

import model.*;

import org.eclipse.jface.viewers.*;

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
