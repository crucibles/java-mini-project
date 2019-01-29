package services;

import java.util.Scanner;

public class InputService {
	Scanner sc = new Scanner(System.in);
	ErrorTrapService error_check;
	TimeService time_methods;
	final static int min_cinema_num = 1;
	final static int max_cinema_num = 4;
	final static int min_rating = 0;
	final static int max_rating = 5;
	private String input = "";

	public InputService() {
		error_check = new ErrorTrapService();
		time_methods = new TimeService();
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
		} while (!error_check.isValidRating(rating));

		return rating;
	}

	public String inputGenre() {
		System.out.print("Enter genre:  ");
		String genre = sc.nextLine();
		genre = genre.toLowerCase();
		while (!error_check.isValidGenre(genre)) {
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
				if (error_check.isValidCinemaNum(cn)) {
					break;
				} else {
					System.out.println("Invalid input. Try again.");
					continue;
				}
			} catch (NumberFormatException e) {
				System.out.print("Invalid input. Try again.");
			}
		} while (!error_check.isValidCinemaNum(cn));

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
			System.out.println("Enter title:");
			title = sc.nextLine();
			if (error_check.isValidString(title)) {
				not_legit = false;
			} else
				System.out.println("Invalid input. Enter again.");
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
			System.out.println("Enter rating(out of 5):");
			temp = sc.nextLine();
			if (error_check.isValidNumber(temp)) {
				rating = Integer.parseInt(temp);
				if (error_check.isValidRating(rating)) {
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
				if (error_check.isValidNumber(input)) {
					break;
				} else {
					System.out.println("Invalid input. Try again.");
					continue;
				}
			} catch (NumberFormatException e) {
				System.out.print("Invalid input. Try again.");
			}
		} while (!error_check.isValidNumber(input) && cn<=0);

		return cn;
	}

	public String setFilteredGenre() {
		String genre = "";
		boolean not_legit = true;
		while (not_legit) {
			System.out.println("Enter genre:");
			genre = sc.nextLine();
			if (error_check.isValidString(genre)) {
				genre = genre.toLowerCase();
				if (error_check.isValidGenre(genre)) {
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
			if (error_check.isValidDate(start_date)) {
				start_me = false;
			} else {
				System.out.println("Invalid input. Enter again.");
			}
		}
		return start_date;
	}

	public String setFilteredTime(int type) {
		boolean valid = true;
		String time = "";
		String[] parts;
		while (valid) {
			if (type == 1) {
				System.out.println("Enter Start Time(Military Time)(HH:MM):");
			} else {
				System.out.println("Enter End Time(Military Time)(HH:MM):");
			}

			time = sc.nextLine();
			try {
				if (error_check.isLegitTime(time)) {
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

}
