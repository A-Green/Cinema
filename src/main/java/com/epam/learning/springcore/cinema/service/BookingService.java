package com.epam.learning.springcore.cinema.service;

import java.util.Date;
import java.util.List;

import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.exception.BookingServiceException;

public interface BookingService {
	double getTicketPrice(Event event, Date date, Integer seat, User user, String auditorium) throws BookingServiceException;
	void bookTicket(User user, Ticket ticket) throws BookingServiceException;
	List<Ticket> getTicketsForEvent(Event event, Date date) throws BookingServiceException;
}
