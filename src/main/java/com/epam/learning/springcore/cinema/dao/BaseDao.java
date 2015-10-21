package com.epam.learning.springcore.cinema.dao;

import java.io.Serializable;

public interface BaseDao <T, PK extends Serializable> {

	T fetchById(final PK id);
	void save(final T entity);
	void delete(final T entity);
}
