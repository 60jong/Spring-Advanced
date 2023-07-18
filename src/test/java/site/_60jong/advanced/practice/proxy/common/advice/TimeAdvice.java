package site._60jong.advanced.practice.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeAdvice 실행");

        // logic
        Object result = invocation.proceed();

        log.info("TimeAdvice 종료");
        return result;
    }
}
