package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "seats")
public class Seats {
	List<Seat> list;

	public Seats() {

	}

	public List<Seat> getlist() {
		return list;
	}

	@XmlElement(name = "seat")
	public void setlist(List<Seat> objects) {
		this.list = objects;
	}

	public void add(Seat object) {
		if (this.list == null) {
			this.list = new ArrayList<Seat>();
		}
		this.list.add(object);
	}
}
