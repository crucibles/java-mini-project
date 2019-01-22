package model;

public class Movie {

	long movieID;
	String title;
	int rating;
	String genre;
	
	public Movie(){
		
	}
	
	public void setMovieID(long movieID){
		this.movieID = movieID;
	}
	public long getMovieID(){
		return movieID;
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
}
