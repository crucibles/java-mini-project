package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "reservations")
public class Reservations {
	List<Reservation> list;

	public Reservations(){
		
	}

	public List<Reservation> getlist() {
		return list;
	}

	@XmlElement(name = "reservation")
	public void setlist(List<Reservation> objects) {
		this.list = objects;
	}

	public void add(Reservation t) {
		if (this.list == null) {
			this.list = new ArrayList<Reservation>();
		}
		this.list.add(t);
	}
}
