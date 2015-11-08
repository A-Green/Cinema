package com.epam.learning.springcore.cinema.service;

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

import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Rating;
import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.exception.BookingServiceException;
import com.epam.learning.springcore.cinema.service.exception.EventServiceException;
import com.epam.learning.springcore.cinema.service.exception.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class BookingServiceTest {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private EventService eventSerivce;
	
	private double basePrice = 100;
	private Date eventDate = new Date(System.currentTimeMillis());
	private Event event;
	private String auditoriumName = "auditorium1";
	
	@Before
	public void init() throws EventServiceException {
		event = eventSerivce.create("movie", basePrice, Rating.HIGH);
	}

	@Test
	public void priceTest() throws ServiceException {
		//unregistered user, price should not be changed
		assertTrue(bookingService.getTicketPrice(event, eventDate, 1, new User(),auditoriumName) == basePrice);
		
		User user = new User();
		user.setBirthday(eventDate);
		
		//unregistered user, price should not be changed
		assertTrue(bookingService.getTicketPrice(event, eventDate, 1, user, auditoriumName) < basePrice);
	}
	
	@Test
	public void bookTest() throws BookingServiceException {
		Ticket t1 = new Ticket();
		t1.setEvent(event);
		t1.setEventDate(eventDate);
		
		Ticket t2 = new Ticket();
		t2.setEvent(event);
		t2.setEventDate(eventDate);
		
		bookingService.bookTicket(new User(), t1);
		bookingService.bookTicket(new User(), t2);
		
		List<Ticket> bookedTickets = bookingService.getTicketsForEvent(event, eventDate);
		assertNotNull(bookedTickets);
		assertTrue(bookedTickets.size() == 2);
		
		assertTrue(bookedTickets.contains(t1));
		assertTrue(bookedTickets.contains(t2));
	}
}
