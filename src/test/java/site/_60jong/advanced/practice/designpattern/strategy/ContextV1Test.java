package site._60jong.advanced.practice.designpattern.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import site._60jong.advanced.practice.designpattern.strategy.code.ContextV1;
import site._60jong.advanced.practice.designpattern.strategy.code.StrategyLogic1;
import site._60jong.advanced.practice.designpattern.strategy.code.StrategyLogic2;

@Slf4j
public class ContextV1Test {
    ContextV1 context;

    @Test
    void strategy1() {
        context = new ContextV1(new StrategyLogic1());
        context.execute();
    }

    @Test
    void strategy2() {
        context = new ContextV1(new StrategyLogic2());
        context.execute();
    }

    @Test
    void strategy3() {
        context = new ContextV1(() -> log.info("logic3"));
        context.execute();
    }
}
