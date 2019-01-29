package services;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

import model.Movie;
import model.Movies;

public class TimeService {
	/**
	 * Author: Padrigano Description: Changes a string with format MM DD YYYY to
	 * Class Date Date Modified: 1/25/2019
	 * 
	 * @param date
	 * @return Date
	 * @throws ParseException
	 */
	public Date changeStringToDate(String date) throws ParseException {
		// String temp;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date_real = new Date();

		// temp = date.replaceAll("\t", "");
		// date = temp;
		// format = new SimpleDateFormat("yyyy-MM-dd");
		date_real = format.parse(date);

		return date_real;
	}
	/**
	 * Author: Padrigano Description: Change int hour and minute to Time class
	 * format
	 * 
	 * @param hour
	 * @param minute
	 * @return Time
	 */
	public Time changeIntToTime(int hour, int minute) {

		DateTimeFormatter timeFormatter = DateTimeFormatter
				.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.US);
		LocalTime time = LocalTime.of(hour, minute, 0);
		Time time_me = Time.valueOf(time);
		return time_me;
	}

	public String convert24HourTo12Hour(String date_this) {
		DateFormat df = new SimpleDateFormat("HH:mm");
		DateFormat df2 = new SimpleDateFormat("hh:mm a");
		Date date = null;
		String time_output = null;

		try {
			date = df.parse(date_this);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		time_output = df2.format(date);

		System.out.println("in 12 hour format: " + time_output);
		return time_output;
	}

	public static String getDateToday() {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(
				"E, MMMMM dd yyyy hh:mm:ss");
		String strDate = sdf.format(today); // getting the date today

		return strDate;
	}

	public static boolean checkDate(Date movieShowing) {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy");

		if (sdf.format(movieShowing).equals(sdf.format(today))) {
			return true;
		}

		return false;
	}

	public boolean isDateAvailable(String s_date, String e_date) {

		Movies movies = new Movies();
		LocalDate temp_date = LocalDate.parse(s_date);

		if (movies.getlist() == null) {
			return true;
		} else {

			for (Movie m : movies.getlist()) {
				
				LocalDate current_date = LocalDate.parse(m.getMovieSched().getStartDate());
				LocalDate end_date = LocalDate.parse(m.getMovieSched().getEndDate());
				
				while(current_date != end_date){
					
					if (current_date.equals(temp_date)) {
						return false;
					}
					current_date.plusDays(1);
					
				}
				
			}

		}

		return true;
	}
}
