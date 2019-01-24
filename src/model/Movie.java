package model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * 
 * @author april.padrigano
 *Description: Class movie with setters and getters.
 *Date Modified: 1-21-2019 8:00 pm
 */
public class Movie implements Serializable {

	long movie_id;
	String title;
	int rating;
	String genre;
	private int cinema_num;
	private float price;
	private MovieSched movie_sched = new MovieSched();
	
	public Movie(){
		movie_id = 0;
		title = "";
		rating = 0;
		genre = "";
		cinema_num = 0;
		setPrice(0);
		setMovie_sched(movie_sched);
	}
	
	public Movie(long movie_id, String title, int rating, String genre, int cinemaNum, MovieSched movie_sched){
		this.movie_id = movie_id;
		this.title = title;
		this.rating = rating;
		this.genre = genre;
		this.cinema_num = cinemaNum;
		setMovie_sched(movie_sched);
	}
	
	public void setMovieID(long movie_id){
		this.movie_id = movie_id;
	}
	public long getMovieID(){
		return movie_id;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return title;
	}
	public void setRating(int rating){
		this.rating = rating;
	}
	public int getRating(){
		return rating;
	}
	public void setGenre(String genre){
		this.genre = genre;
	}
	public String getGenre(){
		return genre;
	}
	public int getCinemaNum() {
		return cinema_num;
	}
	public void setCinemaNum(int cinemaNum) {
		this.cinema_num = cinemaNum;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public MovieSched getMovie_sched() {
		return movie_sched;
	}

	public void setMovie_sched(MovieSched movie_sched) {
		this.movie_sched = movie_sched;
	}
}
