package site._60jong.advanced.practice.proxy.config.v3_proxy.proxyfactory.advice;

import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import site._60jong.advanced.practice.proxy.app.trace.TraceStatus;
import site._60jong.advanced.practice.proxy.app.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public class LogTraceAdvice implements MethodInterceptor {

    private final LogTrace logTrace;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TraceStatus status = null;
        try {
            status = logTrace.begin(invocation.getMethod().getDeclaringClass().getSimpleName() + "." + invocation.getMethod().getName() + "()");

            // logic
            Object result = invocation.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }

    }
}
