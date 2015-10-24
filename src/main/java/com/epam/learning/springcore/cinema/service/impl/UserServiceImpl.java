package com.epam.learning.springcore.cinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.learning.springcore.cinema.dao.UserDao;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.UserService;
import com.epam.learning.springcore.cinema.service.exception.ServiceException;
import com.epam.learning.springcore.cinema.service.exception.UserServiceException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao; 

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

	public void getBookedTickets(Integer userId) throws UserServiceException {
		// TODO Auto-generated method stub
		
	}
	
	public void remove(Integer id) throws ServiceException {
		userDao.remove(id);	
	}
}
