package com.epam.learning.springcore.cinema.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.BookingService;
import com.epam.learning.springcore.cinema.service.DiscountService;
import com.epam.learning.springcore.cinema.service.exception.BookingServiceException;

public class BookingServiceImpl implements BookingService {

	@Autowired
	private DiscountService discountService;
	
	@Override
	public double getTicketPrice(Event event, Date date, Integer seat, User user) throws BookingServiceException {
		double price = event.getBaseTicketPrice();
		double discount = discountService.getDiscount(user, event, date);
		return price - discount;
	}

}
