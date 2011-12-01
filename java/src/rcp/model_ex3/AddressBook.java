package model;
import java.util.*;

public class AddressBook {
    private List friends = new ArrayList();
    private List companies = new ArrayList();

    public List getFriends() {
        return friends;
    }
    
    public List getCompanies() {
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
