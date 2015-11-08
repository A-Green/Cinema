package com.epam.learning.springcore.cinema.dao;

import java.util.Date;
import java.util.List;

import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;

public interface UserDao extends BaseDao<Integer, User> {
	
	User registerUser(User user);
	User getByEmail(String email);
	User getByName(String name);
	List<Ticket> getBookedTickets(Integer userId);
	void bookTicket(User user, Ticket ticket);
	List<Ticket> getTicketsForEvent(Event event, Date date);
}
