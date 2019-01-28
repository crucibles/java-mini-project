package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "cinemas")
@XmlSeeAlso({Seat.class})
public class Cinemas {
	List<Cinema> list;

	public Cinemas(){
		
	}

	public List<Cinema> getlist() {
		return list;
	}

	@XmlElement(name = "cinema")
	public void setlist(List<Cinema> objects) {
		this.list = objects;
	}

	public void add(Cinema t) {
		if (this.list == null) {
			this.list = new ArrayList<Cinema>();
		}
		this.list.add(t);
	}
}
