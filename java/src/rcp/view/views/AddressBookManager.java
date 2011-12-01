package com.example.addressbook.view.views;

import java.io.*;
import java.util.*;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;

import com.example.addressbook.view.*;

import model.*;

public class AddressBookManager {
    private static AddressBook instance;
    
    public static AddressBook getInstance() {
        if (instance == null) {
            instance = new AddressBook();
            load();
        }

        return instance;
    }

    public static void save() {
        if (instance == null) {
            return; // Never loaded
        }
        
        File output = getStateFile();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(output));
            List friends = instance.getFriends();
            for (Iterator iter = friends.iterator(); iter.hasNext();) {
                Person friend = (Person) iter.next();
                writer.print("FRIEND\t");
                writer.print(friend.getFirstName());
                writer.print('\t');
                writer.print(friend.getLastName());
                writer.print('\t');
                writer.println(friend.getNumber());
            }
            List companies = instance.getCompanies();
            for (Iterator iter = companies.iterator(); iter.hasNext();) {
                Company company = (Company) iter.next();
                writer.print("COMPANY\t");
                writer.print(company.getName());
                writer.print('\t');
                writer.println(company.getNumber());
            }
        }
        catch (Exception e) {
            // Log the error...
        }
        finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            }
            catch (Exception e) {
                // Can't even close files, eh?
            }
        }
    }
    
    private static void load() {
        File input = getStateFile();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            while (line != null) {
                parseLine(line);
                line = reader.readLine();
            }
        }
        catch (FileNotFoundException e) {
            // Ignore -- no existing data.
        }
        catch (Exception e) {
            // Log the error...
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            }
            catch (Exception e) {
                // Can't even close files, eh?
            }
        }
    }

    private static void parseLine(String line) {
        StringTokenizer st = new StringTokenizer(line, "\t");
        try {
            String type = st.nextToken();
            if (type.equals("FRIEND")) {
                String firstName = st.nextToken();
                String lastName = st.nextToken();
                String number = st.nextToken();
                Person friend = new Person(firstName, lastName, number);
                instance.addFriend(friend);
            }
            else if (type.equals("COMPANY")) {
                String name = st.nextToken();
                String number = st.nextToken();
                Company company = new Company(name, number);
                instance.addCompany(company);
            }
            else {
                throw new IllegalArgumentException("Unknown line type: " + type); 
            }
        }
        catch (Exception e) {
            // Should log the error...
        }
    }

    private static File getStateFile() {
        IPath stateLocation = ViewPlugin.getDefault().getStateLocation();
        IPath stateFilePath = stateLocation.append("addressBook.txt");
        return stateFilePath.toFile();
    }
    
    private static void populateInstance() {
        instance.addFriend(new Person("John", "Doe", "1234"));
        instance.addFriend(new Person("Xavier", "Anon", "2345"));
        instance.addFriend(new Person("Anony", "Moose", "2345"));
        instance.addFriend(new Person("Jane", "Doe", "4567"));
        instance.addCompany(new Company("Interbit", "9999"));
        instance.addCompany(new Company("Acme Computers Corporation", "0000"));
        Employee boss = new Employee("Kermit", "DeFrog", "1", null);
        Employee e1 = new Employee("Swedish", "Chef", "2", boss);
        Employee e2 = new Employee("Miss", "Piggie", "3", boss);
        Employee e1a = new Employee("Chocolate", "Moose", "4", e1);
        Employee e1b = new Employee("Swedish", "Meetballs", "5", e1);
    }
}
