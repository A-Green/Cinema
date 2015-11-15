package com.epam.learning.springcore.cinema.service;

import java.util.Date;
import java.util.List;

import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Rating;
import com.epam.learning.springcore.cinema.service.exception.EventServiceException;

public interface EventService extends BaseService<Integer, Event> {

	Event create(String name, double basePrice, Rating rating) throws EventServiceException;
	void assignAuditorium(Event event, Auditorium auditorium, Date date)throws EventServiceException;
	List<Event> getByName(String name) throws EventServiceException;
	List<Event> getAll() throws EventServiceException;
	List<Event> getEventsForDate(Date date) throws EventServiceException;
}
