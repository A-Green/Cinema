package com.epam.learning.springcore.cinema.model;

public class Event extends Entity<Integer> {
	
	private String name;
	private double ticketPrice;
	private Rating rating;

	public Event() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}
}
