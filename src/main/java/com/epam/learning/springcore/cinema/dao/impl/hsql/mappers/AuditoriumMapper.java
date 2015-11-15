package com.epam.learning.springcore.cinema.dao.impl.hsql.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.learning.springcore.cinema.model.Auditorium;

public class AuditoriumMapper extends ContextMapper implements RowMapper<Auditorium> {

	@Override
	public Auditorium mapRow(ResultSet rs, int rowNum) throws SQLException {
		Auditorium auditorium = context.getBean(Auditorium.class);
		auditorium.setId(rs.getString("id"));
		auditorium.setName(rs.getString("name"));
		auditorium.setSeatsNumber(rs.getInt("seatsAmount"));
		return auditorium;
	}

}
