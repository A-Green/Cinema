package com.epam.learning.springcore.cinema.dao.impl.mapbased;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.epam.learning.springcore.cinema.dao.EventDao;
import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.model.Event;

@Repository
public class EventDaoImpl extends MapBaseDaoImpl<Integer, Event> implements EventDao{
	
	private static Map<Integer, Event> events;
	
	public EventDaoImpl() {
		events = new HashMap<>();
	}

	@Override
	public void assignAuditorium(Event event, Auditorium auditorium, Date date) {
		//do not need in this realization because there is no DB 
		//and EventSerivice stores it in affiche map
	}

	@Override
	public Map<Integer, Event> getEntityMap() {
		return events;
	}

	@Override
	public List<Event> getByName(String name) {
		List<Event> result = new ArrayList<>();
		for (Integer eventId: events.keySet()){
			Event event = events.get(eventId);
			if (event.getName().equals(name)){
				result.add(event);
			}
		}
		return result;
	}

	@Override
	public List<Event> getAllEvents() {
		return  new ArrayList<Event>(events.values());
	}

	@Override
	public List<Event> getEventsForDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}
}
