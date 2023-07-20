package site._60jong.aop.test.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class PerformanceAspect {

    // 응답 시간이 1000ms가 넘어간 요청은 WARN 로그와 응답 시간을 로그로 남긴다.
    @Around("@annotation(site._60jong.aop.test.annotation.Performance)")
    public Object traceLogIfDelayExceed1000Ms(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // logic
        Object result = joinPoint.proceed();

        long resultTime = System.currentTimeMillis() - startTime;
        if (resultTime >= 1000) {
            log.warn("[performance] {} {}ms", joinPoint.getSignature(), resultTime);
        }
        return result;
    }
}
