package site._60jong.advanced.practice.proxy.config.v1_proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.practice.proxy.app.trace.logtrace.LogTrace;
import site._60jong.advanced.practice.proxy.app.v1.*;
import site._60jong.advanced.practice.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import site._60jong.advanced.practice.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import site._60jong.advanced.practice.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public OrderControllerP1 orderController(LogTrace logTrace) {
        OrderControllerP1 target = new OrderControllerP1Impl(orderService(logTrace));
        return new OrderControllerInterfaceProxy(target, logTrace);
    }

    @Bean
    public OrderServiceP1 orderService(LogTrace logTrace) {
        OrderServiceP1 target = new OrderServiceP1Impl(orderRepository(logTrace));
        return new OrderServiceInterfaceProxy(target, logTrace);
    }

    @Bean
    public OrderRepositoryP1 orderRepository(LogTrace logTrace) {
        OrderRepositoryP1 target = new OrderRepositoryP1Impl();
        return new OrderRepositoryInterfaceProxy(target, logTrace);
    }
}
