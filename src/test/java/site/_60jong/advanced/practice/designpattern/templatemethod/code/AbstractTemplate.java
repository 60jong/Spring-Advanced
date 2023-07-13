package site._60jong.advanced.practice.designpattern.templatemethod.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {
    public void execute() {
        long startTimeMs = System.currentTimeMillis();

        call(); // 상속으로 구현

        long endTimeMs = System.currentTimeMillis();
        long resultTimeMs = endTimeMs - startTimeMs;
        log.info("resultTime = {}", resultTimeMs);
    }

    protected abstract void call();
}
