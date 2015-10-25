package com.epam.learning.springcore.cinema.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epam.learning.springcore.cinema.dao.TicketDao;
import com.epam.learning.springcore.cinema.dao.UserDao;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.BookingService;
import com.epam.learning.springcore.cinema.service.DiscountService;
import com.epam.learning.springcore.cinema.service.exception.BookingServiceException;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	@Qualifier("preparedDiscountService")
	private DiscountService discountService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TicketDao ticketDao;
	
	@Override
	public double getTicketPrice(Event event, Date date, Integer seat, User user) throws BookingServiceException {
		double price = event.getBaseTicketPrice();
		double discount = discountService.getDiscount(user, event, date);
		return price - discount;
	}

	@Override
	public void bookTicket(User user, Ticket ticket) {
		userDao.bookTicket(user, ticket);
		ticketDao.track(ticket);
	}

	@Override
	public List<Ticket> getTicketsForEvent(Event event, Date date) {
		return ticketDao.getTicketsForEvent(event, date);
	}

}
