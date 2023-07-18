package site._60jong.advanced.practice.proxy.config.v3_proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.practice.proxy.app.trace.logtrace.LogTrace;
import site._60jong.advanced.practice.proxy.app.v3.OrderControllerP3;
import site._60jong.advanced.practice.proxy.app.v3.OrderRepositoryP3;
import site._60jong.advanced.practice.proxy.app.v3.OrderServiceP3;
import site._60jong.advanced.practice.proxy.config.v3_proxy.proxyfactory.advice.LogTraceAdvice;

@Slf4j
@Configuration
public class ProxyFactoryConfig {
    @Bean
    public OrderControllerP3 orderController(LogTrace logTrace) {
        OrderControllerP3 target = new OrderControllerP3(orderService(logTrace));

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new LogTraceAdvice(logTrace)));
        OrderControllerP3 proxy = (OrderControllerP3) proxyFactory.getProxy();
        log.info("proxy class = {}", proxy.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceP3 orderService(LogTrace logTrace) {
        OrderServiceP3 target = new OrderServiceP3(orderRepository(logTrace));
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new LogTraceAdvice(logTrace)));
        OrderServiceP3 proxy = (OrderServiceP3) proxyFactory.getProxy();
        log.info("proxy class = {}", proxy.getClass());
        return proxy;
    }

    @Bean
    public OrderRepositoryP3 orderRepository(LogTrace logTrace) {
        OrderRepositoryP3 target = new OrderRepositoryP3();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new LogTraceAdvice(logTrace)));
        OrderRepositoryP3 proxy = (OrderRepositoryP3) proxyFactory.getProxy();
        log.info("proxy class = {}", proxy.getClass());
        return proxy;
    }

}
