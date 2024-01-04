package com.nhnacademy.edu.springframework.aop.aspect;

import com.nhnacademy.edu.springframework.aop.annotation.TraceTimeElapsed;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ElapsedAspect {

    //    @Around("execution(public void com.nhnacademy.edu.springframework.aop.service.MessageSender.sendMessage(..))")
//    @Around("@annotation(com.nhnacademy.edu.springframework.aop.annotation.TraceTimeElapsed)")
    @Around("@annotation(traceTimeElapsed)")
    public Object logging(ProceedingJoinPoint pjp, TraceTimeElapsed traceTimeElapsed) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            Object retVal = pjp.proceed();

            System.out.println(traceTimeElapsed.value());

            return retVal;
        } finally {
            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint());
        }
    }
}
