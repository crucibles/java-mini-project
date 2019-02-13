package view;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
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
	final static char MIN_ROW = 'A';
	final static char MAX_ROW = 'O';
	final static private int LINE = 1;
	private ErrorTrapService error_service;
	private FileService files_manager;
	private Scanner sc = new Scanner(System.in);
	private InputService input_service;
	private CinemaService cinema_service;

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
		String start_date = "", start_time = "", end_time = "";
		LocalDate sd, ed;
		int num_of_days = 0;
		TimeService ts = new TimeService();

		try {

			movie.setTitle(input_service.setFilteredTitle());
			movie.setRating(input_service.setFilteredRating());
			movie.setGenre(input_service.setFilteredGenre());
			movie.setCinemaNum(input_service.inputCinemaNum());
			
			do {
				start_date = input_service.setFilteredDate("Input Start Date");
				sd = LocalDate.parse(start_date);
				num_of_days = input_service.inputNumberOfDays();
				ed = sd.plusDays(num_of_days);
			} while (!ts.isDateAvailable(start_date, ed.toString(),
					movie.getCinemaNum()));

			start_time = input_service.setFilteredTime(1);
			end_time = input_service.setFilteredTime(0);

			movie.setMovieSched(new MovieSched(sd, num_of_days,
					LocalTime.parse(start_time), LocalTime.parse(end_time)));
			movie.setPrice(input_service.inputPrice());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return movie;// return movie that has details
	}

	public void displayMovieView(String movie_path) {
		File f = new File(movie_path);

		if (error_service.isEmpty(f) == true) {
			System.out.println("File is Empty!");
			return;
		}
		
		Movies movies = new Movies();
		movies = (Movies) files_manager.readMultipleObjects(movie_path, movies);
		//GINEL AND CLARENCE
		Collections.sort(movies.getlist(), new Comparator<Movie>(){
			@Override
			public int compare(Movie a, Movie b) {
				return a.getMovieSched().getStartDate().compareTo(b.getMovieSched().getStartDate());
			}		
		});	
		System.out.println("\n --------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("|\t\t\t\t\t\t\t    MOVIES\t\t\t\t\t\t                 |");
		System.out.println(" --------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%10s %25s %13s %13s %15s %10s %10s %15s %10s" , "Movie ID", "Movie Title" , "Start Date", "End Date" ," Cinema Number" , "Rating" , "Out of 5", "Start Time" , "End Time");
		System.out.println("\n --------------------------------------------------------------------------------------------------------------------------------"); 	
		
		for (Movie movie : movies.getlist()) {
				System.out.format("%10s %25s %13s %13s %15s %10s %10s %15s %10s" ,movie.getMovieId(),movie.getTitle(),movie.getMovieSched().getStartDate(),movie.getMovieSched().getEndDate(),movie.getCinemaNum(), displayStarsRating(movie.getRating()),movie.getRating(),
						movie.getMovieSched().getStartTime(),(movie.getMovieSched().getEndTime()));
				System.out.println();
		}
		System.out.println(" --------------------------------------------------------------------------------------------------------------------------------"); 	
		
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
		long reservation_id = 0;
		Cinema chosen_cinema = new Cinema();
		Reservation reservation = new Reservation();
		Seats seat_list = new Seats(), r_seat_list = new Seats();
		File movie = new File(movie_path), customer = new File(customer_path);
		Movies movies = new Movies();
		Customers customers = new Customers();
		Cinemas cinemas = new Cinemas();

		if (error_service.isEmpty(movie)) {
			System.out.println("No movies yet!");
			return;
		} else {
			movies = (Movies) files_manager.readMultipleObjects(movie_path,
					movies);
			cinemas = cinema_service.getCinemas();
		}

		if (error_service.isEmpty(customer)) {
			System.out.println("No registered customer yet!");
			return;
		} else {
			customers = (Customers) files_manager.readMultipleObjects(customer_path, customers);
		}

		System.out.print("Enter customer id: ");
		long customer_id = sc.nextLong();
		sc.nextLine();
		reservation.setCustomer_id(customer_id);

		while (isEnough != true) {

			int cinema_num = input_service.inputCinemaNum();
			String watch_date = input_service.setFilteredDate("Enter cinema date");
			long movie_id = input_service.inputMovieId();
			
			System.out.println("cn: " + cinema_num + " wd: " + watch_date + "mi: " + movie_id);


			if (cinema_service.isCinemaExisting(movie_id, cinema_num, watch_date, cinemas)) {
				chosen_cinema = cinema_service.getChosenCinema(movie_id, cinema_num, watch_date, cinemas);
				displaySeatPlan(chosen_cinema);
				String[] seat = input_service.inputSeats();
				seat_list.setlist(chosen_cinema.getSeatList().getlist());
				
				for(String st : seat){
					
					int seat_num = Integer.parseInt(String.valueOf(st.charAt(1)));
					char seat_row = st.charAt(0);					
					Seat current_seat = seat_list.getlist().stream().filter(s -> s.getSeatNum() == seat_num && s.getSeatRow() == seat_row).collect(Collectors.toList()).get(0);
					
					if (!current_seat.isReserved()) {
						current_seat.setReserved(true);
						r_seat_list.add(current_seat);
					} else if (current_seat.isReserved()) {
						System.out.println("There is a seat selected that is reserved already.");
						return;
					}
					
				}

				displaySeatPlan(chosen_cinema);
				reservation.getBookings()
						.add(new Booking(movie_id, r_seat_list));

				System.out.println("Reserve more? (Y for yes, Any for no)");
				String choice = sc.nextLine().toUpperCase();
				if (choice.charAt(0) != 'Y') {
					isEnough = true;
				}
			}

		}
		
		for(Cinema c: cinemas.getlist()){
			if(c.getReservations().getlist() != null){				
				reservation_id += c.getReservations().getlist().size();
			}
		}

		reservation.setReservation_id(reservation_id+1);
		chosen_cinema.getReservations().add(reservation);

		
		System.out.println("This is the total fee: " + getTotalReservationFee(reservation, movie_path, movies));
		
		if(isCheckOut()){			
			files_manager.saveObject(cinemas, cinema_path);		
		}
	}
	
	public boolean isCheckOut(){
		
		System.out.print("Would you like to check out these seats?\n (Y for yes) (Any for No)");
		String choice = sc.nextLine().toUpperCase();
		
		if(choice.equals("Y")){
			return true;
		} else {
			return false;
		}
		
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
					System.out.println("HotSeat " + seat.getSeatRow() + seat.getSeatNum() + ": " + hotPrice + ".");
					total += hotPrice;
				} else if (cinema_service.isRegularRow(seat.getSeatRow())) {
					System.out.println("RegSeat " + seat.getSeatRow() + seat.getSeatNum() + ": " + moviePrice + ".");
					total += moviePrice;
				}

			}

		}

		return total;

	}
	
	public void cancelBookingView() {
		String cinema_path = "cinema_records.xml";
		Cinemas cinemas = new Cinemas();
		CinemaService cservice = new CinemaService();

		cinemas = (Cinemas) files_manager.readMultipleObjects(cinema_path,
				cinemas);
		List<Reservation> reservation_list = new ArrayList<>();
		List<Cinema> cs = new ArrayList<>();

		String customer_id = "";
		long parsed_customer_id;
				
		// ask customer id
		do{
			System.out.print("\n\nEnter customer id: ");
			customer_id = sc.nextLine();
		}while(!error_service.isValidNumber(customer_id));
		parsed_customer_id = Integer.parseInt(customer_id);

		cs = cinemas.getlist();

		// find all reservation in cinemas
		for (Cinema c : cs) {
			// 1 here is the customer id
			if (c.getReservations().getlist() != null) {
				reservation_list.addAll(c.getReservations().getlist().stream()
						.filter(reservation -> reservation
								.getCustomer_id() == parsed_customer_id)
						.collect(Collectors.toList()));
			}
		}
		
		if(reservation_list.size()>0){
			//display all reservation
			System.out.println("\"Reservation IDs of the customer\"");
			for (Reservation r : reservation_list) {
				System.out.println(r.getReservation_id()); 
			}
			

			// input desired reservation id to be deleted
			System.out.print("Enter reservation id that you want to delete: ");
			long reservation_id = sc.nextLong();
			
			try{
				Reservation cancel_r = reservation_list.stream()
						.filter(r -> r.getReservation_id() == reservation_id)
						.collect(Collectors.toList()).get(0);
				
				if(cancel_r != null){
					// loop bookings to find the cinema seats, and update the cancel_r list
					// of seats to not reserved.
					for (Booking b : cancel_r.getBookings().getlist()){

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
			}catch(Exception e){
				System.out.println("Inputted reservation ID was not made by the customer.");
			}
			
		}else{
			System.out.println("No Reservation made by the Customer.");
		}
		

	}


	// improve to grid grid.x
	public void displaySeatPlan(Cinema cinema) {
		int counter = 0;
		System.out.println(
				"==================== SEAT PLAN ======================");

		for (char a = MIN_ROW; a <= MAX_ROW; a++) {
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
