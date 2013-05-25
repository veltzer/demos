package com.example.addressbook.view.views.model;

public class Company {
	private String name;
	private String number;
	private Employee ceo;

	public Company(String name, String number) {
		this.name = name;
		this.number = number;
	}

	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Employee getCeo() {
		return ceo;
	}

	public void setCeo(Employee ceo) {
		this.ceo = ceo;
	}
}
