package com.dskimina.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Log LOG = LogFactory.getLog(LoggingAspect.class);

    //based on https://www.mkyong.com/spring3/spring-aop-aspectj-annotation-example/

    @Around("execution(* com.dskimina.logic.BusinessLogic.testTransaction(..))")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        LOG.info("Before ASPECT");
        joinPoint.proceed(); //continue on the intercepted method
        LOG.info("After ASPECT");
    }

    @Around("execution(* com.dskimina.logic.BusinessLogic.testTransactionEx(..))")
    public void logAroundEx(ProceedingJoinPoint joinPoint) throws Throwable {

        LOG.info("Before ASPECT");
        joinPoint.proceed(); //continue on the intercepted method
        LOG.info("After ASPECT");
    }

    @AfterThrowing(pointcut = "execution(* com.dskimina.logic.BusinessLogic.testTransactionEx(..))", throwing= "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

        System.out.println("logAfterThrowing() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("Exception : " + error);
        System.out.println("******");

    }
}
