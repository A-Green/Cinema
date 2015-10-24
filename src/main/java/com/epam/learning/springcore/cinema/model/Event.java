package com.epam.learning.springcore.cinema.model;

public class Event extends Entity<Integer> {
	
	private String name;
	private double baseTicketPrice;
	private Rating rating;

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
