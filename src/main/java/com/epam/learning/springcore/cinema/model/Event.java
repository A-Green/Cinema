package com.epam.learning.springcore.cinema.model;

public abstract class Event extends Entity<Integer> {
	
	private String name;
	private double baseTicketPrice;

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
}
