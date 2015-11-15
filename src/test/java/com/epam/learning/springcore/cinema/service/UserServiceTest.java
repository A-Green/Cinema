package com.epam.learning.springcore.cinema.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.exception.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class UserServiceTest {

	@Autowired
	private UserService userService;

	private User testUser;

	@Before
	public void init() throws ServiceException {
		testUser = new User();
		testUser.setEmail("testemail");
		testUser.setName("testName");
		testUser.setBirthday(new Date());
		testUser.setBookedTickets(new ArrayList<>(Arrays.asList(new Ticket(), new Ticket())));
	}

	@Test
	public void CRUDTest() throws ServiceException {
		assertNotNull(userService);
		userService.save(testUser);

		assertNotNull(userService.getById(1));
		assertNotNull(userService.getByName("testName"));
		assertNotNull(userService.getByEmail("testemail"));

		List<Ticket> bookedTickets = userService.getBookedTickets(testUser.getId());
		assertNotNull(bookedTickets);
		assertTrue(bookedTickets.isEmpty());

		assertNull(userService.getById(11));
		assertNull(userService.getByName("testName1"));
		assertNull(userService.getByEmail("testemail1"));

		bookedTickets = userService.getBookedTickets(11);
		assertTrue(bookedTickets.isEmpty());
	}
}
