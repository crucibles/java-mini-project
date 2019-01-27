package services;

import java.util.Scanner;

public class InputService {
	Scanner sc = new Scanner(System.in);
	ErrorTrapService error_check;
	final static int min_cinema_num = 1;
	final static int max_cinema_num = 4;
	final static int min_rating = 0;
	final static int max_rating = 5;
	private String input = "";

	public InputService() {
		error_check = new ErrorTrapService();
	}

	public int inputRating() {

		int rating = -1;
		System.out.print("Enter rating(out of 5): ");
		
		do{
			try{
				input = sc.nextLine();
				rating = Integer.parseInt(input);
			} catch(NumberFormatException e) {
				System.out.print("Enter a valid rating: ");
			}
		} while(!error_check.isValidRating(rating));
		
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
		System.out.print("Enter cinema num: ");
		
		do{
			try{
				input = sc.nextLine();
				cn = Integer.parseInt(input);
			} catch(NumberFormatException e) {
				System.out.print("Enter a valid cn: ");
			}
		} while(!error_check.isValidCinemaNum(cn));
		
		return cn;
	}

}
