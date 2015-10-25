package com.epam.learning.springcore.cinema.model;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Auditorium extends Entity<String> {

	private int seatsNumber;
	private List<Integer> vipSeats;
	private String name;

	public Auditorium() {
	}
	
	public Auditorium(String id, String name, int seatsNumber, List<Integer> vips) {
		this.id = id;
		this.name = name;
		this.seatsNumber = seatsNumber;
		this.vipSeats = vips;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
