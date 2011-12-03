package model;

import java.util.*;

public class Employee extends Person {
    public Employee(String firstName, String lastName, String number, Employee manager) {
        super(firstName, lastName, number);
        this.manager = manager;
        if (manager != null)
            manager.addManaged(this);
    }

    private List managed = new ArrayList();
    private Employee manager = null;
    
    public void addManaged(Employee e) {
        managed.add(e);
    }
    
    public List getManaged() {
        return managed;
    }
    
    public Employee getManager() {
        return manager;
    }
}
