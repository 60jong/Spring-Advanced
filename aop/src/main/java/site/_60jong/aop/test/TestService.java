package site._60jong.aop.test;

import org.springframework.stereotype.Service;
import site._60jong.aop.test.annotation.Performance;

@Service
public class TestService {

    private static int seq = 0;

    @Performance
    public int doTest(String something) {
        sleep(100);
        manipulateResponseTimeBySeq();
        return something.length();
    }

    private void manipulateResponseTimeBySeq(){
        seq++;

        if (seq % 5 == 0) {
            sleep(1000);
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new IllegalStateException("예외 발생");
        }
    }
}
