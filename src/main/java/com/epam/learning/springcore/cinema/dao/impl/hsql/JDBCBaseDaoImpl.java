package com.epam.learning.springcore.cinema.dao.impl.hsql;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.epam.learning.springcore.cinema.dao.BaseDao;
import com.epam.learning.springcore.cinema.model.Entity;

public abstract class JDBCBaseDaoImpl<PK, T extends Entity<PK>> implements BaseDao<PK, T> {

	@Autowired
	protected NamedParameterJdbcTemplate jdbcTemplate;

	protected RowMapper<T> mapper;

	protected String dbTableName;

	@Override
	public T getById(PK id) {
		String insertStmt = "SELECT * FROM " + dbTableName + " WHERE id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
		T entity = null;
		try {
			entity = jdbcTemplate.queryForObject(insertStmt, namedParameters, this.mapper);
		} catch (EmptyResultDataAccessException e) {
			entity = null;
		}
		return entity;
	}

	@Override
	public void remove(PK id) {
		String removeStmt = "DELETE FROM " + dbTableName + " WHERE id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
		jdbcTemplate.update(removeStmt, namedParameters);
	}

	@Override
	public abstract T save(T entity);

	public void setDbTableName(String tableName) {
		this.dbTableName = tableName;
	}

	public void setMapper(RowMapper<T> mapper) {
		this.mapper = mapper;
	}

	@SuppressWarnings("unchecked")
	public <V> V handleEmptyResult(String sql, MapSqlParameterSource params) {
		V result = null;
		try {
			result = (V) jdbcTemplate.queryForObject(sql, params, mapper);
		} catch (EmptyResultDataAccessException e) {
		}
		return result;
	}

	public Collection<T> handleEmptyResult(String sql, MapSqlParameterSource params, RowMapper<T> mapper) {
		Collection<T> result = null;
		try {
			result = (Collection<T>) jdbcTemplate.query(sql, params, mapper);
		} catch (EmptyResultDataAccessException e) {
		}
		return result;
	}
	
	public Collection<T> handleEmptyResult(String sql, RowMapper<T> mapper) {
		return handleEmptyResult(sql, new MapSqlParameterSource(), mapper);
	}

	public <V> Collection<V> handleEmptyResult(String sql, MapSqlParameterSource params, Class<V> targetClass) {
		Collection<V> result = null;
		try {
			result = jdbcTemplate.queryForList(sql, params, targetClass);
		} catch (EmptyResultDataAccessException e) {
		}
		return result;
	}
}
