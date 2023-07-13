package site._60jong.advanced.trace.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import site._60jong.advanced.trace.threadlocal.code.FieldService;

@Slf4j
class FieldServiceTest {

    FieldService service = new FieldService();

    @Test
    void field() {
        log.info("main start");

        Runnable userA = () -> {
            service.logic("userA");
        };

        Runnable userB = () -> {
            service.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(500);
        threadB.start();

        sleep(3000);
        log.info("main exit");
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
