package site._60jong.aop.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import site._60jong.aop.test.aop.aspect.PerformanceAspect;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Import(PerformanceAspect.class)
class TestServiceTest {

    @Autowired
    TestService service;

    @Test
    void longDelayPer5() {
        for (int i = 0; i < 5; i++) {
//            long startTime = System.currentTimeMillis();
//            service.doTest("something");
//            long resultTime = System.currentTimeMillis() - startTime;
//            log.info("resultTime = {}ms", resultTime);
            service.doTest("something");
        }

    }
}