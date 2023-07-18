package site._60jong.advanced.kj.aop.proxy.beanpostprocessor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.proxy.proxyfactory.advice.LogTracerAdvice;

//@Configuration
public class AutoProxyConfig {
    // AnnotationAwareAspectJAutoProxyCreator
    private static final String ASPECTJ_EXPRESSION = "execution(* site._60jong.advanced.kj.aop.proxy.common.v3..*(..))";

    @Bean
    public Advisor advisor1(LogTracer logTracer) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(ASPECTJ_EXPRESSION);

        return new DefaultPointcutAdvisor(pointcut, new LogTracerAdvice(logTracer));
    }

    @Bean
    public Advisor advisor2() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(ASPECTJ_EXPRESSION);

        return new DefaultPointcutAdvisor(pointcut, new MyAdvice());
    }

    @Slf4j
    static class MyAdvice implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("myAdvice");
            return invocation.proceed();
        }
    }
}

