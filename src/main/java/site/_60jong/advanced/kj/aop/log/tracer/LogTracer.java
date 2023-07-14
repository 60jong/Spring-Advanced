package site._60jong.advanced.kj.aop.log.tracer;

import site._60jong.advanced.kj.aop.log.TraceStatus;

public interface LogTracer {

    TraceStatus begin(String message);

    void end(TraceStatus status);
}
