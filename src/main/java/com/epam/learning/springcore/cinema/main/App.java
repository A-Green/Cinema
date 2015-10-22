package com.epam.learning.springcore.cinema.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.epam.learning.springcore.cinema.service.EventService;
import com.epam.learning.springcore.cinema.service.UserService;

@Component
public class App {

	@Autowired
	public UserService userService;
	
	@Autowired
	public EventService eventService;
	
	@SuppressWarnings("resource")
	public static void main(String... args) {
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		App app = (App) ctx.getBean("app");	
		System.out.println(app.userService);
	}
}
