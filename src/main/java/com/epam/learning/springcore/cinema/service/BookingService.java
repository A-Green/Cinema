package com.epam.learning.springcore.cinema.service;

import java.util.Date;

import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.exception.BookingServiceException;

public interface BookingService {
	double getTicketPrice(Event event, Date date, Integer seat, User user) throws BookingServiceException;
}
