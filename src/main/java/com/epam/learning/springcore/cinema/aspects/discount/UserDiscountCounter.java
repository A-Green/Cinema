package com.epam.learning.springcore.cinema.aspects.discount;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserDiscountCounter {

	private Map<Class<?>, Map<Integer, Integer>> counter;
	
	@Pointcut("execution( private com.epam.learning.springcore.cinema.service.impl.DiscountServiceImpl applyDiscount(..))")
	private void applyDscount() {}
	
	@Around("applyDscount() && args(strategy, event, user, date)")
	public void count(ProceedingJoinPoint joinPoint, 
			Object strategy, Object event, Object user, Object date) {
		System.out.println("aspectWorks");
	};
	
	@SuppressWarnings("rawtypes")
	public int getTotalByStrategy(Class discClass) {
		int count = 0;
		Map<Integer, Integer> stats = counter.get(discClass);
		if (stats != null) {
			for(Integer userId: stats.keySet()) {
				count += stats.get(userId);
			}
		}
		return count;
	}
	
	@SuppressWarnings("rawtypes")
	public Map<Class<?>, Integer> getTotalByUser(Integer userId) {
		Map<Class<?>, Integer> result = new HashMap<>();
		for (Class descClass: counter.keySet()) {
			int count = counter.get(descClass).get(userId);
			result.put(descClass, count);
		}
		return result;
	}
}
