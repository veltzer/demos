package com.example.addressbook.view.views;


import model.*;

import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.*;

import com.example.addressbook.view.*;

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
    
    public String getColumnText(Object o, int column) {
        if (o instanceof Person) {
            Person p = (Person)o;
            switch(column) {
            case NAME_COLUMN: 
                return p.getLastName() + ", " + p.getFirstName();
            case NUMBER_COLUMN:
                return p.getNumber();
            }
        }
        if (o instanceof Company) {
            Company c = (Company)o;
            switch(column) {
            case NAME_COLUMN: 
                return c.getName();
            case NUMBER_COLUMN:
                return c.getNumber();
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
