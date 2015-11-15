package com.epam.learning.springcore.cinema.dao;

import java.util.Date;
import java.util.List;

import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;

public interface TicketDao {
	List<Ticket> getTicketsForEvent(Event event, Date date);
	List<Ticket> getBookedTickets(Integer userId);
	void bookTicket(User user, Ticket ticket);
}
