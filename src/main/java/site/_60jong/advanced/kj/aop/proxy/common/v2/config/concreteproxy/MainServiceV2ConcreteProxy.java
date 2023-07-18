package site._60jong.advanced.kj.aop.proxy.common.v2.config.concreteproxy;

import site._60jong.advanced.kj.aop.log.TraceStatus;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.proxy.common.v2.MainServiceV2;

public class MainServiceV2ConcreteProxy extends MainServiceV2 {

    private final MainServiceV2 target;
    private final LogTracer logTracer;

    public MainServiceV2ConcreteProxy(MainServiceV2 target, LogTracer logTracer) {
        super(null);
        this.target = target;
        this.logTracer = logTracer;
    }

    @Override
    public long execute(String name) {
        TraceStatus status = null;
        try {
            status = logTracer.begin("MainService.execute()");

            // logic
            long result = target.execute(name);

            logTracer.end(status);
            return result;
        } catch (Exception e) {
            logTracer.exception(status, e);
            throw e;
        }
    }
}
