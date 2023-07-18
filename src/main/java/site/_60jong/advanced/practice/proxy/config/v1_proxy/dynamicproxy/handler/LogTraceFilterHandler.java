package site._60jong.advanced.practice.proxy.config.v1_proxy.dynamicproxy.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.util.PatternMatchUtils;
import site._60jong.advanced.practice.proxy.app.trace.TraceStatus;
import site._60jong.advanced.practice.proxy.app.trace.logtrace.LogTrace;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public class LogTraceFilterHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace trace;
    private final String[] patterns;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
            return method.invoke(target, args);
        }

        TraceStatus status = null;
        try {
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = trace.begin(message);

            // logic 호출
            Object result = method.invoke(target, args);

            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
