package com.epam.learning.springcore.cinema.logic.discount;

import java.util.Date;

import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.User;

public abstract class DiscountStrategy {
	
	protected double discountPercent;
	
	public abstract double getDiscount(Event event, User user, Date date);
}
