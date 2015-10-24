package com.epam.learning.springcore.cinema.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.epam.learning.springcore.cinema.dao.EventDao;
import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.model.Event;

public class EventDaoImpl extends MapBaseDaoImpl<Integer, Event> implements EventDao{
	
	private Map<Integer, Event> events;
	
	public EventDaoImpl() {
		events = new HashMap<>();
	}

	@Override
	public void assignAuditorium(Event event, Auditorium auditorium, Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Integer, Event> getAll() {
		return events;
	}
}
