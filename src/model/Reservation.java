package model;

public class Reservation {
	private long reservation_id;
	private long customer_id;
	private Bookings bookings;

	public Reservation() {
		bookings = new Bookings();
	}

	public Reservation(long customerId) {
		setCustomer_id(customerId);
		bookings = new Bookings();
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

	public Bookings getBookings() {
		return bookings;
	}

	public void setBookings(Bookings reservations) {
		this.bookings = reservations;
	}

	public void displaySeatPlan(long movieId) {

	}

}
