package site._60jong.advanced.practice.designpattern.strategy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StrategyLogic1 implements Strategy {
    @Override
    public void call() {
        log.info("logic1");
    }
}
