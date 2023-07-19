package site._60jong.advanced.kj.aop.proxy.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.proxy.aop.aspect.LogTracerAspect;

@Configuration
public class AopConfig {
    @Bean
    public LogTracerAspect logTraceAspect(LogTracer logTracer) {
        return new LogTracerAspect(logTracer);
    }
}
