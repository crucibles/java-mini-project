package services;

import java.util.Scanner;

public class InputService {
	Scanner sc = new Scanner(System.in);
	ErrorTrapService es;
	TimeService ts;
	CinemaService cs;
	final static char MIN_ROW = 'A';
	final static char MAX_ROW = 'O';
	final static char MIN_HOT_ROW = 'F';
	final static char MAX_HOT_ROW = 'J';
	final static int MIN_SEAT_NUM = 1;
	final static int MAX_SEAT_NUM = 10;
	final static int MIN_CINEMA_NUM = 1;
	final static int MAX_CINEMA_NUM = 4;
	final static int MIN_RATING = 0;
	final static int MAX_RATING = 5;
	private final static String[] GENRE_LIST = {"horror", "action", "drama", "comedy", "romance"};
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
		
		System.out.println("* * * * * *");
		System.out.println("*  GENRE  *");
		System.out.println("* * * * * *");
		for(int i = 0;i< GENRE_LIST.length;i++){
			System.out.println(GENRE_LIST[i]);
		}
		while(not_legit){
			System.out.print("Enter genre: ");
			genre = sc.nextLine();
			if (es.isValidString(genre)) {
				genre = genre.toLowerCase();
				if (es.isValidGenre(genre)) {
					not_legit = false;
				}else{
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
					if( !(row >= MIN_ROW && row <= MAX_ROW && num >= MIN_SEAT_NUM && num<= MAX_SEAT_NUM)){
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
		
		while (valid) {
			if (type == 1) {
				System.out.print("Enter Start Time(Military Time)(HH:MM): ");
			} else {
				System.out.print("Enter End Time(Military Time)(HH:MM): ");
			}

			time = sc.nextLine();
			try {
				if (es.isLegitTime(time)) {
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
