package com.epam.learning.springcore.cinema.service;

import java.util.Date;
import java.util.List;

import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.service.exception.EventServiceException;

public interface EventService extends BaseService<Integer, Event> {
	
	List<Event> getForDateRange(Date form, Date to) throws EventServiceException;
	List<Event> getNextEvents(Date to) throws EventServiceException;
	void assignAuditorium(Event event, Auditorium auditorium, Date date)throws EventServiceException;
	List<Event> getByName(String name) throws EventServiceException;
	List<Event> getAll() throws EventServiceException;
}
