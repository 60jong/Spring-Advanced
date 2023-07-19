package site._60jong.advanced.kj.aop.proxy.aop.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import site._60jong.advanced.kj.aop.log.TraceStatus;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;

@RequiredArgsConstructor
@Aspect
public class LogTracerAspect {

    private final LogTracer logTracer;

    @Around("execution(* site._60jong.advanced.kj.aop.proxy.common.v3..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;
        try {
            String message = joinPoint.getSignature().toShortString();
            status = logTracer.begin(message);

            Object result = joinPoint.proceed();

            logTracer.end(status);
            return result;
        } catch (Exception e) {
            logTracer.exception(status, e);
            throw e;
        }
    }
}
