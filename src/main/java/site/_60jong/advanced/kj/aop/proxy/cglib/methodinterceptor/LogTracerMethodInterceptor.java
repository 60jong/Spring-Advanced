package site._60jong.advanced.kj.aop.proxy.cglib.methodinterceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.util.PatternMatchUtils;
import site._60jong.advanced.kj.aop.log.TraceStatus;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;

import java.lang.reflect.Method;

@RequiredArgsConstructor
public class LogTracerMethodInterceptor implements MethodInterceptor {

    private final Object target;
    private final LogTracer logTracer;
    private final String[] patterns;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (!PatternMatchUtils.simpleMatch(patterns, method.getName())) {
            return methodProxy.invoke(target, objects);
        }

        TraceStatus status = null;
        try {
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = logTracer.begin(message);

            // logic
            Object result = methodProxy.invoke(target, objects);

            logTracer.end(status);
            return result;
        } catch (Exception e) {
            logTracer.exception(status, e);
            throw e;
        }
    }
}
