package com.epam.learning.springcore.cinema.dao.impl.hsql;

import java.sql.Date;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.epam.learning.springcore.cinema.dao.UserDao;
import com.epam.learning.springcore.cinema.model.User;

public class JDBCUserDaoImpl extends JDBCBaseDaoImpl<Integer, User> implements UserDao {

	private static final String GET_BY_NAME = "SELECT * FROM user WHERE name = :name";
	private static final String GET_BY_EMAIL = "SELECT * FROM user WHERE email = :email";
	private static final String SAVE = "INSERT INTO user(name, email, birthday) VALUES(:name, :email, :birthday)";
	
	@Override
	public User registerUser(User user) {
		return save(user);
	}

	@Override
	public User getByEmail(final String email) {
		return handleEmptyResult(GET_BY_EMAIL, new MapSqlParameterSource("email", email));
	}

	@Override
	public User getByName(final String name) {
		return handleEmptyResult(GET_BY_NAME, new MapSqlParameterSource("name", name));
	}

	@Override
	public User save(User entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("name", entity.getName());
		paramMap.addValue("email", entity.getEmail());
		paramMap.addValue("birthday", new Date(entity.getBirthday().getTime()));	
		jdbcTemplate.update(SAVE, paramMap, keyHolder);
		entity.setId((Integer) keyHolder.getKey());
		return entity;
	}
}
