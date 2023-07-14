package site._60jong.advanced.kj.aop.log.tracer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import site._60jong.advanced.kj.aop.log.Trace;
import site._60jong.advanced.kj.aop.log.TraceStatus;

@Slf4j
@Component
public class ThreadLocalLogTracer implements LogTracer {

    private static final String BEGIN_PREFIX = "-->";
    private static final String END_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private ThreadLocal<Trace> traceHolder = new ThreadLocal<>();

    @Override
    public TraceStatus begin(String message) {
        Trace trace = syncTrace();
        TraceStatus status = new TraceStatus(trace, message);

        log.info("[{}] {}{}", trace.getTraceId(), addSpace(BEGIN_PREFIX, trace.getLevel()), message);

        return status;
    }

    @Override
    public void end(TraceStatus status) {
        Trace trace = status.getTrace();
        log.info("[{}] {}{}", trace.getTraceId(), addSpace(END_PREFIX, trace.getLevel()), status.getMessage());

        releaseTrace(trace);
    }

    private void releaseTrace(Trace trace) {
        if (trace.isFirstLevel()) {
            traceHolder.remove();
            return;
        }
        traceHolder.set(trace.createPrevious());
    }

    private Trace syncTrace() {
        Trace trace = traceHolder.get();
        if (trace == null) {
            trace = new Trace();
            traceHolder.set(trace);

            return trace;
        }
        return trace.createNext();
    }

    private String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();

        if (level > 0) {
            sb.append("|");
            sb = addDepth(sb, level);
            sb.append(prefix);
        }
        return sb.toString();
    }

    private StringBuilder addDepth(StringBuilder sb, int level) {
        for (int i = 0; i < level; i++) {
            sb.append("\t|");
        }

        return sb;
    }
}
