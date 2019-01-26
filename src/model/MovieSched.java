package model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "schedule")
public class MovieSched {

	private LocalDate start_date;
	private LocalDate end_date;
	private LocalTime start_time;
	private LocalTime end_time;
	private int number_of_days;
	private Duration duration;

	public MovieSched() {

	}

	public MovieSched(LocalDate sd, LocalDate ed, LocalTime st, LocalTime et) {
		setStart_date(sd);
		setEnd_date(ed);
		setStart_time(st);
		setEnd_time(et);
		setNumber_of_days(Period.between(sd, ed).getDays());
		setDuration(Duration.between(st, et));
	}
	
	public MovieSched(LocalDate sd, int nd, LocalTime st, LocalTime et) {
		setStart_date(sd);
		setEnd_date(sd.plusDays(nd));
		setStart_time(st);
		setEnd_time(et);
		setNumber_of_days(Period.between(sd, getEnd_date()).getDays());
		setDuration(Duration.between(st, et));
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public LocalTime getStart_time() {
		return start_time;
	}

	public void setStart_time(LocalTime start_time) {
		this.start_time = start_time;
	}

	public LocalTime getEnd_time() {
		return end_time;
	}

	public void setEnd_time(LocalTime end_time) {
		this.end_time = end_time;
	}

	public int getNumber_of_days() {
		return number_of_days;
	}

	public void setNumber_of_days(int number_of_days) {
		this.number_of_days = number_of_days;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

}
