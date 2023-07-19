package site._60jong.advanced.practice.proxy.config.v6_aspect.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import site._60jong.advanced.practice.proxy.app.trace.TraceStatus;
import site._60jong.advanced.practice.proxy.app.trace.logtrace.LogTrace;

@Slf4j
@RequiredArgsConstructor
@Aspect // 애노테이션을 통해 Advisor 생성
public class LogTraceAspect {

    private final LogTrace logTrace;

    @Around("execution(* site._60jong.advanced.practice.proxy.app..*(..))") // pointcut
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable { // advice
        TraceStatus status = null;
        try {
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
