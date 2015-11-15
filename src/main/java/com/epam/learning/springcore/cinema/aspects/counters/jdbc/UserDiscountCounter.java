package com.epam.learning.springcore.cinema.aspects.counters.jdbc;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.epam.learning.springcore.cinema.logic.discount.DiscountStrategy;
import com.epam.learning.springcore.cinema.model.User;

@Aspect
@Component
public class UserDiscountCounter {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String TABLE_NAME = "discount_count";
	private static final String INIT = "INSERT INTO " + TABLE_NAME  + "(user_id, discType, counter) VALUES(:id, :discType, 1)";
	private static final String INCREMENT = "UPDATE " + TABLE_NAME  + " SET counter = counter + 1 WHERE user_id = :id AND discType = :discType";
	private static final String GET_BY_USER_AND_TYPE = "SELECT counter FROM " + TABLE_NAME + " WHERE user_id = :id AND discType = :discType";
	private static final String GET_TOTAL_BY_USER = "SELECT SUM(counter) FROM " + TABLE_NAME + " WHERE user_id = :id AND discType = :discType";
	private static final String GET_TOTAL_BY_STRATEGY = "SELECT SUM(counter) FROM " + TABLE_NAME + " WHERE discType = :discType";
	
	@Pointcut("execution( * com.epam.learning.springcore.cinema.logic.discount.DiscountStrategy+.getDiscount(..))")
	public void applyDscount() {}

	@AfterReturning(pointcut = "applyDscount()", returning = "discount")
	public void count(JoinPoint joinPoint, double discount) {
		if (discount > 0) {
			User user = (User) joinPoint.getArgs()[1];
			Class<?> discountClass = joinPoint.getTarget().getClass();
			count(user.getId(), discountClass);
		}
	}

	public Integer getTotalByStrategy(Class<? extends DiscountStrategy > discClass) {
		try {
			String  strategy = String.valueOf(StrategyType.fromValue(discClass));
			return jdbcTemplate.queryForObject(GET_TOTAL_BY_STRATEGY, 
					new MapSqlParameterSource("discType", strategy), Integer.class);
		} catch(EmptyResultDataAccessException e) {
			return 0;
		}
	}

	public Integer getTotalByUser(Integer userId, Class<? extends DiscountStrategy > discClass) {
		try {
			String  strategy = String.valueOf(StrategyType.fromValue(discClass));
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("discType", strategy);
			params.addValue("id", userId);
			return jdbcTemplate.queryForObject(GET_TOTAL_BY_USER, params, Integer.class);
		} catch(EmptyResultDataAccessException e) {
			return 0;
		}
	}

	private synchronized void count(Integer userId, Class<?> discountClass) {
		if (userId != null && discountClass != null) {
			MapSqlParameterSource paramMap = new MapSqlParameterSource();
			try {
				paramMap.addValue("id", userId);
				StrategyType strategyType = StrategyType.fromValue(discountClass);
				paramMap.addValue("discType", String.valueOf(strategyType));
				jdbcTemplate.queryForObject(GET_BY_USER_AND_TYPE, paramMap, Integer.class);
				jdbcTemplate.update(INCREMENT, paramMap);
			} catch (EmptyResultDataAccessException e) {
				// there is no record for the event, insert initial
				jdbcTemplate.update(INIT, paramMap);
			}
		}
	}
}
