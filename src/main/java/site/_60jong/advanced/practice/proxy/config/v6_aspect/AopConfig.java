package site._60jong.advanced.practice.proxy.config.v6_aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.practice.proxy.app.trace.logtrace.LogTrace;
import site._60jong.advanced.practice.proxy.app.trace.logtrace.ThreadLocalLogTrace;
import site._60jong.advanced.practice.proxy.config.v6_aspect.aspect.LogTraceAspect;

@Configuration
public class AopConfig {

    @Bean // advisor
    public LogTraceAspect logTraceAspect() {
        return new LogTraceAspect(new ThreadLocalLogTrace());
    }
}
