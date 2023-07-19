package site._60jong.advanced.practice.designpattern.templatemethod;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import site._60jong.advanced.practice.designpattern.templatemethod.code.AbstractTemplate;
import site._60jong.advanced.practice.designpattern.templatemethod.code.SubClassLogic1;
import site._60jong.advanced.practice.designpattern.templatemethod.code.SubClassLogic2;

@Slf4j
public class TemplateMethodTest {
    @Test
    void logic1() {
        long startTimeMs = System.currentTimeMillis();

        log.info("logic1 수행");

        long endTimeMs = System.currentTimeMillis();
        long resultTimeMs = endTimeMs - startTimeMs;
        log.info("resultTime = {}", resultTimeMs);
    }

    @Test
    void logic2() {
        long startTimeMs = System.currentTimeMillis();

        log.info("logic2 수행");

        long endTimeMs = System.currentTimeMillis();
        long resultTimeMs = endTimeMs - startTimeMs;
        log.info("resultTime = {}", resultTimeMs);
    }

    @Test
    void template_method_logic1() {
        AbstractTemplate logic1 = new SubClassLogic1();
        logic1.execute();

        AbstractTemplate logic2 = new SubClassLogic2();
        logic2.execute();

        AbstractTemplate logic3 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("logic3");
            }
        };
        logic3.execute();
    }
}
