package com.chiroro.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Component
@Log4j
public class AspectLoger {
	
	@Before("execution(* com.chiroro.service.*.*(..))")
	public void beforeServiceLog(JoinPoint jp) {
		log.info(jp.getTarget()+"-------------------------------before");
		log.info("\tSignature: "+jp.getSignature());
		log.info("\tArgs: "+Arrays.toString(jp.getArgs()));
	}
	
	@AfterReturning(pointcut = "execution(* com.chiroro.service.*.*(..))", returning = "retVal")
	public void afterServiceLog(JoinPoint jp, Object retVal) {
		log.info(jp.getTarget()+"--------------------------------after");
		log.info("\tSignature: "+jp.getSignature());
		log.info("\treturn: "+retVal);
	}
}
