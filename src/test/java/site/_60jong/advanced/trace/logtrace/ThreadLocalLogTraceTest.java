package site._60jong.advanced.trace.logtrace;

import org.junit.jupiter.api.Test;
import site._60jong.advanced.trace.TraceStatus;

import static org.junit.jupiter.api.Assertions.*;

class ThreadLocalLogTraceTest {
    ThreadLocalLogTrace trace = new ThreadLocalLogTrace();

    @Test
    void begin_end_level2() {
        TraceStatus status1= trace.begin("Hello");
        TraceStatus status2 = trace.begin("Hello2");

        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception_level2() {
        TraceStatus status1= trace.begin("Hello");
        TraceStatus status2 = trace.begin("Hello2");

        Exception exception = new IllegalStateException();
        trace.exception(status2, exception);
        trace.exception(status1, exception);
    }
}