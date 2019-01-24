package view;

import java.io.EOFException;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import model.Booking;
import model.Cinema;
import model.Customer;
import model.Movie;
import model.Reservation;
import model.Seat;
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

		String line = sc.nextLine();
		while (line.length() < 1) {
			line = sc.nextLine();
		}
		choice = line.charAt(0);

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
			title = sc.nextLine();
			movie.setTitle(title);
			System.out.println("Enter genre:");
			genre = sc.nextLine();
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

	public void displayMovieView(String path) {
		File f = new File(path);

		if (error_trap.isEmpty(f) == true) {
			System.out.println("File is Empty!");
		}

		ArrayList<Movie> movie_list = new ArrayList<Movie>();
		movie_list = files_manager.readMultipleObjects(path, movie_list);
		for (int i = 0; i < movie_list.size(); i++) {
			System.out.println(
					"Movie Title: " + movie_list.get(i).getTitle() + "Movie Rating:" + movie_list.get(i).getRating());
		}
	}

	public void createBookingView(String movie_path, String customer_path, String cinema_path) {

		boolean isEnough = false;

		Reservation reservation = new Reservation();

		ArrayList<Movie> movie_list = new ArrayList<>();
		ArrayList<Cinema> cinema_list = new ArrayList<>();
		ArrayList<Customer> customer_list = new ArrayList<>();
		ArrayList<Seat> seat_list = new ArrayList<>();
		ArrayList<Seat> r_seat_list = new ArrayList<>();

		File movie = new File(movie_path);
		File customer = new File(customer_path);
		File cinema = new File(cinema_path);

		if (error_trap.isEmpty(movie) == true) {
			System.out.println("File is Empty!");
		} else {
			movie_list = files_manager.readMultipleObjects(movie_path, movie_list);
		}

		if (error_trap.isEmpty(customer) == true) {
			System.out.println("File is Empty!");
		} else {
			customer_list = files_manager.readMultipleObjects(customer_path, customer_list);
		}

		if (error_trap.isEmpty(cinema) == true) {
			System.out.println("File is Empty!");
		} else {
			cinema_list = files_manager.readMultipleObjects(cinema_path, cinema_list);
		}

		System.out.print("Enter cinema num: ");
		int cinema_num = sc.nextInt();

		// check if customer is existing
		System.out.print("Enter customer id: ");
		long customer_id = sc.nextLong();

		reservation.setCustomer_id(customer_id);

		while (isEnough != true) {
			System.out.print("Enter movie id:  [0 to exit]");
			long movie_id = sc.nextLong();
			
			if(movie_id == 0){
				break;
			}
			
			
			for (Cinema c : cinema_list) {

				System.out.println(c.getCinema_id());
				if (c.getCinema_id() == cinema_num && c.getMovie_id() == movie_id) {
					seat_list = c.getSeat_list();
					displaySeatPlan(c);

					System.out.print(
							"Input desired seats with the format: [A2,A3,A4,B6] seats are separated by the comma: ");
					String seats = sc.nextLine();
					seats = sc.nextLine().toUpperCase();
					String[] seat = seats.split(",");

					System.out.println(seat[0]);

					for (Seat s : seat_list) {
						int x = 0;
						int seatNum = (int) seat[x].charAt(1);
						char row = seat[x].charAt(0);

						if (s.getSeatNum() + 49 == seatNum && s.getSeatRow() == row) {
							String status = s.isReserved() ? "reserved already. " : "available. ";
							System.out.println("This seat is " + status);
							if (!s.isReserved()) {
								r_seat_list.add(s);
								// pass the actual seat number. Row * 10 + seatNum
								c.getSeat_list().get(x).setReserved(true);
								displaySeatPlan(c);
							}
							break;
						}

						x++;
					}

					reservation.getBookings().add(new Booking(movie_id, r_seat_list));

				}

			}

		}
		
		

	}

	public void cancelBookingView() {// add more as needed

	}

	// improve to grid grid.x
	public void displaySeatPlan(Cinema cinema) {
		int counter = 0;
		System.out.println("==================== SEAT PLAN ======================");

		for (char a = 'A'; a <= 'O'; a++) {
			System.out.print(a + ": ");
			for (int b = 1; b <= 10; b++) {

				int s = cinema.getSeat_list().get(counter).isReserved() ? 1 : 0;
				System.out.print(" [" + s + "] ");
				counter++;
			}
			System.out.println();
		}

		System.out.println("==================== SEAT PLAN ======================");
	}

}
