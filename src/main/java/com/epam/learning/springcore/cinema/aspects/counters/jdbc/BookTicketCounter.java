package com.epam.learning.springcore.cinema.aspects.counters.jdbc;

import javax.annotation.PostConstruct;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.epam.learning.springcore.cinema.model.Ticket;

@Aspect
@Component
public class BookTicketCounter extends SimpleEventCounter {
	
	@PostConstruct
	public void init() {
		tableName = "book_ticket_count";
	}

	@After("execution( * com.epam.learning.springcore.cinema.service.BookingService+.bookTicket(..))")
	public void countName(JoinPoint joinPoint) throws Throwable {
		Ticket ticket = (Ticket) joinPoint.getArgs()[1];	
		if (ticket != null && ticket.getEvent() != null) {
			Integer id = ticket.getEvent().getId();
			increment(id);
		}
	}
}
