package com.epam.learning.springcore.cinema.dao;

import java.util.Date;
import java.util.List;

import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.model.Event;

public interface EventDao extends BaseDao<Integer, Event> {
	void assignAuditorium(Event event, Auditorium auditorium, Date date);
	List<Event> getByName(String name);
	List<Event> getAllEvents();
	List<Event> getEventsForDate(Date date);
}
