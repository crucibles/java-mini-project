package view;

import java.util.Scanner;

import model.Movie;

public class Menus {
	public Menus() {

	}

	public char startingMenu() {
		boolean legit = true;
		char choice = 'a';
		Scanner sc = new Scanner(System.in);
		while (legit) {
			System.out.println("MOVIE RESERVATION  SYSTEM");
			System.out.println("A.Create Movie");
			System.out.println("B.Display Movies");
			System.out.println("C.Create Booking");
			System.out.println("D.Cancel Booking");
			System.out.println("E.Exit");
			System.out.printf("[Input]");
			choice = sc.next().charAt(0);

			switch (choice) {
			case 'e':
				System.out.println("Exiting Program.");
				legit = false;
				break;
			default:
				return choice;
			}
		}
		return choice;
	}

	public Movie createMovieView(Movie movie) {

		return movie;// return movie that has details
	}

}
