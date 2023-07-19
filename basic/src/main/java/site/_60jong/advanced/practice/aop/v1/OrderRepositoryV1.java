package site._60jong.advanced.practice.aop.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site._60jong.advanced.practice.trace.TraceStatus;
import site._60jong.advanced.practice.trace.hellotrace.HelloTraceV1;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryV1 {

    private final HelloTraceV1 trace;

    public void save(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.save()");
            // 저장 로직
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
