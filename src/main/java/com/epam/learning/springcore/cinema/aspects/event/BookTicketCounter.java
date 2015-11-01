package com.epam.learning.springcore.cinema.aspects.event;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.epam.learning.springcore.cinema.model.Ticket;

@Aspect
@Component
public class BookTicketCounter extends AbstractSimpleCounter<Integer> {

	@Around("execution( * com.epam.learning.springcore.cinema.service.BookingService+.bookTicket(..))"
			+ " && args(user, ticket)")
	public void countName(ProceedingJoinPoint joinPoint, Object user, Object ticket) {
		
		if (ticket != null && ((Ticket) ticket).getEvent() != null) {
			Integer id = ((Ticket) ticket).getEvent().getId();
			increment(id);
		}
	}
}
