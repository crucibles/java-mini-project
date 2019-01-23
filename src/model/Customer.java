package model;

import java.io.Serializable;

/**
 * 
 * @author april.padrigano
 * Description: Customer class model with setters and getters
 * Date Modified: 1-21-2019 8:00 pm
 */
public class Customer implements Serializable {
	private long customer_ID;
	private String name;
	private String middle_name;
	private String last_name;
	public Customer(){
		name = new String();
		customer_ID = 0;
		middle_name = new String();
		last_name = new String();
	}
	/**
	 * Description: Constructor of class that sets the name.
	 * @param name
	 */
	public Customer(long customer_ID, String name,String middle_name, String last_name){
		this.customer_ID = customer_ID;
		this.name = name;
		this.middle_name = middle_name;
		this.last_name = last_name;
	}
	public void setCustomerID(long customerID){
		this.customer_ID = customerID;
	}
	public long getCustomerID(){
		return customer_ID;
	}
	public void setName(String str){
		name = str;
		
	}
	public String getName(){
		return name;
	}
	
}

