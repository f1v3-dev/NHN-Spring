package com.nhnacademy.edu.springframework.aop;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ElapsedAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Around("execution(* com.nhnacademy.edu.springframework.*.*.*(..))")
    public Object elapse(ProceedingJoinPoint pjp) throws Throwable {

        StopWatch clock = new StopWatch();
        try {
            clock.start();
            return pjp.proceed();
        } finally {
            clock.stop();

            String className = pjp.getTarget().getClass().getSimpleName();
            String methodName = pjp.getSignature().getName();
            long totalTimeMillis = clock.getTotalTimeMillis();

            log.info("{}.{} {}ms", className, methodName, totalTimeMillis);
        }
    }

}
