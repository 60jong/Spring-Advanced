package site._60jong.advanced.practice.proxy.config.v1proxy.interface_proxy;

import lombok.RequiredArgsConstructor;
import site._60jong.advanced.practice.proxy.app.trace.TraceStatus;
import site._60jong.advanced.practice.proxy.app.trace.logtrace.LogTrace;
import site._60jong.advanced.practice.proxy.app.v1.OrderServiceP1;

@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements OrderServiceP1 {

    private final OrderServiceP1 target;
    private final LogTrace trace;

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
