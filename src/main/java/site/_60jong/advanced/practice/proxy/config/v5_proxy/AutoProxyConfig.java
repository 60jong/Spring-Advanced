package site._60jong.advanced.practice.proxy.config.v5_proxy;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.practice.proxy.app.trace.logtrace.LogTrace;
import site._60jong.advanced.practice.proxy.config.v3_proxy.proxyfactory.advice.LogTraceAdvice;

@Configuration
public class AutoProxyConfig {

//    @Bean
    public Advisor advisor1(LogTrace logTrace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "save*", "order*");

        return new DefaultPointcutAdvisor(pointcut, new LogTraceAdvice(logTrace));
    }
    @Bean
    public Advisor advisor2(LogTrace logTrace) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* site._60jong.advanced.practice.proxy.app..*(..))");

        return new DefaultPointcutAdvisor(pointcut, new LogTraceAdvice(logTrace));
    }
}
