package site._60jong.advanced.kj.aop.general.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.log.tracer.strategy.Context;
import site._60jong.advanced.kj.aop.log.tracer.templatemethod.AbstractTemplate;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryVV2 {

    private final Context context;

    public void save(String itemId) {
        context.execute("OrderRepository.save()", () -> {
            // 저장 로직
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
