package site._60jong.advanced.practice.trace.callback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import site._60jong.advanced.practice.trace.TraceStatus;
import site._60jong.advanced.practice.trace.logtrace.LogTrace;

@Slf4j
@RequiredArgsConstructor
@Component
public class TraceTemplate {

    private final LogTrace trace;

    public <T> T execute(String message, TraceCallback<T> callback) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            // Logic
            T result = callback.call();

            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
