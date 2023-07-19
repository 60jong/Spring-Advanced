package site._60jong.advanced.kj.aop.log;

import lombok.Getter;

@Getter
public class TraceStatus {
    private Trace trace;
    private String message;
    private long startTimeMs;

    public TraceStatus(Trace trace, String message) {
        this.trace = trace;
        this.message = message;
        this.startTimeMs = System.currentTimeMillis();
    }
}
