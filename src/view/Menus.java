package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import model.Cinema;
import model.Movie;
import others.ErrorTrap;
import others.FilesManager;

public class Menus {
	private ErrorTrap error_trap;
	private FilesManager files_manager;
	Scanner sc = new Scanner(System.in); 
	public Menus(){
		error_trap = new ErrorTrap();
		files_manager = new FilesManager();
	}
	/**
	 * Initial Author: Padrigano
	 * Last Author: Padrigano
	 * Description: Displays the starting menu of the program.
	 * Date Modified: 1-22-2019 8:00pm
	 * @return char
	 */
	public char startingMenu(){
		boolean legit = true;
		char choice = 'a';
		
		
		System.out.println("MOVIE RESERVATION  SYSTEM");
		System.out.println("A.Create Movie");
		System.out.println("B.Display Movies");
		System.out.println("C.Create Booking");
		System.out.println("D.Cancel Booking");
		System.out.println("E.Exit");
		System.out.printf("[Input]");
		 while (sc.hasNextLine()){
			 choice = sc.nextLine().charAt(0);
		      break;
		   }
	
		return choice;
	}
	
	/**
	 * Initial Author: Padrigano
	 * Last Author: Padrigano
	 * Description: Fills out the fields of Movie class via input and returns the Movie to controller.
	 * Date Modified: 1-22-2019 8:00 pm 
	 * @param movie
	 * @return Movie
	 */
	public Movie createMovieView(Movie movie){
		String title= "", genre = "";
		int rating=0;
		try{
				System.out.println("Enter title:");
				title = sc.nextLine();
				movie.setTitle(title);		
				System.out.println("Enter genre:");
				genre = sc.nextLine();		
				if(error_trap.isLegitString(genre)){
					genre = genre.toLowerCase();
					if(error_trap.isLegitGenre(genre)){
						movie.setGenre(genre);
					}
				}
				System.out.println("Enter rating(out of 5):");
				rating = sc.nextInt();
				if(error_trap.isLegitRating(rating)){
					movie.setRating(rating);
				}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		sc.close();
		return movie;//return movie that has details
	}
	
	public void displayMovieView(ArrayList<Movie> movieList){
		
	}
	
	public void createBookingView(){//add more as needed
		
	}
	
	public void cancelBookingView(){//add more as needed
		
	}
	
	// improve to grid grid.x
	public void displaySeatPlan(Cinema cinema){
		for (char a = 'A'; a < 'O'; a++) {
			for (int b = 1; b <= 10; b++) {
				System.out.print(a +  ": " + b +"|");
			}
			System.out.println();
		}
	}
}
