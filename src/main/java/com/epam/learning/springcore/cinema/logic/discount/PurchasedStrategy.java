package com.epam.learning.springcore.cinema.logic.discount;

import java.util.Date;

import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.User;

public class PurchasedStrategy extends DiscountStrategy {
	
	private int ticketCountForDiscount = 5;
	
	public PurchasedStrategy() {
	}
	
	public PurchasedStrategy(double discountPercent) {
		this.discountPercent = discountPercent;
	}

	@Override
	public double getDiscount(Event event, User user, Date date) {
		double discount = 0;
		if (user != null) {
			if (!user.getBookedTickets().isEmpty()
					&& user.getBookedTickets().size() % ticketCountForDiscount == 0) {
				discount =  event.getBaseTicketPrice() / 100 * discountPercent;
			}
		}
		return discount;
	}

	public int getTicketCountForDiscount() {
		return ticketCountForDiscount;
	}

	public void setTicketCountForDiscount(int ticketCountForDiscount) {
		this.ticketCountForDiscount = ticketCountForDiscount;
	}
}
