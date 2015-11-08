package com.epam.learning.springcore.cinema.model;

public abstract class Event extends Entity<Integer> {
	
	protected String name;
	protected double baseTicketPrice;
	protected Rating rating = Rating.MID;

	public Event() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBaseTicketPrice() {
		return baseTicketPrice;
	}

	public void setBaseTicketPrice(double baseTicketPrice) {
		this.baseTicketPrice = baseTicketPrice;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}
}
