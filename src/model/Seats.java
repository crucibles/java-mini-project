package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "seats")
public class Seats<T> {
	List<T> list;

	public Seats(){
		
	}
	
	public List<T> getlist() {
		return list;
	}

	@XmlElement(name = "seat")
	public void setlist(List<T> objects) {
		this.list = objects;
	}

	public void add(Object object) {
		if (this.list == null) {
			this.list = new ArrayList<T>();
		}
		this.list.add((T) object);
	}
}
