package site._60jong.advanced.practice.trace.logtrace;

import site._60jong.advanced.practice.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
