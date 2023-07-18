package site._60jong.advanced.kj.aop.proxy.common.v1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.proxy.common.v1.*;
import site._60jong.advanced.kj.aop.proxy.common.v1.interfaceproxy.MainControllerV1InterfaceProxy;
import site._60jong.advanced.kj.aop.proxy.common.v1.interfaceproxy.MainRepositoryV1InterfaceProxy;
import site._60jong.advanced.kj.aop.proxy.common.v1.interfaceproxy.MainServiceV1InterfaceProxy;

//@Configuration
public class AppV1Config {
    @Bean
    public MainControllerV1 mainControllerV1(LogTracer logTracer) {
        MainControllerV1Impl target = new MainControllerV1Impl(mainServiceV1(logTracer));
        return new MainControllerV1InterfaceProxy(target, logTracer);
    }

    @Bean
    public MainServiceV1 mainServiceV1(LogTracer logTracer) {
        MainServiceV1 target = new MainServiceV1Impl(mainRepositoryV1(logTracer));
        return new MainServiceV1InterfaceProxy(target, logTracer);
    }

    @Bean
    public MainRepositoryV1 mainRepositoryV1(LogTracer logTracer) {
        MainRepositoryV1 target = new MainRepositoryV1Impl();
        return new MainRepositoryV1InterfaceProxy(target, logTracer);
    }
}
