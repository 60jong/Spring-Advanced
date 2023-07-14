package site._60jong.advanced.kj.aop.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.log.tracer.strategy.Context;
import site._60jong.advanced.kj.aop.log.tracer.templatemethod.AbstractTemplate;

@RequiredArgsConstructor
@Service
public class OrderServiceVV2 {

    private final OrderRepositoryVV2 orderRepository;
    private final Context context;

    public void orderItem(String itemId) {
        context.execute("OrderService.orderItem()", () -> {
                    orderRepository.save(itemId);
                    return null;
                }
        );
    }
}
