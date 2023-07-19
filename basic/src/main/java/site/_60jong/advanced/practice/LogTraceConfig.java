package site._60jong.advanced.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site._60jong.advanced.practice.trace.logtrace.LogTrace;
import site._60jong.advanced.practice.trace.logtrace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
