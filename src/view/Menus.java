package view;

import java.io.EOFException;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import model.Cinema;
import model.Customer;
import model.Movie;
import others.ErrorTrap;
import others.FilesManager;

public class Menus {
	private ErrorTrap error_trap;
	private FilesManager files_manager;
	Scanner sc = new Scanner(System.in);

	public Menus() {
		error_trap = new ErrorTrap();
		files_manager = new FilesManager();
	}

	/**
	 * Initial Author: Padrigano Last Author: Padrigano Description: Displays
	 * the starting menu of the program. Date Modified: 1-22-2019 8:00pm
	 * 
	 * @return char
	 */
	public char startingMenu() {
		boolean legit = true;
		char choice = 'a';

		System.out.println("MOVIE RESERVATION  SYSTEM");
		System.out.println("A.Create Movie");
		System.out.println("B.Display Movies");
		System.out.println("C.Create Booking");
		System.out.println("D.Cancel Booking");
		System.out.println("E.Exit");
		System.out.printf("[Input]");

		while (sc.hasNext()) {
			choice = sc.next().charAt(0);
			break;
		}

		return choice;
	}

	/**
	 * Initial Author: Padrigano Last Author: Padrigano Description: Fills out
	 * the fields of Movie class via input and returns the Movie to controller.
	 * Date Modified: 1-22-2019 8:00 pm
	 * 
	 * @param movie
	 * @return Movie
	 */
	public Movie createMovieView(Movie movie) {
		String title = "", genre = "";
		int rating = 0;
		try {
			System.out.println("Enter title:");
			title = sc.next();
			movie.setTitle(title);
			System.out.println("Enter genre:");
			genre = sc.next();
			if (error_trap.isLegitString(genre)) {
				genre = genre.toLowerCase();
				if (error_trap.isLegitGenre(genre)) {
					movie.setGenre(genre);
				}
			}
			System.out.println("Enter rating(out of 5):");
			rating = sc.nextInt();
			if (error_trap.isLegitRating(rating)) {
				movie.setRating(rating);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return movie;// return movie that has details
	}

	public void displayMovieView(String path, ArrayList<Movie> movie_list) {
		File f = new File(path);

		if (error_trap.isEmpty(f) == true) {
			System.out.println("File is Empty!");
		}

		movie_list = files_manager.readMultipleObjects(path, movie_list);
		for (int i = 0; i < movie_list.size(); i++) {
			System.out.println(
					"Movie Title: " + movie_list.get(i).getTitle() + "Movie Rating:" + movie_list.get(i).getRating());
		}
	}

	public void createBookingView(String movie_path, String customer_path, String cinema_path,
			ArrayList<Cinema> cinema_list, ArrayList<Movie> movie_list, ArrayList<Customer> customer_list) {// add
																											// more
																											// as
																											// needed

		File movie = new File(movie_path);

		if (error_trap.isEmpty(movie) == true) {
			System.out.println("File is Empty!");
		}

		movie_list = files_manager.readMultipleObjects(movie_path, movie_list);

		File customer = new File(customer_path);

		if (error_trap.isEmpty(customer) == true) {
			System.out.println("File is Empty!");
		}

		customer_list = files_manager.readMultipleObjects(customer_path, customer_list);

		File cinema = new File(cinema_path);

		if (error_trap.isEmpty(cinema) == true) {
			System.out.println("File is Empty!");
		}

		// cinema_list = files_manager.readMultipleObjects(cinema_path,
		// cinema_list);

		System.out.print("Enter cinema num: ");
		int cinema_num = sc.nextInt();

		System.out.println("Enter customer id");
		long customer_id = sc.nextLong();

		System.out.println("Enter movie id");
		long movie_id = sc.nextLong();

		for (Cinema c : cinema_list) {
			if (c.getCinema_id() == cinema_num && c.getMovie_id() == movie_id) {
				System.out.println("cinema is found");
			}
			// something here
		}

	}

	public void cancelBookingView() {// add more as needed

	}

	// improve to grid grid.x
	public void displaySeatPlan(Cinema cinema) {
		for (char a = 'A'; a < 'O'; a++) {
			for (int b = 1; b <= 10; b++) {

				System.out.print(a + ": " + b + " status: " + cinema.getSeat_list() + " |");
			}
			System.out.println();
		}
	}

}
