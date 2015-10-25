package com.epam.learning.springcore.cinema.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.epam.learning.springcore.cinema.service.exception.UserServiceException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	private User testUser;
	
	@Before
	public void init() throws ServiceException{
		testUser = new User();
		testUser.setId(1);
		testUser.setEmail("testemail");
		testUser.setName("testName");
		testUser.setBookedTickets(
				new ArrayList<>(
						Arrays.asList(new Ticket(), new Ticket())));
		
		userService.save(testUser);
	}
	
	@Test
	public void userTest() throws UserServiceException {
		assertNotNull(userService.getById(1));
		assertNotNull(userService.getByName("testName"));
		assertNotNull(userService.getByEmail("testemail"));
		
		List<Ticket> bookedTickets = userService.getBookedTickets(1);	
		assertNotNull(bookedTickets);
		assertTrue(bookedTickets.size() == 2);
		
		assertNull(userService.getById(11));
		assertNull(userService.getByName("testName1"));
		assertNull(userService.getByEmail("testemail1"));
		
		bookedTickets = userService.getBookedTickets(11);	
		assertNull(bookedTickets);
	}
}
