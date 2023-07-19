package site._60jong.advanced.kj.aop.general.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.log.tracer.templatemethod.AbstractTemplate;

@RequiredArgsConstructor
@Service
public class OrderServiceVV1 {

    private final OrderRepositoryVV1 orderRepository;
    private final LogTracer tracer;

    public void orderItem(String itemId) {
        AbstractTemplate<Void> template = new AbstractTemplate<>(tracer) {
            @Override
            protected Void call() {
                orderRepository.save(itemId);
                return null;
            }
        };

        template.execute("OrderService.orderItem()");
    }
}
