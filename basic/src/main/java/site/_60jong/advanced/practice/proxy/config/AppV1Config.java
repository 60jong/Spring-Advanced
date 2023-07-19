package site._60jong.advanced.practice.proxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.practice.proxy.app.v1.*;

@Configuration
public class AppV1Config {

    @Bean
    public OrderControllerP1 orderControllerP1() {
        return new OrderControllerP1Impl(orderServiceP1());
    }

    @Bean
    public OrderServiceP1 orderServiceP1() {
        return new OrderServiceP1Impl(orderRepositoryP1());
    }

    @Bean
    public OrderRepositoryP1 orderRepositoryP1() {
        return new OrderRepositoryP1Impl();
    }
}
