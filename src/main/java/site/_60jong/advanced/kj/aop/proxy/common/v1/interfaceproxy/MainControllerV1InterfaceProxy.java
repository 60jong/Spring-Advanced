package site._60jong.advanced.kj.aop.proxy.common.v1.interfaceproxy;

import lombok.RequiredArgsConstructor;
import site._60jong.advanced.kj.aop.log.TraceStatus;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.proxy.common.v1.MainControllerV1;

@RequiredArgsConstructor
public class MainControllerV1InterfaceProxy implements MainControllerV1 {

    private final MainControllerV1 target;
    private final LogTracer logTracer;

    @Override
    public String request(String name) {
        TraceStatus status = null;
        try {
            status = logTracer.begin("MainController.request()");

            // logic
            String result = target.request(name);

            logTracer.end(status);
            return result;
        } catch (Exception e) {
            logTracer.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
        return target.noLog();
    }
}
