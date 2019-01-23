package model;

public class Movie {

	private long movieID;
	private String title;
	private int rating;
	private String genre;
	private int cinemaNum;
	
	public Movie(long movieID, String title, int cinemaNum){
		setCinemaNum(cinemaNum);
		setMovieID(movieID);
		setTitle(title);
	}
	
	public Movie(int movieID2, String title2, int cinemaNum2) {
		setCinemaNum(cinemaNum2);
		setMovieID(movieID2);
		setTitle(title2);
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

	public int getCinemaNum() {
		return cinemaNum;
	}

	public void setCinemaNum(int cinemaNum) {
		this.cinemaNum = cinemaNum;
	}
}
