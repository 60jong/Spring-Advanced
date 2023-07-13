package site._60jong.advanced.trace;

import java.util.UUID;

public class TraceId {
    private String id;
    private int level;

    // Constructor
    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    // Method
    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    public TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel() {
        return this.level == 0;
    }

    // Getter
    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
