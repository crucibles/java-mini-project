package model;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {
	private long customerID;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date birthDay;
	
	public Customer(){
		
	}

	public Customer(String firstName, String lastName, long customerId){
		setName(firstName);
		setLastName(lastName);
		setCustomerID(customerId);;
	}
	
	@XmlElement
	public void setCustomerID(long customerID){
		this.customerID = customerID;
	}
	public long getCustomerID(){
		return customerID;
	}
	
	@XmlElement
	public void setName(String str){
		this.firstName = str;
		System.out.println("New customer is " + this.firstName);
	}
	public String getName(){
		System.out.println("Returned " + this.firstName);
		return this.firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	@XmlElement
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
}

