package controller;

import java.util.ArrayList;

import model.Cinema;
import model.Customer;
import model.Movie;
import model.PopulateFiles;
import others.FilesManager;
import view.Menus;

public class MainController {
	//declarations
	private static Menus menu;
	private static Movie temp_movie;
	private static FilesManager files_manager;
	private static String movie_path = "movie_records.txt";
	private static String customer_path = "customer_records.txt";
	private static String cinema_path = "cinema_records.txt";
	private static PopulateFiles populateFiles;
	
	/**
	 * Initial Author: Padrigano
	 * Last Author: Padrigano
	 * Description: Controls the models, views and other classes.
	 * Date Modified: 1-22-2019 8:00 pm
	 * @param args
	 */
	public static void main(String[] args){
		char choice;
		int temp_id = 0;
		int cinema_num = 1;
		ArrayList<Movie> movie_list = new ArrayList<>();
		ArrayList<Cinema> cinema_list = new ArrayList<>();
		ArrayList<Customer> customer_list = new ArrayList<>();
 		//---
		files_manager = new FilesManager();
		files_manager.createFile(movie_path);
		files_manager.createFile(customer_path);
		
		populateFiles = new PopulateFiles(files_manager);
		
		//build cinema

		menu = new Menus();
		
		do{
			choice = menu.startingMenu();
			if(choice == 'a' || choice == 'A' ){//create movie
				temp_movie = new Movie();
				Cinema c = new Cinema();
				temp_movie = menu.createMovieView(temp_movie);
				//check File first, then increment
				temp_id = files_manager.checkCurrentNumOfRecord(movie_path, temp_movie);
				if(temp_id == 0){
					System.out.println("First record");
					temp_movie.setMovieID(1);
					temp_movie.setCinemaNum(2);
					//haven't assigned the Cinema number in movie input
					c = new Cinema(temp_movie.getCinemaNum(), temp_movie.getMovieID());
					cinema_list.add(c);
					files_manager.saveObjectFirstTime(temp_movie, movie_path);
					files_manager.saveObjectFirstTime(c, cinema_path);

				}else{
					System.out.println("More than 1 record exists");
					temp_movie.setMovieID(
							temp_id++);
					files_manager.saveObjectSucceedingTime(temp_movie, movie_path);
					files_manager.saveObjectSucceedingTime(c, cinema_path);
				}	
			}else if(choice == 'b' || choice == 'B'){//display movies
				//menu
				menu.displayMovieView(movie_path);
			}else if(choice == 'c' || choice == 'C'){//create booking
				menu.createBookingView(movie_path, customer_path, cinema_path);
				
			}else if(choice == 'd' || choice == 'D'){//cancel booking
				menu.cancelBookingView();
			} else {
				choice = menu.startingMenu();
			}
			//break;
		}while(choice != 'e' || choice == 'E');
		
	}
	

	
	
	
	
}
