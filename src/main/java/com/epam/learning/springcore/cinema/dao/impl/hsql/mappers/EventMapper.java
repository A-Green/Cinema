package com.epam.learning.springcore.cinema.dao.impl.hsql.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Rating;

public class EventMapper extends ContextMapper implements RowMapper<Event> {

	@Override
	public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
		Event event = context.getBean(Event.class);
		event.setId(rs.getInt("id"));
		event.setName(rs.getString("name"));
		event.setRating(Rating.valueOf(rs.getString("rating")));
		event.setBaseTicketPrice(rs.getDouble("base_ticket_price"));
		return event;
	}

}
