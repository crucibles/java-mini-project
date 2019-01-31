package controller;

import java.io.File;
import model.Cinema;
import model.Cinemas;
import model.Movie;
import model.Movies;
import services.ErrorTrapService;
import services.FileService;
import view.Menus;

public class MainController {
	// declarations
	private static Menus menu;
	private static FileService files_manager;
	private final static String MOVIE_PATH = "movie_records.xml";
	private final static String CUSTOMER_PATH = "customer_records.xml";
	private final static String CINEMA_PATH = "cinema_records.xml";
	final static char CREATE_MOVIE = 'A';
	final static char DISPLAY_MOVIE = 'B';
	final static char CREATE_BOOKING = 'C';
	final static char CANCEL_BOOKING = 'D';
	final static char EXIT = 'E';

	/**
	 * Initial Author: Padrigano Last Author: Padrigano Description: Controls
	 * the models, views and other classes. Date Modified: 1-22-2019 8:00 pm
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		char choice;
		files_manager = new FileService();
		files_manager.populateCustomers();
		initializeFileManager();
		menu = new Menus();

		do {
			choice = Character.toUpperCase(menu.startingMenu());

			switch (choice) {

				case CREATE_MOVIE : {
					createMovie();
					break;
				}

				case DISPLAY_MOVIE : {
					menu.displayMovieView(MOVIE_PATH);
					break;
				}

				case CREATE_BOOKING : {
					menu.createBookingView(MOVIE_PATH, CUSTOMER_PATH,
							CINEMA_PATH);
					break;
				}

				case CANCEL_BOOKING : {
					menu.cancelBookingView();
				}

			}

		} while (choice != EXIT);

	}

	private static void initializeFileManager() {
		files_manager = new FileService();
		files_manager.createFile(MOVIE_PATH);
		files_manager.createFile(CINEMA_PATH);
		files_manager.createFile(CUSTOMER_PATH);
	}

	private static void createMovie() {
		int latest_id = 0;
		Movie temp_movie = new Movie();
		Cinema temp_cinema = new Cinema();
		Movies movies = new Movies();
		Cinemas cinemas = new Cinemas();
		ErrorTrapService ec = new ErrorTrapService();

		if (!ec.isEmpty(new File(MOVIE_PATH))) {
			movies = (Movies) files_manager.readMultipleObjects(MOVIE_PATH,
					movies);
			cinemas = (Cinemas) files_manager.readMultipleObjects(CINEMA_PATH,
					cinemas);
			latest_id = movies.getlist().size();
		}

		temp_movie = menu.createMovieView(temp_movie);

		if (latest_id == 0) {

			latest_id++;
			temp_movie.setMovieId(latest_id);
			movies.add(temp_movie);
			files_manager.saveObject(movies, MOVIE_PATH);

			// make all cinemas for that movie
			cinemas.setlist(temp_cinema.getCs().makeCinemasForMovie(temp_movie));
			files_manager.saveObject(cinemas, CINEMA_PATH);

		} else {
			
			latest_id++;
			temp_movie.setMovieId(latest_id);
			movies.add(temp_movie);
			files_manager.saveObject(movies, MOVIE_PATH);

			// make all cinemas for that movie
			cinemas.getlist().addAll(temp_cinema.getCs().makeCinemasForMovie(temp_movie));
			files_manager.saveObject(cinemas, CINEMA_PATH);
		}
		

	} // create movie bracket

} // end bracket
