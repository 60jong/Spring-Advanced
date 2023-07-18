package site._60jong.advanced.kj.aop.proxy.common.v2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.proxy.common.v2.MainControllerV2;
import site._60jong.advanced.kj.aop.proxy.common.v2.MainRepositoryV2;
import site._60jong.advanced.kj.aop.proxy.common.v2.MainServiceV2;
import site._60jong.advanced.kj.aop.proxy.common.v2.cglib.LogTracerMethodInterceptor;

@RequiredArgsConstructor
@Configuration
public class CglibProxyConfig {

    private final LogTracer logTracer;
    private final String[] patterns = new String[]{"request*", "save*", "execute*"};

    @Bean
    public MainRepositoryV2 mainRepositoryV2() {
        MainRepositoryV2 target = new MainRepositoryV2();
        LogTracerMethodInterceptor interceptor = new LogTracerMethodInterceptor(target, logTracer, patterns);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MainRepositoryV2.class);
        enhancer.setCallback(interceptor);

        MainRepositoryV2 proxy = (MainRepositoryV2) enhancer.create();
        return proxy;
    }

    @Bean
    public MainServiceV2 mainServiceV2() {
        MainServiceV2 target = new MainServiceV2(mainRepositoryV2());
        LogTracerMethodInterceptor interceptor = new LogTracerMethodInterceptor(target, logTracer, patterns);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MainServiceV2.class);
        enhancer.setCallback(interceptor);

        MainServiceV2 proxy = (MainServiceV2) enhancer.create();
        return proxy;
    }

    @Bean
    public MainControllerV2 mainControllerV2() {
        MainControllerV2 target = new MainControllerV2(mainServiceV2());
        LogTracerMethodInterceptor interceptor = new LogTracerMethodInterceptor(target, logTracer, patterns);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MainControllerV2.class);
        enhancer.setCallback(interceptor);

        MainControllerV2 proxy = (MainControllerV2) enhancer.create();
        return proxy;
    }
}
