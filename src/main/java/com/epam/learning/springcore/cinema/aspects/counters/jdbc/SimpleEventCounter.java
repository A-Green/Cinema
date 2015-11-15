package com.epam.learning.springcore.cinema.aspects.counters.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class SimpleEventCounter {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	protected volatile boolean isFirst = true;

	protected String tableName;

	public synchronized void increment(Integer id) {
		String sql;
		if (isFirst) {
			sql = "INSERT INTO " + tableName + "(event_id, counter) VALUES(:id, 1)";
			isFirst = false;
		} else {
			sql = "UPDATE " + tableName + " SET counter = counter + 1 WHERE event_id = :id";
		}
		jdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
	}

	public Integer getCount(Integer id) {
		String sql = "SELECT counter FROM " + tableName + " WHERE event_id = :id";
		return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", id), Integer.class);
	}
}
