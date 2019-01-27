package model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customers")
public class Customers {
	List<Customer> list;


	public List<Customer> getlist() {
		return list;
	}

	@XmlElement(name = "customer")
	public void setlist(List<Customer> objects) {
		this.list = objects;
	}

	public void add(Customer t) {
		if (this.list == null) {
			this.list = new ArrayList<Customer>();
		}
		this.list.add(t);
	}
}
