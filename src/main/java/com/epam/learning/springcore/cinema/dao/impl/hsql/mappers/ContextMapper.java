package com.epam.learning.springcore.cinema.dao.impl.hsql.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class ContextMapper {
	
	@Autowired
	protected ApplicationContext context;
}
