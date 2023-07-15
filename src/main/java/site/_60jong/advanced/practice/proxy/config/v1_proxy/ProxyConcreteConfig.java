package site._60jong.advanced.practice.proxy.config.v1_proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.practice.proxy.app.trace.logtrace.LogTrace;
import site._60jong.advanced.practice.proxy.app.v2.OrderControllerP2;
import site._60jong.advanced.practice.proxy.app.v2.OrderRepositoryP2;
import site._60jong.advanced.practice.proxy.app.v2.OrderServiceP2;
import site._60jong.advanced.practice.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import site._60jong.advanced.practice.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import site._60jong.advanced.practice.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;

@Configuration
public class ProxyConcreteConfig {

    @Bean
    public OrderControllerP2 orderController(LogTrace trace) {
        return new OrderControllerConcreteProxy(new OrderControllerP2(orderService(trace)), trace);
    }

    @Bean
    public OrderServiceP2 orderService(LogTrace trace) {
        return new OrderServiceConcreteProxy(new OrderServiceP2(orderRepository(trace)), trace);
    }

    @Bean
    public OrderRepositoryP2 orderRepository(LogTrace trace) {
        return new OrderRepositoryConcreteProxy(new OrderRepositoryP2(), trace);
    }
}
