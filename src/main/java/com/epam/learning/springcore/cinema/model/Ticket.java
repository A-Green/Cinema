package com.epam.learning.springcore.cinema.model;

public class Ticket extends Entity {

	Integer seatNumber;
	
	public Ticket() {	
	}
	
	public Ticket(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Integer getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}

}
