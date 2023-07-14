package site._60jong.advanced.kj.aop.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogTracer {

    private static final String BEGIN_PREFIX = "-->";
    private static final String END_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private ThreadLocal<Trace> traceHolder = new ThreadLocal<>();

    public Trace begin(String message) {
        Trace trace = syncTrace();
        log.info("[{}] {}{}", trace.getTraceId(), addSpace(BEGIN_PREFIX, trace.getLevel()), message);

        return trace;
    }
    public void end(String message, Trace trace) {
        log.info("[{}] {}{}", trace.getTraceId(), addSpace(END_PREFIX, trace.getLevel()), message);

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
