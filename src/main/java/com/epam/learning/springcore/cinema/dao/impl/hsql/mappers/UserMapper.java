package com.epam.learning.springcore.cinema.dao.impl.hsql.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.learning.springcore.cinema.model.User;

public class UserMapper extends ContextMapper implements RowMapper<User> {
	
	@Override
	public User mapRow(ResultSet rs	, int arg1) throws SQLException {
		User user = context.getBean(User.class);
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setBirthday(rs.getTimestamp("birthday"));
		return user;
	}
}
