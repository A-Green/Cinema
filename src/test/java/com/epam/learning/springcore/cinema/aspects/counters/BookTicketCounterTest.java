package com.epam.learning.springcore.cinema.aspects.counters;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.learning.springcore.cinema.aspects.counters.jdbc.BookTicketCounter;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Movie;
import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.AuditoriumService;
import com.epam.learning.springcore.cinema.service.BookingService;
import com.epam.learning.springcore.cinema.service.UserService;
import com.epam.learning.springcore.cinema.service.exception.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class BookTicketCounterTest {

	@Autowired
	private BookTicketCounter bookTicketCounter;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private AuditoriumService auditoriumService;
	
	@Autowired
	private UserService userService;
	
	private Event event;
	private Ticket ticket;
	
	@Before
	public void init() throws ServiceException {
		//assuming than there is data from populate-db.sql
		assertNotNull(bookTicketCounter);
		assertNotNull(applicationContext);
		event = applicationContext.getBean(Movie.class);
		event.setId(1);
		event.setName("Terminator");
		event.setBaseTicketPrice(100);
		
		ticket = applicationContext.getBean(Ticket.class);
		ticket.setId(1);
		ticket.setEvent(event);
		ticket.setAuditorium(auditoriumService.getByName("aud1"));
	}
	
	@Test
	public void bookCounterTest() throws ServiceException {
		User testUser = userService.getByName("test");
		int expectedCounterValue = 10;
		for(int i = 0; i < expectedCounterValue; i++) {
			bookingService.bookTicket(testUser, ticket);
		}
		assertTrue(bookTicketCounter.getCount(event.getId()) == expectedCounterValue);
	}
}
