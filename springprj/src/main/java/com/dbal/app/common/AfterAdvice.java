package com.dbal.app.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterAdvice {
	
	@Pointcut("execution(* com.dbal..*Impl.*(..))")
	public void allpointcut() {	}
	
	@AfterReturning(pointcut = "BeforeAdvice.allpointcut()", returning = "returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {
		String method = jp.getSignature().getName();
		String result = returnObj != null ? returnObj.toString() : "no return";
		System.out.println("[사후로그 어노테이션 실행]" + "method name : " + method + "result : " + result);
	}
}
