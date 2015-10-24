package com.epam.learning.springcore.cinema.model;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class User extends Entity {
	
	Integer id;
	String email;
	String name;
	List<Ticket> bookedTickets;
	
	public User(){
	}
	
	public User(int id){
		this.id = id;
	}
	
	public User(int id, String email, String name) {
		this.id = id;
		this.email = email;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Ticket> getBookedTickets() {
		return bookedTickets;
	}

	public void setBookedTickets(List<Ticket> bookedTickets) {
		this.bookedTickets = bookedTickets;
	}
}
