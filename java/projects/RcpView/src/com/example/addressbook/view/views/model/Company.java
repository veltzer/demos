package com.example.addressbook.view.views.model;

public class Company {
	private String name;
	private String number;
	private Employee ceo;

	public Company(String iname, String inumber) {
		name = iname;
		number = inumber;
	}

	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String iname) {
		name = iname;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String inumber) {
		number = inumber;
	}

	public Employee getCeo() {
		return ceo;
	}

	public void setCeo(Employee iceo) {
		ceo = iceo;
	}
}
