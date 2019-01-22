package view;

import java.util.Scanner;

import model.Movie;

public class Menus {
	public Menus(){
		
	}
	
	public char startingMenu(){
		boolean legit = true;
		char choice = 'a';
		Scanner sc = new Scanner(System.in); 
		while(legit){
			System.out.println("MOVIE RESERVATION  SYSTEM");
			System.out.println("A.Create Movie");
			System.out.println("B.Display Movies");
			System.out.println("C.Create Booking");
			System.out.println("D.Cancel Booking");
			System.out.println("E.Exit");
			System.out.printf("[Input]");
	        choice = sc.next().charAt(0); 
	        if(choice == 'a'){
	        	return choice;
	        }else if(choice == 'b'){
	        	return choice;
	        }else if(choice == 'c'){
	        	return choice;
	        }else if(choice == 'd'){
	        	return choice;
	        }else if(choice == 'e'){
	        	System.out.println("Exiting the program...");
	        }
		}
		return choice;
	}
	
	public Movie createMovieView(Movie movie){
		
		
		return movie;//return movie that has details
	}
	
}
