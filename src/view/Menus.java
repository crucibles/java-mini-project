package view;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.Booking;
import model.Cinema;
import model.Cinemas;
import model.Customer;
import model.Customers;
import model.Movie;
import model.MovieSched;
import model.Movies;
import model.Reservation;
import model.Seat;
import model.Seats;
import services.ErrorTrapService;
import services.FileService;
import services.InputService;

public class Menus {
	private ErrorTrapService error_service;
	private FileService files_manager;
	private Scanner sc = new Scanner(System.in);
	private InputService input_service;
	private DateTimeFormatter formatter = new DateTimeFormatterBuilder()
			.appendOptional(DateTimeFormatter.ISO_LOCAL_DATE).optionalStart()
			.appendLiteral("T").optionalEnd()
			.appendOptional(DateTimeFormatter.ISO_LOCAL_TIME).toFormatter();

	public Menus() {
		error_service = new ErrorTrapService();
		files_manager = new FileService();
		input_service = new InputService();
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
		System.out.printf("[Choice]: ");

		String line = sc.nextLine();
		while (line.length() < 1) {
			line = sc.nextLine().toUpperCase();
		}
		choice = line.charAt(0);

		return choice;
	}

	/**
	 * Initial Author: Padrigano Last Author: Padrigano Description: Fills out
	 * the fields of Movie class via input_service and returns the Movie to
	 * controller. Date Modified: 1-22-2019 8:00 pm
	 * 
	 * @param movie
	 * @return Movie
	 */
	public Movie createMovieView(Movie movie) {
		String title = "", genre = "", start_date = "", start_time = "",
				end_time = "";
		LocalDate sd, ed;
		int num_of_days = 0, rating = 0, cinema_num = 0;
		LocalTime st, et;
		float price;

		try {

			// Title input_service
			System.out.print("Enter title: ");
			title = sc.nextLine();
			movie.setTitle(title);

			// Rating input_service
			rating = input_service.inputRating();
			movie.setRating(rating);

			// Genre input_service
			genre = input_service.inputGenre();
			movie.setGenre(genre);

			// Start Date input_service
			System.out.print("Enter start date: ");
			start_date = sc.nextLine();
			sd = LocalDate.parse(start_date, formatter);
			System.out.println("Difference of: "
					+ sd.compareTo(java.time.LocalDate.now()));
			System.out.println(sd);

			// Days the movie is showing
			System.out.print("Enter number of days to show this movie: ");
			num_of_days = sc.nextInt();
			sc.nextLine();
			ed = sd.plusDays(num_of_days);

			System.out.println(ed);
			// check if conflict with other dates

			// Start time input_service
			System.out.print("Enter start time: ");
			start_time = sc.nextLine();
			st = LocalTime.parse(start_time, formatter);

			// End time input_service
			System.out.print("Enter end time: ");
			end_time = sc.nextLine();
			et = LocalTime.parse(end_time, formatter);

			movie.setMovieSched(new MovieSched(sd, num_of_days, st, et));

			// input_service cinema num
			cinema_num = input_service.inputCinemaNum();
			movie.setCinemaNum(cinema_num);

			System.out.print("Input price: ");
			price = sc.nextFloat();
			movie.setPrice(price);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return movie;// return movie that has details
	}

	public void displayMovieView(String movie_path) {
		File f = new File(movie_path);

		if (error_service.isEmpty(f) == true) {
			System.out.println("File is Empty!");
		}

		Movies movies = new Movies();
		movies = (Movies) files_manager.readMultipleObjects(movie_path, movies);
		for (int i = 0; i < movies.getlist().size(); i++) {
			System.out.println("Movie Title: "
					+ movies.getlist().get(i).getTitle() + "Movie Rating:"
					+ movies.getlist().get(i).getRating());
		}
	}

	public void createBookingView(String movie_path, String customer_path,
			String cinema_path, String reservation_path) {

		boolean isEnough = false;
		Cinema chosen_cinema = new Cinema();
		Reservation reservation = new Reservation();

		ArrayList<Movie> movie_list = new ArrayList<>();
		ArrayList<Cinema> cinema_list = new ArrayList<>();
		ArrayList<Customer> customer_list = new ArrayList<>();
		Seats seat_list = new Seats();
		Seats r_seat_list = new Seats();

		File movie = new File(movie_path);
		File customer = new File(customer_path);
		File cinema = new File(cinema_path);

		if (error_service.isEmpty(movie) == true) {
			System.out.println("File is Empty!");
		} else {
			Movies movies = new Movies();
			movies = (Movies) files_manager.readMultipleObjects(movie_path,
					movies);
		}

		if (error_service.isEmpty(customer) == true) {
			System.out.println("File is Empty!");
		} else {
			Customers customers = new Customers();
			customers = (Customers) files_manager
					.readMultipleObjects(customer_path, customers);
		}

		if (error_service.isEmpty(cinema) == true) {
			System.out.println("File is Empty!");
		} else {
			Cinemas cinemas = new Cinemas();
			cinemas = (Cinemas) files_manager.readMultipleObjects(cinema_path,
					cinema_list);
		}

		System.out.print("Enter cinema num: ");
		int cinema_num = sc.nextInt();

		System.out.print("Enter customer id: ");
		long customer_id = sc.nextLong();

		reservation.setCustomer_id(customer_id);

		while (isEnough != true) {
			System.out.print("(0 - exit )Enter movie id:");
			long movie_id = sc.nextLong();

			if (!error_service.movieIdValid(movie_path, movie_id)) {
				System.out.println("Movie Invalid");
				break;
			}

			chosen_cinema = cinema_list.stream()
					.filter(c -> c.getMovieId() == movie_id)
					.collect(Collectors.toList()).get(0);

			displaySeatPlan(chosen_cinema);

			System.out.print(
					"input_service desired seats with the format: [A2,A3,A4,B6] seats are separated by the comma: ");
			String seats = sc.nextLine();
			seats = sc.nextLine().toUpperCase();
			String[] seat = seats.split(",");
			seat_list = (Seats) chosen_cinema.getSeatList().getlist();

			int x = 0, counter = 0;
			while (counter != seat.length) {

				int seat_num = (int) seat[x].charAt(1);
				char seat_row = seat[x].charAt(0);

				int realSeatNum = ((seat_row - 65) * 10) + seat_num - 49;
				Seat current_seat = (Seat) seat_list.getlist().get(realSeatNum);
				if (!current_seat.isReserved()) {
					current_seat.setReserved(true);
					r_seat_list.add(seat_list.getlist().get(realSeatNum));
					x++;
				} else if (current_seat.isReserved()) {
					System.out.println(
							"This seat is reserved already.\nContinue");

				}

				counter++;
			}

			displaySeatPlan(chosen_cinema);
			reservation.getBookings().add(new Booking(movie_id, r_seat_list));

			System.out.println("Reserve more? (Y for yes, Any for no)");
			String choice = sc.nextLine().toUpperCase();
			if (choice.charAt(0) != 'Y') {
				isEnough = true;
			}
		}

		long temp_id = files_manager.checkCurrentNumOfRecord(reservation_path,
				reservation);
		reservation.setReservation_id(temp_id++);
		if (temp_id == 0) {
			// files_manager.saveObject(reservation, reservation_path);
		} else {
			// files_manager.saveObject(reservation, reservation_path);
		}

		// files_manager.saveObject(cinema_list, cinema_path);

		System.out.println("This is the toal fee: "
				+ getTotalReservationFee(reservation, movie_path));
	}

	public float getTotalReservationFee(Reservation r, String movie_path) {

		String hot_seat = "HotSeat";
		String reg_seat = "RegularSeat";
		float total = 0;
		float moviePrice = 0;
		float hotPrice = 0;

		File movie = new File(movie_path);
		ArrayList<Movie> movie_list = new ArrayList<>();

		if (error_service.isEmpty(movie) == true) {
			System.out.println("File is Empty!");
		} else {
			Movies movies = new Movies();
			movies = (Movies) files_manager.readMultipleObjects(movie_path,
					movies);
		}

		for (Booking b : r.getBookings()) {

			moviePrice = movie_list.stream()
					.filter(m -> m.getMovieId() == b.getMovieId())
					.collect(Collectors.toList()).get(0).getPrice();

			System.out.println("movie price retrieved: " + moviePrice);

			hotPrice = moviePrice * (float) 1.2;

			List<Seat> seats = (List<Seat>) b.getSeats().getlist();

			for (Seat seat : seats) {

				System.out.println(seat.getClass().getSimpleName());

				if (seat.getClass().getSimpleName().equals(hot_seat)) {
					total += hotPrice;
				} else if (seat.getClass().getSimpleName().equals(reg_seat)) {
					total += moviePrice;
				}

			}

		}

		return total;

	}

	public void cancelBookingView() {
		String reservation_path = "reservation_records.txt";
		String cancelled_path = "cancelled_reservation_records.txt";
		String input_service_res_id = "";
		long reservation_id;

		boolean found = false;
		char confirmation = ' ';
		Reservation cancelled_reservation = new Reservation();

		try {

			System.out.println(
					"Enter Reservation ID of the booking that you want to cancel:");
			input_service_res_id = sc.nextLine();
			if (error_service.isValidNumber(input_service_res_id)) {
				reservation_id = Integer.parseInt(input_service_res_id);

				ArrayList<Reservation> list = new ArrayList<Reservation>();
				ArrayList<Reservation> temp = new ArrayList<Reservation>();

				// Cedric
				// list = files_manager.readMultipleObjects(reservation_path,
				// list);
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getReservation_id() != reservation_id) {
						temp.add(list.get(i));
					} else {
						found = true;
						cancelled_reservation = list.get(i);
					}
				}
				if (found) {
					System.out.println(
							"Do you really want to delete this reservation? (y/n)");
					while (sc.hasNextLine()) {
						confirmation = sc.nextLine().charAt(0);
						break;
					}

					// store the cancelled_reservation into the
					// cancelled_reservation.txt file
					if (confirmation == 'Y' || confirmation == 'y') {
						File f = new File("cancelled_reservation.txt");
						if (error_service.isEmpty(f) == true) {
							// files_manager.saveObject(cancelled_reservation,
							// cancelled_path);

						} else {
							// files_manager.saveObject(cancelled_reservation,
							// cancelled_path);
						}
						System.out.println(
								"Your reservation has been cancelled.");
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// improve to grid grid.x
	public void displaySeatPlan(Cinema cinema) {
		int counter = 0;
		System.out.println(
				"==================== SEAT PLAN ======================");

		for (char a = 'A'; a <= 'O'; a++) {
			System.out.print(a + ": ");
			for (int b = 1; b <= 10; b++) {
				
				Seat current_seat = (Seat) cinema.getSeatList().getlist().get(counter); 
				int s = current_seat.isReserved() ? 1 : 0;
				System.out.print(" [" + s + "] ");
				counter++;
			}
			System.out.println();
		}

		System.out.println(
				"==================== SEAT PLAN ======================");
	}

}
