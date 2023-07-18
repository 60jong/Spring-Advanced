package site._60jong.advanced.kj.aop.proxy.common.v1.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.proxy.common.v1.*;
import site._60jong.advanced.kj.aop.proxy.common.v1.jdkdynamic.handler.FilteredLogTracerHandler;
import site._60jong.advanced.kj.aop.proxy.common.v1.jdkdynamic.handler.LogTracerHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@RequiredArgsConstructor
@Configuration
public class JdkDynamicProxyConfig {

    private final LogTracer logTracer;

    @Bean
    public MainRepositoryV1 mainRepositoryV1() {
        MainRepositoryV1Impl target = new MainRepositoryV1Impl();
        InvocationHandler logTraceHandler = new FilteredLogTracerHandler(target, logTracer, new String[]{"request*", "save*", "execute*"});

        MainRepositoryV1 proxy = (MainRepositoryV1) Proxy.newProxyInstance(
                MainRepositoryV1.class.getClassLoader(),
                new Class[]{MainRepositoryV1.class},
                logTraceHandler
        );

        return proxy;
    }

    @Bean
    public MainServiceV1 mainServiceV1() {
        MainServiceV1 target = new MainServiceV1Impl(mainRepositoryV1());
        InvocationHandler logTraceHandler = new LogTracerHandler(target, logTracer);

        MainServiceV1 proxy = (MainServiceV1) Proxy.newProxyInstance(
                MainServiceV1.class.getClassLoader(),
                new Class[]{MainServiceV1.class},
                logTraceHandler
        );

        return proxy;
    }

    @Bean
    public MainControllerV1 mainControllerV1() {
        MainControllerV1 target = new MainControllerV1Impl(mainServiceV1());
        InvocationHandler logTraceHandler = new FilteredLogTracerHandler(target, logTracer, new String[]{"request*", "save*", "execute*"});

        MainControllerV1 proxy = (MainControllerV1) Proxy.newProxyInstance(
                MainControllerV1.class.getClassLoader(),
                new Class[]{MainControllerV1.class},
                logTraceHandler
        );

        return proxy;
    }
}
