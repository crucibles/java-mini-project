package model;

import java.util.ArrayList;

public class Cinema {
	int cinema_id;
	ArrayList<Seat> seat_list;
	Movie assign_movie;
	Reservation reservation;

	public Cinema(int cinema_id) {
		this.cinema_id = cinema_id;
		seat_list = new ArrayList<Seat>();
		initializeSeatList();
	}
	public void setMovie(Movie movie){
		assign_movie = movie;
	}
	public Movie getMovie(){
		return assign_movie;
	}
	public void initializeSeatList() {
		for (char a = 'A'; a < 'O'; a++) {
			for (int b = 1; b <= 10; b++) {
				if(a >= 'F' && a <= 'J' ){//hot
					seat_list.add(new HotSeat(a,b,false));
				}else{
					seat_list.add(new RegularSeat(a,b,false));
				}
			}
		}
	}
	
	public ArrayList<Seat> getSeatList(){
		return seat_list;
	}
	public void setSeatList(ArrayList<Seat> seat){
		seat_list = seat;
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
	
}
