package com.epam.learning.springcore.cinema.service;

import com.epam.learning.springcore.cinema.model.Entity;
import com.epam.learning.springcore.cinema.service.exception.ServiceException;

public interface BaseService<T extends Entity, PK> {
	
	void save(T entity) throws ServiceException;
	void remove(PK key) throws ServiceException;
}
