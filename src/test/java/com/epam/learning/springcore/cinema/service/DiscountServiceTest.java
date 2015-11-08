package com.epam.learning.springcore.cinema.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.learning.springcore.cinema.logic.discount.BirthdayStrategy;
import com.epam.learning.springcore.cinema.logic.discount.PurchasedStrategy;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Movie;
import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.exception.DiscountServiceException;
import com.epam.learning.springcore.cinema.service.exception.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class DiscountServiceTest {

	@Autowired
	@Qualifier("preparedDiscountService")
	private DiscountService discountService;

	@Autowired
	@Qualifier("purchasedDiscStrategy")
	private PurchasedStrategy purchasedStrategy;
	
	@Autowired
	@Qualifier("birthdayDiscStrategy")
	private BirthdayStrategy birthdayDiscStrategy;
	
	private User testUser;
	private Event event;
	private static final double basePrice = 100;

	@Before
	public void init() throws ServiceException {
		testUser = new User();
		testUser.setBirthday(new Date(System.currentTimeMillis()));
		event = new Movie();
		event.setId(1);
		event.setBaseTicketPrice(basePrice);
		
		int amount = purchasedStrategy.getTicketCountForDiscount();
		for (int i = 0; i < amount; i++) {
			testUser.getBookedTickets().add(new Ticket());
		}
	}

	@Test
	public void discountTest() throws DiscountServiceException {
		assertNotNull(discountService);
		double bPercent = birthdayDiscStrategy.getDiscountPercent();
		double bDiscount = birthdayDiscStrategy.getDiscount(event, testUser, new Date(System.currentTimeMillis()));
		assertEquals(basePrice / 100 * bPercent, bDiscount, 0);
		
		assertNotNull(purchasedStrategy);
		double pPercent = purchasedStrategy.getDiscountPercent();
		double pdiscount = purchasedStrategy.getDiscount(event, testUser, new Date(System.currentTimeMillis()));
		assertEquals(basePrice / 100 * pPercent, pdiscount, 0);
		
		double max = (bDiscount > pdiscount)? bDiscount: pdiscount;
		assertEquals(max, discountService.getDiscount(testUser, event,  new Date(System.currentTimeMillis())), 0);
	}
}
