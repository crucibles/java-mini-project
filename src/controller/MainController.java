package controller;

import model.Customer;
import model.Movie;
import others.FilesManager;
import view.Menus;

public class MainController {
	//declarations
	private static Menus menu;
	private static Movie tempMovie;
	public static void main(String[] args){
		char choice;
		menu = new Menus();
		choice = menu.startingMenu();
		if(choice == 'a'){
			tempMovie = new Movie();
			
		}
	}
}
