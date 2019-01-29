package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bookings")
public class Bookings {
	List<Booking> list;

	public Bookings(){
		
	}
	
	public List<Booking> getlist() {
		return list;
	}

	@XmlElement(name = "booking")
	public void setlist(List<Booking> objects) {
		this.list = objects;
	}

	public void add(Booking t) {
		if (this.list == null) {
			this.list = new ArrayList<Booking>();
		}
		this.list.add(t);
	}
}
