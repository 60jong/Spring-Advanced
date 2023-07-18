package site._60jong.advanced.kj.aop.proxy.common.v1.jdkdynamic.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.util.PatternMatchUtils;
import site._60jong.advanced.kj.aop.log.TraceStatus;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public class FilteredLogTracerHandler implements InvocationHandler {

    private final Object target;
    private final LogTracer logTracer;
    private final String[] patterns;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (!PatternMatchUtils.simpleMatch(patterns, method.getName())) {
            return method.invoke(target);
        }

        TraceStatus status = null;
        try {
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = logTracer.begin(message);

            // logic
            Object result = method.invoke(target, args);

            logTracer.end(status);
            return result;
        } catch (Exception e) {
            logTracer.exception(status, e);
            throw e;
        }
    }
}
