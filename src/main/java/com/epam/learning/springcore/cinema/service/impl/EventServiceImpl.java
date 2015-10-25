package com.epam.learning.springcore.cinema.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.learning.springcore.cinema.dao.EventDao;
import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.service.EventService;
import com.epam.learning.springcore.cinema.service.exception.EventServiceException;

@Service
public class EventServiceImpl implements EventService {

	//Affiche. <eventid, <auditoriumid, dates>>
	private Map<Integer, Map<String, List<Date>>> affiche;
	
	@Autowired
	private EventDao eventDao;

	public void save(Event event) throws EventServiceException {
		eventDao.save(event);
	}

	public void remove(Integer id) throws EventServiceException {
		eventDao.remove(id);
	}

	public List<Event> getForDateRange(Date form, Date to) throws EventServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Event> getNextEvents(Date to) throws EventServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public void assignAuditorium(Event event, Auditorium auditorium, Date date) throws EventServiceException {
		Map<String, List<Date>> row = affiche.get(event.getId());
		if (row == null) {
			row = new HashMap<String, List<Date>>();
		}
		
		List<Date> dates = row.get(auditorium.getId());
		
		if (dates == null){
			dates = new ArrayList<Date>();
		}
		
		dates.add(date);
		row.put(auditorium.getId(), dates);
		affiche.put(event.getId(), row);
	}

	@Override
	public List<Event> getByName(String name) throws EventServiceException {
		return eventDao.getByName(name);
	}

	@Override
	public List<Event> getAll() throws EventServiceException {
		return eventDao.getAllEvents();
	}
}
