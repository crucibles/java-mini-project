package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.Booking;
import model.Customer;
import model.HotSeat;
import model.Movie;
import model.RegularSeat;
import model.Reservation;
import model.Seat;
import others.FilesManager;
import view.Menus;


public class MainController {
	// declarations
	private static Menus menu;
	private static Movie tempMovie;
	static List<Customer> x = new ArrayList<Customer>();

	public static void main(String[] args) {
		
		System.out.println("hello I am making a reservation now");
		Movie m1 = new Movie(1, "Harry Potter", 2);
		Movie m2 = new Movie(2, "Harry Potter2", 3);
		Movie m3 = new Movie(3, "Harry Potter3", 4);
		Customer ced = new Customer("Cedric", "Alvaro", 1);
		
		List<Seat> cedSeats = new ArrayList<Seat>(); 
		List<Booking> cedBookings = new ArrayList<Booking>();
	//	cedSeats.add(new RegularSeat('B'));
	//	cedSeats.add(new RegularSeat('A'));
	//	cedSeats.add(new HotSeat('F'));
	
		cedSeats.add(new RegularSeat('A',1, true));
		cedSeats.add(new RegularSeat('A',2, true));
		cedSeats.add(new HotSeat('F',6,true));
		
		Booking cedb1 = new Booking(1, cedSeats);
		Booking cedb2 = new Booking(2, cedSeats);
		
		cedBookings.add(cedb1);
		cedBookings.add(cedb2);
		
		
		Reservation cedReserve = new Reservation(ced.getCustomerID(), cedBookings);
		
		System.out.println( "customerID :" + cedReserve.getCustomer_id() +  " reserveID : " + cedReserve.getReservation_id());
		
		int c = 0;
		for(; c<cedReserve.getReservations().size(); c++){
			List<Booking> x = cedReserve.getReservations();
			System.out.println(x.get(c).getMovie_id());
			System.out.println(x.get(c).getSeats().get(c).seatRow);
		}
		
	}


}
