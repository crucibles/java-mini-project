package model;

import java.util.ArrayList;

import services.FileService;

public class PopulateFiles {
	private FileService files_manager;
	private Movie movie;
	private Customer customer;
	private String movie_path = "movie_records.xml";
	private String customer_path = "customer_records.xml";
	public PopulateFiles(FileService x){
		populateCustomers(x);
	}

	public void populateCustomers(FileService x){
		Customers c_list = new Customers();
		customer = new Customer(1, "April Joy","B", "Padrigano");
		c_list.add(customer);
		customer = new Customer(2, "Sabs","C", "Sabs");
		c_list.add(customer);
		customer = new Customer(6, "Noel","E", "Garcia");
		c_list.add(customer);
		customer = new Customer(3, "Kriz","D", "Urmeneta");
		c_list.add(customer);
		customer = new Customer(4, "Ferlie","Z", "Penido");
		c_list.add(customer);
		x.saveObject(c_list, customer_path);
	}
	
}
