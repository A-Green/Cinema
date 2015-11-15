package com.epam.learning.springcore.cinema.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class JDBCTicketDaoTest extends ContextSupportTest {

	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private EventDao eventDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuditoriumDao auditoriumDao;

	private static Event event;
	private static Ticket ticket;
	private static User user;

	// Assuming that tickets exists (populated from populate-db.sql)

	@Test
	public void bookTicket() {
		event = eventDao.getByName("Terminator").get(0);
		assertNotNull(event);
		user = context.getBean(User.class);
		user.setName("userName");
		user.setEmail("userEmail");
		user.setBirthday(new Date());
		user = userDao.save(user);
		assertNotNull(user);
		Auditorium auditorium = auditoriumDao.getByName("aud1");
		assertNotNull(auditorium);
		ticket = context.getBean(Ticket.class);
		ticket.setEvent(event);
		ticket.setAuditorium(auditorium);
		ticketDao.bookTicket(user, ticket);
	}

/*	@Test
	public void getTicketForEventTest() {
		List<Ticket> ticketsForEvent = ticketDao.getTicketsForEvent(event, new Date());
		assertNotNull(ticketsForEvent);
		assertTrue(ticketsForEvent.size() == 1);
		assertEquals(ticket.getId(), ticketsForEvent.get(0).getId());
		//TODO implemet assertEquals(ticket, ticketsForEvent.get(0));
	}*/

	@Test
	public void getBookedTickets() {
		List<Ticket> ticketsForEvent = ticketDao.getBookedTickets(user.getId());
		assertNotNull(ticketsForEvent);
		assertTrue(ticketsForEvent.size() == 1);
		assertEquals(ticket.getId(), ticketsForEvent.get(0).getId());
		//TODO implemet assertEquals(ticket, ticketsForEvent.get(0));
	}

}
