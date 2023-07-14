package site._60jong.advanced.kj.aop.log.tracer.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import site._60jong.advanced.kj.aop.log.TraceStatus;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;

@RequiredArgsConstructor
@Component
public class Context {

    private final LogTracer tracer;

    public <T> T execute(String message, Strategy<T> strategy) {
        TraceStatus status = tracer.begin(message);

        // logic
        T call = strategy.call();

        tracer.end(status);

        return call;
    }
}
