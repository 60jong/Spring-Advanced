package site._60jong.advanced.practice.proxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.practice.proxy.app.v2.OrderControllerP2;
import site._60jong.advanced.practice.proxy.app.v2.OrderRepositoryP2;
import site._60jong.advanced.practice.proxy.app.v2.OrderServiceP2;

@Configuration
public class AppV2Config {

    @Bean
    public OrderControllerP2 orderControllerP2() {
        return new OrderControllerP2(orderServiceP2());
    }

    @Bean
    public OrderServiceP2 orderServiceP2() {
        return new OrderServiceP2(orderRepositoryP2());
    }

    @Bean
    public OrderRepositoryP2 orderRepositoryP2() {
        return new OrderRepositoryP2();
    }
}
