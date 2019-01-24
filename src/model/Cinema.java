package model;

import java.util.ArrayList;

public class Cinema {
	private int cinema_id;
	private ArrayList<Seat> seat_list = new ArrayList<>();
	private long movie_id;
	private ArrayList<Reservation> reservations = new ArrayList<>();

	public Cinema(int cinema_id) {
		setCinema_id(cinema_id);
		setMovie_id(cinema_id);
		setSeat_list(seat_list);
		setReservations(reservations);
		initializeSeatList();
	}
	

	public void initializeSeatList() {
		for (char a = 'A'; a < 'O'; a++) {
			for (int b = 1; b <= 10; b++) {
				if(a >= 'F' && a <= 'J' ){//hot
					getSeat_list().add(new HotSeat(a,b,false));
				}else{
					getSeat_list().add(new RegularSeat(a,b,false));
				}
			}
		}
	}
	

	public boolean isAvailable(Seat seat){
		
		for (char a = 'A'; a < 'O'; a++) {
			for (int b = 1; b <= 10; b++) {
				if(seat.getSeatRow() == a && seat.getSeatNum() == b){
					if(seat.isReserved()){
						return false;
					} else {						
						break;
					}
				}
			}
		}
		
		return true;
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


}
