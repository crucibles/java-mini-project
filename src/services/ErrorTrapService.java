package services;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	 * Authors: Ginel, Clarence Description: Checks if a date in a String is
	 * correctly formatted
	 * 
	 * @param str
	 * @return
	 */
	public boolean isValidDate(String str) {
		boolean is_valid = false;

		String regex = "^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
				+ "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
				+ "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
				+ "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		boolean matched = matcher.matches();
		if (matched == true) {
			is_valid = true;
		} else {
			System.out.println("Date " + str + " is not valid.");
			is_valid = false;
		}
		return is_valid;
	}

	public boolean isLegitTime(String time) {
		boolean legit = false;
		String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(time);

		boolean matched = matcher.matches();
		if (matched == true) {
			legit = true;
		} else {
			System.out.println("Tme " + time + " is not valid.");
			legit = false;
		}
		return legit;
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
		if (str.equals("action") || str.equals("drama")
				|| str.equals("horror")) {
			result = true;
		}
		return result;
	}

	public boolean isEmpty(File f) {
		if (f.length() == 0)
			return true;
		else
			return false;
	}

	public boolean isValidCinemaNum(int cn) {

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

		Movies movies = new Movies();
		movies = (Movies) files_manager.readMultipleObjects(movie_path, movies);

		if (movies.getlist().stream()
				.filter(movie -> movie.getMovieId() == movie_id).count() > 0) {
			return true;
		}

		return false;
	}
}
