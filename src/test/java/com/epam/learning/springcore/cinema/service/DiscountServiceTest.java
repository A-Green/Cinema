package com.epam.learning.springcore.cinema.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Movie;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.exception.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class DiscountServiceTest {

	@Autowired
	private DiscountService discountService;

	private User testUser;
	private static final double basePrice = 100;

	@Before
	public void init() throws ServiceException {
		testUser = new User();
		testUser.setBirthday(new Date(System.currentTimeMillis()));
	}

	@Test
	public void discountTest() {
		assertNotNull(discountService);
		Event event = new Movie();
		event.setBaseTicketPrice(basePrice);
		double discount = discountService.getDiscount(testUser, event, new Date(System.currentTimeMillis()));
		assertEquals(5, discount, 0);	
	}
}
