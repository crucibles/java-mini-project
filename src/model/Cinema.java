package model;


import services.CinemaService;

public class Cinema {
	private int cinema_id;
	private Seats seat_list = new Seats();
	private long movie_id;
	private Reservations reservation_list = new Reservations();
	private CinemaService cs;
	private String cinema_date;

	public Cinema(){
		cs = new CinemaService();
	}
	
	public Cinema(int cinema_id, long movie_id,String cinema_date) {
		setCinemaId(cinema_id);
		setMovieId(movie_id);
		setCinemaDate(cinema_date);
		cs = new CinemaService();
		cs.initializeSeatList(this);
	}

	public long getMovieId() {
		return movie_id;
	}


	public void setMovieId(long movie_id) {
		this.movie_id = movie_id;
	}


	public int getCinemaId() {
		return cinema_id;
	}


	public void setCinemaId(int cinema_id) {
		this.cinema_id = cinema_id;
	}


	public Seats getSeatList() {
		return seat_list;
	}


	public void setSeatList(Seats seats) {
		seat_list = seats;
	}


	public Reservations getReservations() {
		return reservation_list;
	}


	public void setReservations(Reservations reservations) {
		reservation_list = reservations;
	}

	public CinemaService getCs() {
		return cs;
	}

	public void setCs(CinemaService cs) {
		this.cs = cs;
	}

	public String getCinemaDate() {
		return cinema_date;
	}

	public void setCinemaDate(String cinema_date) {
		this.cinema_date = cinema_date;
	}




}
