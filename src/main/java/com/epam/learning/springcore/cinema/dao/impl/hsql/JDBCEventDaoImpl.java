package com.epam.learning.springcore.cinema.dao.impl.hsql;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.epam.learning.springcore.cinema.dao.EventDao;
import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.model.Event;

public class JDBCEventDaoImpl extends JDBCBaseDaoImpl<Integer, Event> implements EventDao {
	
	private static final String ASSIGN_AUDITORIUM = "INSERT INTO affiche(event_id, auditorium_id, date)"
			+ " VALUES(:eventId, :audId, :date)";
	
	private static final String SAVE = "INSERT INTO event(name, rating, base_ticket_price) "
			+ "VALUES (:name, :rating, :base_ticket_price)";
	
	private static final String GET_EVENTS_FOR_DATE = "SELECT * FROM affiche JOIN event "
			+ "ON affiche.event_id = event.id "
			+ "WHERE date = :date";
	
	private static final String GET_BY_NAME = "SELECT * FROM event WHERE name = :name";
	private static final String GET_ALL_EVENTS = "SELECT * FROM event";

	@Override
	public void assignAuditorium(Event event, Auditorium auditorium, Date date) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("eventId", event.getId());
		params.addValue("audId", auditorium.getId());
		params.addValue("date", date);
		jdbcTemplate.update(ASSIGN_AUDITORIUM, params);
	}

	@Override
	public List<Event> getByName(String name) {
		return (List<Event>) handleEmptyResult(GET_BY_NAME, new MapSqlParameterSource("name", name), mapper);
	}

	@Override
	public List<Event> getAllEvents() {
		return  (List<Event>) handleEmptyResult(GET_ALL_EVENTS, mapper);
	}
	
	@Override
	public List<Event> getEventsForDate(Date date) {
		return (List<Event>) handleEmptyResult(GET_EVENTS_FOR_DATE, new MapSqlParameterSource("date", date), mapper);
	}

	@Override
	public Event save(Event entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("base_ticket_price", entity.getBaseTicketPrice());
		params.addValue("name", entity.getName());
		params.addValue("rating", String.valueOf(entity.getRating()));
		jdbcTemplate.update(SAVE, params, keyHolder);
		entity.setId((Integer) keyHolder.getKey());
		return entity;
	}
}
