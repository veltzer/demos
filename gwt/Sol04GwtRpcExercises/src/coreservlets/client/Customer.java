package coreservlets.client;
import java.io.*;

@SuppressWarnings("serial")
public class Customer implements Serializable {
  private String customerID, firstName, lastName;
  private double balance;
  
  public Customer(String customerID, String firstName, 
                  String lastName, double balance) {
    setCustomerID(customerID);
    setFirstName(firstName);
    setLastName(lastName);
    setBalance(balance);
  }
  
  public  Customer() {  // Needed for Serializable
    this("no-id", "none", "none", 0.0);
  }
  
  public String getCustomerID() {
    return (customerID);
  }
  
  public void setCustomerID(String customerID) {
    this.customerID = customerID;
  }
  
  public String getFirstName() {
    return (firstName);
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String getLastName() {
    return (lastName);
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public double getBalance() {
    return (balance);
  }
  
  public void setBalance(double balance) {
    this.balance = balance;
  }
  
  // GWT does not support String.format
  
  public String getFormattedBalance() {
    return("Balance: " + balance);
  }
}
