package com.epam.learning.springcore.cinema.aspects.counters.mapbased;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.epam.learning.springcore.cinema.model.User;

@Aspect
@Component
public class UserDiscountCounter {
	// <strategy, <userId, discount count>>
	private Map<Class<?>, Map<Integer, Integer>> counter = new HashMap<>();

	@Pointcut("execution( * com.epam.learning.springcore.cinema.logic.discount.DiscountStrategy+.getDiscount(..))")
	public void applyDscount() {
	}

	@AfterReturning(pointcut = "applyDscount()", returning = "discount")
	public void count(JoinPoint joinPoint, double discount) {
		if (discount > 0) {
			User user = (User) joinPoint.getArgs()[1];
			Class<?> discountClass = joinPoint.getTarget().getClass();
			count(user.getId(), discountClass);
		}
	}

	@SuppressWarnings("rawtypes")
	public int getTotalByStrategy(Class discClass) {
		int count = 0;
		Map<Integer, Integer> stats = counter.get(discClass);
		if (stats != null) {
			for (Integer userId : stats.keySet()) {
				count += stats.get(userId);
			}
		}
		return count;
	}

	@SuppressWarnings("rawtypes")
	public Map<Class<?>, Integer> getTotalByUser(Integer userId) {
		Map<Class<?>, Integer> result = new HashMap<>();
		for (Class descClass : counter.keySet()) {
			Integer count = counter.get(descClass).get(userId);
			if (count != null) {
				result.put(descClass, count);
			}
		}
		return result;
	}

	private synchronized void count(Integer userId, Class<?> discountClass) {
		if (userId != null && discountClass != null) {
			Map<Integer, Integer> userCount = counter.get(discountClass);
			if (userCount == null) {
				userCount = new HashMap<>();
				userCount.put(userId, 1);
			} else {
				Integer count = userCount.get(userId);
				if (count == null) {
					userCount.put(userId, 1);
				} else {
					userCount.put(userId, count + 1);
				}
			}
			counter.put(discountClass, userCount);
		}
	}
}
