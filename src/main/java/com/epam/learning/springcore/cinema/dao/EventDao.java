package com.epam.learning.springcore.cinema.dao;

import java.util.Date;

import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.model.Event;

public interface EventDao extends BaseDao<Integer, Event> {
	void assignAuditorium(Event event, Auditorium auditorium, Date date);
}
