package model;


/**
 * 
 * @author april.padrigano
 * Description: Customer class model with setters and getters
 * Date Modified: 1-21-2019 8:00 pm
 */
public class Customer {
	private long customer_ID;
	private String first_name;
	private String middle_name;
	private String last_name;
	
	public Customer(){
		setFirstName(new String());
		setCustomerId(0);
		setMiddleName(new String());
		setLastName(new String());
	}
	
	/**
	 * Description: Constructor of class that sets the name.
	 * @param name
	 */
	public Customer(long customer_ID, String name,String middle_name, String last_name){
		this.setCustomerId(customer_ID);
		this.setFirstName(name);
		this.setMiddleName(middle_name);
		this.setLastName(last_name);
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getMiddleName() {
		return middle_name;
	}

	public void setMiddleName(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public long getCustomerId() {
		return customer_ID;
	}

	public void setCustomerId(long customer_ID) {
		this.customer_ID = customer_ID;
	}
	

	
}

