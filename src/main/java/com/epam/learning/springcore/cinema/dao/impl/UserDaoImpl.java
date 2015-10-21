package com.epam.learning.springcore.cinema.dao.impl;

import org.springframework.stereotype.Repository;

import com.epam.learning.springcore.cinema.dao.UserDao;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.exception.UserServiceException;

@Repository
public class UserDaoImpl implements UserDao {

	public User fetchById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(User entity) {
		// TODO Auto-generated method stub
		
	}

	public void delete(User entity) {
		// TODO Auto-generated method stub
		
	}

	public User getById(Integer id) throws UserServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public User getByEmail(String email) throws UserServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public User getByName(String name) throws UserServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public void getBookedTickets(Integer userId) throws UserServiceException {
		// TODO Auto-generated method stub
		
	}

}
