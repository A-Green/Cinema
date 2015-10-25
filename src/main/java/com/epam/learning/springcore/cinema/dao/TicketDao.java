package com.epam.learning.springcore.cinema.dao;

import java.util.Date;
import java.util.List;

import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Ticket;

public interface TicketDao {
	void track(Ticket ticket);
	List<Ticket> getTicketsForEvent(Event event, Date date);
}
