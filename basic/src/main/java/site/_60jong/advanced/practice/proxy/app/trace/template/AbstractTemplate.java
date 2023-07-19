package site._60jong.advanced.practice.proxy.app.trace.template;

import lombok.RequiredArgsConstructor;
import site._60jong.advanced.practice.proxy.app.trace.TraceStatus;
import site._60jong.advanced.practice.proxy.app.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public T execute(String message) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            // Logic
            T result = call();

            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();
}
