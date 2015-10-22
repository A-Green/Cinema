package com.epam.learning.springcore.cinema.service;

import java.util.Date;

import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.User;

public interface DiscountService {
	
	double getDiscount(User user, Event event, Date date); 

}
