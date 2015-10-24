package com.epam.learning.springcore.cinema.service;

import java.util.List;

import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.exception.UserServiceException;

public interface UserService extends BaseService<Integer, User> {

	User getById(Integer id) throws UserServiceException;
	User getByEmail(String email) throws UserServiceException;
	User getByName(String name) throws UserServiceException;
	List<Ticket> getBookedTickets(Integer userId) throws UserServiceException;
}
	