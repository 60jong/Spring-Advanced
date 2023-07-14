package site._60jong.advanced.practice.aop.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site._60jong.advanced.practice.trace.callback.TraceTemplate;
import site._60jong.advanced.practice.trace.logtrace.LogTrace;
import site._60jong.advanced.practice.trace.template.AbstractTemplate;

@RequiredArgsConstructor
@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate template;

    public void orderItem(String itemId) {
        template.execute("OrderService.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
