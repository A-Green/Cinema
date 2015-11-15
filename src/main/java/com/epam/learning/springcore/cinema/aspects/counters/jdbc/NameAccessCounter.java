package com.epam.learning.springcore.cinema.aspects.counters.jdbc;

import javax.annotation.PostConstruct;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.epam.learning.springcore.cinema.model.Event;

@Aspect
@Component
public class NameAccessCounter extends SimpleEventCounter {
	
	@PostConstruct
	public void init() {
		tableName = "name_access_count";
	}

	@Pointcut("execution( * com.epam.learning.springcore.cinema.model.Event+.getName(..))")
	public void getNameMethod(){}
	
	@After("getNameMethod()")
	public void countName(JoinPoint joinPoint) {
		Integer id = ((Event) joinPoint.getTarget()).getId();
		increment(id);
	}
}
