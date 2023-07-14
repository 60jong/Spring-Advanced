package site._60jong.advanced.practice.aop.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site._60jong.advanced.practice.trace.callback.TraceTemplate;
import site._60jong.advanced.practice.trace.logtrace.LogTrace;
import site._60jong.advanced.practice.trace.template.AbstractTemplate;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate template;

    public void save(String itemId) {
        template.execute("OrderRepository.save()", () -> {
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            return null;
        });
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
