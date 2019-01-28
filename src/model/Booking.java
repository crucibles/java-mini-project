package model;


public class Booking  {
	
	private long movie_id;
	private Seats<?> seats;

	public Booking(long movieId, Seats<?> seats) {
		setMovieId(movieId);
		setSeats(seats);
	}

	public long getMovieId() {
		return movie_id;
	}

	public void setMovieId(long movie_id) {
		this.movie_id = movie_id;
	}

	public Seats<?> getSeats() {
		return seats;
	}

	public void setSeats(Seats<?> seats) {
		this.seats = seats;
	}

}
