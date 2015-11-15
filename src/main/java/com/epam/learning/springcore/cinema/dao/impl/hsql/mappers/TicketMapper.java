package com.epam.learning.springcore.cinema.dao.impl.hsql.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.learning.springcore.cinema.model.Ticket;

public class TicketMapper extends ContextMapper implements RowMapper<Ticket> {

	@Override
	public Ticket mapRow(ResultSet result, int arg1) throws SQLException {
		Ticket ticket = context.getBean(Ticket.class);
		ticket.setId(result.getInt("id"));
		ticket.setSeatNumber(result.getInt("seat"));
		return ticket;
	}
}
