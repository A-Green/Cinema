package com.epam.learning.springcore.cinema.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.epam.learning.springcore.cinema.logic.discount.DiscountStrategy;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {

	private List<DiscountStrategy> discountStrategies;
	
	public DiscountServiceImpl() {
		
	}
	 
	public DiscountServiceImpl(List<DiscountStrategy> discountStrategies) {
		this.discountStrategies = discountStrategies;
	}
	
	@Override
	public double getDiscount(User user, Event event, Date date) {
		double maxDiscount = 0;
		DiscountStrategy bestStrategy = null;
		for (DiscountStrategy strategy: discountStrategies) {
			double discount = strategy.getDiscount(event, user, date);
			if (maxDiscount < discount) {
				maxDiscount = discount;
				bestStrategy = strategy;
			}
		}
		return applyDiscount(bestStrategy, event, user, date);
	}
	
	private double applyDiscount(DiscountStrategy strategy, Event event, User user, Date date) {
		return (strategy == null)? 0 : strategy.getDiscount(event, user, date);
	}
}
