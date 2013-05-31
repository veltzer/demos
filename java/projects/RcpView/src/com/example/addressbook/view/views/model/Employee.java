package com.example.addressbook.view.views.model;

import java.util.ArrayList;
import java.util.List;

public class Employee extends Person {
	public Employee(String firstName, String lastName, String number,
			Employee imanager) {
		super(firstName, lastName, number);
		manager = imanager;
		if (manager != null) {
			manager.addManaged(this);
		}
	}

	private List<Employee> managed = new ArrayList<Employee>();
	private Employee manager = null;

	public void addManaged(Employee e) {
		managed.add(e);
	}

	public List<Employee> getManaged() {
		return managed;
	}

	public Employee getManager() {
		return manager;
	}
}
