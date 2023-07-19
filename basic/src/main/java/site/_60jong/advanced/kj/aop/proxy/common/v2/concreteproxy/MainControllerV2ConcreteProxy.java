package site._60jong.advanced.kj.aop.proxy.common.v2.concreteproxy;

import site._60jong.advanced.kj.aop.log.TraceStatus;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.proxy.common.v2.MainControllerV2;


public class MainControllerV2ConcreteProxy extends MainControllerV2 {

    private final MainControllerV2 target;
    private final LogTracer logTracer;

    public MainControllerV2ConcreteProxy(MainControllerV2 target, LogTracer logTracer) {
        super(null);
        this.target = target;
        this.logTracer = logTracer;
    }

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
