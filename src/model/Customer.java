package model;

public class Customer {
	private long customerID;
	private String name;
	public Customer(){
		name = new String();
	}
	public Customer(String name){
		this.name = name;
	}
	public void setCustomerID(long customerID){
		this.customerID = customerID;
	}
	public long getCustomerID(){
		return customerID;
	}
	public void setName(String str){
		name = str;
		System.out.println("New customer is " + name);
	}
	public String getName(){
		System.out.println("Returned " + name);
		return name;
	}
	
}

