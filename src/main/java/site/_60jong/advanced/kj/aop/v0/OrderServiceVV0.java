package site._60jong.advanced.kj.aop.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.log.tracer.ThreadLocalLogTracer;
import site._60jong.advanced.kj.aop.log.TraceStatus;

@RequiredArgsConstructor
@Service
public class OrderServiceVV0 {

    private final OrderRepositoryVV0 orderRepository;
    private final LogTracer tracer;

    public void orderItem(String itemId) {
        TraceStatus status = tracer.begin("OrderService.orderItem()");

        orderRepository.save(itemId);

        tracer.end(status);    }
}
