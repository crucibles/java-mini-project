package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "movies")
public class Movies {
	List<Movie> list;


	public List<Movie> getlist() {
		return list;
	}

	@XmlElement(name = "movie")
	public void setlist(List<Movie> objects) {
		this.list = objects;
	}

	public void add(Movie t) {
		if (this.list == null) {
			this.list = new ArrayList<Movie>();
		}
		this.list.add(t);
	}
}
