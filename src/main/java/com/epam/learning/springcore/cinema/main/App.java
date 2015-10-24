package com.epam.learning.springcore.cinema.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.epam.learning.springcore.cinema.model.Ticket;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.EventService;
import com.epam.learning.springcore.cinema.service.UserService;
import com.epam.learning.springcore.cinema.service.exception.ServiceException;


@Component
public class App {

	@Autowired
	public UserService userService;
	
	@Autowired
	public EventService eventService;
	
	@SuppressWarnings("resource")
	public static void main(String... args) throws ServiceException {
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		App app = (App) ctx.getBean("app");	
		
		User user = new User();
		user.setId(1);
		user.setEmail("testemail");
		user.setName("testName");
		user.setBookedTickets(
				new ArrayList<>(
						Arrays.asList(new Ticket(), new Ticket())));
		
		app.userService.save(user);
		
		User newUser = app.userService.getById(1);
		System.out.println(newUser);
		newUser = null;
		
		newUser = app.userService.getByName("testName");
		System.out.println(newUser);
		newUser = null;
		
		newUser = app.userService.getByEmail("testemail");
		System.out.println(newUser);
		newUser = null;
		
		List<Ticket> bookedTickets = app.userService.getBookedTickets(1);
		System.out.println(bookedTickets);

	}
}
