package com.epam.learning.springcore.cinema.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.learning.springcore.cinema.dao.EventDao;
import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Movie;
import com.epam.learning.springcore.cinema.model.Rating;
import com.epam.learning.springcore.cinema.service.EventService;
import com.epam.learning.springcore.cinema.service.exception.EventServiceException;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventDao eventDao;

	@Override
	public void save(Event event) throws EventServiceException {
		eventDao.save(event);
	}
	
	@Override
	public void remove(Integer id) throws EventServiceException {
		eventDao.remove(id);
	}
	
	@Override
	public void assignAuditorium(Event event, Auditorium auditorium, Date date) throws EventServiceException {
		eventDao.assignAuditorium(event, auditorium, date);
	}

	@Override
	public List<Event> getByName(String name) throws EventServiceException {
		return eventDao.getByName(name);
	}

	@Override
	public List<Event> getAll() throws EventServiceException {
		return eventDao.getAllEvents();
	}

	@Override
	public Event create(String name, double basePrice, Rating rating) throws EventServiceException {
		Event event = new Movie(name, basePrice, rating);
		return eventDao.save(event);
	}

	@Override
	public List<Event> getEventsForDate(Date date) throws EventServiceException {
		return eventDao.getEventsForDate(date);
	}
}
