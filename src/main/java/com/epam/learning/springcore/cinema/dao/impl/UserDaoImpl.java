package com.epam.learning.springcore.cinema.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.epam.learning.springcore.cinema.dao.UserDao;
import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.exception.UserServiceException;

@Repository
public class UserDaoImpl extends MapBaseDaoImpl<Integer, User> implements UserDao {

	private static Map<Integer,User> users;
	
	public  UserDaoImpl() {
		users = new HashMap<>();
	}

	@Override
	public User getById(Integer id) {
		return users.get(id);
	}

	@Override
	public User getByEmail(String email) throws UserServiceException {
		return fieldGetter(email, "getEmail");
	}

	@Override
	public User getByName(String name) throws UserServiceException {
		return fieldGetter(name, "getName");
	}

	@Override
	public List<Ticket> getBookedTickets(Integer userId) throws UserServiceException {
		User user = users.get(userId);
		if (user != null) {
			return user.getBookedTickets();
		}
		return null;
	}

	@Override
	public Map<Integer, User> getAll() {
		return users;
	}
}
