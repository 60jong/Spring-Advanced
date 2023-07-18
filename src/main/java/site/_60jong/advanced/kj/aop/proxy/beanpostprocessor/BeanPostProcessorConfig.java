package site._60jong.advanced.kj.aop.proxy.beanpostprocessor;

import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.proxy.beanpostprocessor.code.PackageBaseBeanPostProcessor;
import site._60jong.advanced.kj.aop.proxy.proxyfactory.advice.LogTracerAdvice;

//@Configuration
public class BeanPostProcessorConfig {
    @Bean
    public BeanPostProcessor packageBaseBeanPostProcessor(LogTracer logTracer) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "execute*", "save*");

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new LogTracerAdvice(logTracer));
        return new PackageBaseBeanPostProcessor("site._60jong.advanced.kj.aop.proxy.common", advisor);
    }
}
