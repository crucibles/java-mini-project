package view;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.Booking;
import model.Cinema;
import model.Cinemas;
import model.Customers;
import model.Movie;
import model.MovieSched;
import model.Movies;
import model.Reservation;
import model.Seat;
import model.Seats;
import services.CinemaService;
import services.ErrorTrapService;
import services.FileService;
import services.InputService;
import services.TimeService;

public class Menus {
	final static private int LINE = 1;
	private ErrorTrapService error_service;
	private FileService files_manager;
	private Scanner sc = new Scanner(System.in);
	private InputService input_service;
	private CinemaService cinema_service;
	private DateTimeFormatter formatter = new DateTimeFormatterBuilder()
			.appendOptional(DateTimeFormatter.ISO_LOCAL_DATE).optionalStart()
			.appendLiteral("T").optionalEnd()
			.appendOptional(DateTimeFormatter.ISO_LOCAL_TIME).toFormatter();

	public Menus() {
		error_service = new ErrorTrapService();
		files_manager = new FileService();
		input_service = new InputService();
		cinema_service = new CinemaService();
	}

	/**
	 * Initial Author: Padrigano Last Author: Padrigano Description: Displays
	 * the starting menu of the program. Date Modified: 1-22-2019 8:00pm
	 * 
	 * @return char
	 */
	public char startingMenu() {
		char choice = 'a';
		System.out.println(
				"\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		System.out.println(
				"*                    MOVIE RESERVATION  SYSTEM                   *");
		System.out.println(
				"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		System.out.println(
				"*                       [A] Create Movie                         *");
		System.out.println(
				"*                       [B] Display Movies                       *");
		System.out.println(
				"*                       [C] Create Booking                       *");
		System.out.println(
				"*                       [D] Cancel Booking                       *");
		System.out.println(
				"*                       [E] Exit                                 *");
		System.out.println(
				"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		System.out.printf("[Input] : ");

		String line = sc.nextLine();
		while (line.length() < LINE) {
			line = sc.nextLine();
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
		int num_of_days = 0;
		TimeService ts = new TimeService();
		float price;

		try {

			movie.setTitle(input_service.setFilteredTitle());
			movie.setRating(input_service.setFilteredRating());
			movie.setGenre(input_service.setFilteredGenre());
			start_date = input_service.setFilteredDate("Input Start Date");
			sd = LocalDate.parse(start_date);
			num_of_days = input_service.inputNumberOfDays();
			ed = sd.plusDays(num_of_days);
			
			// date available
//			ts.isDateAvailable(start_date, end_date);
			

			// Start time input_service
//			System.out.print("Enter start time: ");
//			start_time = sc.nextLine();
//			st = LocalTime.parse(start_time, formatter);
//
//			// End time input_service
//			System.out.print("Enter end time: ");
//			end_time = sc.nextLine();
//			et = LocalTime.parse(end_time, formatter);
//
//			movie.setMovieSched(new MovieSched(sd, num_of_days, st, et));
//
//			// input_service cinema num
//			cinema_num = input_service.inputCinemaNum();
//			movie.setCinemaNum(cinema_num);
//
//			System.out.print("Input price: ");
//			price = sc.nextFloat();
//			movie.setPrice(price);

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
		// GINEL AND CLARENCE
		Collections.sort(movies.getlist(), new Comparator<Movie>() {
			@Override
			public int compare(Movie a, Movie b) {
				return a.getMovieSched().getStartDate()
						.compareTo(b.getMovieSched().getStartDate());
			}
		});
		System.out.println(
				"\n ---------------------------------------------------------------------------------------------------------------------------");
		System.out.println(
				"|\t\t\t\t\t\t    MOVIES\t\t\t\t\t\t                    |");
		System.out.println(
				" ---------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%25s %13s %13s %15s %10s %10s %15s %10s",
				"Movie Title", "Start Date", "End Date", "Cinema Number",
				"Rating", "Out of 5", "Start Time", "End Time");
		System.out.println(
				"\n ---------------------------------------------------------------------------------------------------------------------------");

		for (Movie movie : movies.getlist()) {
			System.out.format("%25s %13s %13s %13s %10s %10s %15s %10s",
					movie.getTitle(), movie.getMovieSched().getStartDate(),
					movie.getMovieSched().getEndDate(), movie.getCinemaNum(),
					displayStarsRating(movie.getRating()), movie.getRating(),
					movie.getMovieSched().getStartTime(),
					(movie.getMovieSched().getEndTime()));
			System.out.println();
		}
		System.out.println(
				" ---------------------------------------------------------------------------------------------------------------------------");

	}

	public static String displayStarsRating(int rating) {
		String stars = "";
		while (rating != 0) {
			stars += "*";
			rating--;
		}
		return stars;
	}

	public void createBookingView(String movie_path, String customer_path, String cinema_path) {

		boolean isEnough = false;
		Cinema chosen_cinema = new Cinema();
		Reservation reservation = new Reservation();

		Seats seat_list = new Seats();
		Seats r_seat_list = new Seats();

		long reservation_id = 0;
		File movie = new File(movie_path);
		File customer = new File(customer_path);
		File cinema = new File(cinema_path);
		Movies movies = new Movies();
		Customers customers = new Customers();
		Cinemas cinemas = new Cinemas();

		if (error_service.isEmpty(movie) == true) {
			System.out.println("File is Empty!");
		} else {
			movies = (Movies) files_manager.readMultipleObjects(movie_path,
					movies);
			System.out.println(movies.getlist().get(0));
		}

		if (error_service.isEmpty(customer) == true) {
			System.out.println("File is Empty!");
		} else {
			customers = (Customers) files_manager
					.readMultipleObjects(customer_path, customers);
			System.out.println(customers.getlist().get(0));
		}

		if (error_service.isEmpty(cinema) == true) {
			System.out.println("File is Empty!");
		} else {
			cinemas = ((Cinemas) files_manager.readMultipleObjects(cinema_path,
					cinemas));
			System.out.println(cinemas.getlist().get(0));
		}

		System.out.print("Enter customer id: ");
		long customer_id = sc.nextLong();

		reservation.setCustomer_id(customer_id);

		while (isEnough != true) {
			System.out.print("Enter cinema num: ");
			int cinema_num = sc.nextInt();

			System.out.print("(0 - exit )Enter movie id:");
			long movie_id = sc.nextLong();

			if (!error_service.movieIdValid(movie_path, movie_id)) {
				System.out.println("Movie Invalid");
				break;
			}

			chosen_cinema = cinemas.getlist().stream()
					.filter(c -> c.getMovieId() == movie_id
							&& c.getCinemaId() == cinema_num)
					.collect(Collectors.toList()).get(0);

			displaySeatPlan(chosen_cinema);

			System.out.print(
					"input_service desired seats with the format: [A2,A3,A4,B6] seats are separated by the comma: ");
			String seats = sc.nextLine();
			seats = sc.nextLine().toUpperCase();
			String[] seat = seats.split(",");
			seat_list.setlist(chosen_cinema.getSeatList().getlist());

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

		if (chosen_cinema.getReservations().getlist() == null) {
			reservation_id = 1;
		} else {
			reservation_id = chosen_cinema.getReservations().getlist().size();
		}
		reservation.setReservation_id(reservation_id++);
		chosen_cinema.getReservations().add(reservation);

		files_manager.saveObject(cinemas, cinema_path);

		System.out.println("This is the total fee: "
				+ getTotalReservationFee(reservation, movie_path, movies));
	}

	public float getTotalReservationFee(Reservation r, String movie_path,
			Movies movies) {

		float total = 0;
		float moviePrice = 0;
		float hotPrice = 0;
		List<Seat> seats = new ArrayList<>();

		for (Booking b : r.getBookings().getlist()) {

			moviePrice = movies.getlist().stream()
					.filter(m -> m.getMovieId() == b.getMovieId())
					.collect(Collectors.toList()).get(0).getPrice();

			hotPrice = moviePrice * (float) 1.2;

			seats = (List<Seat>) b.getSeats().getlist();

			for (Seat seat : seats) {

				if (cinema_service.isHotRow(seat.getSeatRow())) {
					total += hotPrice;
				} else if (cinema_service.isRegularRow(seat.getSeatRow())) {
					total += moviePrice;
				}

			}

		}

		return total;

	}

	public void cancelBookingView() {// add more as needed
		String cinema_path = "cinema_records.xml";
		Cinemas cinemas = new Cinemas();
		CinemaService cservice = new CinemaService();

		cinemas = (Cinemas) files_manager.readMultipleObjects(cinema_path,
				cinemas);
		List<Reservation> reservation_list = new ArrayList<>();
		List<Cinema> cs = new ArrayList<>();

		// ask customer id

		cs = cinemas.getlist();

		// find all reservation in cinemas
		for (Cinema c : cs) {
			// 1 here is the customer id
			if (c.getReservations().getlist() != null) {
				reservation_list.addAll(c.getReservations().getlist().stream()
						.filter(reservation -> reservation
								.getCustomer_id() == 1)
						.collect(Collectors.toList()));
			}
		}

		// input desired reservation id to be deleted

		Reservation cancel_r = reservation_list.stream()
				.filter(r -> r.getReservation_id() == 1)
				.collect(Collectors.toList()).get(0);

		// loop bookings to find the cinema seats, and update the cancel_r list
		// of seats to not reserved.
		for (Booking b : cancel_r.getBookings().getlist()) {

			int cinema_num = cservice.getCinemaNum(b.getMovieId());

			Cinema chosen_cinema = cinemas.getlist().stream()
					.filter(c -> c.getCinemaId() == cinema_num)
					.collect(Collectors.toList()).get(0);

			ArrayList<Seat> seats = (ArrayList<Seat>) chosen_cinema
					.getSeatList().getlist();
			ArrayList<Seat> b_seats = (ArrayList<Seat>) b.getSeats().getlist();

			for (Seat s : seats) {

				for (Seat sb : b_seats) {

					if (s.getSeatNum() == sb.getSeatNum()
							&& s.getSeatRow() == sb.getSeatRow()) {
						s.setReserved(false);
					}

				}

			}
			Seats ss = new Seats();
			ss.setlist(seats);

			chosen_cinema.getReservations().getlist().remove(cancel_r);
			chosen_cinema.setSeatList(ss);

		}

		files_manager.saveObject(cinemas, cinema_path);

	}

	// improve to grid grid.x
	public void displaySeatPlan(Cinema cinema) {
		int counter = 0;
		System.out.println(
				"==================== SEAT PLAN ======================");

		for (char a = 'A'; a <= 'O'; a++) {
			System.out.print(a + ": ");
			for (int b = 1; b <= 10; b++) {

				Seat current_seat = (Seat) cinema.getSeatList().getlist()
						.get(counter);
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
