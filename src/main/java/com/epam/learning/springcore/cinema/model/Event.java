package com.epam.learning.springcore.cinema.model;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Event extends Entity {
	
	private Integer id;
	private String name;
	private double ticketPrice;
	private Date holdingDate;
	private Rating rating;

	private Event() {

	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getHoldingDate() {
		return holdingDate;
	}

	public void setHoldingDate(Date holdingDate) {
		this.holdingDate = holdingDate;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(name)
				.append(ticketPrice)
				.append(holdingDate)
				.append(rating).toHashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}
}
