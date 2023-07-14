package site._60jong.advanced.practice.designpattern.strategy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV1 {

    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        long startTimeMs = System.currentTimeMillis();

        strategy.call(); // 상속으로 구현

        long endTimeMs = System.currentTimeMillis();
        long resultTimeMs = endTimeMs - startTimeMs;
        log.info("resultTime = {}", resultTimeMs);
    }
}
