package site._60jong.advanced.practice.proxy.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.practice.proxy.app.trace.logtrace.LogTrace;
import site._60jong.advanced.practice.proxy.app.trace.logtrace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
