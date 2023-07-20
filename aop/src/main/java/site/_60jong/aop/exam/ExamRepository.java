package site._60jong.aop.exam;

import org.springframework.stereotype.Repository;
import site._60jong.aop.exam.annotation.Trace;

@Repository
public class ExamRepository {

    private static int seq = 0;

    @Trace
    public String save(String itemId) {
        checkAbleToResponse();
        return "ok";
    }

    private static void checkAbleToResponse() {
        seq++;
        if (seq % 5 == 0) {
            throw new IllegalStateException("예외 발생");
        }
    }
}
