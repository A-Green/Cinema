package com.epam.learning.springcore.cinema.dao;

import java.io.Serializable;
import java.util.Collection;

public interface BaseDao <T, PK extends Serializable> {

	T getById(final PK id);
	T save(final T entity);
	void remove(final PK id);
	Collection<T> getAll();
}
