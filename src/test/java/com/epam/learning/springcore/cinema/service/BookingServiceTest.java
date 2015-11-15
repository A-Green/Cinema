package com.epam.learning.springcore.cinema.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Rating;
import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.exception.EventServiceException;
import com.epam.learning.springcore.cinema.service.exception.ServiceException;
import com.epam.learning.springcore.cinema.utils.Formatter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class BookingServiceTest {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private EventService eventSerivce;
	
	@Autowired
	private AuditoriumService auditoriumService;
	
	@Autowired
	private UserService userService;
	
	private double basePrice = 100;
	private Date eventDate = Formatter.getCurrentDate();
	private Event event;
	private String auditoriumName = "aud1";
	
	@Before
	public void init() throws EventServiceException {
		event = eventSerivce.create("movie", basePrice, Rating.LOW);
	}

	@Test
	public void priceTest() throws ServiceException {
		//without any discounts
		assertTrue(bookingService.getTicketPrice(event, eventDate, 11, new User(),auditoriumName) == basePrice);
		
		User user = new User();
		user.setBirthday(eventDate);

		//birthday discount assuming
		assertTrue(bookingService.getTicketPrice(event, eventDate, 11, user, auditoriumName) < basePrice);
	}
	
	@Test
	public void bookTest() throws ServiceException {
		Auditorium auditorium = auditoriumService.getByName("aud1");
		assertNotNull(auditorium);
		
		Ticket t1 = new Ticket();
		t1.setEvent(event);
		t1.setEventDate(eventDate);
		t1.setAuditorium(auditorium);
		
		Ticket t2 = new Ticket();
		t2.setEvent(event);
		t2.setEventDate(eventDate);
		t2.setAuditorium(auditorium);

		User user = new User("userEmail@test", "userName", Formatter.getCurrentDate());
		user = userService.registerUser(user);
		
		eventSerivce.assignAuditorium(event, auditorium, eventDate);
		
		bookingService.bookTicket(user, t1);
		bookingService.bookTicket(user, t2);
		
		List<Ticket> bookedTickets = bookingService.getTicketsForEvent(event, eventDate);
		assertNotNull(bookedTickets);
		assertEquals(bookedTickets.size(),2);
	}
}
