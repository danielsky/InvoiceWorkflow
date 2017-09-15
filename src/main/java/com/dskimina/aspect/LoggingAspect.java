package com.dskimina.aspect;

import com.dskimina.services.LogService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Log LOG = LogFactory.getLog(LoggingAspect.class);

    @Autowired
    private LogService logService;

    //based on https://www.mkyong.com/spring3/spring-aop-aspectj-annotation-example/

    @Around("execution(* com.dskimina.logic.BusinessLogic.createServiceRequest(..))")
    public Object logAroundInvoiceCreation(ProceedingJoinPoint joinPoint) throws Throwable {

        long t1 = System.currentTimeMillis();
        Object o = joinPoint.proceed(); //continue on the intercepted method
        long t2 = System.currentTimeMillis();

        String user = getCurrentUsername();
        logService.createLog("INVOICE_CREATION", user, t2-t1);
        return o;
    }

    @Around("execution(* com.dskimina.logic.BusinessLogic.createUser(..))")
    public void logAroundUserCreation(ProceedingJoinPoint joinPoint) throws Throwable {

        long t1 = System.currentTimeMillis();
        joinPoint.proceed(); //continue on the intercepted method
        long t2 = System.currentTimeMillis();

        String user = getCurrentUsername();
        logService.createLog("USER_CREATION", user, t2-t1);
    }

    private String getCurrentUsername(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            return auth.getName();
        }else{
            return null;
        }
    }
}
