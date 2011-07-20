package interbit.bank;

import java.io.*;
import java.util.*;

public class Bank implements Business,Serializable{
	private static Bank bank;
	private List customers;	
	private static boolean isExist;
		
	private Bank(){
		customers=new LinkedList();
	}
	
	public static Business getBank(){
		if(!isExist){
			bank=new Bank();
			isExist=true;
		}
		return bank;
	}
	
	public void addCustomer(Customer customer){
		customers.add(customer);
	}
	
	public Customer getCustomer(int index){
		if(index<customers.size())
			return (Customer)customers.get(index);
		return null;
	}
	
	public int getNumOfCustomers(){
		return customers.size();
	}
	
	public void removeCustomer(int index){
		customers.remove(index);
	}
}