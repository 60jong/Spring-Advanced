package site._60jong.advanced.practice.trace.hellotrace;

import org.junit.jupiter.api.Test;
import site._60jong.advanced.practice.trace.TraceStatus;
import site._60jong.advanced.practice.trace.hellotrace.HelloTraceV2;

class HelloTraceV2Test {
    @Test
    void begin_end() {
        HelloTraceV2 trace = new HelloTraceV2();

        // trace
        TraceStatus status1 = trace.begin("Hello1");
        TraceStatus status2 = trace.sync(status1.getTraceId(), "Hello2");
        TraceStatus status3 = trace.sync(status2.getTraceId(), "Hello3");

        // end
        trace.end(status3);
        trace.end(status2);
        trace.end(status1);
    }
    @Test
    void begin_exception() {
        HelloTraceV2 trace = new HelloTraceV2();

        // trace
        TraceStatus status1 = trace.begin("Hello1");
        TraceStatus status2 = trace.sync(status1.getTraceId(), "Hello2");
        TraceStatus status3 = trace.sync(status2.getTraceId(), "Hello3");

        // end
        trace.exception(status3, new IllegalStateException());
        trace.end(status2);
        trace.end(status1);
    }
}