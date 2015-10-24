package com.epam.learning.springcore.cinema.dao.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.learning.springcore.cinema.dao.UserDao;
import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.exception.UserServiceException;

@Repository
public class UserDaoImpl implements UserDao {

	private static List<User> users;
	
	public  UserDaoImpl() {
		users = new ArrayList<>();
	}
	
	@Override
	public User save(User entity) {
		if (entity != null) {
			users.add(entity);
		}
		return entity;
	}

	@Override
	public void remove(Integer id) {
		for (User user : users) {
			if (user.getId() == id) {
				users.remove(user);
				break;
			}
		}
	}

	@Override
	public User getById(Integer id) {
		return fieldGetter(id, "getId");
	}

	@Override
	public User getByEmail(String email) throws UserServiceException {
		return fieldGetter(email, "getEmail");
	}

	@Override
	public User getByName(String name) throws UserServiceException {
		return fieldGetter(name, "getName");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getBookedTickets(Integer userId) throws UserServiceException {
		return (List<Ticket>) fieldGetter(userId, "getBookedTickets");
	}

	@Override
	public Collection<User> getAll() {
		return users;
	}

	@SuppressWarnings("unchecked")
	private <T> User fieldGetter(T value, String methodName) {
		try {
			for (User user : users) {
				Method m = user.getClass().getDeclaredMethod(methodName);
				if (value == (T) m.invoke(user)) {
					return user;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
