package site._60jong.advanced.practice.proxy.config.v1_proxy.concrete_proxy;

import site._60jong.advanced.practice.proxy.app.trace.TraceStatus;
import site._60jong.advanced.practice.proxy.app.trace.logtrace.LogTrace;
import site._60jong.advanced.practice.proxy.app.v2.OrderControllerP2;
import site._60jong.advanced.practice.proxy.app.v2.OrderServiceP2;

public class OrderControllerConcreteProxy extends OrderControllerP2 {

    private final OrderControllerP2 target;
    private final LogTrace trace;

    public OrderControllerConcreteProxy(OrderControllerP2 target, LogTrace trace) {
        super(null);
        this.target = target;
        this.trace = trace;
    }

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            String result = target.request(itemId);
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
        return target.noLog();
    }
}
