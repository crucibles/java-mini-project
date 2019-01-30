package services;

import java.util.Scanner;

public class InputService {
	Scanner sc = new Scanner(System.in);
	ErrorTrapService es;
	TimeService ts;
	CinemaService cs;
	final static char min_row = 'A';
	final static char max_row = 'O';
	final static char min_hot_row = 'F';
	final static char max_hot_row = 'J';
	final static int min_seat_num = 1;
	final static int max_seat_num = 10;
	final static int min_cinema_num = 1;
	final static int max_cinema_num = 4;
	final static int min_rating = 0;
	final static int max_rating = 5;
	private String input = "";

	public InputService() {
		es = new ErrorTrapService();
		ts = new TimeService();
		cs = new CinemaService();
	}

	public int inputRating() {

		int rating = -1;
		System.out.print("Enter rating(out of 5): ");

		do {
			try {
				input = sc.nextLine();
				rating = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.print("Enter a valid rating: ");
			}
		} while (!es.isValidRating(rating));

		return rating;
	}

	public String inputGenre() {
		System.out.print("Enter genre:  ");
		String genre = sc.nextLine();
		genre = genre.toLowerCase();
		while (!es.isValidGenre(genre)) {
			System.out.print("a valid genre: ");
			genre = sc.nextLine();
		}

		return genre;
	}

	public int inputCinemaNum() {

		int cn = -1;

		do {
			try {
				System.out.print("Enter cinema num: ");
				input = sc.nextLine();
				cn = Integer.parseInt(input);
				if (es.isValidCinemaNum(cn)) {
					break;
				} else {
					System.out.println("Invalid input. Try again.");
					continue;
				}
			} catch (NumberFormatException e) {
				System.out.print("Invalid input. Try again.");
			}
		} while (!es.isValidCinemaNum(cn));

		return cn;
	}
	
	public long inputMovieId() {

		long cn = -1;

		do {
			try {
				System.out.print("Enter movie id: ");
				input = sc.nextLine();
				cn = Long.parseLong(input);
				if (es.isValidMovieId(cn)) {
					break;
				} else {
					System.out.println("Invalid input. Try again.");
					continue;
				}
			} catch (NumberFormatException e) {
				System.out.print("Invalid input. Try again.");
			}
		} while (!es.isValidMovieId(cn));

		return cn;
	}

	/**
	 * Author:Padrigano Description:Errortraps the title entered by the user
	 * 
	 * @param not_legit
	 * @param movie
	 * @return movie
	 */
	public String setFilteredTitle() {
		String title = "";
		boolean not_legit = true;
		// error-trap for Title input
		while (not_legit) {
			System.out.print("Enter title: ");
			title = sc.nextLine();
			if (es.isValidString(title)) {
				not_legit = false;
			} else {
				System.out.println("Invalid input. Enter again.");
			}
		}
		return title;
	}
	/**
	 * Author: Padrigano Description: Error trap the rating input by the user.
	 * 
	 * @param not_legit
	 * @param movie
	 * @return
	 */
	public int setFilteredRating() {
		String temp;
		int rating = 0;
		boolean not_legit = true;
		while (not_legit) {
			System.out.print("Enter rating(out of 5): ");
			temp = sc.nextLine();
			if (es.isValidNumber(temp)) {
				rating = Integer.parseInt(temp);
				if (es.isValidRating(rating)) {
					not_legit = false;
				} else {
					System.out.println("Invalid input. Enter again.");
					continue;
				}
			} else {
				System.out.println("Invalid input. Enter again.");
				continue;
			}
		}
		return rating;
	}

	public int inputNumberOfDays() {
		int cn = -1;

		do {
			try {
				System.out.print("Enter number of days: ");
				input = sc.nextLine();
				cn = Integer.parseInt(input);
				if (es.isValidNumber(input)) {
					break;
				} else {
					System.out.println("Invalid input. Try again.");
					continue;
				}
			} catch (NumberFormatException e) {
				System.out.print("Invalid input. Try again.");
			}
		} while (!es.isValidNumber(input) && cn <= 0);

		return cn;
	}

	public String setFilteredGenre() {
		String genre = "";
		boolean not_legit = true;
		while (not_legit) {
			System.out.print("Enter genre: ");
			genre = sc.nextLine();
			if (es.isValidString(genre)) {
				genre = genre.toLowerCase();
				if (es.isValidGenre(genre)) {
					not_legit = false;
				} else {
					System.out.println("Invalid input. Enter again.");
				}
			}
		}
		return genre;
	}

	public String setFilteredDate(String msg) {
		// error trap dates,hours and minutes
		String start_date = "";
		boolean start_me = true;
		// error-trap for Start Date
		while (start_me) {
			System.out.print(msg + ": ");
			start_date = sc.nextLine();
			if (es.isValidDate(start_date)) {
				start_me = false;
			} else {
				System.out.println("Invalid input. Enter again.");
			}
		}
		return start_date;
	}
	
	public String[] inputSeats(){
		boolean flag = true;
		String[] seats;
		
		do{
			flag = true;
			System.out.print("Input seats to reserve.\nThis is the format [A2,A3,A4,B6].\nSeats are separated by the comma: ");
			seats = sc.nextLine().toUpperCase().split(",");
			try{				
				for(String s: seats){
					char row = s.charAt(0);
					int num = Integer.parseInt(String.valueOf(s.charAt(1)));
					
					System.out.println(row + ": " +  num);
					// inputs are real rows and columns of seat
					if( !(row >= min_row && row <= max_row && num >= min_seat_num && num<= max_seat_num)){
						flag = false;
						break;
					}
				}
			} catch(NumberFormatException e){
				System.out.println("Invalid seat inputted.");
				flag = false;
			}
			
		}while(!flag);
		
		return seats;
	}

	public String setFilteredTime(int type) {
		boolean valid = true;
		String time = "";
		String[] parts;
		while (valid) {
			if (type == 1) {
				System.out.print("Enter Start Time(Military Time)(HH:MM): ");
			} else {
				System.out.print("Enter End Time(Military Time)(HH:MM): ");
			}

			time = sc.nextLine();
			try {
				if (es.isLegitTime(time)) {
					parts = time.split(":");
					valid = false;
				} else {
					System.out.println("Invalid input. Enter again.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return time;
	}

	public float inputPrice() {
		
		float cn = -1;

		do {
			try {
				System.out.print("Enter price: ");
				input = sc.nextLine();
				cn = Float.parseFloat(input);
			} catch (NumberFormatException e) {
				System.out.print("Invalid input. Try again.");
			}
		} while (cn == -1);

		return cn;
	}

}
