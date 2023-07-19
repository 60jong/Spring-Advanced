package site._60jong.advanced.kj.aop.proxy.common.v1.interfaceproxy;

import lombok.RequiredArgsConstructor;
import site._60jong.advanced.kj.aop.log.TraceStatus;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.proxy.common.v1.MainRepositoryV1;
import site._60jong.advanced.kj.aop.proxy.common.v1.MainServiceV1;

@RequiredArgsConstructor
public class MainRepositoryV1InterfaceProxy implements MainRepositoryV1 {

    private final MainRepositoryV1 target;
    private final LogTracer logTracer;

    @Override
    public long save(String name) {
        TraceStatus status = null;
        try {
            status = logTracer.begin("MainRepository.save()");

            // logic
            long result = target.save(name);

            logTracer.end(status);
            return result;
        } catch (Exception e) {
            logTracer.exception(status, e);
            throw e;
        }
    }
}
