package com.epam.learning.springcore.cinema.service.impl;

import java.util.Date;
import java.util.List;

import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.service.EventService;
import com.epam.learning.springcore.cinema.service.exception.ServiceException;

public class EventServiceImpl implements EventService {

	public void save(Event entity) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	public void remove(Integer key) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	public List<Event> getForDateRange(Date form, Date to) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Event> getNextEvents(Date to) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public void assignAuditorium(Event event, Auditorium auditorium, Date date) throws ServiceException {
		// TODO Auto-generated method stub
		
	}
}
