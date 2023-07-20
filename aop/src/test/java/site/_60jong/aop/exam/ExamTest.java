package site._60jong.aop.exam;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import site._60jong.aop.exam.aop.RetryAspect;
import site._60jong.aop.exam.aop.TraceAspect;

@Slf4j
@Import({TraceAspect.class, RetryAspect.class})
@SpringBootTest
public class ExamTest {

    @Autowired
    ExamService examService;

    @Test
    void exceptionTest() {
        for (int i = 0; i < 5; i++) {
            String id = examService.request("id");
            log.info(id);
        }
    }
}
