package others;

import java.io.File;

public class ErrorTrap {

	public ErrorTrap() {

	}

	/**
	 * Initial Author: Padrigano Last Author: Padrigano Description: Checks if a
	 * string is composed of alphanumeric characters. Date Modified: 1-22-2019
	 * 8:00 pm
	 * 
	 * @param str
	 * @return boolean
	 */
	public boolean isLegitString(String str) {
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
	public boolean isLegitNumber(String str) {
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
	public boolean isLegitRating(int i) {
		if (!(i < 0) && i <= 5) {
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
	public boolean isLegitGenre(String str) {
		boolean result = false;
		if (str.equals("action") || str.equals("drama") || str.equals("horror")) {
			result = true;
		}
		return result;
	}

	public boolean isEmpty(File movie){
		if (movie.length() == 0)
         return true;
        else
         return false;
	}
}
