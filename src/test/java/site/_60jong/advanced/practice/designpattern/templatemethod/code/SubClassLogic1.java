package site._60jong.advanced.practice.designpattern.templatemethod.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubClassLogic1 extends AbstractTemplate {
    @Override
    protected void call() {
        log.info("logic1");
    }
}
