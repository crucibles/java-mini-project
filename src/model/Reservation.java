package model;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
	private long reservation_id;
	private long customer_id;
	private List<Booking> reservations = new ArrayList<Booking>();
	
	public Reservation(){
		System.out.println("hello new reservation");
	}
	
	public Reservation(long customerId, List<Booking> bookings){
		setCustomer_id(customerId);
		// setReservation_id(); call function getLatest reservationId
		setReservations(bookings);
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

	public List<Booking> getReservations() {
		return reservations;
	}

	public void setReservations(List<Booking> reservations) {
		this.reservations = reservations;
	}
	
	public void displaySeatPlan(long movieId){
		// looks for all movieID in reservation file, 
	}

	
	
	
}
