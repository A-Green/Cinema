package com.epam.learning.springcore.cinema.dao.impl.hsql;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.epam.learning.springcore.cinema.dao.AuditoriumDao;
import com.epam.learning.springcore.cinema.model.Auditorium;

public class JDBCAuditoriumDaoImpl extends JDBCBaseDaoImpl<String, Auditorium>implements AuditoriumDao {

	private static final String GET_ALL = "SELECT * FROM auditorium";
	private static final String GET_SEATS_BY_NAME = "SELECT seatsAmount FROM auditorium WHERE name = :name";
	private static final String GET_AUD_BY_NAME = "SELECT * FROM auditorium WHERE name = :name";
	private static final String GET_VIP_BY_NAME = "SELECT seatNumber FROM vip_to_auditorium "
			+ "JOIN auditorium ON vip_to_auditorium.auditorium_id = auditorium.id "
			+ "WHERE auditorium.name = :name";
	private static final String GET_ASSIGNED_AUDS = "SELECT auditorium.id AS id, name, seatsAmount FROM affiche JOIN auditorium"
			+ " ON auditorium.id = affiche.auditorium_id WHERE date = :date";

	@Override
	public List<Auditorium> getAuditoriums() {
		return (List<Auditorium>) handleEmptyResult(GET_ALL, mapper);
	}

	@Override
	public int getSeatsNumber(String auditName) {
		List<Integer> result = (List<Integer>) handleEmptyResult(GET_SEATS_BY_NAME, new MapSqlParameterSource("name", auditName), Integer.class);
		return (int) ((result == null)? result: result.get(0));
	}

	@Override
	public List<Integer> getVipSeats(String auditName) {
		return (List<Integer>) handleEmptyResult(GET_VIP_BY_NAME, new MapSqlParameterSource("name", auditName), Integer.class);
	}

	@Override
	public Auditorium getByName(String name) {
		return (Auditorium) handleEmptyResult(GET_AUD_BY_NAME, new MapSqlParameterSource("name", name));
	}

	@Override
	public Auditorium save(Auditorium entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Auditorium> getAssignedAuditoriums(Date date) {
		return (List<Auditorium>) handleEmptyResult(GET_ASSIGNED_AUDS, new MapSqlParameterSource("date", date), mapper);
	}
}
