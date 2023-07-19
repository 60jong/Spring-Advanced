package site._60jong.advanced.practice.proxy.config.v1_proxy.interface_proxy;

import lombok.RequiredArgsConstructor;
import site._60jong.advanced.practice.proxy.app.trace.TraceStatus;
import site._60jong.advanced.practice.proxy.app.trace.logtrace.LogTrace;
import site._60jong.advanced.practice.proxy.app.v1.OrderControllerP1;

@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerP1 {

    private final OrderControllerP1 target;
    private final LogTrace trace;

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
