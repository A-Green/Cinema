package com.epam.learning.springcore.cinema.service;

import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.exception.UserServiceException;

public interface UserService extends BaseService<User, Integer> {

	User getById(Integer id) throws UserServiceException;
	User getByEmail(String email) throws UserServiceException;
	User getByName(String name) throws UserServiceException;
	void getBookedTickets(Integer userId) throws UserServiceException;
}
	