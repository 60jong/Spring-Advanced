package site._60jong.advanced.kj.aop.proxy.common.v1.interfaceproxy;

import lombok.RequiredArgsConstructor;
import site._60jong.advanced.kj.aop.log.TraceStatus;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.proxy.common.v1.MainControllerV1;
import site._60jong.advanced.kj.aop.proxy.common.v1.MainServiceV1;

@RequiredArgsConstructor
public class MainServiceV1InterfaceProxy implements MainServiceV1 {

    private final MainServiceV1 target;
    private final LogTracer logTracer;

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
