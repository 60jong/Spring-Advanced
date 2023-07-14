package site._60jong.advanced.practice.designpattern.strategy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy) {
        long startTimeMs = System.currentTimeMillis();

        strategy.call(); // 상속으로 구현

        long endTimeMs = System.currentTimeMillis();
        long resultTimeMs = endTimeMs - startTimeMs;
        log.info("resultTime = {}", resultTimeMs);
    }
}
