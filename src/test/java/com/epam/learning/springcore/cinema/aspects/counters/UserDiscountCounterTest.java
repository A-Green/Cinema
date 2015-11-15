package com.epam.learning.springcore.cinema.aspects.counters;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.learning.springcore.cinema.aspects.counters.jdbc.UserDiscountCounter;
import com.epam.learning.springcore.cinema.logic.discount.BirthdayStrategy;
import com.epam.learning.springcore.cinema.logic.discount.PurchasedStrategy;
import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Movie;
import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.AuditoriumService;
import com.epam.learning.springcore.cinema.service.BookingService;
import com.epam.learning.springcore.cinema.service.DiscountService;
import com.epam.learning.springcore.cinema.service.UserService;
import com.epam.learning.springcore.cinema.service.exception.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class UserDiscountCounterTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserDiscountCounter discountCounter;
	
	@Autowired
	private AuditoriumService auditoriumService;

	@Autowired
	@Qualifier("preparedDiscountService")
	private DiscountService discountService;

	private Event event;
	private String auditoriumName = "aud1";

	@Before
	public void init() {
		event = applicationContext.getBean(Movie.class);
		event.setId(1);
		event.setBaseTicketPrice(100);
		event.setName("Hangover");
	}

	@Test
	public void purchasedDiscountTest() throws ServiceException {
		// Will book enough tickets to get purchased discount and check it with
		// counter
		Date date = new Date();
		User purchasedUser = applicationContext.getBean(User.class);
		purchasedUser.setName("testUser");
		purchasedUser.setEmail("testEmail");
		purchasedUser.setBirthday(new Date(12452345));
		purchasedUser = userService.registerUser(purchasedUser);

		Integer initialUserCount = discountCounter.getTotalByUser(purchasedUser.getId(), PurchasedStrategy.class);
		if (initialUserCount == null) {
			initialUserCount = 0;
		}

		Integer initialStrategyCount = discountCounter.getTotalByStrategy(PurchasedStrategy.class);
		if (initialStrategyCount == null) {
			initialStrategyCount = 0;
		}
		PurchasedStrategy strategy = (PurchasedStrategy) applicationContext.getBean("purchasedDiscStrategy");
		// tickets amount to achieve discount
		int buyCount = strategy.getTicketCountForDiscount();
		Auditorium auditorium = auditoriumService.getByName("aud1");
		if (strategy != null) {
			for (int i = 0; i <= buyCount; i++) {
				Ticket ticket = applicationContext.getBean(Ticket.class);
				ticket.setEvent(event);
				ticket.setAuditorium(auditorium);
				bookingService.getTicketPrice(event, date, 1, purchasedUser, auditoriumName);
				bookingService.bookTicket(purchasedUser, ticket);
			}
		}

		Integer userCount = discountCounter.getTotalByUser(purchasedUser.getId(), PurchasedStrategy.class);
		Integer strategyCount = discountCounter.getTotalByStrategy(PurchasedStrategy.class);

		// due to purchasing user one discount by PurchasedStrategy have to be
		// applied
		assertTrue(userCount == (initialUserCount + 1));
		assertTrue(strategyCount == (initialStrategyCount + 1));
	}

	@Test
	public void birthdayDiscountTest() throws ServiceException {
		// Get ticket price for user which celebrating birthday
		Date date = new Date();
		User birthdayUser = applicationContext.getBean(User.class);
		birthdayUser.setBirthday(date);
		birthdayUser = userService.registerUser(birthdayUser);

		Integer initialUserCount = discountCounter.getTotalByUser(birthdayUser.getId(), BirthdayStrategy.class);
		if (initialUserCount == null) {
			initialUserCount = 0;
		}
		Integer initialStrategyCount = discountCounter.getTotalByStrategy(BirthdayStrategy.class);
		if (initialStrategyCount == null) {
			initialStrategyCount = 0;
		}
		// counter in the aspect should be increased by this amount
		int discAmount = 5;

		for (int i = 0; i < discAmount; i++) {
			bookingService.getTicketPrice(event, date, 1, birthdayUser, auditoriumName);
		}

		int userCount = discountCounter.getTotalByUser(birthdayUser.getId(), BirthdayStrategy.class);
		int strategyCount = discountCounter.getTotalByStrategy(BirthdayStrategy.class);

		assertTrue(userCount == (initialUserCount + discAmount));
		assertTrue(strategyCount == (initialStrategyCount + discAmount));
	}
}
