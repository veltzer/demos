package com.example.addressbook.view.views.model;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
	private List<Person> friends = new ArrayList<Person>();
	private List<Company> companies = new ArrayList<Company>();

	public List<Person> getFriends() {
		return friends;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void addFriend(Person f) {
		friends.add(f);
	}

	public void addCompany(Company c) {
		companies.add(c);
	}

	public void remove(Object o) {
		friends.remove(o);
		companies.remove(o);
	}
}
