package site._60jong.advanced.kj.aop.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site._60jong.advanced.kj.aop.log.LogTracer;
import site._60jong.advanced.kj.aop.log.Trace;

@RequiredArgsConstructor
@Service
public class OrderServiceVV0 {

    private final OrderRepositoryVV0 orderRepository;
    private final LogTracer tracer;

    public void orderItem(String itemId) {
        Trace begin = tracer.begin("OrderService.orderItem()");

        orderRepository.save(itemId);

        tracer.end("OrderService.orderItem()", begin);
    }
}
