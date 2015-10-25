package com.epam.learning.springcore.cinema.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Movie extends Event {

	private Rating rating = Rating.MID;

	public Movie() {
	}

	public Movie(String name, double basePrice, Rating rating) {
		this.rating = rating;
		this.name = name;
		this.baseTicketPrice = basePrice;
	}
	
	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}
}
