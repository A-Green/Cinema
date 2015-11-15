package com.epam.learning.springcore.cinema.dao.impl.hsql;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.epam.learning.springcore.cinema.dao.TicketDao;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;

public class JDBCTicketDaoImpl extends JDBCBaseDaoImpl<Integer, Ticket> implements TicketDao {

	private static final String GET_BOOKED_TICKETS = "SELECT * "
			+ "FROM ticket LEFT JOIN auditorium"
			+ " ON ticket.auditorium_id = auditorium.id "
			+ "WHERE user_id = :id";
	
	private static final String GET_PURCHASED_TICKETS = "SELECT * FROM "
			+ "ticket JOIN event ON ticket.event_id = event.id "
			+ "JOIN affiche ON event.id = affiche.event_id "
			+ "WHERE event.id = :id AND affiche.date = :date";
	
	private static final String SAVE = "INSERT INTO ticket(seat, price, auditorium_id, event_id, user_id) "
			+ "VALUES(:seat, :price, :audId, :eventId, :userId)";

	@Override
	public List<Ticket> getTicketsForEvent(final Event event, final Date date) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", event.getId());
		params.addValue("date", date);
		return (List<Ticket>) handleEmptyResult(GET_PURCHASED_TICKETS, params, mapper);
	}

	@Override
	public List<Ticket> getBookedTickets(final Integer userId) {
		return (List<Ticket>) handleEmptyResult(GET_BOOKED_TICKETS, new MapSqlParameterSource("id", userId), mapper);
	}

	@Override
	public Ticket save(Ticket entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("seat", entity.getSeatNumber());
		params.addValue("price", entity.getPrice());
		params.addValue("audId", entity.getAuditorium().getId());
		params.addValue("eventId", entity.getEvent().getId());
		params.addValue("userId", entity.getUser().getId());
		jdbcTemplate.update(SAVE, params, keyHolder);
		entity.setId((Integer)keyHolder.getKey());
		return entity;
	}

	@Override
	public void bookTicket(User user, Ticket ticket) {
		ticket.setUser(user);
		save(ticket);
	}
}
