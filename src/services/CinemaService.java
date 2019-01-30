package services;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Cinema;
import model.Cinemas;
import model.HotSeat;
import model.Movie;
import model.Movies;
import model.RegularSeat;
import model.Seat;

public class CinemaService {
	final static char min_row = 'A';
	final static char max_row = 'O';
	final static char min_hot_row = 'F';
	final static char max_hot_row = 'J';
	final static int min_seat_num = 1;
	final static int max_seat_num = 10;
	final static String cinema_path = "cinema_records.xml";
	private FileService fs = new FileService();
	private ErrorTrapService es = new ErrorTrapService();

	public void initializeSeatList(Cinema c) {

		for (char row = min_row; row <= max_row; row++) {

			for (int seat_num = min_seat_num; seat_num <= max_seat_num; seat_num++) {

				if (isHotRow(row)) {
					c.getSeatList().add(new HotSeat(row, seat_num, false));
				} else if (isRegularRow(row)) {
					c.getSeatList().add(new RegularSeat(row, seat_num, false));
				}

			}

		}

	}
	
	public Cinemas getCinemas(){
		return ((Cinemas) fs.readMultipleObjects(cinema_path,new Cinemas()));
	}
	
	public Cinema getChosenCinema(long movie_id, int cinema_num, String date, Cinemas cinemas){
		
		return cinemas.getlist().stream()
		.filter(c -> c.getMovieId() == movie_id
				&& c.getCinemaId() == cinema_num
				&& c.getCinemaDate().equals(date))
		.collect(Collectors.toList()).get(0);
		
	}
	
	public boolean isCinemaExisting(long movie_id, int cinema_num, String date, Cinemas cinemas){
		return !cinemas.getlist().stream()
				.filter(c -> c.getMovieId() == movie_id
						&& c.getCinemaId() == cinema_num
						&& c.getCinemaDate().equals(date))
				.collect(Collectors.toList()).isEmpty();
	}

	public boolean isHotRow(char row) {

		if (row >= min_hot_row && row <= max_hot_row) {
			return true;
		}

		return false;
	}

	public boolean isRegularRow(char row) {

		if (row >= min_hot_row && row <= max_hot_row) {
			return false;
		}

		return true;
	}

	public boolean isSeatAvailable(Cinema c, char row, int num) {

		if (((Seat) getSeatByRowAndNum(c, row, num)).isReserved()) {
			return false;
		}

		return true;
	}

	public Seat getSeatByRowAndNum(Cinema c, char row, int num) {

		int realSeatNum = ((row - 65) * 10) + num - 49;

		return (Seat) c.getSeatList().getlist().get(realSeatNum);
	}

	public List<Cinema> makeCinemasForMovie(Movie movie) {

		List<Cinema> cinemas = new ArrayList<>();
		int end = movie.getMovieSched().getNumberOfDays();
		LocalDate date = LocalDate.parse(movie.getMovieSched().getStartDate());

		for (int start = 0; start <= end; start++) {
			cinemas.add(new Cinema(movie.getCinemaNum(), movie.getMovieId(),
					date.toString()));
			date = date.plusDays(1);
		}

		return cinemas;
	}

	public int getCinemaNum(long movie_id) {

		String movie_path = "movie_records.xml";
		Movies movies = new Movies();

		movies = fs.readMultipleObjects(movie_path, movies);

		return movies.getlist().stream().filter(m -> m.getMovieId() == movie_id)
				.collect(Collectors.toList()).get(0).getCinemaNum();
	}

	public boolean isCinemaAvailable(int c_num, LocalDate temp_sdate, LocalDate temp_edate) {
		String cinema_path = "cinema_records.xml";
		Cinemas cinemas = new Cinemas();
		LocalDate holder = temp_sdate;
		
		if(es.equals(new File(cinema_path))){
			return true;
		}
		
		cinemas = fs.readMultipleObjects(cinema_path, cinemas);
		
		for(Cinema c: cinemas.getlist()){
			
			temp_sdate = holder;
			while(!temp_sdate.equals(temp_edate.plusDays(1))){
				if(c.getCinemaDate().equals(temp_sdate.toString()) && c.getCinemaId() == c_num){
					return false;
				}
				temp_sdate =  temp_sdate.plusDays(1);
			}
			
		}
		
		return true;
		
	}

} // end bracket
