package model;

import java.util.ArrayList;
import java.util.Date;

import services.CinemaService;

public class Cinema {
	private int cinema_id;
	private ArrayList<Seat> seat_list;
	private long movie_id;
	private ArrayList<Reservation> reservations;
	private CinemaService cs;
	private Date cinema_date;

	public Cinema(){
		reservations = new ArrayList<>();
		seat_list = new ArrayList<>();
		cs = new CinemaService();
	}
	
	public Cinema(int cinema_id, long movie_id) {
		reservations = new ArrayList<>();
		seat_list = new ArrayList<>();
		setCinema_id(cinema_id);
		setMovie_id(movie_id);
		cs = new CinemaService();
		cs.initializeSeatList(this);
	}

	public long getMovie_id() {
		return movie_id;
	}


	public void setMovie_id(long movie_id) {
		this.movie_id = movie_id;
	}


	public int getCinema_id() {
		return cinema_id;
	}


	public void setCinema_id(int cinema_id) {
		this.cinema_id = cinema_id;
	}


	public ArrayList<Seat> getSeat_list() {
		return seat_list;
	}


	public void setSeat_list(ArrayList<Seat> seat_list) {
		this.seat_list = seat_list;
	}


	public ArrayList<Reservation> getReservations() {
		return reservations;
	}


	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}

	public CinemaService getCs() {
		return cs;
	}

	public void setCs(CinemaService cs) {
		this.cs = cs;
	}

	public Date getCinema_date() {
		return cinema_date;
	}

	public void setCinema_date(Date cinema_date) {
		this.cinema_date = cinema_date;
	}


}
