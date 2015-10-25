package com.epam.learning.springcore.cinema.model;

public class Movie extends Event {

	private Rating rating;

	public Movie() {
	}
	
	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}
	
}
