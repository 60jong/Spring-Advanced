package site._60jong.advanced.kj.aop.proxy.common.v2.config.concreteproxy;

import lombok.RequiredArgsConstructor;
import site._60jong.advanced.kj.aop.log.TraceStatus;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.proxy.common.v2.MainRepositoryV2;

@RequiredArgsConstructor
public class MainRepositoryV2ConcreteProxy extends MainRepositoryV2 {

    private final MainRepositoryV2 target;
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
