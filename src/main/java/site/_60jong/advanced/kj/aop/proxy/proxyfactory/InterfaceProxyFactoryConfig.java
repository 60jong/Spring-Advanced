package site._60jong.advanced.kj.aop.proxy.proxyfactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.proxy.common.v1.*;
import site._60jong.advanced.kj.aop.proxy.proxyfactory.advice.LogTracerAdvice;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class InterfaceProxyFactoryConfig {

    private final LogTracer logTracer;

    @Bean
    public MainRepositoryV1 mainRepositoryV1() {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("execute*", "request*", "save*");

        MainRepositoryV1 target = new MainRepositoryV1Impl();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(
                new DefaultPointcutAdvisor(pointcut, new LogTracerAdvice(logTracer))
        );
        // 인터페이스가 있음에도 클래스 상속으로 프록시 만드는 설정!!
        proxyFactory.setProxyTargetClass(true);

        MainRepositoryV1 proxy = (MainRepositoryV1) proxyFactory.getProxy();
        log.info("proxy class : {}", proxy.getClass());
        return proxy;
    }

    @Bean
    public MainServiceV1 mainServiceV1() {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("execute*", "request*", "save*");

        MainServiceV1 target = new MainServiceV1Impl(mainRepositoryV1());

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(
                new DefaultPointcutAdvisor(pointcut, new LogTracerAdvice(logTracer))
        );

        MainServiceV1 proxy = (MainServiceV1) proxyFactory.getProxy();
        log.info("proxy class : {}", proxy.getClass());
        return proxy;
    }

    @Bean
    public MainControllerV1 mainControllerV1() {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("execute*", "request*", "save*");

        MainControllerV1 target = new MainControllerV1Impl(mainServiceV1());

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(
                new DefaultPointcutAdvisor(pointcut, new LogTracerAdvice(logTracer))
        );

        MainControllerV1 proxy = (MainControllerV1) proxyFactory.getProxy();
        log.info("proxy class : {}", proxy.getClass());
        return proxy;
    }
}
