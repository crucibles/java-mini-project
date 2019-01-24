package model;

import others.FilesManager;

public class PopulateFiles {
	private FilesManager files_manager;
	private Movie movie;
	private Customer customer;
	private String movie_path = "movie_records.txt";
	private String customer_path = "customer_records.txt";
	public PopulateFiles(FilesManager x){
		populateCustomers(x);
	}
	public void populateMovies(FilesManager x){
		movie = new Movie(1, "Alone Together", 5, "horror", 1, new MovieSched());
		x.saveObjectFirstTime(movie, movie_path);
		movie = new Movie(2, "Alice in Wonderland", 5, "horror", 1, new MovieSched());
		x.saveObjectSucceedingTime(movie, movie_path);
		movie = new Movie(3, "Kita kita", 3, "horror", 1, new MovieSched());
		x.saveObjectSucceedingTime(movie, movie_path);
		movie = new Movie(4, "Ayoko na", 2, "drama", 2, new MovieSched());
		x.saveObjectSucceedingTime(movie, movie_path);
		movie = new Movie(5, "Action Na kami", 2, "action", 3, new MovieSched());
		x.saveObjectSucceedingTime(movie, movie_path);
	}
	public void populateCustomers(FilesManager x){
		customer = new Customer(1, "April Joy","B", "Padrigano");
		x.saveObjectFirstTime(customer, customer_path);
	
		customer = new Customer(2, "Sabs","C", "Sabs");
		x.saveObjectSucceedingTime(customer, customer_path);
		customer = new Customer(2, "Noel","C", "Garcia");
		x.saveObjectSucceedingTime(customer, customer_path);
		customer = new Customer(3, "Kriz","D", "Urmeneta");
		x.saveObjectSucceedingTime(customer, customer_path);
		customer = new Customer(4, "Ferlie","Z", "Penido");
		x.saveObjectSucceedingTime(customer, customer_path);
	}
	
}
