package com.epam.learning.springcore.cinema.aspects.event;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.epam.learning.springcore.cinema.model.Event;

@Aspect
@Component
public class NameAccessCounter {
	
	private Map<Integer, Integer> nameAccesCounter;
	
	public NameAccessCounter(){
		nameAccesCounter = new HashMap<>();
	}
	
	@Pointcut("execution( * com.epam.learning.springcore.cinema.model.Event+.getName(..))")
	private void getNameExecuted(){}
	
	@After("getNameExecuted()")
	public void countName(JoinPoint joinPoint) {
		Integer id = ((Event) joinPoint.getTarget()).getId();
		Integer count = nameAccesCounter.get(id);
		if (count == null){
			count = 0;
		}
		nameAccesCounter.put(id, ++count);
	}

	public Map<Integer, Integer> getNameAccesCounter() {
		return nameAccesCounter;
	}

	public void setNameAccesCounter(Map<Integer, Integer> nameAccesCounter) {
		this.nameAccesCounter = nameAccesCounter;
	};
}
