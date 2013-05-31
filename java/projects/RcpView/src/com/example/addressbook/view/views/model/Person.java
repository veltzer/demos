package com.example.addressbook.view.views.model;

public class Person {
	private String firstName;
	private String lastName;
	private String number;

	public Person(String ifirstName, String ilastName, String inumber) {
		firstName = ifirstName;
		lastName = ilastName;
		number = inumber;
	}

	public String toString() {
		return firstName + " " + lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String ifirstName) {
		firstName = ifirstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String ilastName) {
		lastName = ilastName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String inumber) {
		number = inumber;
	}
}
