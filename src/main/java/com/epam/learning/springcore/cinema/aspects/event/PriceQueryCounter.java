package com.epam.learning.springcore.cinema.aspects.event;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.epam.learning.springcore.cinema.model.Event;

@Aspect
@Component
public class PriceQueryCounter extends AbstractSimpleCounter<Integer>{

	@After("execution( * com.epam.learning.springcore.cinema.model.Event+.getBaseTicketPrice(..))")
	public void countName(JoinPoint joinPoint) {
		Integer id = ((Event) joinPoint.getTarget()).getId();
		increment(id);
	}
}
