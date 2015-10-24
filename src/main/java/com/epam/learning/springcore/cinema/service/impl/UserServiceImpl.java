package com.epam.learning.springcore.cinema.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.learning.springcore.cinema.dao.UserDao;
import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.UserService;
import com.epam.learning.springcore.cinema.service.exception.ServiceException;
import com.epam.learning.springcore.cinema.service.exception.UserServiceException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao; 

	@Override
	public void save(User entity) throws ServiceException {
		userDao.save(entity);
	}

	public User getById(Integer id) throws UserServiceException {
		return userDao.getById(id);
	}

	public User getByEmail(String email) throws UserServiceException {
		return userDao.getByEmail(email);
	}

	public User getByName(String name) throws UserServiceException {
		return userDao.getByName(name);
	}

	public List<Ticket> getBookedTickets(Integer userId) throws UserServiceException {
		return userDao.getBookedTickets(userId);
	}
	
	public void remove(Integer id) throws ServiceException {
		userDao.remove(id);	
	}
}
