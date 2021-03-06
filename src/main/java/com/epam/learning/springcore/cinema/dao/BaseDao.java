package com.epam.learning.springcore.cinema.dao;

import com.epam.learning.springcore.cinema.model.Entity;

public interface BaseDao <PK, T extends Entity<PK>> {

	T getById(final PK id);
	T save(final T entity);
	void remove(final PK id);
}
