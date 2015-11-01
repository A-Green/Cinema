package com.epam.learning.springcore.cinema.aspects.discount;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.learning.springcore.cinema.logic.discount.PurchasedStrategy;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Movie;
import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.DiscountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class UserDiscountCounterTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private UserDiscountCounter discountCounter;

	@Autowired
	@Qualifier("preparedDiscountService")
	private DiscountService discountService;

	private Event event;

	@Before
	public void init() {
		event = applicationContext.getBean(Movie.class);
		event.setId(1);
		event.setBaseTicketPrice(100);
		event.setName("Hangover");
	}

	@Test
	public void counterTest() {
		Date date = new Date();
		User birthdayUser = applicationContext.getBean(User.class);
		birthdayUser.setBirthday(date);
		birthdayUser.setId(1);

		User purchasedUser = applicationContext.getBean(User.class);
		purchasedUser.setId(2);

		PurchasedStrategy strategy = (PurchasedStrategy) applicationContext.getBean("purchasedDiscStrategy");
		
		if (strategy != null) {
			int buyCount = strategy.getTicketCountForDiscount();
			for (int i = 0; i < buyCount; i++) {
				Ticket ticket = applicationContext.getBean(Ticket.class);
				ticket.setEvent(event);
				discountService.getDiscount(purchasedUser, event, date);
			}
		}
	}
}
