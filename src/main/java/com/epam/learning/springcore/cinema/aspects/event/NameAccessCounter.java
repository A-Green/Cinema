package com.epam.learning.springcore.cinema.aspects.event;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.epam.learning.springcore.cinema.model.Event;

@Aspect
@Component
public class NameAccessCounter extends AbstractSimpleCounter<Integer> {

	@Pointcut("execution( * com.epam.learning.springcore.cinema.model.Event+.getName(..))")
	private void getNameMethod(){}
	
	@After("getNameMethod()")
	public void countName(JoinPoint joinPoint) {
		Integer id = ((Event) joinPoint.getTarget()).getId();
		increment(id);
	}
}
