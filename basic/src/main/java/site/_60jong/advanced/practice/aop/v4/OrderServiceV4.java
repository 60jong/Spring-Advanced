package site._60jong.advanced.practice.aop.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site._60jong.advanced.practice.trace.TraceStatus;
import site._60jong.advanced.practice.trace.logtrace.LogTrace;
import site._60jong.advanced.practice.trace.template.AbstractTemplate;

@RequiredArgsConstructor
@Service
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId) {

        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                orderRepository.save(itemId);
                return null;
            }
        };

        template.execute("OrderService.orderItem()");
    }
}
