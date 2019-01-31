package model;

/**
 * 
 * @author april.padrigano
 *Description: Class movie with setters and getters.
 *Date Modified: 1-21-2019 8:00 pm
 */
public class Movie {
	private long movie_id;
	private String title;
	private int rating;
	private String genre;
	private MovieSched movie_sched;
	private int cinema_num;
	private float price;
	
	public Movie(){

	}
	
	public Movie(long movie_id, String title, int rating, String genre, int cinemaNum, MovieSched movie_sched){
		setMovieId(movie_id);
		setTitle(title);
		setRating(rating);
		setGenre(genre);
		setCinemaNum(cinemaNum);
		setMovieSched(movie_sched);
		setPrice(0);
	}

	
	public long getMovieId() {
		return movie_id;
	}

	public void setMovieId(long movie_id) {
		this.movie_id = movie_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String t) {
		title = t;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int r) {
		rating = r;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String g) {
		genre = g;
	}

	public int getCinemaNum() {
		return cinema_num;
	}

	public void setCinemaNum(int cn) {
		cinema_num = cn;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public MovieSched getMovieSched() {
		return movie_sched;
	}

	public void setMovieSched(MovieSched movie_sched) {
		this.movie_sched = movie_sched;
	}
}
