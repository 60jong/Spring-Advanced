package site._60jong.advanced.kj.aop.proxy.proxyfactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.proxy.common.v2.MainControllerV2;
import site._60jong.advanced.kj.aop.proxy.common.v2.MainRepositoryV2;
import site._60jong.advanced.kj.aop.proxy.common.v2.MainServiceV2;
import site._60jong.advanced.kj.aop.proxy.proxyfactory.advice.LogTracerAdvice;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class ConcreteProxyFactoryConfig {

    private final LogTracer logTracer;

    @Bean
    public MainRepositoryV2 mainRepositoryV2() {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "save*", "execute*");

        MainRepositoryV2 target = new MainRepositoryV2();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(pointcut, new LogTracerAdvice(logTracer)));

        MainRepositoryV2 proxy = (MainRepositoryV2) proxyFactory.getProxy();
        log.info("proxy class : {}", proxy.getClass());
        return proxy;
    }

    @Bean
    public MainServiceV2 mainServiceV2() {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "save*", "execute*");

        MainServiceV2 target = new MainServiceV2(mainRepositoryV2());

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(pointcut, new LogTracerAdvice(logTracer)));

        MainServiceV2 proxy = (MainServiceV2) proxyFactory.getProxy();
        log.info("proxy class : {}", proxy.getClass());
        return proxy;    }

    @Bean
    public MainControllerV2 mainControllerV2() {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "save*", "execute*");

        MainControllerV2 target = new MainControllerV2(mainServiceV2());

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(pointcut, new LogTracerAdvice(logTracer)));

        MainControllerV2 proxy = (MainControllerV2) proxyFactory.getProxy();
        log.info("proxy class : {}", proxy.getClass());
        return proxy;    }
}
