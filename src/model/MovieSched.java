package model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class MovieSched implements Serializable {

	private Date start_date;
	private Date end_date;
	private Time start_time;
	private Time end_time;
	private int number_of_days;
	private Time duration;

	
	public MovieSched() {
		setStart_date(new Date());
		setEnd_date(new Date());
		setStart_time(new Time(0));
		setEnd_time(new Time(0));
		setNumber_of_days((int) (end_date.getTime() - start_date.getTime()));
		setDuration(new Time(end_time.getTime() - start_time.getTime()));
	}
	
	
	public MovieSched(Date start_date, Date end_Date, Time start_time, Time end_time) {
		setStart_date(start_date);
		setEnd_date(end_date);
		setStart_time(start_time);
		setEnd_time(end_time);
		setNumber_of_days((int) (end_date.getTime() - start_date.getTime()));
		setDuration(new Time(end_time.getTime() - start_time.getTime()));
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Time getStart_time() {
		return start_time;
	}

	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}

	public Time getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}

	public int getNumber_of_days() {
		return number_of_days;
	}

	public void setNumber_of_days(int number_of_days) {
		this.number_of_days = number_of_days;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

}
