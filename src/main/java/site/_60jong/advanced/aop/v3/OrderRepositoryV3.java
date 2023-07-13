package site._60jong.advanced.aop.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site._60jong.advanced.trace.TraceId;
import site._60jong.advanced.trace.TraceStatus;
import site._60jong.advanced.trace.logtrace.LogTrace;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryV3 {

    private final LogTrace trace;

    public void save(TraceId traceId, String itemId) {

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
