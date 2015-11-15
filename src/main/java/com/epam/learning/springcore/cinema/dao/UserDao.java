package com.epam.learning.springcore.cinema.dao;

import com.epam.learning.springcore.cinema.model.User;

public interface UserDao extends BaseDao<Integer, User> {
	
	User registerUser(User user);
	User getByEmail(String email);
	User getByName(String name);
}
