package site._60jong.advanced.practice.proxy.config.v4_proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.practice.proxy.app.trace.logtrace.LogTrace;
import site._60jong.advanced.practice.proxy.config.v3_proxy.proxyfactory.advice.LogTraceAdvice;
import site._60jong.advanced.practice.proxy.config.v4_proxy.postprocessor.PackageLogTracePostProcessor;

@Slf4j
@Configuration
public class BeanProcessorConfig {
    @Bean
    public BeanPostProcessor logTracePostProcessor(LogTrace logTrace) {
        return new PackageLogTracePostProcessor("site._60jong.advanced.practice.proxy.app", new DefaultPointcutAdvisor(Pointcut.TRUE, new LogTraceAdvice(logTrace)));
    }

}
