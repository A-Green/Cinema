package com.epam.learning.springcore.cinema.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class ContextSupportTest {
	@Autowired
	protected ApplicationContext context;
}
