package com.example.addressbook.view.views;

import model.*;

import org.eclipse.jface.viewers.*;
import org.eclipse.swt.widgets.*;

public class AddressBookCellModifier implements ICellModifier {
    private TableViewer viewer;
    
    public AddressBookCellModifier(TableViewer viewer) {
        this.viewer = viewer;
    }

    public boolean canModify(Object element, String property) {
        return true;
    }

    public Object getValue(Object element, String property) {
        boolean name = property.equals("name");
        if (element instanceof Person) {
            Person p = (Person)element;
            if (name) {
                return p.getLastName() + ", " + p.getFirstName();
            }
            else {
                return p.getNumber();
            }
        }
        else {
            Company c = (Company)element;
            if (name) {
                return c.getName();
            }
            else {
                return c.getNumber();
            }
        }
    }

    public void modify(Object element, String property, Object value) {
        boolean name = property.equals("name");
        TableItem item = (TableItem)element;
        Object data = item.getData();
        String val = value.toString();
        
        if (data instanceof Person) {
            Person p = (Person)data;
            if (name) {
                int comma = val.indexOf(", ");
                if (comma >= 0) {
                    p.setFirstName(val.substring(comma + 2));
                    p.setLastName(val.substring(0, comma));
                }
                else {
                    p.setFirstName(val);
                    p.setLastName("");
                }
            }
            else {
                p.setNumber(val);
            }
        }
        else {
            Company c = (Company)data;
            if (name) {
                c.setName(val);
            }
            else {
                c.setNumber(val);
            }
        }
        viewer.refresh();
    }

}
