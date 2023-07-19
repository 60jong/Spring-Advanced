package site._60jong.advanced.kj.aop.log.tracer.templatemethod;

import lombok.RequiredArgsConstructor;
import site._60jong.advanced.kj.aop.log.TraceStatus;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;

@RequiredArgsConstructor
public abstract class AbstractTemplate<T> {

    private final LogTracer tracer;

    public T execute(String message) {
        TraceStatus status = tracer.begin(message);

        // logic
        T result = call();

        tracer.end(status);
        return result;
    }

    protected abstract T call();
}
