package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Booking implements Serializable {
	
	private long movie_id;
	private List<Seat> seats = new ArrayList<>();

	public Booking(long movieId, List<Seat> seats) {
		setMovieId(movieId);
		setSeats(seats);
	}

	public long getMovieId() {
		return movie_id;
	}

	public void setMovieId(long movie_id) {
		this.movie_id = movie_id;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

}
