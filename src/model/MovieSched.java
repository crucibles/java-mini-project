package model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "schedule")
public class MovieSched {
	private String start_date;
	private String end_date;
	private String start_time;
	private String end_time;
	private int number_of_days;
	private String duration;

	public MovieSched() {

	}
	
	public MovieSched(LocalDate sd, int nd, LocalTime st, LocalTime et) {
		setStartDate(sd.toString());
		setEndDate(sd.plusDays(nd).toString());
		setStartTime(st.toString());
		setEndTime(et.toString());
		setNumberOfDays(nd);
		setDuration(Duration.between(st, et).toString());
	}

	public String getStartDate() {
		return start_date.toString();
	}
	
	public void setStartDate(String string) {
		start_date = string;
	}

	public String getEndDate() {
		return end_date;
	}

	public void setEndDate(String ed) {
		end_date = ed;
	}

	public String getStartTime() {
		return start_time;
	}

	public void setStartTime(String st) {
		start_time = st;
	}

	public String getEndTime() {
		return end_time;
	}

	public void setEndTime(String et) {
		end_time = et;
	}

	public int getNumberOfDays() {
		return number_of_days;
	}

	public void setNumberOfDays(int nod) {
		number_of_days = nod;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String d) {
		duration = d;
	}

}
