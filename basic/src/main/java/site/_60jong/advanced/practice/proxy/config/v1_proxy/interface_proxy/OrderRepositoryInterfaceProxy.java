package site._60jong.advanced.practice.proxy.config.v1_proxy.interface_proxy;

import lombok.RequiredArgsConstructor;
import site._60jong.advanced.practice.proxy.app.trace.TraceStatus;
import site._60jong.advanced.practice.proxy.app.trace.logtrace.LogTrace;
import site._60jong.advanced.practice.proxy.app.v1.OrderRepositoryP1;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryP1 {

    private final OrderRepositoryP1 target;
    private final LogTrace trace;

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.save()");

            target.save(itemId);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
