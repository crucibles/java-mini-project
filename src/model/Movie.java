package model;

import java.io.Serializable;

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
	private int cinemaNum;
	
	public Movie(){
		movie_id = 0;
		title = "";
		rating = 0;
		genre = "";
		
	}
	public Movie(long movie_id, String title, int rating, String genre, int cinemaNum){
		this.movie_id = movie_id;
		this.title = title;
		this.rating = rating;
		this.genre = genre;
		this.cinemaNum = cinemaNum;
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
		return cinemaNum;
	}
	public void setCinemaNum(int cinemaNum) {
		this.cinemaNum = cinemaNum;
	}
}
