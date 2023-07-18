package site._60jong.advanced.kj.aop.proxy.common.v2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.proxy.common.v2.MainControllerV2;
import site._60jong.advanced.kj.aop.proxy.common.v2.MainRepositoryV2;
import site._60jong.advanced.kj.aop.proxy.common.v2.MainServiceV2;
import site._60jong.advanced.kj.aop.proxy.common.v2.config.concreteproxy.MainControllerV2ConcreteProxy;
import site._60jong.advanced.kj.aop.proxy.common.v2.config.concreteproxy.MainRepositoryV2ConcreteProxy;
import site._60jong.advanced.kj.aop.proxy.common.v2.config.concreteproxy.MainServiceV2ConcreteProxy;

@Configuration
public class AppV2Config {

    @Bean
    public MainRepositoryV2 mainRepositoryV2(LogTracer logTracer) {
        MainRepositoryV2 target = new MainRepositoryV2();
        return new MainRepositoryV2ConcreteProxy(target, logTracer);
    }

    @Bean
    public MainServiceV2 mainServiceV2(LogTracer logTracer) {
        MainServiceV2 target = new MainServiceV2(mainRepositoryV2(logTracer));
        return new MainServiceV2ConcreteProxy(target, logTracer);
    }

    @Bean
    public MainControllerV2 mainControllerV2(LogTracer logTracer) {
        MainControllerV2 target = new MainControllerV2(mainServiceV2(logTracer));
        return new MainControllerV2ConcreteProxy(target, logTracer);
    }
}
