package site._60jong.advanced.kj.aop.general.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site._60jong.advanced.kj.aop.log.TraceStatus;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.log.tracer.templatemethod.AbstractTemplate;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryVV1 {

    private final LogTracer tracer;

    public void save(String itemId) {
        AbstractTemplate<Void> template = new AbstractTemplate<>(tracer) {
            @Override
            protected Void call() {
                // 저장 로직
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("예외 발생!");
                }
                sleep(1000);
                return null;
            }
        };

        template.execute("OrderRepository.save()");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
