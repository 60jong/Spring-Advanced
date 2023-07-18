package site._60jong.advanced.practice.proxy.config.v1_proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.practice.proxy.app.trace.logtrace.LogTrace;
import site._60jong.advanced.practice.proxy.app.v1.*;
import site._60jong.advanced.practice.proxy.config.v1_proxy.dynamicproxy.handler.LogTraceBasicHandler;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyBasicConfig {

    @Bean
    public OrderControllerP1 orderController(LogTrace trace) {
        OrderControllerP1 orderController = new OrderControllerP1Impl(orderService(trace));

        OrderControllerP1 proxy = (OrderControllerP1) Proxy.newProxyInstance(
                OrderControllerP1.class.getClassLoader(),
                new Class[]{OrderControllerP1.class},
                new LogTraceBasicHandler(orderController, trace)
        );

        return proxy;
    }

    @Bean
    public OrderServiceP1 orderService(LogTrace trace) {
        OrderServiceP1 orderService = new OrderServiceP1Impl(orderRepository(trace));

        OrderServiceP1 proxy = (OrderServiceP1) Proxy.newProxyInstance(
                OrderServiceP1.class.getClassLoader(),
                new Class[]{OrderServiceP1.class},
                new LogTraceBasicHandler(orderService, trace)
        );

        return proxy;
    }

    @Bean
    public OrderRepositoryP1 orderRepository(LogTrace trace) {
        OrderRepositoryP1 orderRepository = new OrderRepositoryP1Impl();

        OrderRepositoryP1 proxy = (OrderRepositoryP1) Proxy.newProxyInstance(
                OrderRepositoryP1.class.getClassLoader(),
                new Class[]{OrderRepositoryP1.class},
                new LogTraceBasicHandler(orderRepository, trace)
        );

        return proxy;
    }
}
