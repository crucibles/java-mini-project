package controller;

import java.io.File;
import java.util.ArrayList;

import model.Cinema;
import model.Cinemas;
import model.Customer;
import model.Movie;
import model.Movies;
import model.PopulateFiles;
import services.ErrorTrapService;
import services.FileService;
import view.Menus;

public class MainController {
	// declarations
	private static Menus menu;
	private static FileService files_manager;
	private static String movie_path = "movie_records.xml";
	private static String customer_path = "customer_records.xml";
	private static String cinema_path = "cinema_records.xml";
	private static String reservation_path = "reservation_records.xml";
	private static PopulateFiles populateFiles;
	final static char create_movie = 'A';
	final static char create_booking = 'C';

	/**
	 * Initial Author: Padrigano Last Author: Padrigano Description: Controls
	 * the models, views and other classes. Date Modified: 1-22-2019 8:00 pm
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		char choice;
		initializeFileManager();
		files_manager = new FileService();
		populateFiles = new PopulateFiles(files_manager);
		menu = new Menus();

		do {
			choice = Character.toUpperCase(menu.startingMenu());

			switch (choice) {

				case create_movie : {
					createMovie();
					break;
				}

				case 'B' : {
					menu.displayMovieView(movie_path);
					break;
				}

				case create_booking : {
					menu.createBookingView(movie_path, customer_path,
							cinema_path, reservation_path);
					break;
				}

				case 'd' : {
					menu.cancelBookingView();
				}

			}

		} while (choice != 'e' || choice == 'E');

	}

	private static void initializeFileManager() {
		files_manager = new FileService();
		files_manager.createFile(movie_path);
		files_manager.createFile(reservation_path);
		files_manager.createFile(cinema_path);
		files_manager.createFile(customer_path);
	}

	private static void createMovie() {
		int latest_id;
		Movie temp_movie = new Movie();
		Cinema temp_cinema = new Cinema();
		Movies movies = new Movies();
		Cinemas cinemas = new Cinemas();
		ErrorTrapService ec = new ErrorTrapService();
		
		if(!ec.isEmpty(new File(movie_path))){		
			System.out.println("hello");
			movies = (Movies) files_manager.readMultipleObjects(movie_path, movies);
			cinemas = (Cinemas) files_manager.readMultipleObjects(cinema_path, cinemas);
			System.out.println("adsasd" + cinemas);
		}
			
		
		temp_movie = menu.createMovieView(temp_movie);
		System.out.println("IM HERE =-===============");
		latest_id = files_manager.checkCurrentNumOfRecord(movie_path, temp_movie);
		System.out.println("IM HERE =-===============");
		System.out.println(latest_id);

		if (latest_id == 0) {

			latest_id++;
			temp_movie.setMovieId(latest_id);
			movies.add(temp_movie);
			files_manager.saveObject(movies, movie_path);
			
			System.out.println("KILOOOOO");
			// make all cinemas for that movie
			cinemas.setlist(temp_cinema.getCs().makeCinemasForMovie(temp_movie));
			System.out.println("KILOOOOO");
			
			System.out.println();
			files_manager.saveObject(cinemas, cinema_path);

		} else {

			temp_movie.setMovieId(latest_id++);
			movies.add(temp_movie);
			files_manager.saveObject(movies, movie_path);
			
			// make all cinemas for that movie
			cinemas.setlist(temp_cinema.getCs().makeCinemasForMovie(temp_movie));
			files_manager.saveObject(cinemas, cinema_path);
		}

	} // create movie bracket

} // end bracket
