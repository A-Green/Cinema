package com.epam.learning.springcore.cinema.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class User extends Entity<Integer> {
	
	String email;
	String name;
	List<Ticket> bookedTickets = new ArrayList<>();
	Date birthday;
	
	public User(){
	}
	
	public User(int id){
		this.id = id;
	}
	
	public User(int id, String email, String name, Date date) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.birthday = date;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
