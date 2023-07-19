package site._60jong.advanced.practice.proxy.config.v1_proxy.concrete_proxy;

import site._60jong.advanced.practice.proxy.app.trace.TraceStatus;
import site._60jong.advanced.practice.proxy.app.trace.logtrace.LogTrace;
import site._60jong.advanced.practice.proxy.app.v2.OrderRepositoryP2;
import site._60jong.advanced.practice.proxy.app.v2.OrderServiceP2;

public class OrderServiceConcreteProxy extends OrderServiceP2 {

    private final OrderServiceP2 target;
    private final LogTrace trace;

    public OrderServiceConcreteProxy(OrderServiceP2 target, LogTrace trace) {
        super(null);
        this.target = target;
        this.trace = trace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");

            target.orderItem(itemId);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
