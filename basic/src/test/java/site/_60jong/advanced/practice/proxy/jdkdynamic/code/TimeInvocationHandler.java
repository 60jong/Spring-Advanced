package site._60jong.advanced.practice.proxy.jdkdynamic.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
public class TimeInvocationHandler implements InvocationHandler {

    private final Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("proxy Class : {}", proxy.getClass());
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        Object result = method.invoke(target, args);

        long resultTime = System.currentTimeMillis() - startTime;
        log.info("TimeProxy 종료, resultTime = {}ms", resultTime);
        return result;
    }
}
