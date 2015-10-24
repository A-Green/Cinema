package com.epam.learning.springcore.cinema.model;

import java.util.List;

public class Auditorium extends Entity<String> {

	int seatsNumber;
	List<Integer> vipSeats;
	
	public Auditorium() {
		
	}
	public int getSeatsNumber() {
		return seatsNumber;
	}
	public void setSeatsNumber(int seatsNumber) {
		this.seatsNumber = seatsNumber;
	}
	public List<Integer> getVipSeats() {
		return vipSeats;
	}
	public void setVipSeats(List<Integer> vipSeats) {
		this.vipSeats = vipSeats;
	}
}
