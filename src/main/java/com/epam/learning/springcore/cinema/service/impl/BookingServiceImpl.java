package com.epam.learning.springcore.cinema.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epam.learning.springcore.cinema.dao.TicketDao;
import com.epam.learning.springcore.cinema.dao.UserDao;
import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Rating;
import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.AuditoriumService;
import com.epam.learning.springcore.cinema.service.BookingService;
import com.epam.learning.springcore.cinema.service.DiscountService;
import com.epam.learning.springcore.cinema.service.exception.AuditoriumServiceException;
import com.epam.learning.springcore.cinema.service.exception.BookingServiceException;
import com.epam.learning.springcore.cinema.service.exception.DiscountServiceException;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	@Qualifier("preparedDiscountService")
	private DiscountService discountService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private AuditoriumService auditoriumService;
	
	private static final double VIP_SEATS_MULTIPLIER = 2;	
	private static final double RATING_MULTIPLIER = 1.2;

	@Override
	public double getTicketPrice(Event event, Date date, Integer seat, User user, String auditorium) throws BookingServiceException {	
		try {
			double price = event.getBaseTicketPrice();
			Auditorium reqestedAud = auditoriumService.getByName(auditorium);
			if (reqestedAud == null) {
				throw new BookingServiceException("Can not find specified auditorium " + auditorium);
			}
			
			if (reqestedAud.getVipSeats().contains(seat)) {
				price = price * VIP_SEATS_MULTIPLIER;
			}
			
			if (event.getRating() == Rating.HIGH) {
				price = price * RATING_MULTIPLIER;
			}	
			double discount = discountService.getDiscount(user, event, date);
			return price - discount;
		} catch (DiscountServiceException | AuditoriumServiceException e) {
			throw new BookingServiceException(e);
		}
	}

	@Override
	public void bookTicket(User user, Ticket ticket) {
		//ticketDao.bookTicket(user, ticket);
		//ticketDao.track(ticket);
	}

	@Override
	public List<Ticket> getTicketsForEvent(Event event, Date date) {
		return ticketDao.getTicketsForEvent(event, date);
	}

}
