package site._60jong.advanced.practice.designpattern.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import site._60jong.advanced.practice.designpattern.strategy.code.ContextV1;
import site._60jong.advanced.practice.designpattern.strategy.code.ContextV2;
import site._60jong.advanced.practice.designpattern.strategy.code.StrategyLogic1;
import site._60jong.advanced.practice.designpattern.strategy.code.StrategyLogic2;

@Slf4j
public class ContextV2Test {

    @Test
    void strategy() {
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
        context.execute(() -> log.info("logic3"));
    }

}
