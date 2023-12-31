package site._60jong.advanced.practice.trace.hellotrace;

import org.junit.jupiter.api.Test;
import site._60jong.advanced.practice.trace.TraceStatus;
import site._60jong.advanced.practice.trace.hellotrace.HelloTraceV1;


class HelloTraceV1Test {
    @Test
    void begin_end() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    @Test
    void begin_exception() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");

        trace.exception(status, new IllegalStateException());
    }

}