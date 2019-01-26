package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Reservation implements Serializable {
	private long reservation_id;
	private long customer_id;
	private List<Booking> bookings;
	
	public Reservation(){
		bookings = new ArrayList<Booking>();
	}
	
	public Reservation(long customerId){
		setCustomer_id(customerId);
		setBookings(bookings);
	}
	
	public long getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(long reservation_id) {
		this.reservation_id = reservation_id;
	}
	public long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> reservations) {
		this.bookings = reservations;
	}
	
	public void displaySeatPlan(long movieId){
		
	}

	
	
	
}
