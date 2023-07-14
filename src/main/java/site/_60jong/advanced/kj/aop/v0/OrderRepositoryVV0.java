package site._60jong.advanced.kj.aop.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site._60jong.advanced.kj.aop.log.LogTracer;
import site._60jong.advanced.kj.aop.log.Trace;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryVV0 {

    private final LogTracer tracer;

    public void save(String itemId) {
        Trace begin = tracer.begin("OrderRepository.save()");

        // 저장 로직
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }
        sleep(1000);

        tracer.end("OrderRepository.save()", begin);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
