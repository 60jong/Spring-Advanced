package site._60jong.advanced.practice.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class BasicAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("BasicAdvice Start");

        Object result = invocation.proceed();

        log.info("BasicAdvice End");
        return result;
    }
}
