package com.epam.learning.springcore.cinema.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.learning.springcore.cinema.dao.TicketDao;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Ticket;

@Repository
public class TicketDaoImpl implements TicketDao {

	private List<Ticket> ticketTrack = new ArrayList<>();

	@Override
	public void track(Ticket ticket) {
		ticketTrack.add(ticket);
	}

	@Override
	public List<Ticket> getTicketsForEvent(Event event, Date date) {
		List<Ticket> result = new ArrayList<>();
		for (Ticket t: ticketTrack) {
			if (t.getEventDate().compareTo(date) == 0 
					&& t.getEvent().getId() == event.getId()) {
				result.add(t);
			}
		}
		return result;
	}
	
}
