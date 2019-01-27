package services;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import model.Movie;
import model.Movies;

public class ErrorTrapService {
	private FileService files_manager;
	final static int min_cinema_num = 1;
	final static int max_cinema_num = 4;
	final static int min_rating = 0;
	final static int max_rating = 5;
	
	public ErrorTrapService() {
		files_manager = new FileService();
	}

	/**
	 * Initial Author: Padrigano Last Author: Padrigano Description: Checks if a
	 * string is composed of alphanumeric characters. Date Modified: 1-22-2019
	 * 8:00 pm
	 * 
	 * @param str
	 * @return boolean
	 */
	public boolean isValidString(String str) {
		boolean result = str.matches("^[a-zA-Z0-9]+$");
		return result;
	}

	/**
	 * Initial Author: Padrigano Last Author: Padrigano Description: Check if
	 * string is composed of all numbers or not using regex. Date Modified:
	 * 1-22-2018 8:00 pm
	 * 
	 * @param str
	 * @return boolean
	 */
	public boolean isValidNumber(String str) {
		return str.matches("^\\d+$");
	}

	/**
	 * Initial Author: Padrigano Last Author: Padrigano Description: Checks if
	 * rating given is below 5 and greater than 0. Date Modified: 1-22-2018 8:00
	 * pm
	 * 
	 * @param i
	 * @return boolean
	 */
	public boolean isValidRating(int i) {
		if (!(i < min_rating) && i <= max_rating) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Initial Author: Padrigano Last Author: Padrigano Description: Checks if
	 * contents of string makes up one of genres. Date Modified: 1-22-2019
	 * 8:00pm
	 * 
	 * @param str
	 * @return boolean
	 */
	public boolean isValidGenre(String str) {
		boolean result = false;
		if (str.equals("action") || str.equals("drama") || str.equals("horror")) {
			result = true;
		}
		return result;
	}

	public boolean isEmpty(File f){
		if (f.length() == 0)
         return true;
        else
         return false;
	}
	
	public boolean isValidCinemaNum(int cn){
		
		if (!(cn < min_cinema_num) && cn <= max_cinema_num) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean movieIdValid(String movie_path, long movie_id) {

		File f = new File(movie_path);

		if (isEmpty(f) == true) {
			return false;
		}

		ArrayList<Movie> movie_list = new ArrayList<Movie>();
		Movies movies = new Movies();
		movies = (Movies) files_manager.readMultipleObjects(movie_path, movies);
		
		if (movie_list.stream().filter(movie -> movie.getMovieId() == movie_id).count() > 0) {
			return true;
		}

		return false;
	}
}
