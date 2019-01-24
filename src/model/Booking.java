package model;

import java.util.ArrayList;
import java.util.List;

public class Booking {
	private long movie_id;
	private List<Seat> seats = new ArrayList<>();

	public Booking(long movieId, List<Seat> seats) {
		setMovie_id(movieId);
		setSeats(seats);
	}

	public long getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(long movie_id) {
		this.movie_id = movie_id;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

}
