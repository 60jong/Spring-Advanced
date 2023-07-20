package site._60jong.aop.exam.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import site._60jong.aop.exam.annotation.Retry;

@Slf4j
@Aspect
public class RetryAspect {

    @Around("@annotation(retry)")
    public Object doTry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        log.info("[retry] {} args = {}", joinPoint.getSignature(), joinPoint.getArgs());

        int maxRetry = retry.value();
        Exception exceptionHolder = null;

        for (int retryCount = 1; retryCount <= maxRetry; retryCount++) {
            log.info("[retry] try count = {}/{}", retryCount, maxRetry);

            try {
                return joinPoint.proceed();
            } catch (Exception e) {
                exceptionHolder = e;
            }
        }
        throw exceptionHolder;
    }
}
