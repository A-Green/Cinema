package com.epam.learning.springcore.cinema.dao;

import java.util.List;

import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.exception.UserServiceException;

public interface UserDao extends BaseDao<User, Integer> {
	
	User getByEmail(String email) throws UserServiceException;
	User getByName(String name) throws UserServiceException;
	List<Ticket> getBookedTickets(Integer userId) throws UserServiceException;
}
