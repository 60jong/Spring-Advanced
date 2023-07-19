package site._60jong.advanced.kj.aop.log;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Trace {
    private String traceId;
    private int level;

    public Trace() {
        this.traceId = createTraceId();
        this.level = 0;
    }

    private String createTraceId() {
        return UUID.randomUUID()
                .toString()
                .substring(0, 8);
    }

    public Trace createNext() {
        this.level++;
        return this;
    }

    public Trace createPrevious() {
        this.level--;
        return this;
    }

    public boolean isFirstLevel() {
        return level == 0;
    }
}
