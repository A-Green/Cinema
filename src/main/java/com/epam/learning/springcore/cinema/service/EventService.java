package com.epam.learning.springcore.cinema.service;

import java.util.Date;
import java.util.List;

import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.service.exception.ServiceException;

public interface EventService extends BaseService<Event, Integer>{
	
	List<Event> getForDateRange(Date form, Date to) throws ServiceException;
	List<Event> getNextEvents(Date to) throws ServiceException;
	void assignAuditorium(Event event, Auditorium auditorium, Date date)throws ServiceException;
}
