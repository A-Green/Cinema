package com.epam.learning.springcore.cinema.model;

public class Ticket extends Entity {

	Integer seetNumber;
	
	public Ticket() {	
	}
	
	public Ticket(int seetNumber) {
		this.seetNumber = seetNumber;
	}

	public Integer getSeetNumber() {
		return seetNumber;
	}

	public void setSeetNumber(Integer seetNumber) {
		this.seetNumber = seetNumber;
	}
}
