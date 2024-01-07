package com.nhnacademy.edu.springframework.dooray.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

@Aspect
public class ElapsedTimeAspect {

    private final Logger log = LoggerFactory.getLogger(ElapsedTimeAspect.class);

    @Around("@annotation(com.nhnacademy.edu.springframework.dooray.annotation.TraceElapsedTime)")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {

        StopWatch clock = new StopWatch();
        try {
            clock.start();
            return pjp.proceed();
        } finally {
            clock.stop();

            String clazz = pjp.getTarget().getClass().getSimpleName();
            String method = pjp.getSignature().getName();
            long totalTime = clock.getTotalTimeMillis();

            log.info("{}.{} {}ms", clazz, method, totalTime);
        }
    }
}
