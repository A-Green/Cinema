package com.epam.learning.springcore.cinema.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

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
		
		app.userService.save(user);
		
		User newUser = app.userService.getById(1);
		System.out.println(newUser);
		newUser = null;
		
		newUser = app.userService.getByName("testName");
		System.out.println(newUser);
		newUser = null;
		
		newUser = app.userService.getByEmail("testemail");
		System.out.println(newUser);
		
	}
}
