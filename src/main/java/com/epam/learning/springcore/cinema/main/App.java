package com.epam.learning.springcore.cinema.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.service.AuditoriumService;
import com.epam.learning.springcore.cinema.service.EventService;
import com.epam.learning.springcore.cinema.service.UserService;
import com.epam.learning.springcore.cinema.service.exception.ServiceException;

@Component
public class App {

	@Autowired
	public UserService userService;
	
	@Autowired
	public EventService eventService;
	
	@Autowired
	public AuditoriumService auditoriumSerivce;
	
	@SuppressWarnings("resource")
	public static void main(String... args) throws ServiceException {
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		App app = (App) ctx.getBean("app");	
		System.out.println(app.auditoriumSerivce.getVipSeats("auditorium1"));
/*		Auditorium auditorium = (Auditorium) ctx.getBean("auditorium1");
		System.out.println(auditorium);
		auditorium = (Auditorium) ctx.getBean("auditorium2");
		System.out.println(auditorium);
		auditorium = (Auditorium) ctx.getBean("auditorium3");
		System.out.println(auditorium);*/	
	}
}
